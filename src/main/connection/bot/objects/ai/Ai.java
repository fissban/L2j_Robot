package main.connection.bot.objects.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.IntStream;

import gui.frames.game.GameFrame;
import gui.frames.game.windows.ConsoleWindow;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BObject;
import main.connection.bot.objects.BPlayer;
import main.connection.bot.objects.BSummon;
import main.data.SkillData;
import main.enums.EIntention;
import main.enums.EMode;
import main.enums.ERequestRestart;
import main.managers.ObjectsManager;
import main.managers.ThreadManager;
import main.network.game.client.ingame.Action;
import main.network.game.client.ingame.Appearing;
import main.network.game.client.ingame.AttackRequest;
import main.network.game.client.ingame.MoveBackwardToLocation;
import main.network.game.client.ingame.RequestActionUse;
import main.network.game.client.ingame.RequestActionUse.RequestActionUseType;
import main.network.game.client.ingame.RequestAutoSoulShot;
import main.network.game.client.ingame.RequestMagicSkillUse;
import main.network.game.client.ingame.RequestRestartPoint;
import main.network.game.client.ingame.Say2;
import main.network.game.client.ingame.SendBypassBuildCmd;
import main.network.game.client.ingame.UseItem;
import main.network.game.client.ingame.ValidatePosition;
import main.util.Util;

/**
 * @author fissban
 */
public abstract class Ai implements Runnable
{
	private static final List<Integer> SOULSHOTS = Arrays.asList(5789, 1835, 1463, 1464, 1465, 1466, 1467);
	private static final List<Integer> SPIRITSHOT = Arrays.asList(5790, 2509, 2510, 2511, 2512, 2513, 2514);
	private static final List<Integer> BLESSED_SPIRITSHOT = Arrays.asList(3947, 3948, 3949, 3950, 3951, 3952);

	/** Config Radio attack*/
	public int RADIO_ATTACK = 3000;

	/** List of ids of templates of mobs that the bot will ignore. */
	private List<Integer> listIgnoreIdAttack = new ArrayList<>();
	/** List of titles of templates of mobs that the bot will ignore. */
	private List<String> listIgnoreTitleAttack = new ArrayList<>();
	{
		for (String title : "Champion,RaidBoss,GrandBoss,fissban".split(","))
		{
			listIgnoreTitleAttack.add(title);
		}
	}

	// AUTO POTS -------------------------------------------------------------------------

	private static final int[] POTS_HP_ID =
	{
			725, //Healing Drug
			727, //Healing Potion
			1060, //Lesser Healing Potion
			1061, //Healing Potion
			1539, //Greater Healing Potion
			1540, //Quick Healing Potion
	};

	private static final int[] POTS_MP_ID =
	{
			726, //Mana Drug
			728, //Mana Potion
	};

	private static final int[] POTS_CP_ID =
	{
			5591, //CP Potion
			5592, //Greater CP Potion
	};

	private static final int TICKS_CHECK_HP_MP = 10; // see TASK_DELAY

	public boolean AUTO_POTS = true;
	public int HP_USE_POT = 60;
	public int MP_USE_POT = 60;
	public int CP_USE_POT = 90;
	public boolean SIT_DOWN_IS_HP_VERY_SLOW = true;
	public boolean SIT_DOWN_IS_MP_VERY_SLOW = true;

	private static int checkPots = TICKS_CHECK_HP_MP;
	// AUTO LOOT -------------------------------------------------------------------------
	public boolean LOOT_ALL_ITEMS = true;
	public boolean LOOT_ENCHANTS = true;
	public boolean LOOT_ADENA = true;
	public boolean LOOT_HERBS = true;

	private static final List<Integer> ENCHANTS_ID = Arrays.asList(729, 730, 731, 732, 947, 948, 949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 6569, 6570, 6571, 6572, 6573, 6574, 6575, 6576, 6577, 6578);
	private static final List<Integer> HERBS_ID = Arrays.asList(8154, 8155, 8156, 8157, 8601, 8602, 8603, 8604, 8605, 8606, 8607, 8608, 8609, 8610, 8611, 8612, 8613, 8614, 8952, 8953);

	private static final byte ADENA_ID = 57;

	/** Percentage of life with which the character must sit to recover hp or mp. */
	private final byte CHECK_HPMP_NEED_SIT = 10;
	// ----------------------------------------------
	private static final int TASK_DELAY = 500;
	/** Task on level up */
	public ScheduledFuture<?> taskLevelUp;
	/** Task on return farm zone*/
	public ScheduledFuture<?> taskReturnFarZone;
	/** Coordinates of the place where the Ai was activated */
	public int x, y;

	private List<Integer> skillsNukers = new ArrayList<>();
	private List<Integer> skillsDebuff = new ArrayList<>();
	private List<Integer> skillsBuffs = new ArrayList<>();
	private List<Integer> cubics = new ArrayList<>();

	/** Intention in progress  */
	private EIntention intention = EIntention.NONE;
	/** Mode */
	private EMode mode = EMode.NONE;

	private static int targetObjId = -1;

	public Ai()
	{
		//
	}

	/**
	 * Start task Ai
	 */
	public void startLevelUp()
	{
		// Active all possible auto shots
		GameFrame.getInstance().getActions().addActionBot("Active auto shots");
		activeAutoShot(SOULSHOTS);
		activeAutoShot(SPIRITSHOT);
		activeAutoShot(BLESSED_SPIRITSHOT);

		// attack zone
		x = getBot().getX();
		y = getBot().getY();

		// Remove target
		removeTarget();
		// Init task
		taskLevelUp = ThreadManager.scheduleAtFixedRate(this, 0, TASK_DELAY);
	}

	/**
	 * Stop task Ai
	 */
	public void stopLevelUp()
	{
		if (taskLevelUp != null)
		{
			taskLevelUp.cancel(false);
			taskLevelUp = null;
		}

		// Remove target
		removeTarget();
		// Change intention
		setIntention(EIntention.NONE);
	}

	public boolean isRunning()
	{
		return taskLevelUp != null;
	}

	@Override
	public void run()
	{
		// Use potions if the hp, mp or cp is low.
		autoPots();

		pickupItem();
		// If intention is pickup item, waiting!
		if (hasIntention(EIntention.PICKUP_ITEM))
		{
			return;
		}

		// if bot cast skill wait!
		if (hasIntention(EIntention.CAST))
		{
			return;
		}

		// sit down if hp/mp is very low.
		// sit up if hp/mp is full
		checkNeedSitDown();
		// If intention is resting item, waiting!
		if (hasIntention(EIntention.RESTING))
		{
			return;
		}

		// use all buffs predefined
		checkBuffs();

		// se verifica si tenemos algun pk o modo pvp
		if (!isInMode(EMode.PVP))
		{
			for (BPlayer player : ObjectsManager.getAll(BPlayer.class))
			{
				if (player.isPvpFlag() || player.getKarma() > 0)
				{
					if (!getTarget().equals(player))
					{
						selectTarget(player);
						mode = EMode.PVP;
					}
				}
			}
		}

		try
		{
			switch (mode)
			{
				case NONE:
				case PVE:
					doActionPVE();
					break;
				case PVP:
					doActionPVP();
					break;
			}
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error Ai doAction()", e);
		}
	}

	public abstract void doActionPVE();

	public abstract void doActionPVP();

	private void checkCubics()
	{
		// Don't use buffs if bot attacking or cast other skills.
		if (hasIntention(EIntention.ATTACKING))
		{
			return;
		}

		searchCubicsSkill().forEach(c ->
		{
			boolean hasCubic = false;
			for (int cubic : getBot().getCubicIds())
			{
				if (cubic == c)
				{
					hasCubic = true;
					break;
				}
			}
		});

		// check all buffs -> filter:
		// - player has skill
		// - player don't have effect
		searchCubicsSkill().stream().filter(buff -> getBot().getSkills().get(buff) != null && getBot().getEffects().get(buff) == null).forEach(buff ->
		{
			useMagic(getBot(), buff);
			// FIXME Wait 2 seconds to give time to the buff to be released, it seems that this time is more than enough.
			Util.sleep(2000);
		});
	}

	/**
	 * It is checked if the list of buffs already defined are present in the effects and are used otherwise.<br>
	 * Wait 2 seconds to give time to the buff to be released, it seems that this time is more than enough.<br>
	 * If the bot is attacking or using other skills, it will wait until the actions are completed.
	 */
	private void checkBuffs()
	{
		// dont use buffs if bot attacking or cast other skills.
		if (hasIntention(EIntention.ATTACKING))
		{
			return;
		}

		// check all buffs -> filter:
		// - player has skill
		// - player don't have effect
		searchBuffSkill().stream().filter(buff -> getBot().getSkills().get(buff) != null && getBot().getEffects().get(buff) == null).forEach(buff ->
		{
			useMagic(getBot(), buff);
			// FIXME Wait 2 seconds to give time to the buff to be released, it seems that this time is more than enough.
			Util.sleep(2000);
		});

		setIntention(EIntention.NONE);
		removeTarget();
	}

	/**
	 * Search and pick up a drop. -> setIntention:PICKUP_ITEM
	 */
	private void pickupItem()
	{
		// Search and pick up a drop
		if (hasIntention(EIntention.NONE))
		{
			BItem drop = searchDrop();
			if (drop != null)
			{
				// Change Intention
				setIntention(EIntention.PICKUP_ITEM);
				// Pickup item
				pickupItem(drop);
			}
		}
	}

	/**
	 * - Check if the hp or mp is very low and if yes the bot will sit down. -> setIntention:RESTING<br>
	 * - Check if the hp and mp are complete and if the bot is sitting up. -> setIntention:NONE
	 */
	private void checkNeedSitDown()
	{
		if (!SIT_DOWN_IS_HP_VERY_SLOW && !SIT_DOWN_IS_MP_VERY_SLOW)
		{
			return;
		}

		int hp = getPorcentageHpCurrent();
		int mp = getPorcentageMpCurrent();
		// If the character is resting leave it like this until this hp and mp full
		if (hasIntention(EIntention.RESTING) && (hp >= 95 && mp >= 95))
		{
			// Change intention
			setIntention(EIntention.NONE);
			// Bot stand up 
			sitOrStand();
			return;
		}

		if (!hasIntention(EIntention.RESTING))
		{
			// Check MP is very very slow
			if ((mp <= CHECK_HPMP_NEED_SIT && SIT_DOWN_IS_MP_VERY_SLOW))
			{
				// Change intention
				setIntention(EIntention.RESTING);
				// Remove target
				removeTarget();
				// Bot sit down
				sitOrStand();
				return;
			}
			// Check HP is very very slow
			if ((hp <= CHECK_HPMP_NEED_SIT && SIT_DOWN_IS_HP_VERY_SLOW))
			{
				// Change intention
				setIntention(EIntention.RESTING);
				// Remove target
				removeTarget();
				// Bot sit down
				sitOrStand();
			}
		}
	}

	/**
	 * Check if need use hp or mp pots.
	 */
	private void autoPots()
	{
		checkPots--;

		if (checkPots != 0)
		{
			return;
		}

		int hp = getPorcentageHpCurrent();
		int mp = getPorcentageMpCurrent();
		int cp = getPorcentageCpCurrent();

		if (hp >= HP_USE_POT)
		{
			usePot(POTS_HP_ID);
		}

		if (mp >= MP_USE_POT)
		{
			usePot(POTS_MP_ID);
		}

		if (cp >= CP_USE_POT)
		{
			usePot(POTS_CP_ID);
		}

		checkPots = TICKS_CHECK_HP_MP;
	}

	/**
	 * A array of defined potions is searched in the inventory.
	 * @param array
	 */
	private void usePot(int[] array)
	{
		getBot().getInventory().getItems().forEach(it ->
		{
			if (IntStream.of(array).anyMatch(id -> it.getId() == id))
			{
				// Send packet client <-> server
				getBot().sendPacket(new UseItem(it));
				return;
			}
		});
	}

	/**
	 * Searches for the drops around the bot and select one, only if {@link #LOOT_ALL_ITEMS} is activated
	 * @return
	 */
	private BItem searchDrop()
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Search Drops");

		for (BItem it : ObjectsManager.getAll(BItem.class))
		{
			// Only look for items without an owner or that the owner is us.
			if (it.getOwnerObjectId() == 0 || it.getOwnerObjectId() == getBot().getObjectId())
			{
				if (LOOT_ALL_ITEMS)
				{
					return it;
				}

				if (LOOT_ENCHANTS && ENCHANTS_ID.contains(it.getId()))
				{
					return it;
				}

				if (LOOT_HERBS && HERBS_ID.contains(it.getId()))
				{
					return it;
				}

				if (LOOT_ADENA && ADENA_ID == it.getId())
				{
					return it;
				}
			}
		}

		return null;
	}

	/**
	 * Search in {@link #RADIO_ATTACK} all possible targets to attack and you get the closest one.
	 * @return
	 */
	public BNpc searchNextTarget()
	{
		GameFrame.getInstance().getActions().addActionBot("Search next target to kill");
		// Map sorted by key
		Map<Double, BNpc> listAttack = new TreeMap<>();

		for (BNpc npc : ObjectsManager.getAll(BNpc.class))
		{
			// check if npc its monster
			if (!npc.isAttackable())
			{
				continue;
			}
			//
			if (listIgnoreIdAttack.contains(npc.getIdTemplate()))
			{
				continue;
			}

			// The distance to the target is calculated
			double dist = Util.calculateDistance(x, y, npc, true);

			if (dist < RADIO_ATTACK)
			{
				listAttack.put(dist, npc);
			}
		}

		return listAttack.values().stream().findFirst().orElse(null);
	}

	/**
	 * The percentage of HP that the bot currently has.
	 * @return
	 */
	public int getPorcentageHpCurrent()
	{
		return getBot().getCurrentHp() * 100 / getBot().getMaxHp();
	}

	/**
	 * The percentage of MP that the bot currently has.
	 * @return
	 */
	public int getPorcentageMpCurrent()
	{
		return getBot().getCurrentMp() * 100 / getBot().getMaxMp();
	}

	/**
	 * The percentage of CP that the bot currently has.
	 * @return
	 */
	public int getPorcentageCpCurrent()
	{
		return getBot().getCurrentCp() * 100 / getBot().getMaxCp();
	}

	/**
	 * Active auto shots
	 * Send packets: {@link RequestAutoSoulShot}
	 * @param shot
	 */
	public void activeAutoShot(List<Integer> shot)
	{
		shot.forEach(s -> getBot().getInventory().getItems().stream().filter(it -> it.getId() == s).forEach(it -> getBot().sendPacket(new RequestAutoSoulShot(s, true))));
	}

	/**
	 * AttackTarget<br>
	 * Send packets: {@link Action} -> {@link AttackRequest}<br>
	 * Change Intention {@link EIntention#ATTACKING}<br>
	 * @param objectId
	 */
	public void attackMele(BObject npc)
	{
		// Change intention
		setIntention(EIntention.ATTACKING);
		// Select Target if required
		selectTarget(npc);
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Attack mele: " + npc.getName());
		// Send packet client <-> server
		getBot().sendPacket(new AttackRequest(npc));

	}

	/**
	 * AttackTarget<br>
	 * Send packets: {@link Action} -> {@link RequestMagicSkillUse}<br>
	 * Change Intention {@link main.enums.EIntention#ATTACKING}<br>
	 * @param objectId
	 */
	public void attackMagic(BObject npc, int skillId)
	{
		// Change Intention
		setIntention(EIntention.CAST);
		// Select Target if required
		selectTarget(npc);
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Attack magic: " + SkillData.getName(skillId));
		// Send packet client <-> server
		getBot().sendPacket(new RequestMagicSkillUse(skillId, false, false));
	}

	/**
	 * Use Magic<br>
	 * It will be used only for the launch of non-offensive skills, otherwise use <b><font color=FF0000>attackMagic.</font></b><br>
	 * Send packets: {@link Action} -> {@link RequestMagicSkillUse}<br>
	 * Change Intention {@link main.enums.EIntention#ATTACKING}<br>
	 * @param objectId
	 */
	public void useMagic(BObject npc, int skillId)
	{
		// Change Intention
		setIntention(EIntention.CAST);
		// Select Target if required
		selectTarget(npc);
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Use magic: " + SkillData.getName(skillId));
		// Send packet client <-> server
		getBot().sendPacket(new RequestMagicSkillUse(skillId, false, false));
	}

	/**
	 * AttackTarget<br>
	 * Send packets: {@link Action} -> {@link AttackRequest}
	 * @param objectId
	 */
	public void talk(BObject npc)
	{
		// Select Target if required
		selectTarget(npc);
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Talk on: " + npc.getName());
		// Send packet client <-> server
		getBot().sendPacket(new Action(npc, false));
	}

	/**
	 * Interact Target<br>
	 * Send packets: {@link Action} -> {@link Action}
	 * @param BObject o
	 */
	public void interact(BObject obj)
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Interact on: " + obj.getName());
		// Select Target if required
		selectTarget(obj);
		// Send packet client <-> server
		getBot().sendPacket(new Action(obj, false));
	}

	/**
	 * Pickup item<br>
	 * Send packet: {@link Action}
	 * @param BObject o
	 */
	public void pickupItem(BObject obj)
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Pickup item: " + obj.getName());
		// Select target
		setTarget(obj);
		// Send packet client <-> server
		getBot().sendPacket(new Action(obj, false));
	}

	/**
	 * Move to position<br>
	 * Send packets: {@link Action} -> {@link MoveBackwardToLocation}
	 * @param x
	 * @param y
	 */
	public void moveTo(int x, int y)
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Move to x:" + x + " y:" + y);
		// Send packet client <-> server
		getBot().sendPacket(new MoveBackwardToLocation(getBot().getX(), getBot().getY(), getBot().getZ(), x, y, getBot().getZ()));

		getBot().sendPacket(new ValidatePosition());
		getBot().sendPacket(new ValidatePosition());
	}

	/**
	 * Send packets: {@link Say2}
	 * @param text
	 */
	public void say(String text)
	{
		if (text.startsWith("//"))
		{
			// Add action in interface
			GameFrame.getInstance().getActions().addActionBot("Admin command: " + text);
			// Send packet client <-> server
			getBot().sendPacket(new SendBypassBuildCmd(text));
		}
		else if (text.startsWith("/"))
		{
			// Add action in interface
			GameFrame.getInstance().getActions().addActionBot("User command: " + text);
		}
		else
		{
			// Add action in interface
			GameFrame.getInstance().getActions().addActionBot("Say: " + text);
			// Send packet client <-> server
			getBot().sendPacket(new Say2(text));
		}
	}

	/**
	 * This method serves to invoke the action of sitting or stopping according to the current state of the bot.
	 * Send packets: {@link RequestActionUse}
	 */
	private void sitOrStand()
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot(intention == EIntention.RESTING ? "Bot Sit Down" : "Bot Stand Up");
		// Send packet client <-> server
		getBot().sendPacket(new RequestActionUse(RequestActionUseType.SIT_OR_STAND));
	}

	/**
	 * Check if the Bot already has a target or if you need to select it
	 * @param obj
	 */
	private void selectTarget(BObject obj)
	{
		if (obj == null || obj.isDead())
		{
			return;
		}

		if (!hasTarget() || !getTarget().equals(obj))
		{
			// Add action in interface
			GameFrame.getInstance().getActions().addActionBot("Select target: " + obj.getName());
			// Select target
			setTarget(obj);
			// Send packet client <-> server
			getBot().sendPacket(new Action(obj, false));
		}
	}

	/**
	 * Get Bot
	 * @return
	 */
	protected BPlayer getBot()
	{
		return ObjectsManager.getBot();
	}

	protected BSummon getSummon()
	{
		return ObjectsManager.getSummon();
	}

	// XXX INTENTION ---------------------------------------------------------------------------------
	public synchronized EIntention getIntention()
	{
		return intention;
	}

	public synchronized void setIntention(EIntention intention)
	{
		if (hasIntention(intention))
		{
			return;
		}
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Change intention: " + intention.toString());

		this.intention = intention;
	}

	public synchronized boolean hasIntention(EIntention intention)
	{
		return this.intention == intention;
	}

	/**
	 * Define the "nukers" skills to be used.
	 * @param skills
	 */
	public void setNukerSkill(int... skills)
	{
		for (int s : skills)
		{
			skillsNukers.add(0, s);
		}
	}

	public void removeNukkerSkill(Integer id)
	{
		skillsNukers.remove(id);
	}

	/**
	 * It looks for the first <b>damage</b> skill that its reuse is less than 15 milliseconds,<br>
	 * in case of not finding a match it will return -1.
	 * @return
	 */
	public int searchNukerSkill()
	{
		return skillsNukers.stream().filter(s -> getBot().getSkills().get(s) != null && getBot().getSkills().get(s).getReuseTime() < 15).findFirst().orElse(-1);
	}

	public List<Integer> getAllNukerSkills()
	{
		return skillsNukers;
	}

	/**
	 * Define the "debuff" skills to be used.
	 * @param skills
	 */
	public void setDebuffsSkill(int... skills)
	{
		for (int s : skills)
		{
			skillsDebuff.add(0, s);
		}
	}

	public void removeDebuffsSkill(Integer id)
	{
		skillsDebuff.remove(id);
	}

	public List<Integer> getAllDebuffsSkills()
	{
		return skillsDebuff;
	}

	/**
	 * It looks for the first <b>debuff</b> skill that its reuse is less than 15 milliseconds,<br>
	 * in case of not finding a match it will return -1.
	 * @return
	 */
	public int searchDebuffSkill()
	{
		return skillsDebuff.stream().filter(s -> getBot().getSkills().get(s) != null && getBot().getSkills().get(s).getReuseTime() < 15).findFirst().orElse(-1);
	}

	/**
	 * Define the "buff" skills to be used.
	 * @param skills
	 */
	public void setBuffsSkill(int... skills)
	{
		for (int s : skills)
		{
			skillsBuffs.add(0, s);
		}
	}

	public void removeBuffsSkill(Integer id)
	{
		skillsBuffs.remove(id);
	}

	public List<Integer> getAllBuffsSkills()
	{
		return skillsBuffs;
	}

	/**
	 * It looks all buffs
	 * @return
	 */
	public List<Integer> searchBuffSkill()
	{
		return skillsBuffs;
	}

	public void setCubics(int... cubic)
	{
		for (int c : cubic)
		{
			cubics.add(c);
		}
	}

	public List<Integer> searchCubicsSkill()
	{
		return cubics;
	}

	/**
	 * Return actual target, if you do not have a target it will return null as a value.
	 * @return
	 */
	public BObject getTarget()
	{
		return (targetObjId == -1) ? null : ObjectsManager.get(targetObjId);
	}

	/**
	 * Check if bot has target, its more efficient with "getTarget() != null"
	 * @return
	 */
	public boolean hasTarget()
	{
		return (targetObjId == -1) ? false : (ObjectsManager.get(targetObjId) != null && !ObjectsManager.get(targetObjId).isDead());
	}

	public int getTargetId()
	{
		return targetObjId;
	}

	/**
	 * Set actual target
	 * @param target
	 */
	public void setTarget(BObject target)
	{
		targetObjId = target.getObjectId();
	}

	/**
	 * Remove Ai target
	 */
	public void removeTarget()
	{
		targetObjId = -1;
	}

	/**
	 * Add the template id of a mob that the bot ignored in its Ai.
	 * @param id
	 */
	public void addIgnoreIdAttack(int id)
	{
		listIgnoreIdAttack.add(id);
	}

	/**
	 * Remove the template id of a mob that the bot ignored in its Ai.
	 * @param id
	 */
	public void removeIgnoreIdAttack(int id)
	{
		listIgnoreIdAttack.add(id);
	}

	/**
	 * All the titles of the mobs that the bot will ignore are defined.<br>
	 * These must be separated with ",".
	 * @param titles
	 */
	public void setIgnoreTitleAttack(String titles)
	{
		for (String t : titles.split(","))
		{
			listIgnoreTitleAttack.add(t);
		}
	}

	/**
	 * You get all the titles that the bot ignored the mobs.<br>
	 * These are separated with ",".
	 * @return
	 */
	public String getIgnoreTitleAttack()
	{
		String titles = "";
		for (String s : listIgnoreTitleAttack)
		{
			titles += s + ",";
		}

		return titles;
	}

	// XXX NOTIFY ------------------------------------------------------------------------------

	/**
	 * Actions upon dying
	 */
	public void notifyDie()
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Die player");
		// Teleport to city and revive
		getBot().sendPacket(new RequestRestartPoint(ERequestRestart.TOWN));

		// FIXME when dying this package comes out twice
		getBot().sendPacket(new ValidatePosition());
		getBot().sendPacket(new ValidatePosition());

		getBot().sendPacket(new Appearing());
		// Change intention
		setIntention(EIntention.NONE);
	}

	/**
	 * Actions to revive
	 */
	public void notifyRevive()
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Revive player");

		if (isInMode(EMode.PVP))
		{
			setMode(EMode.PVE);
		}
		// Stop task on leveling
		stopLevelUp();

		// TODO faltaria actualizar el boton de la interface para iniciar el "Ai"
	}

	public void notifyDieTarget()
	{
		try
		{
			onDieTarget();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Remove target
		removeTarget();
		// Change intention
		setIntention(EIntention.NONE);

		if (isInMode(EMode.PVP))
		{
			setMode(EMode.PVE);
		}
	}

	/**
	 * @return the mode
	 */
	public EMode getMode()
	{
		return mode;
	}

	public boolean isInMode(EMode mode)
	{
		return this.mode == mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(EMode mode)
	{
		// Add action in interface
		GameFrame.getInstance().getActions().addActionBot("Change mode: " + mode.toString());

		this.mode = mode;
	}

	// XXX LISTENER ------------------------------------------------------------------------------

	public void onDieTarget()
	{

	}
}
