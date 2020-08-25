package main.network.login.server;

import main.managers.ConnectionManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class GGAuth extends ServerNetwork
{

	public GGAuth(byte[] data)
	{
		super(data);
	}

	@Override
	public void read()
	{
		ConnectionManager.getLogin().sessionId = readD();
		readD(); // 0x00
		readD(); // 0x00
		readD(); // 0x00
		readD(); // 0x00

	}

	@Override
	public void write()
	{

	}
}
