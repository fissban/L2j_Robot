package main.model;

import java.net.InetAddress;

/**
 * @author fissban
 */
public class ServerDataModel
{
	public InetAddress ip;
	public int port;
	public boolean pvp;
	public int currentPlayers;
	public int maxPlayers;
	public int ageLimit;
	public boolean testServer;
	public boolean brackets;
	public boolean clock;
	public boolean isOnline;
	public int id;

	/**
	 * @param serverId
	 * @param ip
	 * @param port
	 * @param pvp
	 * @param testServer
	 * @param ageLimit
	 * @param currentPlayers
	 * @param pMaxPlayers
	 * @param brackets
	 * @param clock
	 * @param isOnline
	 */
	public ServerDataModel(int serverId, InetAddress ip, int port, boolean pvp, boolean testServer, int ageLimit, int currentPlayers, int pMaxPlayers, boolean brackets, boolean clock, boolean isOnline)
	{
		this.ip = ip;
		this.port = port;
		this.pvp = pvp;
		this.testServer = testServer;
		this.ageLimit = ageLimit;
		this.currentPlayers = currentPlayers;
		this.maxPlayers = pMaxPlayers;
		this.brackets = brackets;
		this.clock = clock;
		this.isOnline = isOnline;
		this.id = serverId;
	}

	@Override
	public String toString()
	{
		StringBuffer string = new StringBuffer();

		string.append(id + "                                                     ");
		string.append("CurrentPlayers: " + currentPlayers + "                     ");
		string.append("Online: " + isOnline + ".");

		return string.toString();
	}
}
