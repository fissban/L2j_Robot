package main.network.game.client.ingame;

import main.connection.bot.objects.BItem;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestDestroyItem extends ClientToGameServerMessage
{
	public RequestDestroyItem(BItem item)
	{
		writeD(item.getObjectId());
		writeD(item.getCount());
	}
}
