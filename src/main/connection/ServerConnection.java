package main.connection;

import java.io.IOException;

import javax.swing.JFrame;

import main.model.ConnectionModel;

/**
 * @author fissban
 */
public abstract class ServerConnection
{
	private ConnectionModel conn;

	/** The server IP address. */
	protected String serverAddress;
	/** The server port. */
	protected int serverPort;

	protected int state = -1;

	/** Actual frame execute, used in show message error (LoginConnection & GameConnection) */
	private JFrame actualFrame = null;

	/**
	 * Constructor. Does not connect until connect() method is called.
	 * @param serverAddress: The server IP address.
	 * @param serverPort:    The server port.
	 */
	public ServerConnection(String address, int port)
	{
		if (address == null)
		{
			throw new IllegalArgumentException("serverAddress == null");
		}

		if (port <= 0)
		{
			throw new IllegalArgumentException("serverPort <= 0");
		}

		serverAddress = address;
		serverPort = port;
	}

	/**
	 * Connects to the server.
	 * @throws IOException
	 */
	public void connect() throws IOException
	{
		conn = new ConnectionModel(serverAddress, serverPort);
	}

	public abstract void readPacket();

	public abstract void writePacket();

	/**
	 * Returns the connection state of the socket.
	 * @return true if the socket successful connected to a server.
	 */
	public boolean isConnected()
	{
		synchronized (conn) // avoids tow threads writing in the mean time
		{
			return conn.isConnected();
		}
	}

	/**
	 * Disconnects from the server
	 * @throws IOException
	 */
	public void disconnect()
	{
		conn.close();
	}

	/**
	 * Sends a message to server.
	 * @param data: The raw message.
	 * @throws IOException
	 */
	public void send(byte data[]) throws IOException
	{
		conn.send(data);
	}

	/**
	 * Receives a message from socket. Waits until one message is available.
	 * @return The read message.
	 * @throws IOException
	 */
	public byte[] receive() throws IOException
	{
		return conn.receive();
	}

	/**
	 * @return
	 */
	public int getState()
	{
		return state;
	}

	/**
	 * @return
	 */
	public ConnectionModel getConn()
	{
		return conn;
	}

	/**
	 * @return the actualFrame
	 */
	public JFrame getActualFrame()
	{
		return actualFrame;
	}

	/**
	 * @param actualFrame the actualFrame to set
	 */
	public void setActualFrame(JFrame actualFrame)
	{
		this.actualFrame = actualFrame;
	}
}
