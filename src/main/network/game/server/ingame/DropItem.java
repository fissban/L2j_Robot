package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BItem;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class DropItem extends ServerNetwork
{
	private int ownerObjectId;
	private int itemObjectId;
	private int itemId;
	private int x, y, z;
	private boolean isStackable;
	private int itemCount;

	public DropItem(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		ownerObjectId = readD();
		itemObjectId = readD();
		itemId = readD();
		x = readD();
		y = readD();
		z = readD();
		isStackable = readD() == 0 ? false : true;
		itemCount = readD();
		readD(); // unknown
	}

	@Override
	public void write()
	{
		BItem item = new BItem(itemObjectId);
		item.setId(itemId);
		item.setOwnerObjectId(ownerObjectId);
		item.setXYZ(x, y, z);
		item.setCount(itemCount);

		ObjectsManager.add(item);
		GameFrame.getInstance().getKnown().addKnown(item);
		GameFrame.getInstance().getLiveMap().updateOrAddObject(item);
	}
}
