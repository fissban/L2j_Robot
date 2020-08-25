package main.network.game.client.ingame;

import main.connection.bot.objects.BItem;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class UseItem extends ClientToGameServerMessage
{
	public UseItem(BItem item)
	{
		writeC(0x14);
		writeD(item.getObjectId());
		writeD(0x00);//ctrlPressed
	}
}
