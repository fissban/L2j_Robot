package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BSummon;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class PetInfo extends ServerNetwork
{
	private static final int SUMMON = 1;
	private static final int PET = 2;

	private int type;
	private int objectId;
	private int templateId;
	private int x, y, z, heading;
	private int mAtkSpd;
	private int pAtkSpd;
	private int runSpd;
	private int walkSpd;
	private int swimRunSpd;
	private int swimWalkSpd;
	private int flRunSpd;
	private int flWalkSpd;
	private int flyRunSpd;
	private int flyWalkSpd;
	private int weapon;
	private int armor;
	private boolean isInCombat;
	private boolean isDead;
	private String name;
	private String title;
	private int lvl;
	private boolean isPvpFlag;
	private int karma;
	private int curFeed;
	private int maxFeed;
	private int currentHp;
	private int maxHp;
	private int currentMp;
	private int maxMp;
	private int sp;
	private int exp;
	private int expForThisLevel;
	private int expForNextLevel;
	private int totalWeight;
	private int maxLoad;
	private int pAtk;
	private int pDef;
	private int mAtk;
	private int mDef;
	private int accuracy;
	private int evasion;
	private int criticalHit;
	private int moveSpeed;
	private int abnormalEffect;
	private int isMountable;
	private int team;

	public PetInfo(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		type = readD();
		objectId = readD();

		templateId = readD();
		readD(); // 1=attackable

		x = readD();
		y = readD();
		z = readD();
		heading = readD();
		readD(); // 0x00
		mAtkSpd = readD();
		pAtkSpd = readD();
		runSpd = readD();
		walkSpd = readD();
		swimRunSpd = readD();
		swimWalkSpd = readD();
		flRunSpd = readD();
		flWalkSpd = readD();
		flyRunSpd = readD();
		flyWalkSpd = readD();

		readF(); // speed multiplier
		readF(); // atk speed multiplier
		readF();// collisionRadius
		readF();// collisionHeight
		weapon = readD(); // right hand weapon
		armor = readD();
		readD(); // left hand weapon
		readC(); // master name above pet 1=true 0=false
		readC(); // running=1
		isInCombat = (readC() == 1) ? true : false; // attacking 1=true 0=false
		isDead = (readC() == 1) ? true : false; // dead 1=true 0=false
		readC(); // 0=teleported 1=default 2=summoned
		name = readS();
		title = readS();
		readD();// 0x01
		isPvpFlag = readD() == 0 ? false : true;
		karma = readD();
		curFeed = readD();
		maxFeed = readD();
		currentHp = readD();
		maxHp = readD();
		currentMp = readD();
		maxMp = readD();
		sp = readD();
		lvl = readD();
		exp = readD();
		expForThisLevel = readD();// 0% absolute value
		expForNextLevel = readD();// 100% Absolute value

		totalWeight = readD();
		maxLoad = readD();

		pAtk = readD();// patk
		pDef = readD();// pdef
		mAtk = readD();// matk
		mDef = readD();// mdef
		accuracy = readD();// accuracy
		evasion = readD();// evasion
		criticalHit = readD();// critical
		moveSpeed = readD();// speed
		pAtkSpd = readD();// atkspeed
		mAtkSpd = readD();// casting speed

		readD();// c2 abnormal visual effect... bleed=1; poison=2; poison & bleed=3; flame=4;
		readH();// c2 ride button

		readC(); // 0x00

		readH(); // 0x00
		readC(); // team aura (1 = blue, 2 = red)

		readD();//getSoulShotsPerHit
		readD();//getSpiritShotsPerHit

	}

	@Override
	public void write()
	{
		// check if exist summon
		if (ObjectsManager.getSummon() == null)
		{
			// add new object
			ObjectsManager.setSummon(objectId);
		}

		BSummon summon = ObjectsManager.getSummon();

		summon.setIdTemplate(templateId);
		summon.setXYZ(x, y, z);
		// heading
		summon.setMAtkSpd(mAtkSpd);
		summon.setPAtkSpd(pAtkSpd);
		summon.setRunSpd(runSpd);
		// walkSpd;
		// swimRunSpd;
		// swimWalkSpd;
		// flRunSpd;
		// flWalkSpd;
		// flyRunSpd;
		// flyWalkSpd;

		// weapon;
		// armor;
		// isInCombat;
		summon.setDead(isDead);
		summon.setName(name);
		summon.setTitle(title);
		summon.setLevel(lvl);
		// isPvpFlag;
		// karma;
		summon.setCurFed(curFeed);
		summon.setMaxFed(maxFeed);
		summon.setCurrentHp(currentHp);
		summon.setMaxHp(maxHp);
		summon.setCurrentHp(currentMp);
		summon.setMaxMp(maxMp);
		//sp;
		//exp
		//expForThisLevel;
		//expForNextLevel;
		//totalWeight;
		//maxLoad;
		summon.setPAtk(pAtk);
		summon.setPDef(pDef);
		summon.setMAtk(mAtk);
		summon.setMDef(mDef);
		summon.setAccuracy(accuracy);
		summon.setEvasion(evasion);
		summon.setCriticalRate(criticalHit);
		summon.setRunSpd(moveSpeed);
		// abnormalEffect;
		// isMountable;
		// team;

		GameFrame.getInstance().getLiveMap().updateOrAddObject(summon);
		GameFrame.getInstance().getPetInfo().updateStats();
	}
}
