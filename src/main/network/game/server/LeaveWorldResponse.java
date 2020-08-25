package main.network.game.server;

import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class LeaveWorldResponse extends ServerNetwork
{
	public LeaveWorldResponse(byte[] data)
	{
		super(data);
		init();
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
