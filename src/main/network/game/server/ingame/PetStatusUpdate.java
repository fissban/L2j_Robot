package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BSummon;
import main.managers.ChronicleManager;
import main.managers.ObjectsManager;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class PetStatusUpdate extends ServerNetwork implements IChronicle
{
	private int objectId;
	private String title;

	private int curHp;
	private int maxHp;

	private int curMp;
	private int maxMp;

	private int curFed;
	private int maxFed;

	private int x;
	private int y;
	private int z;
	private int level;

	public PetStatusUpdate(byte[] data)
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
		readD(); // summonType
		objectId = readD();
		x = readD();
		y = readD();
		z = readD();
		title = readS();

		curFed = readD();
		maxFed = readD();

		curHp = readD();
		maxHp = readD();

		curMp = readD();
		maxMp = readD();

		level = readD();
		readD(); // getExp
		readD();// getExpForThisLevel -> 0% absolute value
		readD();// getExpForNextLevel -> 100% absolute value
	}

	@Override
	public void c6()
	{
		readD(); // summonType
		objectId = readD();
		x = readD();
		y = readD();
		z = readD();
		title = readS();

		curFed = readD();
		maxFed = readD();

		curHp = readD();
		maxHp = readD();

		curMp = readD();
		maxMp = readD();

		level = readD();
		readQ(); // getExp
		readQ();// getExpForThisLevel -> 0% absolute value
		readQ();// getExpForNextLevel -> 100% absolute value
	}

	@Override
	public void write()
	{
		// check if exist summon
		if (ObjectsManager.getSummon() == null)
		{
			// remove old object
			// add new object
			ObjectsManager.setSummon(objectId);
		}

		BSummon summon = ObjectsManager.getSummon();

		summon.setTitle(title);
		summon.setLevel(level);

		summon.setMaxHp(maxHp);
		summon.setCurrentHp(curHp);
		summon.setXYZ(x, y, z);

		summon.setCurrentMp(curMp);
		summon.setMaxMp(maxMp);
		summon.setCurFed(curFed);
		summon.setMaxFed(maxFed);

		GameFrame.getInstance().getLiveMap().updateOrAddObject(summon);
		GameFrame.getInstance().getPetInfo().updateStats();
	}
}
