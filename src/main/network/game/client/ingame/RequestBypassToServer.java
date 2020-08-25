package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestBypassToServer extends ClientToGameServerMessage
{
	public RequestBypassToServer(String text)
	{
		writeC(0x21);
		writeS(text);
	}
}
