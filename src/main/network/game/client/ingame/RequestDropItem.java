package main.network.game.client.ingame;

import main.connection.bot.objects.BItem;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestDropItem extends ClientToGameServerMessage
{
	public RequestDropItem(BItem item)
	{
		writeD(item.getObjectId());
		writeD(item.getCount());
		writeD(getBot().getX() + 10);
		writeD(getBot().getY() + 10);
		writeD(getBot().getZ());
	}
}
