package main.network.login.server;

import main.connection.login.LoginConnection;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class LoginOkResponse extends ServerNetwork
{
	public LoginOkResponse(LoginConnection login, byte[] data)
	{
		super(data);

		login.getSessionKey().loginKey1 = readD();
		login.getSessionKey().loginKey2 = readD();
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
