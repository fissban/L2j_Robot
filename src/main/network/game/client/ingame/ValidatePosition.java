package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class ValidatePosition extends ClientToGameServerMessage
{
	public ValidatePosition()
	{
		writeC(0x48);
		writeD(getBot().getX());
		writeD(getBot().getY());
		writeD(getBot().getZ());
		writeD(0x00);//heading
		writeD(0x00);//vehicle id
	}

}
