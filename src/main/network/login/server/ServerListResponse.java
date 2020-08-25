package main.network.login.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import main.model.ServerDataModel;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class ServerListResponse extends ServerNetwork
{
	private List<ServerDataModel> serverList;

	public ServerListResponse(byte[] data)
	{
		super(data);

		int serversNumber = readC();
		readC(); // loginServerNum -> unused

		serverList = new ArrayList<>(serversNumber);
		for (int i = 0; i < serversNumber; ++i)
		{
			int serverId = readC();
			byte rawIP[] = new byte[4];
			rawIP[0] = (byte) (readC() & 0xff);
			rawIP[1] = (byte) (readC() & 0xff);
			rawIP[2] = (byte) (readC() & 0xff);
			rawIP[3] = (byte) (readC() & 0xff);

			InetAddress i4 = null;
			try
			{
				i4 = InetAddress.getByAddress(rawIP);
			}
			catch (UnknownHostException e)
			{
				// do nothing
			}

			int serverPort = readD();
			int ageLimit = readC();
			boolean pvpAllowed = (readC() == 0) ? false : true;
			int currentPlayers = readH();
			int maxPlayers = readH();
			boolean serverUp = (readC() == 0) ? false : true;
			int stateBits = readD();
			boolean isTestServer = (stateBits & 0x04) == 0x04 ? true : false;
			boolean clock = (stateBits & 0x02) == 0x02 ? true : false;

			boolean brackets = (readC() == 0) ? false : true;

			serverList.add(new ServerDataModel(serverId, i4, serverPort, pvpAllowed, isTestServer, ageLimit, currentPlayers, maxPlayers, brackets, clock, serverUp));
		}
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

	public List<ServerDataModel> getServerList()
	{
		return serverList;
	}
}
