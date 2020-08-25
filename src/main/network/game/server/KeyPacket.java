package main.network.game.server;

import main.managers.ChronicleManager;
import main.network.ServerNetwork;
import main.network.crypt.game.GameCrypt;

/**
 * @author fissban
 */
public class KeyPacket extends ServerNetwork
{
	private byte[] key;

	public KeyPacket(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		switch (ChronicleManager.get())
		{
			case C4:
			{
				readC(); // protocol ok?
				key = readB(0x08); // 8
				break;
			}
			case C6:
			{
				readC(); // protocol ok?
				key = readB(0x10); // 16
				readD(); // This must be 0x01... but I don't know why
				readD(); // This must be 0x01... but I don't know why
				break;
			}
		}

	}

	@Override
	public void write()
	{
		// set crypt packets
		GameCrypt.setKey(key);
	}
}
