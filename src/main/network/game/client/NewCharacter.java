package main.network.game.client;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class NewCharacter extends ClientToGameServerMessage
{
	public NewCharacter()
	{
		writeC(0x0e);
	}
}
