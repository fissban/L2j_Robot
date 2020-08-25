package main.network.game.server.ingame;

import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class GetItem extends ServerNetwork
{
	//private int playerObjectId;
	private int itemObjectId;

	public GetItem(byte[] data)
	{
		super(data);

		init();
	}

	@Override
	public void read()
	{
		readD();//playerObjectId
		itemObjectId = readD();

		readD();//x
		readD();//y
		readD();//z
	}

	@Override
	public void write()
	{
		ObjectsManager.remove(itemObjectId, true);
	}
}
