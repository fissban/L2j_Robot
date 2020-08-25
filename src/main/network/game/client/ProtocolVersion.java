package main.network.game.client;

import main.managers.ChronicleManager;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class ProtocolVersion extends ClientToGameServerMessage
{
	public ProtocolVersion()
	{
		writeC(0x00);
		switch (ChronicleManager.get())
		{
			case C4:
				writeD(660);
				break;
			case C6:
				writeD(740);
				break;
		}
	}
}
