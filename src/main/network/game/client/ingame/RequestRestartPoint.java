package main.network.game.client.ingame;

import main.enums.ERequestRestart;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestRestartPoint extends ClientToGameServerMessage
{
	public RequestRestartPoint(ERequestRestart type)
	{
		writeC(0x6d);
		writeD(type.getType());
	}
}
