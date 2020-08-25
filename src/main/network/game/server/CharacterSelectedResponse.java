package main.network.game.server;

import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class CharacterSelectedResponse extends ServerNetwork
{
	public CharacterSelectedResponse(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		String name = readS();
		int charId = readD();
		String title = readS();
		int sessionId = readD();
		int clanId = readD();

		readD(); // This will be 0x00

		int sex = readD();
		int race = readD();
		int classId = readD();

		readD(); // This will be 0x01. Active?

		int x = readD();
		int y = readD();
		int z = readD();

		double currentHp = readF();
		double currentMp = readF();

		int sp = readD();
		long exp = readQ();
		int level = readD();

		int karma = readD(); // This will be 0x00
		int pkKills = readD(); // This will be 0x00

		int INT = readD();
		int STR = readD();
		int CON = readD();
		int MEN = readD();
		int DEX = readD();
		int WIT = readD();

		for (int i = 0; i < 30; i++)
		{
			readD();
		}

		readD();// c3 work
		readD();// c3 work

		int gameTime = readD();

		readD();

		readD();// classId

		readD();
		readD();
		readD();
		readD();
	}

	@Override
	public void write()
	{
		// 
	}
}
