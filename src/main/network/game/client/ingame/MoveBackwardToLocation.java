package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class MoveBackwardToLocation extends ClientToGameServerMessage
{
	public MoveBackwardToLocation(int oriX, int oriY, int oriZ, int destX, int destY, int destZ)
	{
		writeC(0x01);
		writeD(destX);
		writeD(destY);
		writeD(destZ);
		writeD(oriX);
		writeD(oriY);
		writeD(oriZ);
		writeD(0x01);//_moveMovement
	}
}
