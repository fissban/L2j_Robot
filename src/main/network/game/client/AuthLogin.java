package main.network.game.client;

import main.managers.ConnectionManager;
import main.model.SessionKeyModel;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class AuthLogin extends ClientToGameServerMessage
{
	public AuthLogin(SessionKeyModel sessionKey)
	{
		writeC(0x08);

		writeS(ConnectionManager.getLogin().getAccount());

		writeD(sessionKey.playKey2);
		writeD(sessionKey.playKey1);
		writeD(sessionKey.loginKey1);
		writeD(sessionKey.loginKey2);
	}
}
