package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class ServerClose extends ServerNetwork
{
	public ServerClose(byte[] data)
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
		GameFrame.getInstance().clientClose("Close");
	}
}
