package main.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import gui.frames.game.windows.ConsoleWindow;

/**
 * @author fissban
 */
public class ConnectionModel
{
	/**
	 * The socket.
	 */
	protected Socket connection;
	/**
	 * The input stream obtained from socket.
	 */
	protected InputStream in;
	/**
	 * The output stream obtained from socket.
	 */
	protected OutputStream out;

	/**
	 * Connects to the server.
	 * @throws IOException
	 */
	public ConnectionModel(String serverAddress, int serverPort)
	{
		try
		{
			if (connection != null && isConnected())
			{
				return;
			}
			connection = new Socket(serverAddress, serverPort);

			if (connection == null || !isConnected())
			{
				return;
			}

			in = new BufferedInputStream(connection.getInputStream());
			out = new BufferedOutputStream(connection.getOutputStream());
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error init connection", e);
		}
	}

	public void close()
	{
		try
		{
			connection.close();
			in.close();
			out.close();

			connection = null;
			in = null;
			out = null;
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error close connection", e);
		}
	}

	/**
	 * Sends a message to server.
	 *
	 * @param data The raw message.
	 * @throws IOException
	 */
	public void send(byte data[]) throws IOException
	{
		// Adds 2 bytes of size header.
		int length = data.length + 2;
		synchronized (out) // avoids tow threads writing in the mean time
		{
			out.write(length & 0xff);
			out.write(length >> 8 & 0xff);
			out.write(data);
			out.flush();
		}
	}

	/**
	 * Receives a message from socket. Waits until one message is available.
	 *
	 * @return The read message.
	 * @throws IOException
	 */
	public byte[] receive() throws IOException
	{
		int lengthLo = in.read();
		int lengthHi = in.read();
		int length = ((lengthHi * 256) + lengthLo) - 2;

		if (length <= 0)
		{
			return new byte[0];
		}

		byte[] data = new byte[length];

		int receivedBytes = in.read(data, 0, length);

		if (receivedBytes != length)
		{
			return new byte[0];
		}
		return data;
	}

	public boolean isConnected()
	{
		return connection != null && connection.isConnected();
	}
}
