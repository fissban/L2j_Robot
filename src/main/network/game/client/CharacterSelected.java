package main.network.game.client;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class CharacterSelected extends ClientToGameServerMessage
{
	public CharacterSelected(int charSlot)
	{
		writeC(0x0D);
		writeD(charSlot);
		// What are these values?
		writeH(0x00);
		writeD(0x00);
		writeD(0x00);
		writeD(0x00);
	}
}
