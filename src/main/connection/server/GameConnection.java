package main.connection.server;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import main.connection.ServerConnection;
import main.managers.ObjectsManager;
import main.model.CharSelectInfoPackage;
import main.model.SessionKeyModel;
import main.network.crypt.game.GameCrypt;
import main.network.game.ClientToGameServerMessage;
import main.network.game.client.AuthLogin;
import main.network.game.client.CharacterSelected;
import main.network.game.client.EnterWorld;
import main.network.game.client.ProtocolVersion;
import main.network.game.server.CharCreateFail;
import main.network.game.server.CharCreateOk;
import main.network.game.server.CharSelectInfo;
import main.network.game.server.CharacterSelectedResponse;
import main.network.game.server.KeyPacket;

/**
 * Class responsible for managing packages between client <-> server before entering the "world"
 * @author fissban
 */
public class GameConnection extends ServerConnection
{
	private final static ReentrantLock lock = new ReentrantLock();

	/**
	 * The session values.
	 */
	private SessionKeyModel sessionKey;

	private boolean decryptPacket = false;

	private Map<Integer, CharSelectInfoPackage> charList = new ConcurrentHashMap<>();

	private String selectPlayerName = "";

	/**
	 * Constructor. Does not connect until connect() method is called.
	 * @param sessionKey SessionKey obtained from LoginServer.
	 * @throws IOException
	 */
	public GameConnection(String serverIp, SessionKeyModel sk) throws IOException
	{
		super(serverIp, 7777);

		sessionKey = sk;
	}

	@Override
	public void connect() throws IOException
	{
		super.connect();

		if (isConnected())
		{
			// Don't encrypt this message!
			super.send(new ProtocolVersion().getByteArray());

			// read and write -> KEY_PACKET
			readPacket();
			writePacket();

			// read -> PLAYER_AUTH_REQUEST
			// read -> CHAR_SELECT_INFO
			readPacket();
			// read -> CHAR_SELECT_INFO
			// readPacket();
		}
	}

	@Override
	public void readPacket()
	{
		try
		{
			byte[] data = new byte[0];
			while (data.length == 0)
			{
				data = super.receive();

				if (data.length != 0)
				{
					// if need decrypt packet
					if (decryptPacket)
					{
						GameCrypt.decrypt(data);
					}

					byte opCode = data[0];

					state = opCode & 0xFF;

					System.out.println("game -> " + (state));
					switch (state)
					{
						case 0x00:
							// next packet need decrypt
							decryptPacket = true;
							new KeyPacket(data);
							break;
						case 0x13:
							new CharSelectInfo(data);
							break;
						case 0x17:
							// CharTemplate
							break;
						case 0xf8://SSQInfo
							state = 0x15;
							break;
						case 0x15:
							new CharacterSelectedResponse(data);
							break;
						case 0x1a:
							new CharCreateFail(data);
							break;
						case 0x19:
							new CharCreateOk(data);
							break;
						default:
							System.out.println("read default " + state);
							break;
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void writePacket()
	{
		lock.lock();

		try
		{
			switch (state)
			{
				case 0x00:
					sendPacket(new AuthLogin(sessionKey));
					break;
				case 0x05: //PLAYER_AUTH_REQUEST
					//
					break;
				case 0x13:
					for (Entry<Integer, CharSelectInfoPackage> entry : charList.entrySet())
					{
						int slot = entry.getKey();
						CharSelectInfoPackage character = entry.getValue();

						if (character.getName().equals(selectPlayerName))
						{
							// init bot instance
							ObjectsManager.setBot(character.getObjectId());
							// send packet
							sendPacket(new CharacterSelected(slot));
							break;
						}
					}
					break;
				case 0x15:
					state = -1;
					sendPacket(new EnterWorld());
					break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			lock.unlock();
		}
	}

	public Map<Integer, CharSelectInfoPackage> getCharList()
	{
		return charList;
	}

	/**
	 * Encrypts and sends thought a socket a message.
	 *
	 * @param message Plain message to send.
	 */
	public void sendPacket(ClientToGameServerMessage packet) throws Exception
	{
		byte[] data = packet.getByteArray();

		GameCrypt.encrypt(data);
		super.send(data);
	}

	public String getSelectPlayerName()
	{
		return selectPlayerName;
	}

	public void setSelectPlayerName(String value)
	{
		selectPlayerName = value;
	}
}
