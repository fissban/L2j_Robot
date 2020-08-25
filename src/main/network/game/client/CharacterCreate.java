package main.network.game.client;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public final class CharacterCreate extends ClientToGameServerMessage
{

	public CharacterCreate(String name, int race, int sex, int classId)
	{
		writeC(0x0b); // opCode

		writeS(name);
		writeD(race);
		writeD(sex);
		writeD(classId);
		writeD(0x00); // int
		writeD(0x00); // str
		writeD(0x00); // con
		writeD(0x00); // men
		writeD(0x00); // dex
		writeD(0x00); // wit

		writeD(0x00); // hairStyle
		writeD(0x00); // hairColor
		writeD(0x00); // face
	}
}