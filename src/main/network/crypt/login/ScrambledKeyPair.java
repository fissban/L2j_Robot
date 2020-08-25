package main.network.crypt.login;

import java.math.BigInteger;

/**
 * @author fissban
 */
public class ScrambledKeyPair
{
	public static BigInteger unScrambleModulus(byte[] scrambledMod)
	{
		// step 1 : xor last $40 bytes with first $40 bytes
		for (int i = 0; i < 0x40; i++)
		{
			scrambledMod[0x40 + i] ^= scrambledMod[i];
		}
		// step 2 : xor bytes $0d-$10 with bytes $34-$38
		for (int i = 0; i <= 3; i++)
		{
			scrambledMod[0x0d + i] ^= scrambledMod[0x34 + i];
		}
		// step 3 : xor first $40 bytes with last $40 bytes
		for (int i = 0; i < 0x40; i++)
		{
			scrambledMod[i] ^= scrambledMod[0x40 + i];
		}
		// step 4 : $4d-$50 <-> $00-$04
		byte tmp = 0;
		for (int i = 0; i <= 3; i++)
		{
			tmp = scrambledMod[0x00 + i];
			scrambledMod[0x00 + i] = scrambledMod[0x4d + i];
			scrambledMod[0x4d + i] = tmp;
		}
		byte[] result = new byte[129];
		System.arraycopy(scrambledMod, 0, result, 1, 128);

		return new BigInteger(result);
	}
}