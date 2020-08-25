package main.network.login.server;

import main.connection.login.LoginConnection;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class PlayOkResponse extends ServerNetwork
{
	public PlayOkResponse(LoginConnection login, byte[] data)
	{
		super(data);

		login.getSessionKey().playKey1 = readD();
		login.getSessionKey().playKey2 = readD();
	}

	@Override
	public void read()
	{
		// 
	}

	@Override
	public void write()
	{
		// 
	}
}
