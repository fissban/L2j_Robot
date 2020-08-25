package main.network.game.server.ingame;

import com.alee.laf.label.WebLabel;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BObject;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;
import main.util.UtilMap;

/**
 * @author fissban
 */
public class MoveToPawn extends ServerNetwork
{
	private int objectId;
	private int targetId;

	public MoveToPawn(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		objectId = readD();
		targetId = readD();
		readD();//distance

		readD();//actor x
		readD();//actor y
		readD();//actor z

	}

	@Override
	public void write()
	{
		// TODO falta calcular el movimiento
		BObject c = ObjectsManager.get(objectId);
		BObject t = ObjectsManager.get(targetId);

		if (t == null)
		{
			//
		}
		else if (c != null)
		{
			// update xyz for object
			c.setXYZ(t.getX(), t.getY(), t.getZ());
			// update or add new point in map
			GameFrame.getInstance().getLiveMap().updateOrAddObject(c);
		}
		else if (getBot().getObjectId() == objectId)
		{
			// update xyz for bot
			getBot().setXYZ(t.getX(), t.getY(), t.getZ());
			// update bot in map
			GameFrame.getInstance().getLiveMap().updateBot();

			WebLabel map = GameFrame.getInstance().getLiveMap().getMapLevel();

			// check if need update image for map
			if (!UtilMap.getMapRegion(getBot().getX(), getBot().getY()).equals(map.getIcon()))
			{
				// update image for map
				GameFrame.getInstance().getLiveMap().updateMapImage();
			}
		}
		else
		{
			System.out.println("missing object -> " + objectId + " in MoveToPawn");
		}
	}
}
