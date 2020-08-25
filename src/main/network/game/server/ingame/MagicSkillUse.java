package main.network.game.server.ingame;

import main.managers.ChronicleManager;
import main.model.SkillModel;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class MagicSkillUse extends ServerNetwork implements IChronicle
{
	private int targetId;
	private int skillId;
	private int skillLevel;
	private int hitTime;
	private int reuseDelay;
	private int charObjId;
	private int x, y, z;
	private int targetx, targety, targetz;
	private boolean success = false;

	public MagicSkillUse(byte[] data)
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
		charObjId = readD();
		targetId = readD();
		skillId = readD();
		skillLevel = readD();
		hitTime = readD();
		reuseDelay = readD();
		x = readD();
		y = readD();
		z = readD();
		readH();
		readH();//targetx
		readH();//targety
		readH();//targetz
	}

	@Override
	public void c6()
	{
		charObjId = readD();
		targetId = readD();
		skillId = readD();
		skillLevel = readD();
		hitTime = readD();
		reuseDelay = readD();
		x = readD();
		y = readD();
		z = readD();
		if (readD() == 1)//succes
		{
			readH();
		}
		readD();//targetx
		readD();//targety
		readD();//targetz
	}

	@Override
	public void write()
	{
		if (getBot().getObjectId() == charObjId)
		{
			SkillModel sk = getBot().getSkills().get(skillId);

			// puede dar null al usar skills de armas, pots, soulshots etc etc
			if (sk == null)
			{
				return;
			}

			sk.setReuseTime(reuseDelay + hitTime);
		}

		// si targetId es instanceof BPlayer es porq nos ataca un personaje...

		// Aca podemos detectar si usan backstab y cosas asi q es importante esquivar a tiempo xD

	}
}
