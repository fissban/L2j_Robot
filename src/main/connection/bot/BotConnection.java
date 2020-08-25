package main.connection.bot;

import gui.frames.game.windows.ConsoleWindow;
import main.model.ConnectionModel;
import main.network.crypt.game.GameCrypt;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class BotConnection implements Runnable
{
	/** The Connection */
	private ConnectionModel conn;

	/**
	 * Constructor
	 * @param value
	 */
	public BotConnection(ConnectionModel value)
	{
		conn = value;
	}

	@Override
	public void run()
	{
		// read packets
		while (conn.isConnected())
		{
			try
			{
				byte[] data = conn.receive();

				if (data.length != 0)
				{
					// decrypt data
					GameCrypt.decrypt(data);
					// read packet 
					ServerPacketHandler.proceed(data);
				}
			}
			catch (Exception e)
			{
				ConsoleWindow.getInstance().print("Error read packet", e);
			}
		}
	}

	public void sendPacket(ClientToGameServerMessage packet)
	{
		try
		{
			byte[] data = packet.getByteArray();

			GameCrypt.encrypt(data);
			conn.send(data);
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error send packet", e);
		}
	}

	public ConnectionModel getConn()
	{
		return conn;
	}
}
