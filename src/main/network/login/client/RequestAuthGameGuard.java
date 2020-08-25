package main.network.login.client;

import main.connection.login.LoginConnection;
import main.network.ClientNetwork;
import main.network.login.LoginClientToServerCodes;

/**
 * @author fissban
 */
public class RequestAuthGameGuard extends ClientNetwork
{
	public RequestAuthGameGuard(LoginConnection login)
	{
		writeC(LoginClientToServerCodes.REQUEST_AUTH_GAMEGUARD); // opcode
		writeD(login.sessionId);
		writeD(login.unkGG[0]);
		writeD(login.unkGG[1]);
		writeD(login.unkGG[2]);
		writeD(login.unkGG[3]);
	}
}
