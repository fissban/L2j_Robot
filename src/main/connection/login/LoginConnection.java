package main.connection.login;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.alee.laf.optionpane.WebOptionPane;

import gui.frames.login.Connect;
import main.connection.ServerConnection;
import main.enums.EChronicle;
import main.managers.ChronicleManager;
import main.model.ServerDataModel;
import main.model.SessionKeyModel;
import main.network.ClientNetwork;
import main.network.crypt.login.InitCrypt;
import main.network.crypt.login.LoginCrypt;
import main.network.login.LoginServerToClientCodes;
import main.network.login.client.RequestAuthGameGuard;
import main.network.login.client.RequestAuthLogin;
import main.network.login.client.RequestServerList;
import main.network.login.client.RequestServerLogin;
import main.network.login.server.GGAuth;
import main.network.login.server.Init;
import main.network.login.server.LoginFail;
import main.network.login.server.LoginOkResponse;
import main.network.login.server.PlayFail;
import main.network.login.server.PlayOkResponse;
import main.network.login.server.ServerListResponse;
import main.util.Util;

/**
 * Class responsible for managing the packages between client <-> server until the selection of "server"
 * @author fissban
 */
public class LoginConnection extends ServerConnection
{
	public byte[] blowfishKey = "_;v.]05-31!|+-%xT!^[$\00".getBytes();

	public int sessionId;
	/** The Public RSA key sent by LoginServer. */
	public RSAPublicKey publicKey;
	/** The session values. */
	public SessionKeyModel sessionKey = new SessionKeyModel(0, 0, 0, 0);
	/** Unknown gameguard packets */
	public int[] unkGG = new int[4];

	/** Player account */
	private String account;
	/** Player password */
	private String password;

	/** Selection server list */
	private Map<Integer, ServerDataModel> serverList = new LinkedHashMap<>();
	/** Server player selection */
	private int serverId = 1;

	/**
	 * Constructor. Does not connect until connect() method is called.
	 * @param serverAddress
	 * @param serverPort
	 * @throws IOException
	 */
	public LoginConnection(String serverIp) throws IOException
	{
		super(serverIp, 2106);
	}

	@Override
	public void connect() throws IOException
	{
		super.connect();
		// Read first packet
		if (isConnected())
		{
			readPacket();
		}
	}

	@Override
	public void readPacket()
	{
		try
		{
			byte[] data = new byte[0];
			while (data.length == 0 && state != LoginServerToClientCodes.PLAY_OK)
			{
				data = super.receive();

				if (data.length != 0)
				{
					// Decode packets
					if (state == -1)
					{
						if (ChronicleManager.get() == EChronicle.C4)
						{
							// In C4 the first package is not encrypted.
						}
						else
						{
							data = InitCrypt.decrypt(data);
						}
					}
					else
					{
						LoginCrypt.decrypt(data);
						LoginCrypt.verifyChecksum(data);
					}

					//System.out.println("login -> " + (data[0] & 0xFF));
					switch (data[0] & 0xFF) // opcode
					{
						case LoginServerToClientCodes.INIT:
						{
							new Init(this, data);
							break;
						}
						case LoginServerToClientCodes.GAME_GUARD_OK:
						{
							new GGAuth(data);
							break;
						}
						case LoginServerToClientCodes.LOGIN_OK:
						{
							new LoginOkResponse(this, data);
							break;
						}
						case LoginServerToClientCodes.SERVER_LIST:
						{
							ServerListResponse serverListResponse = new ServerListResponse(data);
							for (ServerDataModel serverData : serverListResponse.getServerList())
							{
								serverList.put(serverData.id, serverData);
							}
							break;
						}
						case LoginServerToClientCodes.PLAY_OK:
						{
							new PlayOkResponse(this, data);
							break;
						}
						case LoginServerToClientCodes.LOGIN_FAILED:
						{
							LoginFail fail = new LoginFail(data);
							int option = JOptionPane.CANCEL_OPTION;

							while (option == JOptionPane.CANCEL_OPTION)
							{
								option = JOptionPane.showConfirmDialog(getActualFrame(), fail.getReason(), "Login", WebOptionPane.ERROR_MESSAGE);

								if (option == JOptionPane.OK_OPTION)
								{
									// close actual frame
									getActualFrame().setVisible(false);
									getActualFrame().dispose();
									// open first frame
									Util.openLoginFrame(Connect.class);
									// init state
									state = -1;
									// close network connection
									if (isConnected())
									{
										getConn().close();
									}
									serverList.clear();
								}
							}

							break;
						}
						case LoginServerToClientCodes.PLAY_FAILED:
						{
							PlayFail fail = new PlayFail(data);
							WebOptionPane.showMessageDialog(getActualFrame(), fail.getReason(), "Login", WebOptionPane.ERROR_MESSAGE);
							break;
						}
						default:
						{
							System.out.println("read default " + (data[0] & 0xFF));
							break;
						}
					}

					state = data[0] & 0xFF; // Set new state
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
		try
		{
			switch (state)
			{
				case LoginServerToClientCodes.INIT:
				{
					sendPacket(new RequestAuthGameGuard(this));
					break;
				}
				case LoginServerToClientCodes.GAME_GUARD_OK:
				{
					sendPacket(new RequestAuthLogin(publicKey, account, password));
					break;
				}
				case LoginServerToClientCodes.LOGIN_OK:
				{
					sendPacket(new RequestServerList(this));
					break;
				}
				case LoginServerToClientCodes.SERVER_LIST:
				{
					sendPacket(new RequestServerLogin(this, serverId));
					break;
				}
				case LoginServerToClientCodes.PLAY_OK:
				{
					//
					break;
				}
				case LoginServerToClientCodes.PLAY_FAILED:
				{
					//
					return;
				}
				default:
				{
					break;
				}
			}

			readPacket();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns the session key.
	 * @return The session key.
	 */
	public SessionKeyModel getSessionKey()
	{
		return sessionKey;
	}

	public void setAccount(String param)
	{
		account = param;
	}

	public String getAccount()
	{
		return account;
	}

	public void setPassword(String param)
	{
		password = param;
	}

	public Map<Integer, ServerDataModel> getServerList()
	{
		return serverList;
	}

	public void setServerId(int value)
	{
		serverId = value;
	}

	public int getServerId()
	{
		return serverId;
	}

	/**
	 * Encrypts and sends a message
	 * @param message The plain message
	 */
	private void sendPacket(ClientNetwork packet) throws IOException
	{
		byte[] data = packet.getByteArray();

		// padding
		int size = data.length;
		size += 8 - size % 8;

		byte[] asd = new byte[size];
		System.arraycopy(data, 0, asd, 0, data.length);
		data = asd;

		LoginCrypt.appendChecksum(data);
		LoginCrypt.encrypt(data);
		super.send(data);
	}
}
