package main.network.crypt.login;

import java.io.IOException;

import main.managers.ChronicleManager;

/**
 * @author fissban
 */
public class InitCrypt
{
	private static final byte[] BLOWFISH_KEY =
	{
			(byte) 0x6b, (byte) 0x60, (byte) 0xcb, (byte) 0x5b, (byte) 0x82, (byte) 0xce, (byte) 0x90, (byte) 0xb1, (byte) 0xcc, (byte) 0x2b, (byte) 0x6c, (byte) 0x55, (byte) 0x6c, (byte) 0x6c, (byte) 0x6c, (byte) 0x6c
	};

	private static int SIZE = 160;

	public InitCrypt()
	{
		//
	}

	public static byte[] decrypt(byte[] raw) throws IOException
	{
		switch (ChronicleManager.get())
		{
			case C4:
				SIZE = 149;
				break;
			case C6:
				SIZE = 180;
				break;
		}

		decrypts(raw);
		return decode(raw);
	}

	private static void decrypts(byte[] raw) throws IOException
	{
		BlowfishEngine decrypts = new BlowfishEngine();
		decrypts.init(false, BLOWFISH_KEY);

		int size = SIZE + 4;
		byte[] result = new byte[size];
		int count = size / 8;

		for (int i = 0; i < count; i++)
		{
			decrypts.processBlock(raw, i * 8, result, i * 8);
		}
		System.arraycopy(result, 0, raw, 0, size);
	}

	private static byte[] decode(byte[] raw)
	{
		int key = byteIntR(raw, SIZE - 4);

		int bloques = (SIZE / 4) - 1;
		int[] r = new int[bloques];
		for (int t = bloques - 1; t > 0; t--)
		{
			int p = byteIntR(raw, (4 * t)) ^ key;
			r[bloques - t - 1] = p;
			key = back(back(key) - back(p));
		}
		r[bloques - 1] = byteIntR(raw, 0);
		return intByte(r);
	}

	private static int byteIntR(byte[] ko, int off)
	{
		int edx = (ko[off + 3] & 0xFF);
		edx |= (ko[off + 2] & 0xFF) << 8;
		edx |= (ko[off + 1] & 0xFF) << 16;
		edx |= (ko[off] & 0xFF) << 24;
		return edx;
	}

	private static int back(int edx)
	{
		byte[] uy = new byte[4];
		uy[0] = (byte) (edx & 0xFF);
		uy[1] = (byte) (edx >> 8 & 0xFF);
		uy[2] = (byte) (edx >> 16 & 0xFF);
		uy[3] = (byte) (edx >> 24 & 0xFF);
		return byteIntR(uy, 0);
	}

	private static byte[] intByte(int[] ji)
	{
		byte[] jo = new byte[ji.length * 4];
		for (int g = 0; g < ji.length; g++)
		{
			jo[(g * 4)] = (byte) (ji[g] & 0xFF);
			jo[(g * 4) + 1] = (byte) (ji[g] >> 8 & 0xFF);
			jo[(g * 4) + 2] = (byte) (ji[g] >> 16 & 0xFF);
			jo[(g * 4) + 3] = (byte) (ji[g] >> 24 & 0xFF);
		}
		byte[] ja = new byte[jo.length];
		for (int g = 0; g < jo.length; g++)
		{
			ja[g] = jo[jo.length - g - 1];
		}
		return ja;
	}
}
