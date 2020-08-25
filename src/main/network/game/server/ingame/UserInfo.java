package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import gui.frames.game.windows.InventoryWindow;
import main.enums.EClassId;
import main.enums.EPaperdoll;
import main.enums.ERace;
import main.enums.ESex;
import main.managers.ChronicleManager;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class UserInfo extends ServerNetwork implements IChronicle
{
	int x;
	int y;
	int z;
	int heading;
	int objectId;
	String name;
	private ERace race;
	private ESex sex;

	EClassId classId;
	int level;
	long exp;
	int STR;
	int DEX;
	int CON;
	int INT;
	int WIT;
	int MEN;

	int maxCp;
	int currentCp;
	int maxHp;
	int currentHp;
	int maxMp;
	int currentMp;
	int sp;
	int currentLoad;
	int maxLoad;

	int pAtk;
	int pAtkSpd;
	int pDef;
	int evasion;
	int accuracy;
	int criticalHit;
	int mAtk;
	int mAtkSpd;

	int mDef;

	boolean isPvpFlag;
	int karma;

	int runSpd;
	int walkSpd;
	int swimRunSpd;
	int swimWalkSpd;
	int flRunSpd;
	int flWalkSpd;
	int flyRunSpd;
	int flyWalkSpd;

	double movementSpeedMultiplier;
	double attackSpeedMultiplier;

	double collisionRadius;
	double collisionHeight;

	int hairStyle;
	int hairColor;
	int face;
	int accessLevel;

	String title;

	int clanId;
	int clanCrestId;
	int allyId;
	int allyCrestId;
	int isClanLeader;//clan leader ? 0x60 : 0

	int mountType;
	int privateStoreType;
	boolean hasDwarvenCraft;

	int pkKills;
	int pvpKills;

	private int underId;
	private int rearId;
	private int learId;
	private int neckId;
	private int rfingerId;
	private int lfingerId;
	private int headId;
	private int rhandId;
	private int lhandId;
	private int glovesId;
	private int chestId;
	private int legsId;
	private int feetId;
	private int backId;
	private int hairId;
	private int faceId;

	private int underObjId;
	private int rearObjId;
	private int learObjId;
	private int neckObjId;
	private int rfingerObjId;
	private int lfingerObjId;
	private int headObjId;
	private int rhandObjId;
	private int lhandObjId;
	private int glovesObjId;
	private int chestObjId;
	private int legsObjId;
	private int feetObjId;
	private int backObjId;
	private int hairObjId;
	private int faceObjId;

	public UserInfo(byte[] data)
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
		x = readD();
		y = readD();
		z = readD();
		heading = readD();
		objectId = readD();
		name = readS();
		race = ERace.values()[readD()];
		sex = ESex.values()[readD()];

		classId = EClassId.values()[readD()];
		level = readD();
		exp = readD();
		STR = readD();
		DEX = readD();
		CON = readD();
		INT = readD();
		WIT = readD();
		MEN = readD();
		maxHp = readD();
		currentHp = readD();
		maxMp = readD();
		currentMp = readD();
		sp = readD();
		currentLoad = readD();
		maxLoad = readD();

		readD();//getActiveWeaponItem (20 no weapon, 40 weapon equipped)

		underObjId = readD();
		rearObjId = readD();
		learObjId = readD();
		neckObjId = readD();
		rfingerObjId = readD();
		lfingerObjId = readD();
		headObjId = readD();
		rhandObjId = readD();
		lhandObjId = readD();
		glovesObjId = readD();
		chestObjId = readD();
		legsObjId = readD();
		feetObjId = readD();
		backObjId = readD();
		readD();//PAPERDOLL_RHAND
		hairObjId = readD();
		//faceObjId = readD();

		underId = readD();
		rearId = readD();
		learId = readD();
		neckId = readD();
		rfingerId = readD();
		lfingerId = readD();
		headId = readD();
		rhandId = readD();
		lhandId = readD();
		glovesId = readD();
		chestId = readD();
		legsId = readD();
		feetId = readD();
		backId = readD();
		readD();//PAPERDOLL_RHAND
		hairId = readD();
		//faceId = readD();

		pAtk = readD();
		pAtkSpd = readD();
		pDef = readD();
		evasion = readD();
		accuracy = readD();
		criticalHit = readD();
		mAtk = readD();
		mAtkSpd = readD();
		pAtkSpd = readD();
		mDef = readD();

		isPvpFlag = readD() == 0 ? false : true;
		karma = readD();

		runSpd = readD();
		walkSpd = readD();
		swimRunSpd = readD();
		swimWalkSpd = readD();
		flRunSpd = readD();
		flWalkSpd = readD();
		flyRunSpd = readD();
		flyWalkSpd = readD();

		movementSpeedMultiplier = readF();
		attackSpeedMultiplier = readF();

		collisionRadius = readF();
		collisionHeight = readF();

		hairStyle = readD();
		hairColor = readD();
		face = readD();

		accessLevel = readD(); // // builder level 0 or 1

		title = readS();

		clanId = readD();
		clanCrestId = readD();
		allyId = readD();
		allyCrestId = readD();

		isClanLeader = readD();//clan leader ? 0x60 : 0

		mountType = readC();
		privateStoreType = readC();
		hasDwarvenCraft = readC() == 0 ? false : true;

		pkKills = readD();
		pvpKills = readD();

		int numberOfCubics = readH();//numberOfCubics
		for (int i = 0; i < numberOfCubics; ++i)
		{
			readH();//cubidId
		}

		readC();//This will be 0x00. 1-find party members
		readD();//abnormalEffect
		readC();//This will be 0x00

		readD();//clanPrivileges

		readD();//
		readD();//
		readD();//
		readD();//
		readD();//
		readD();//
		readD();//

		readH();//RecomLeft
		readH();//RecomHave

		readD();//getMountNpcId() + 1000000

		readH();//inventoryLimit

		readD();//classId2

		//this will be 0x00
		readD();//special effects? circles around getBot()...

		maxCp = readD();
		currentCp = readD();

		readC();//if 0, is mounted, else it is the enchant effect
		readC();//team circle around feet 1= Blue, 2 = red

		readD();//clanCrestLargeId

		readC();//isNoble
		readC();//isHero

		readC();//Fishing Mode -Not Used-
		readD();//fishX
		readD();//fishY
		readD();//fishZ

		readD();//nameColor
	}

	@Override
	public void c6()
	{
		x = readD();
		y = readD();
		z = readD();
		heading = readD();
		objectId = readD();
		name = readS();
		race = ERace.values()[readD()];
		sex = ESex.values()[readD()];

		classId = EClassId.values()[readD()];
		level = readD();
		exp = readQ();
		STR = readD();
		DEX = readD();
		CON = readD();
		INT = readD();
		WIT = readD();
		MEN = readD();
		maxHp = readD();
		currentHp = readD();
		maxMp = readD();
		currentMp = readD();
		sp = readD();
		currentLoad = readD();
		maxLoad = readD();

		readD();//getActiveWeaponItem (20 no weapon, 40 weapon equipped)

		underObjId = readD();
		rearObjId = readD();
		learObjId = readD();
		neckObjId = readD();
		rfingerObjId = readD();
		lfingerObjId = readD();
		headObjId = readD();
		rhandObjId = readD();
		lhandObjId = readD();
		glovesObjId = readD();
		chestObjId = readD();
		legsObjId = readD();
		feetObjId = readD();
		backObjId = readD();
		readD();//PAPERDOLL_RHAND
		hairObjId = readD();
		faceObjId = readD();

		underId = readD();
		rearId = readD();
		learId = readD();
		neckId = readD();
		rfingerId = readD();
		lfingerId = readD();
		headId = readD();
		rhandId = readD();
		lhandId = readD();
		glovesId = readD();
		chestId = readD();
		legsId = readD();
		feetId = readD();
		backId = readD();
		readD();//PAPERDOLL_RHAND
		hairId = readD();
		faceId = readD();

		//c6 new h's
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readD();//augment PAPERDOLL_RHAND
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readH();
		readD();//augment PAPERDOLL_LHAND
		readH();
		readH();
		readH();
		readH();

		pAtk = readD();
		pAtkSpd = readD();
		pDef = readD();
		evasion = readD();
		accuracy = readD();
		criticalHit = readD();
		mAtk = readD();
		mAtkSpd = readD();
		pAtkSpd = readD();

		mDef = readD();

		isPvpFlag = readD() == 0 ? false : true;
		karma = readD();

		runSpd = readD();
		walkSpd = readD();
		swimRunSpd = readD();
		swimWalkSpd = readD();
		flRunSpd = readD();
		flWalkSpd = readD();
		flyRunSpd = readD();
		flyWalkSpd = readD();

		movementSpeedMultiplier = readF();
		attackSpeedMultiplier = readF();

		collisionRadius = readF();
		collisionHeight = readF();

		hairStyle = readD();
		hairColor = readD();
		face = readD();

		accessLevel = readD();

		title = readS();

		clanId = readD();
		clanCrestId = readD();
		allyId = readD();
		allyCrestId = readD();
		isClanLeader = readD();//clan leader ? 0x60 : 0

		mountType = readC();
		privateStoreType = readC();
		hasDwarvenCraft = readC() == 0 ? false : true;

		pkKills = readD();
		pvpKills = readD();

		int numberOfCubics = readH();//numberOfCubics
		for (int i = 0; i < numberOfCubics; ++i)
		{
			readH();//cubidId
		}

		readC();//This will be 0x00. 1-find party members
		readD();//abnormalEffect
		readC();//This will be 0x00

		readD();//clanPrivileges

		readH();//RecomLeft
		readH();//RecomHave

		readD();//getMountNpcId() + 1000000

		readH();//inventoryLimit

		readD();//classId2

		//this will be 0x00
		readD();//special effects? circles around getBot()...

		maxCp = readD();
		currentCp = readD();

		readC();//if 0, is mounted, else it is the enchant effect
		readC();//team circle around feet 1= Blue, 2 = red

		readD();//clanCrestLargeId

		readC();//isNoble
		readC();//isHero

		readC();//Fishing Mode -Not Used-
		readD();//fishX
		readD();//fishY
		readD();//fishZ

		readD();//nameColor

		readC();//isRunning

		readD();//getPledgeClass
		readD();//getPledgeType
		readD();//getTitleColor
		readD();//CursedWeapon
	}

	@Override
	public void write()
	{
		//UPDATE USER INFO
		getBot().setObjectId(objectId);
		getBot().setName(name);
		getBot().setTitle(title);
		getBot().setXYZ(x, y, z);
		getBot().setRace(race);
		getBot().setSex(sex);
		getBot().setHeading(heading);

		getBot().setClassId(classId);
		getBot().setLevel(level);
		getBot().setExp(exp);
		getBot().setSp(sp);

		getBot().setPvpKills(pvpKills);
		getBot().setPkKills(pkKills);

		getBot().setSTR(STR);
		getBot().setDEX(DEX);
		getBot().setCON(CON);
		getBot().setINT(INT);
		getBot().setWIT(WIT);
		getBot().setMEN(MEN);

		getBot().setMaxHp(maxHp);
		getBot().setCurrentHp(currentHp);
		getBot().setMaxMp(maxMp);
		getBot().setCurrentMp(currentMp);

		getBot().setCurrentLoad(currentLoad);
		getBot().setMaxLoad(maxLoad);

		getBot().setPAtk(pAtk);
		getBot().setPAtkSpd(pAtkSpd);
		getBot().setPDef(pDef);

		getBot().setEvasion(evasion);
		getBot().setAccuracy(accuracy);
		getBot().setCriticalRate(criticalHit);

		getBot().setMAtk(mAtk);
		getBot().setMAtkSpd(mAtkSpd);
		getBot().setMDef(mDef);

		getBot().setPvpFlag(isPvpFlag);
		getBot().setKarma(karma);
		getBot().setRunSpd(runSpd);

		getBot().setCurrentCp(currentCp);
		getBot().setMaxCp(maxCp);

		//- ClanId, AllianceId, ...
		//- Hero aura
		getBot().setHairStyle(hairStyle);
		getBot().setHairColor(hairColor);
		getBot().setFace(face);
		getBot().setAccessLevel(accessLevel);

		getBot().getInventory().getPaperdoll(EPaperdoll.UNDER).set(underId, underObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.REAR).set(rearId, rearObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.LEAR).set(learId, learObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.NECK).set(neckId, neckObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.RFINGER).set(rfingerId, rfingerObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.LFINGER).set(lfingerId, lfingerObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.HEAD).set(headId, headObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.RHAND).set(rhandId, rhandObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.LHAND).set(lhandId, lhandObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.GLOVES).set(glovesId, glovesObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.CHEST).set(chestId, chestObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.LEGS).set(legsId, legsObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.FEET).set(feetId, feetObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.BACK).set(backId, backObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.HAIR).set(hairId, hairObjId);
		getBot().getInventory().getPaperdoll(EPaperdoll.FACE).set(faceId, faceObjId);

		GameFrame.getInstance().getBotInfo().updateStats();
		GameFrame.getInstance().getLiveMap().updateMapImage();
		GameFrame.getInstance().getLiveMap().updateBot();

		InventoryWindow.getInstance().updateEquipmentItems();
		InventoryWindow.getInstance().updateInventory();
	}
}
