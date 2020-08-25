package main.connection.bot.objects;

import main.connection.bot.objects.ai.Ai;
import main.connection.bot.objects.managers.Effects;
import main.connection.bot.objects.managers.Inventory;
import main.connection.bot.objects.managers.Skills;
import main.data.ExperienceData;
import main.enums.EClassId;
import main.enums.ERace;
import main.enums.ESex;
import main.managers.ConnectionManager;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class BPlayer extends BCreature
{
	private ERace race;
	private ESex sex;
	private EClassId classId;

	private int sp;
	private long exp;

	private int STR;
	private int DEX;
	private int CON;
	private int INT;
	private int WIT;
	private int MEN;

	private int maxCp;
	private int currentCp;

	private int currentLoad;
	private int maxLoad;

	private int pkKills;
	private int pvpKills;

	private int karma;
	private boolean isPvpFlag;

	private double movementSpeedMultiplier;
	private double attackSpeedMultiplier;

	private int hairStyle;
	private int hairColor;
	private int face;
	private int accessLevel;

	private int clanId;
	private int clanCrestId;
	private int allyId;
	private int allyCrestId;
	private boolean isClanLeader;

	private int mountType;
	private int privateStoreType;
	private boolean hasDwarvenCraft;

	private int numberOfCubics;
	private int[] cubicIds;

	private int clanPrivileges;
	private int swim;

	private boolean isSiting;

	private int charges;

	private Inventory inverntoryManager;
	private Skills skillsManager;
	private Effects effectsManager;
	private Ai ai;

	// party
	private boolean isInParty = false;
	private boolean isLeader = false;

	public BPlayer(int objectId)
	{
		super(objectId);

		inverntoryManager = new Inventory();
		skillsManager = new Skills();
		effectsManager = new Effects();
	}

	public ERace getRace()
	{
		return race;
	}

	public void setRace(ERace value)
	{
		race = value;
	}

	public ESex getSex()
	{
		return sex;
	}

	public void setSex(ESex value)
	{
		sex = value;
	}

	public EClassId getClassId()
	{
		return classId;
	}

	public void setClassId(EClassId value)
	{
		classId = value;

		if (ai != null)
		{
			return;
		}

		// init Ai
		EClassId classIdParent = classId;

		Ai instance = null;

		do
		{
			String aiName = "";
			if (classIdParent.level() == 0)
			{
				switch (classIdParent.getRace())
				{
					case DARK_ELF:
					case ELF:
					case HUMAN:
						switch (classIdParent.getType())
						{
							case FIGHTER:
								aiName = "Fighter";
								break;
							case ARCHER:
								aiName = "Archer";
								break;
							case MYSTIC:
								aiName = "Mystic";
								break;
							case PRIEST:
								// y aca q inventamos? xD
								break;
							default:
								break;
						}
						break;
					case ORC:
					case DWARF:
						aiName = "Fighter";
						break;
				}
			}
			else
			{
				aiName = classIdParent.getName().replace(" ", "");
			}

			try
			{
				instance = (Ai) Class.forName("main.connection.bot.objects.ai." + aiName).newInstance();
			}
			catch (Exception e)
			{
				classIdParent = classIdParent.getParent();
				instance = null;
			}
		}
		while (instance == null);

		ai = instance;
	}

	public long getExp()
	{
		return exp;
	}

	public int getSp()
	{
		return sp;
	}

	public void setSp(int value)
	{
		sp = value;
	}

	public void setExp(long value)
	{
		exp = value;
	}

	public int getSTR()
	{
		return STR;
	}

	public void setSTR(int value)
	{
		STR = value;
	}

	public int getDEX()
	{
		return DEX;
	}

	public void setDEX(int value)
	{
		DEX = value;
	}

	public int getCON()
	{
		return CON;
	}

	public void setCON(int value)
	{
		CON = value;
	}

	public int getINT()
	{
		return INT;
	}

	public void setINT(int value)
	{
		INT = value;
	}

	public int getWIT()
	{
		return WIT;
	}

	public void setWIT(int value)
	{
		WIT = value;
	}

	public int getMEN()
	{
		return MEN;
	}

	public void setMEN(int value)
	{
		MEN = value;
	}

	public int getMaxCp()
	{
		return maxCp;
	}

	public void setMaxCp(int value)
	{
		maxCp = value;
	}

	public int getCurrentCp()
	{
		return currentCp;
	}

	public void setCurrentCp(int value)
	{
		currentCp = value;
	}

	public int getCurrentLoad()
	{
		return currentLoad;
	}

	public void setCurrentLoad(int value)
	{
		currentLoad = value;
	}

	public int getMaxLoad()
	{
		return maxLoad;
	}

	public void setMaxLoad(int value)
	{
		maxLoad = value;
	}

	public int getPkKills()
	{
		return pkKills;
	}

	public void setPkKills(int value)
	{
		pkKills = value;
	}

	public int getPvpKills()
	{
		return pvpKills;
	}

	public void setPvpKills(int value)
	{
		pvpKills = value;
	}

	public int getKarma()
	{
		return karma;
	}

	public void setKarma(int value)
	{
		karma = value;
	}

	public boolean isPvpFlag()
	{
		return isPvpFlag;
	}

	public void setPvpFlag(boolean value)
	{
		isPvpFlag = value;
	}

	public double getMovementSpeedMultiplier()
	{
		return movementSpeedMultiplier;
	}

	public void setMovementSpeedMultiplier(double value)
	{
		movementSpeedMultiplier = value;
	}

	public double getAttackSpeedMultiplier()
	{
		return attackSpeedMultiplier;
	}

	public void setAttackSpeedMultiplier(double value)
	{
		attackSpeedMultiplier = value;
	}

	public int getHairStyle()
	{
		return hairStyle;
	}

	public void setHairStyle(int value)
	{
		hairStyle = value;
	}

	public int getHairColor()
	{
		return hairColor;
	}

	public void setHairColor(int value)
	{
		hairColor = value;
	}

	public int getFace()
	{
		return face;
	}

	public void setFace(int value)
	{
		face = value;
	}

	public int getAccessLevel()
	{
		return accessLevel;
	}

	public void setAccessLevel(int value)
	{
		accessLevel = value;
	}

	public int getClanId()
	{
		return clanId;
	}

	public void setClanId(int value)
	{
		clanId = value;
	}

	public int getClanCrestId()
	{
		return clanCrestId;
	}

	public void setClanCrestId(int value)
	{
		clanCrestId = value;
	}

	public int getAllyId()
	{
		return allyId;
	}

	public void setAllyId(int value)
	{
		allyId = value;
	}

	public int getAllyCrestId()
	{
		return allyCrestId;
	}

	public void setAllyCrestId(int value)
	{
		allyCrestId = value;
	}

	public boolean isClanLeader()
	{
		return isClanLeader;
	}

	public void setClanLeader(boolean value)
	{
		isClanLeader = value;
	}

	public int getMountType()
	{
		return mountType;
	}

	public void setMountType(int value)
	{
		mountType = value;
	}

	public int getPrivateStoreType()
	{
		return privateStoreType;
	}

	public void setPrivateStoreType(int value)
	{
		privateStoreType = value;
	}

	public boolean isHasDwarvenCraft()
	{
		return hasDwarvenCraft;
	}

	public void setHasDwarvenCraft(boolean value)
	{
		hasDwarvenCraft = value;
	}

	public int getNumberOfCubics()
	{
		return numberOfCubics;
	}

	public void setNumberOfCubics(int value)
	{
		numberOfCubics = value;
	}

	public int[] getCubicIds()
	{
		return cubicIds;
	}

	public void setCubicIds(int[] values)
	{
		cubicIds = values;
	}

	public int getClanPrivileges()
	{
		return clanPrivileges;
	}

	public void setClanPrivileges(int value)
	{
		clanPrivileges = value;
	}

	public int getSwim()
	{
		return swim;
	}

	public void setSwim(int value)
	{
		swim = value;
	}

	public void sendPacket(ClientToGameServerMessage packet)
	{
		ConnectionManager.getBot().sendPacket(packet);
	}

	public Inventory getInventory()
	{
		return inverntoryManager;
	}

	public Skills getSkills()
	{
		return skillsManager;
	}

	public Effects getEffects()
	{
		return effectsManager;
	}

	/**
	 * A brief description of char is shown.
	 * Used for interface knownlist
	 *
	 * @return
	 */
	@Override
	public String getDescription()
	{
		return "<html><font color=A9A53A>[" + getObjectId() + "]</font>   " + getName() + " " + getTitle() + "</html>";
	}

	public String getGuiCp()
	{
		return "CP: " + getCurrentCp() + " / " + getMaxCp();
	}

	public String getGuiExp()
	{
		long expNextLevel = ExperienceData.LEVEL[getLevel()] - ExperienceData.LEVEL[getLevel() - 1];
		long expNeed = getExp() - ExperienceData.LEVEL[getLevel()];
		int e = (int) (expNextLevel * 100 / expNeed);

		return "Exp: " + e + "%";
	}

	public Ai getAi()
	{
		return ai;
	}

	/**
	 * @return the isSiting
	 */
	public boolean isSiting()
	{
		return isSiting;
	}

	/**
	 * @param isSiting the isSiting to set
	 */
	public void setSiting(boolean value)
	{
		isSiting = value;
	}

	public void setCharges(int value)
	{
		charges = value;
	}

	public int getCharges()
	{
		return charges;
	}

	public boolean isInParty()
	{
		return isInParty;
	}

	public void setInParty(boolean isInParty)
	{
		this.isInParty = isInParty;
	}

	public boolean isLeader()
	{
		return isLeader;
	}

	public void setLeader(boolean isLeader)
	{
		this.isLeader = isLeader;
	}
}
