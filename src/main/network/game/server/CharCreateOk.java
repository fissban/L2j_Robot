package main.network.game.server;

import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class CharCreateOk extends ServerNetwork
{
	public CharCreateOk(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		readD(); // in L2J always 0x01
	}

	@Override
	public void write()
	{
		//
	}
}