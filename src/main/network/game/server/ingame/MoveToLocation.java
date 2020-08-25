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
public class MoveToLocation extends ServerNetwork
{
	private int objectId;
	private int xDest;
	private int yDest;
	private int zDest;

	public MoveToLocation(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		objectId = readD();
		xDest = readD();
		yDest = readD();
		zDest = readD();

		readD();//xOrig
		readD();//yOrig
		readD();//zOrig
	}

	@Override
	public void write()
	{
		// TODO falta calcular el movimiento
		BObject c = ObjectsManager.get(objectId);

		if (c != null && c instanceof BObject)
		{
			// update xyz for object
			c.setXYZ(xDest, yDest, zDest);
			// update or add new point in map
			GameFrame.getInstance().getLiveMap().updateOrAddObject(c);
		}
		else if (getBot().getObjectId() == objectId)
		{
			// update xyz for bot
			getBot().setXYZ(xDest, yDest, zDest);
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
			System.out.println("missing object -> " + objectId + " in MoveToLocation");
		}
	}
}
