package main.network.game.client.ingame;

import main.connection.bot.objects.BObject;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class Action extends ClientToGameServerMessage
{
	/**
	 * @param objectId
	 * @param shiftPressed
	 */
	public Action(BObject obj, boolean shiftPressed)
	{
		writeC(0x04);
		writeD(obj.getObjectId());// Target object Identifier
		writeD(0x00); // not used in L2j
		writeD(0x00); // not used in L2j
		writeD(0x00); // not used in L2j
		writeC(shiftPressed ? 1 : 0); // Action identifier : 0-Simple click, 1-Shift click
	}
}
