package main.network.login.client;

import java.security.PublicKey;

import javax.crypto.Cipher;

import main.managers.ChronicleManager;
import main.network.ClientNetwork;
import main.network.IChronicle;
import main.network.login.LoginClientToServerCodes;

/**
 * @author fissban
 */
public class RequestAuthLogin extends ClientNetwork implements IChronicle
{
	private PublicKey publicKey;
	private String account;
	private String password;

	public RequestAuthLogin(PublicKey publicKey, String account, String password)
	{
		this.publicKey = publicKey;
		this.account = account;
		this.password = password;

		switch (ChronicleManager.get())
		{
			case C4:
			{
				c4();
				break;
			}
			case C6:
			{
				c6();
				break;
			}
		}
	}

	@Override
	public void c4()
	{
		byte userAndPass[] = new byte[0x80];

		System.arraycopy(account.getBytes(), 0, userAndPass, 0x62, Math.min(14, account.length()));
		System.arraycopy(password.getBytes(), 0, userAndPass, 0x70, Math.min(16, password.length()));

		try
		{
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/nopadding");
			rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			userAndPass = rsaCipher.doFinal(userAndPass, 0x00, 0x80);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		writeC(LoginClientToServerCodes.REQUEST_AUTH_LOGIN); // opcode
		writeB(userAndPass);
	}

	@Override
	public void c6()
	{
		byte userAndPass[] = new byte[0x80];

		System.arraycopy(account.getBytes(), 0, userAndPass, 0x5E, Math.min(14, account.length()));
		System.arraycopy(password.getBytes(), 0, userAndPass, 0x6C, Math.min(16, password.length()));
		userAndPass[92] = 0x24; // WTF!
		try
		{
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/nopadding");
			rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
			userAndPass = rsaCipher.doFinal(userAndPass, 0x00, 0x80);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		writeC(LoginClientToServerCodes.REQUEST_AUTH_LOGIN); // opcode
		writeB(userAndPass);
	}
}
