package main.network.game.server;

import main.managers.ConnectionManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class PlayerAuthRequest extends ServerNetwork
{
	int pKey1;
	int pKey2;
	int lKey1;
	int lKey2;

	public PlayerAuthRequest(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		readS();// account

		pKey1 = readD();
		pKey2 = readD();
		lKey1 = readD();
		lKey2 = readD();
	}

	@Override
	public void write()
	{
		ConnectionManager.getLogin().getSessionKey().playKey1 = pKey1;
		ConnectionManager.getLogin().getSessionKey().playKey2 = pKey2;
		ConnectionManager.getLogin().getSessionKey().loginKey1 = lKey1;
		ConnectionManager.getLogin().getSessionKey().loginKey2 = lKey2;
	}
}
