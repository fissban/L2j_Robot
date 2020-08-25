package main.managers;

import gui.frames.game.windows.ConsoleWindow;
import main.connection.bot.BotConnection;
import main.connection.login.LoginConnection;
import main.connection.server.GameConnection;

/**
 * @author fissban
 */
public class ConnectionManager
{
	// LOGIN -------------------------------------------------------------------------------------
	private static LoginConnection login = null;

	public static boolean initLogin(String serverIp)
	{
		try
		{
			login = new LoginConnection(serverIp);
			login.connect();
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error init login", e);
			return false;
		}

		return login.isConnected();
	}

	public static LoginConnection getLogin()
	{
		return login;
	}

	// GAME -----------------------------------------------------------------------------------------
	private static GameConnection game = null;

	public static boolean initGame()
	{
		try
		{
			game = new GameConnection(login.getServerList().get(login.getServerId()).ip.getHostAddress(), login.getSessionKey());
			game.connect();
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error init game", e);
			return false;
		}

		return game.isConnected();
	}

	public static GameConnection getGame()
	{
		return game;
	}

	// BOT ------------------------------------------------------------------------------------------

	private static BotConnection bot;

	public static boolean initBot()
	{
		try
		{
			// init thread manager
			ThreadManager.init();

			bot = new BotConnection(game.getConn());
			ThreadManager.execute(bot);
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error init bot", e);
		}

		return true;
	}

	public static BotConnection getBot()
	{
		return bot;
	}
}
