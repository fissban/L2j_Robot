package main.network.login.server;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPublicKeySpec;

import main.connection.login.LoginConnection;
import main.managers.ChronicleManager;
import main.network.IChronicle;
import main.network.ServerNetwork;
import main.network.crypt.login.LoginCrypt;
import main.network.crypt.login.ScrambledKeyPair;

/**
 * @author fissban
 */
public class Init extends ServerNetwork implements IChronicle
{
	LoginConnection login;

	public Init(LoginConnection login, byte[] data) throws Exception
	{
		super(data);

		this.login = login;

		switch (ChronicleManager.get())
		{
			case C4:
				c4();
				break;
			case C6:
				c6();
				break;
		}
	}

	@Override
	public void c4()
	{
		int sesionId = readD();// session id
		readD();// 0x0000c621 -> protocol revision

		byte[] publicKey = readB(0x80);

		BigInteger modulus = ScrambledKeyPair.unScrambleModulus(publicKey);

		try
		{
			login.publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, RSAKeyGenParameterSpec.F4));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		login.sessionId = sesionId;
		LoginCrypt.init("_;5.]94-31==-%xT!^[$\000".getBytes());
	}

	@Override
	public void c6()
	{

		int sesionId = readD();// session id
		readD();// 0x0000c621 -> protocol revision

		byte[] publicKey = readB(0x80);

		// unk GG related?
		login.unkGG[0] = readD();// 0x29DD954E
		login.unkGG[1] = readD();// 0x77C39CFC
		login.unkGG[2] = readD();// 0x97ADB620
		login.unkGG[3] = readD();// 0x07BDE0F7

		byte[] blowfishKey = readB(0x10);

		readC();// 0x00 -> null termination ;)

		BigInteger modulus = ScrambledKeyPair.unScrambleModulus(publicKey);

		try
		{
			login.publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, RSAKeyGenParameterSpec.F4));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		login.sessionId = sesionId;
		LoginCrypt.init(blowfishKey);
	}

	@Override
	public void read()
	{
		// 
	}

	@Override
	public void write()
	{
		// 
	}
}
