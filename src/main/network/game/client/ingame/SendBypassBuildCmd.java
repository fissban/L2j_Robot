package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class SendBypassBuildCmd extends ClientToGameServerMessage
{
	public SendBypassBuildCmd(String value)
	{
		writeC(0x5b);
		writeS(value.replace("//", ""));
	}
}
