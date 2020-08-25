package main.network.game.server.ingame;

import main.connection.bot.objects.BCreature;
import main.connection.bot.objects.BObject;
import main.enums.EIntention;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class Die extends ServerNetwork
{
	int objectId;
	boolean toClanHall;
	boolean toCastle;
	boolean toSiegeHQ;
	boolean sweepable;
	boolean allowFixedRes;

	public Die(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		objectId = readD();
		toClanHall = readD() == 0 ? false : true;
		toCastle = readD() == 0 ? false : true;
		toSiegeHQ = readD() == 0 ? false : true;
		sweepable = readD() == 0 ? false : true;
		allowFixedRes = readD() == 0 ? false : true;
	}

	@Override
	public void write()
	{
		BObject obj = ObjectsManager.get(objectId);

		if (obj != null && obj instanceof BCreature)
		{
			BCreature creature = (BCreature) obj;
			creature.setDead(true);
			// cancel target
			if (creature.equals(getBot().getAi().getTarget()))
			{
				getBot().getAi().removeTarget();
				// change intention
				getBot().getAi().setIntention(EIntention.NONE);
			}
		}
		else
		{
			getBot().setDead(true);
			getBot().getAi().notifyDie();
		}
	}
}
