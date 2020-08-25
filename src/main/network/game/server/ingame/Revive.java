package main.network.game.server.ingame;

import main.connection.bot.objects.BCreature;
import main.connection.bot.objects.BObject;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class Revive extends ServerNetwork
{
	private int objectId;

	public Revive(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		objectId = readD();
	}

	@Override
	public void write()
	{
		BObject obj = ObjectsManager.get(objectId);

		if (obj != null && obj instanceof BCreature)
		{
			((BCreature) obj).setDead(false);
		}
		else
		{
			getBot().setDead(false);
		}
	}
}
