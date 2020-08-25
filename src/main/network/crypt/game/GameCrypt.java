package main.network.crypt.game;

import main.managers.ChronicleManager;

/**
 * @author KenM
 */
public class GameCrypt
{
	private static byte[] inKey;
	private static byte[] outKey;

	private static int sizePacket = 0;

	public static void setKey(byte[] key)
	{
		switch (ChronicleManager.get())
		{
			case C4:
				sizePacket = 8;
				break;

			case C6:
				sizePacket = 16;
				break;
		}

		inKey = new byte[sizePacket];
		outKey = new byte[sizePacket];

		System.arraycopy(key, 0, inKey, 0, sizePacket);
		System.arraycopy(key, 0, outKey, 0, sizePacket);
	}

	public static void decrypt(byte[] raw)
	{
		int temp = 0;
		int size = raw.length;

		for (int i = 0; i < size; i++)
		{
			int temp2 = raw[i] & 0xFF;
			raw[i] = (byte) (temp2 ^ inKey[i & (sizePacket - 1)] ^ temp);
			temp = temp2;
		}

		int pos = sizePacket - 8;

		int old = inKey[pos++] & 0xff;
		old |= inKey[pos++] << 8 & 0xff00;
		old |= inKey[pos++] << 0x10 & 0xff0000;
		old |= inKey[pos++] << 0x18 & 0xff000000;

		old += size;

		pos = sizePacket - 8;
		inKey[pos++] = (byte) (old & 0xff);
		inKey[pos++] = (byte) (old >> 0x08 & 0xff);
		inKey[pos++] = (byte) (old >> 0x10 & 0xff);
		inKey[pos++] = (byte) (old >> 0x18 & 0xff);
	}

	public static void encrypt(byte[] raw)
	{
		int temp = 0;
		int size = raw.length;

		for (int i = 0; i < size; i++)
		{
			int temp2 = raw[i] & 0xFF;
			temp = temp2 ^ outKey[i & (sizePacket - 1)] ^ temp;
			raw[i] = (byte) temp;
		}

		int pos = sizePacket - 8;
		int old = outKey[pos++] & 0xff;
		old |= outKey[pos++] << 8 & 0xff00;
		old |= outKey[pos++] << 0x10 & 0xff0000;
		old |= outKey[pos++] << 0x18 & 0xff000000;

		old += size;

		pos = sizePacket - 8;
		outKey[pos++] = (byte) (old & 0xff);
		outKey[pos++] = (byte) (old >> 0x08 & 0xff);
		outKey[pos++] = (byte) (old >> 0x10 & 0xff);
		outKey[pos++] = (byte) (old >> 0x18 & 0xff);
	}
}
