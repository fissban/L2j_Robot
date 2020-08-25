package main.network.game;

import main.network.ClientNetwork;

/**
 * @author fissban
 */
public abstract class ClientToGameServerMessage extends ClientNetwork
{
	public ClientToGameServerMessage()
	{
		super();
	}

	@Override
	public byte[] getByteArray()
	{
		//SnifferManager.sent(getClass().getSimpleName(), bao.toByteArray());
		return bao.toByteArray();
	}
}
