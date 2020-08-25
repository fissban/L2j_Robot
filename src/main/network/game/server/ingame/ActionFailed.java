package main.network.game.server.ingame;

import main.enums.EIntention;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class ActionFailed extends ServerNetwork
{
	public ActionFailed(byte data[])
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
		// Force ai to perform new actions if activated
		if (getBot().getAi() != null)
		{
			getBot().getAi().setIntention(EIntention.NONE);
		}
	}
}
