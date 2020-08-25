package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestAutoSoulShot extends ClientToGameServerMessage
{
	/**
	 * @param itemId
	 * @param type   <br>
	 *               <li>true-> activate
	 *               <li>false-> remove
	 */
	public RequestAutoSoulShot(int itemId, boolean type)
	{
		writeC(0xd0);// opcode
		writeH(0x05);// opcode
		writeD(itemId);
		writeD(type ? 1 : 0);
	}
}
