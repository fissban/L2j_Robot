package main.network.game.client.ingame;

import main.connection.bot.objects.BObject;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class AttackRequest extends ClientToGameServerMessage
{
	/**
	 * @param objectId
	 * @param shiftPressed
	 */
	public AttackRequest(BObject obj)
	{
		writeC(0x0a);
		writeD(obj.getObjectId());// Target object Identifier
		writeD(0x00); // not used in L2j
		writeD(0x00); // not used in L2j
		writeD(0x00); // not used in L2j
		writeC(0x00); // not used in L2j
	}
}
