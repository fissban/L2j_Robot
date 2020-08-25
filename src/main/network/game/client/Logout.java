package main.network.game.client;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class Logout extends ClientToGameServerMessage
{
	public static final Logout STATIC_PACKET = new Logout();

	public Logout()
	{
		writeC(0x09);
	}
}
