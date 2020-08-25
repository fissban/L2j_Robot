package main.network.game.server.ingame;

import main.managers.ChronicleManager;
import main.managers.ObjectsManager;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class EtcStatusUpdate extends ServerNetwork implements IChronicle
{
	private int charges;
	private int weightPenalty;
	private int refusalMode;
	private int dangerArea;
	private int penaltyWeaponArmor;
	private int charmOfCourage;
	private int deathPenalty;

	public EtcStatusUpdate(byte[] data)
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
		charges = readD();
		weightPenalty = readD();
		refusalMode = readD();
		dangerArea = readD();
		penaltyWeaponArmor = readD();
		//charmOfCourage = readD();
		//deathPenalty = readD();
	}

	@Override
	public void c6()
	{
		charges = readD();
		weightPenalty = readD();
		refusalMode = readD();
		dangerArea = readD();
		penaltyWeaponArmor = readD();
		charmOfCourage = readD();
		//deathPenalty = readD();
	}

	@Override
	public void write()
	{
		ObjectsManager.getBot().setCharges(charges);
	}
}
