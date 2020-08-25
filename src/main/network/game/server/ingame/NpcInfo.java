package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BSummon;
import main.data.NpcData;
import main.managers.ChronicleManager;
import main.managers.ObjectsManager;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class NpcInfo extends ServerNetwork implements IChronicle
{
	private int objectId;
	private int idTemplate;
	private boolean isAttackable;
	private int x;
	private int y;
	private int z;
	private int heading;
	private int mAtkSpd;
	private int pAtkSpd;
	private int runSpd;
	private int walkSpd;
	private int swinRunSped;
	private int swinWalkSpd;
	private int flRunSpd;
	private int flWalkSpd;
	private int flyRunSpd;
	private int flyWalkSpd;

	private double propertyMultiplier;
	private double pAtkSpdDouble;
	private double collisionRadious;
	private double collisionHeight;

	private int rhandWeapon;
	private int lhandWeapon;

	private boolean isRunning;
	private boolean isInCombat;
	private boolean isDead;
	private boolean isSummoned;

	private String name;
	private String title;

	private int abnormalEffect;

	public NpcInfo(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		switch (ChronicleManager.get())
		{
			case C4:
				c4();
				break;
			case C6:
				c6();
				break;
		}
	}

	@Override
	public void c4()
	{
		objectId = readD();
		idTemplate = readD() - 1000000;
		isAttackable = readD() == 0 ? false : true;

		x = readD();
		y = readD();
		z = readD();
		heading = readD();

		readD();// This will be 0x00

		mAtkSpd = readD();
		pAtkSpd = readD();
		runSpd = readD();
		walkSpd = readD();
		swinRunSped = readD();
		swinWalkSpd = readD();
		flRunSpd = readD();
		flWalkSpd = readD();
		flyRunSpd = readD();
		flyWalkSpd = readD();

		propertyMultiplier = readF();//speed
		pAtkSpdDouble = readF() * 277.478340719;// Why another pAtkSpd?

		collisionRadious = readF();
		collisionHeight = readF();

		rhandWeapon = readD();
		readD();// chest
		lhandWeapon = readD();

		readC();// This will be 0x01 == name above char

		isRunning = readC() == 0 ? false : true;
		isInCombat = readC() == 0 ? false : true;
		isDead = readC() == 0 ? false : true;
		isSummoned = readC() == 0 ? false : true; // summonAnimation

		name = readS();// pocos servidores usan esta opcion activada por lo q se le desde el cliente
		title = readS();

		readD();// 0x00
		readD();// 0x00
		readD();// 0x00

		abnormalEffect = readD();

		// All of this will be 0x00
		readD();//clanId
		readD();//clanCrest
		readD();//allyId
		readD();//allyCrest

		readC();//isFlying

		readC();// C3 team circle 1-blue, 2-red
		readF();// another collisionRadius?
		readF();// another coliisionHeight ?
		readD();// enchantEffect
	}

	@Override
	public void c6()
	{
		objectId = readD();
		idTemplate = readD() - 1000000;
		isAttackable = readD() == 0 ? false : true;

		x = readD();
		y = readD();
		z = readD();
		heading = readD();

		readD();// This will be 0x00

		mAtkSpd = readD();
		pAtkSpd = readD();
		runSpd = readD();
		walkSpd = readD();
		swinRunSped = readD();
		swinWalkSpd = readD();
		flRunSpd = readD();
		flWalkSpd = readD();
		flyRunSpd = readD();
		flyWalkSpd = readD();

		propertyMultiplier = readF();//speed
		pAtkSpdDouble = readF() * 277.478340719;// Why another pAtkSpd?

		collisionRadious = readF();
		collisionHeight = readF();

		rhandWeapon = readD();
		readD();// chest
		lhandWeapon = readD();

		readC();// This will be 0x01 == name above char

		isRunning = readC() == 0 ? false : true;
		isInCombat = readC() == 0 ? false : true;
		isDead = readC() == 0 ? false : true;
		isSummoned = readC() == 0 ? false : true; // summonAnimation

		name = readS();// pocos servidores usan esta opcion activada por lo q se le desde el cliente
		title = readS();

		readD();// 0x00
		readD();// 0x00
		readD();// 0x00

		abnormalEffect = readD();

		// All of this will be 0x00
		readD();//clanId
		readD();//clanCrest
		readD();//allyId
		readD();//allyCrest

		readC();//isFlying

		readC();// C3 team circle 1-blue, 2-red
		readF();// another collisionRadius?
		readF();// another coliisionHeight ?
		readD();// enchantEffect
		readD();// isFlying
	}

	@Override
	public void write()
	{
		BSummon summon = ObjectsManager.getSummon();
		if (summon != null && summon.getObjectId() == objectId)
		{
			summon.setName(NpcData.getName(idTemplate));
			summon.setTitle(title);
			summon.setIdTemplate(idTemplate);

			summon.setAttackable(isAttackable);
			summon.setXYZ(x, y, z);
			summon.setHeading(heading);

			summon.setPAtkSpd(pAtkSpd);
			summon.setMAtkSpd(mAtkSpd);
			summon.setRunSpd(runSpd);
			summon.setDead(isDead);

			if (isDead)
			{
				ObjectsManager.removeSummon();
			}
			else
			{
				GameFrame.getInstance().getPetInfo().updateStats();
			}
			return;
		}

		BNpc npc = null;
		boolean newNpc = false;
		//Si existe este objeto en nuestra lista se actualiza su info
		if (ObjectsManager.get(objectId) != null && ObjectsManager.get(objectId) instanceof BNpc)
		{
			npc = (BNpc) ObjectsManager.get(objectId);
		}
		else
		{
			npc = new BNpc(objectId);
			newNpc = true;
		}

		if (npc != null)
		{
			npc.setName(NpcData.getName(idTemplate));
			npc.setTitle(title);
			npc.setIdTemplate(idTemplate);

			npc.setAttackable(isAttackable);
			npc.setXYZ(x, y, z);
			npc.setHeading(heading);

			npc.setPAtkSpd(pAtkSpd);
			npc.setMAtkSpd(mAtkSpd);
			npc.setRunSpd(runSpd);
			// npc.setCollisionHeight();
			// npc.setCollisionRadius();
			//npc.setRhandWeapon(rhandWeapon);
			//npc.setLhandWeapon(lhandWeapon);

			//npc.setRunning(isRunning);
			npc.setDead(isDead);
			//npc.setInCombat(isInCombat);
			//npc.setSummoned(isSummoned);

			if (isDead)
			{
				if (newNpc)
				{
					return;
				}

				ObjectsManager.remove(objectId, true);
			}
			else
			{
				if (newNpc)
				{
					ObjectsManager.add(npc);
					//Se envia el paquete Action para recivir luego el paquete StatusUpdate con el hp y mp del player :D
					//ObjectsData.getBot().sendPacket(new Action(objectId, false));
				}

				GameFrame.getInstance().getKnown().addKnown(npc);
				GameFrame.getInstance().getLiveMap().updateOrAddObject(npc);
			}
		}
	}
}
