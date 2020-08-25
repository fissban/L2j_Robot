package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class Appearing extends ClientToGameServerMessage
{
	public Appearing()
	{
		writeC(0x30);
	}
}
