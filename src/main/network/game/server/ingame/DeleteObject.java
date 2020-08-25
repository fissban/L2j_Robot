package main.network.game.server.ingame;

import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class DeleteObject extends ServerNetwork
{
	private int objectId;

	public DeleteObject(byte data[])
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		objectId = readD();
		readD(); // 0 - stand up and delete, 1 - delete
	}

	@Override
	public void write()
	{
		if (ObjectsManager.getSummon() != null && ObjectsManager.getSummon().getObjectId() == objectId)
		{
			ObjectsManager.removeSummon();
		}
		else
		{
			ObjectsManager.remove(objectId, false);
		}
	}
}
