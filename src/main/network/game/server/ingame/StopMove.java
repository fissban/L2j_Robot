package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BObject;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class StopMove extends ServerNetwork
{
	int objectId;
	int x;
	int y;
	int z;
	int heading;

	public StopMove(byte data[])
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		objectId = readD();
		x = readD();
		y = readD();
		z = readD();
		heading = readD();
	}

	@Override
	public void write()
	{
		BObject c = ObjectsManager.get(objectId);
		if (c != null)
		{
			c.setXYZ(x, y, z);
			GameFrame.getInstance().getLiveMap().updateOrAddObject(c);
		}
		else
		{
			getBot().setXYZ(x, y, z);
			GameFrame.getInstance().getLiveMap().updateBot();
		}
	}
}
