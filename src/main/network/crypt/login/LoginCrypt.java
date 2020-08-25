package main.network.crypt.login;

import java.io.IOException;

/**
 * @author fissban
 */
public class LoginCrypt
{
	private static BlowfishEngine crypt;
	private static BlowfishEngine decrypt;

	public LoginCrypt()
	{
		//
	}

	public static void init(byte[] keybytes)
	{
		crypt = new BlowfishEngine();
		crypt.init(true, keybytes);
		decrypt = new BlowfishEngine();
		decrypt.init(false, keybytes);
	}

	public static void appendChecksum(byte[] raw)
	{
		long chksum = 0;
		int count = raw.length - 4;
		long ecx;
		int i;

		for (i = 0; i < count; i += 4)
		{
			ecx = raw[i] & 0xff;
			ecx |= raw[i + 1] << 8 & 0xff00;
			ecx |= raw[i + 2] << 0x10 & 0xff0000;
			ecx |= raw[i + 3] << 0x18 & 0xff000000;

			chksum ^= ecx;
		}

		ecx = raw[i] & 0xff;
		ecx |= raw[i + 1] << 8 & 0xff00;
		ecx |= raw[i + 2] << 0x10 & 0xff0000;
		ecx |= raw[i + 3] << 0x18 & 0xff000000;

		raw[i] = (byte) (chksum & 0xff);
		raw[i + 1] = (byte) (chksum >> 0x08 & 0xff);
		raw[i + 2] = (byte) (chksum >> 0x10 & 0xff);
		raw[i + 3] = (byte) (chksum >> 0x18 & 0xff);
	}

	public static boolean verifyChecksum(byte[] raw)
	{
		int size = raw.length;
		// check if size is multiple of 4 and if there is more then only the checksum
		if ((size & 3) != 0 || size <= 4)
		{
			return false;
		}

		long chksum = 0;
		int count = size - 4;
		long check = -1;
		int i;

		for (i = 0; i < count; i += 4)
		{
			check = raw[i] & 0xff;
			check |= raw[i + 1] << 8 & 0xff00;
			check |= raw[i + 2] << 0x10 & 0xff0000;
			check |= raw[i + 3] << 0x18 & 0xff000000;

			chksum ^= check;
		}

		check = raw[i] & 0xff;
		check |= raw[i + 1] << 8 & 0xff00;
		check |= raw[i + 2] << 0x10 & 0xff0000;
		check |= raw[i + 3] << 0x18 & 0xff000000;

		return check == chksum;
	}

	public static void decrypt(byte[] raw) throws IOException
	{
		byte[] result = new byte[raw.length];
		int count = raw.length / 8;

		for (int i = 0; i < count; i++)
		{
			decrypt.processBlock(raw, i * 8, result, i * 8);
		}
		System.arraycopy(result, 0, raw, 0, raw.length);
	}

	public static void encrypt(byte[] raw) throws IOException
	{
		byte[] result = new byte[raw.length];
		int count = raw.length / 8;

		for (int i = 0; i < count; i++)
		{
			crypt.processBlock(raw, i * 8, result, i * 8);
		}
		System.arraycopy(result, 0, raw, 0, raw.length);
	}
}