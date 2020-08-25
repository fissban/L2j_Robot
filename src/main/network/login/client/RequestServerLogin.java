package main.network.login.client;

import main.connection.login.LoginConnection;
import main.network.ClientNetwork;
import main.network.login.LoginClientToServerCodes;

/**
 * @author fissban
 */
public class RequestServerLogin extends ClientNetwork
{
	public RequestServerLogin(LoginConnection login, int serverId)
	{
		writeC(LoginClientToServerCodes.REQUEST_SERVER_LOGIN);
		writeD(login.getSessionKey().loginKey1);
		writeD(login.getSessionKey().loginKey2);
		writeC(serverId);
	}
}
