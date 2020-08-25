package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BPlayer;
import main.enums.EClassId;
import main.enums.EPaperdoll;
import main.enums.ERace;
import main.enums.ESex;
import main.managers.ChronicleManager;
import main.managers.ObjectsManager;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class CharInfo extends ServerNetwork implements IChronicle
{
	private int x;
	private int y;
	private int z;
	private int heading;
	private int objectId;
	private String name;
	private ERace race;
	private ESex sex;

	private EClassId classId;

	private int maxCp;
	private int currentCp;

	private int pAtkSpd;
	private int mAtkSpd;
	private boolean isPvpFlag;
	private int karma;

	private int runSpd;
	private int walkSpd;
	private int swimRunSpd;
	private int swimWalkSpd;

	private int flyRunSpd;
	private int flyWalkSpd;

	private double movementSpeedMultiplier;
	private double attackSpeedMultiplier;

	private double collisionRadius;
	double collisionHeight;

	private int hairStyle;
	private int hairColor;
	private int face;

	private String title;

	private int clanId;
	private int clanCrestId;
	private int allyId;
	private int allyCrestId;
	private int isClanLeader;//clan leader ? 0x60 : 0

	private int mountType;
	private int privateStoreType;
	private boolean hasDwarvenCraft;

	private boolean isDead;

	private boolean isInvisible;

	private int underId;
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

	public CharInfo(byte[] data)
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

		underId = readD();
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

		isPvpFlag = readD() == 0 ? false : true;
		karma = readD();

		mAtkSpd = readD();
		pAtkSpd = readD();

		readD();//isPvpFlag
		readD();//karma

		runSpd = readD();
		walkSpd = readD();
		swimRunSpd = readD();
		swimWalkSpd = readD();
		readD();//runSpd
		readD();//walkSpd
		flyRunSpd = readD();
		flyWalkSpd = readD();

		movementSpeedMultiplier = readF();
		attackSpeedMultiplier = readF();

		collisionRadius = readF();
		collisionHeight = readF();

		hairStyle = readD();
		hairColor = readD();
		face = readD();

		title = readS();

		clanId = readD();
		clanCrestId = readD();
		allyId = readD();
		allyCrestId = readD();

		readD();//0x00

		readC();//standing = 1 sitting = 0
		readC();//running = 1 walking = 0
		readC();//inCombat

		isDead = readC() == 0 ? false : true;
		isInvisible = readC() == 0 ? false : true;

		readC();//1 on strider 2 on wyvern 0 no mount
		readC();//1 - sellshop

		int numberOfCubics = readH();//numberOfCubics
		for (int i = 0; i < numberOfCubics; ++i)
		{
			readH();//cubidId
		}

		readC();//isInPartyMatchRoom

		readD();//AbnormalEffect

		readC();//RecomLeft
		readH();//RecomHave
		readD();//classId

		maxCp = readD();
		currentCp = readD();

		readC();//enchant effect

		readC();//team circle around feet 1= Blue, 2 = Red

		readD();//clanCrestLargeId
		readC();//isNoble
		readC();//isHero

		readC();//isFishing
		readD();//fishX
		readD();//fishY
		readD();//fishZ

		readD();//NameColor
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

		underId = readD();
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

		isPvpFlag = readD() == 0 ? false : true;
		karma = readD();

		mAtkSpd = readD();
		pAtkSpd = readD();

		readD();//isPvpFlag
		readD();//karma

		runSpd = readD();
		walkSpd = readD();
		swimRunSpd = readD();
		swimWalkSpd = readD();
		readD();//runSpd
		readD();//walkSpd
		flyRunSpd = readD();
		flyWalkSpd = readD();

		movementSpeedMultiplier = readF();
		attackSpeedMultiplier = readF();

		collisionRadius = readF();
		collisionHeight = readF();

		hairStyle = readD();
		hairColor = readD();
		face = readD();

		title = readS();

		clanId = readD();
		clanCrestId = readD();
		allyId = readD();
		allyCrestId = readD();

		readD();//0x00

		readC();//standing = 1 sitting = 0
		readC();//running = 1 walking = 0
		readC();//inCombat

		isDead = readC() == 0 ? false : true;
		isInvisible = readC() == 0 ? false : true;

		readC();//1 on strider 2 on wyvern 0 no mount
		readC();//1 - sellshop

		int numberOfCubics = readH();//numberOfCubics
		for (int i = 0; i < numberOfCubics; ++i)
		{
			readH();//cubidId
		}

		readC();//isInPartyMatchRoom

		readD();//AbnormalEffect

		readC();//RecomLeft
		readH();//RecomHave
		readD();//classId

		maxCp = readD();
		currentCp = readD();

		readC();//enchant effect

		readC();//team circle around feet 1= Blue, 2 = Red

		readD();//clanCrestLargeId
		readC();//isNoble
		readC();//isHero

		readC();//isFishing
		readD();//fishX
		readD();//fishY
		readD();//fishZ

		readD();//NameColor

		readD();//0x00

		readD();//PledgeClass
		readD();//PledgeType

		readD();//TitleColor

		//readD();//CursedWeapon
	}

	@Override
	public void write()
	{
		BPlayer player;
		boolean newPlayer = false;
		//Si existe este objeto en nuestra lista se actualiza su info
		if (ObjectsManager.get(objectId) != null)
		{
			player = (BPlayer) ObjectsManager.get(objectId);
		}
		else
		{
			player = new BPlayer(objectId);
			newPlayer = true;
		}

		player.setName(name);
		player.setTitle(title);
		player.setXYZ(x, y, z);
		player.setRace(race);
		player.setSex(sex);
		player.setHeading(heading);

		player.setClassId(classId);

		player.setPAtkSpd(pAtkSpd);
		player.setMAtkSpd(mAtkSpd);

		player.setPvpFlag(isPvpFlag);
		player.setKarma(karma);
		player.setRunSpd(runSpd);

		player.setCurrentCp(currentCp);
		player.setMaxCp(maxCp);

		//- ClanId, AllianceId, ...
		//- Hero aura
		player.setHairStyle(hairStyle);
		player.setHairColor(hairColor);
		player.setFace(face);

		player.getInventory().getPaperdoll(EPaperdoll.UNDER).setItemId(underId);
		player.getInventory().getPaperdoll(EPaperdoll.HEAD).setItemId(headId);
		player.getInventory().getPaperdoll(EPaperdoll.RHAND).setItemId(rhandId);
		player.getInventory().getPaperdoll(EPaperdoll.LHAND).setItemId(lhandId);
		player.getInventory().getPaperdoll(EPaperdoll.GLOVES).setItemId(glovesId);
		player.getInventory().getPaperdoll(EPaperdoll.CHEST).setItemId(chestId);
		player.getInventory().getPaperdoll(EPaperdoll.LEGS).setItemId(legsId);
		player.getInventory().getPaperdoll(EPaperdoll.FEET).setItemId(feetId);
		player.getInventory().getPaperdoll(EPaperdoll.BACK).setItemId(backId);
		player.getInventory().getPaperdoll(EPaperdoll.HAIR).setItemId(hairId);
		player.getInventory().getPaperdoll(EPaperdoll.FACE).setItemId(faceId);

		if (newPlayer)
		{
			ObjectsManager.add(player);
		}

		//Se envia el paquete Action para recivir luego el paquete StatusUpdate con el hp y mp del player :D
		//ActorsData.getBot().sendPacket(new Action(objectId, false));

		GameFrame.getInstance().getKnown().addKnown(player);
		GameFrame.getInstance().getLiveMap().updateOrAddObject(player);

		if (player.isInParty())
		{
			GameFrame.getInstance().getParty().updateHpMp(player);
		}
	}
}