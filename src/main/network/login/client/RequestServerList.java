package main.network.login.client;

import main.connection.login.LoginConnection;
import main.network.ClientNetwork;
import main.network.login.LoginClientToServerCodes;

/**
 * @author fissban
 */
public class RequestServerList extends ClientNetwork
{
	public RequestServerList(LoginConnection login)
	{
		writeC(LoginClientToServerCodes.REQUEST_SERVER_LIST);
		writeD(login.getSessionKey().loginKey1);
		writeD(login.getSessionKey().loginKey2);
	}
}
