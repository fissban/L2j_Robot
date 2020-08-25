package main.network.game.client;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class EnterWorld extends ClientToGameServerMessage
{
	public EnterWorld()
	{
		writeC(0x03);
	}
}
