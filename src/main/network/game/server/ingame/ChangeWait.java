package main.network.game.server.ingame;

import main.connection.bot.objects.BPlayer;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class ChangeWait extends ServerNetwork
{
	public enum ChangeWaitType
	{
		SITTING,
		STANDING,
		START_FAKEDEATH,
		STOP_FAKEDEATH,
	}

	private int playerObjectId;
	private ChangeWaitType waitType;

	public ChangeWait(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		playerObjectId = readD();
		waitType = ChangeWaitType.values()[readD()];

		readD();//x
		readD();//y
		readD();//z
	}

	@Override
	public void write()
	{
		BPlayer p = null;

		if (getBot().getObjectId() == playerObjectId)
		{
			p = getBot();
		}
		else
		{
			// only Bplayer can sit or use fakedeath
			p = (BPlayer) ObjectsManager.get(playerObjectId);
		}
		switch (waitType)
		{
			case SITTING:
				p.setSiting(true);
				break;
			case STANDING:
				p.setSiting(false);
				break;
			case START_FAKEDEATH:
				break;
			case STOP_FAKEDEATH:
				break;
			default:
				break;

		}
	}
}
