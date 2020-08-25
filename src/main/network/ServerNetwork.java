package main.network;

import gui.frames.game.windows.ConsoleWindow;
import main.managers.SnifferManager;

/**
 * @author fissban
 */
public abstract class ServerNetwork extends BaseNetwork
{
	public int offset;

	public ServerNetwork(byte[] c)
	{
		content = c;
		offset = 1; // skip opcode
		SnifferManager.received(getClass().getSimpleName(), content);
	}

	public void init()
	{
		try
		{
			// read packet
			read();
			// write packet
			write();
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error in packet [" + getClass().getSimpleName() + "]", e);
		}
	}

	public abstract void read();

	public abstract void write();

	public int readD()
	{
		int result = content[offset++] & 0xff;
		result |= content[offset++] << 8 & 0xff00;
		result |= content[offset++] << 0x10 & 0xff0000;
		result |= content[offset++] << 0x18 & 0xff000000;

		return result;
	}

	public long readQ()
	{
		long result = ((content[offset++] & 0xff) | ((long) (content[offset++] & 0xff) << 8) | ((long) (content[offset++] & 0xff) << 16) | ((long) (content[offset++] & 0xff) << 24) | ((long) (content[offset++] & 0xff) << 32) | ((long) (content[offset++] & 0xff) << 40) | ((long) (content[offset++] & 0xff) << 48) | ((long) (content[offset++] & 0xff) << 56));
		return result;
	}

	public int readC()
	{
		return content[offset++] & 0xff;
	}

	public int readH()
	{
		int result = content[offset++] & 0xff;
		result |= content[offset++] << 8 & 0xff00;

		return result;
	}

	public double readF()
	{
		long result = content[offset++] & 0xff;
		result |= content[offset++] << 8 & 0xff00;
		result |= content[offset++] << 0x10 & 0xff0000;
		result |= content[offset++] << 0x18 & 0xff000000;
		result |= content[offset++] << 0x20 & 0xff00000000l;
		result |= content[offset++] << 0x28 & 0xff0000000000l;
		result |= content[offset++] << 0x30 & 0xff000000000000l;
		result |= content[offset++] << 0x38 & 0xff00000000000000l;

		return Double.longBitsToDouble(result);
	}

	public String readS()
	{
		String result = null;

		try
		{
			result = new String(content, offset, content.length - offset, "UTF-16LE");
			result = result.substring(0, result.indexOf(0x00));
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error readS()", e);
		}
		offset += result.length() * 2 + 2;

		return result;
	}

	public byte[] readB(int length)
	{
		byte[] result = new byte[length];

		for (int i = 0; i < length; ++i)
		{
			result[i] = content[offset + i];
		}
		offset += length;

		return result;
	}
}
