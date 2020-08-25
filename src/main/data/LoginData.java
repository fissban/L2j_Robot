package main.data;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author fissban
 */
public class LoginData
{
	// File
	private static final String FILE = "./login/login.properties";
	// Configs
	public static String SERVER_IP;
	public static boolean SAVE_IP;

	public static String USER_ACCOUNT;
	public static String USER_PASS;
	public static boolean SAVE_USER_PASS;

	public LoginData()
	{
		//
	}

	public static void load()
	{
		try
		{
			final BProperties load = new BProperties(FILE);
			SERVER_IP = load.getProperty("ServerIp", "127.0.0.1");
			SAVE_IP = load.getProperty("SaveIp", "true").equals("true") ? true : false;
			USER_ACCOUNT = load.getProperty("UserAccount", "");
			USER_PASS = load.getProperty("UserPass", "");
			SAVE_USER_PASS = load.getProperty("SaveUserPass", "false").equals("true") ? true : false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void save()
	{
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE)))
		{
			pw.println("ServerIp=" + SERVER_IP);
			pw.println("SaveIp=" + (SAVE_IP ? "true" : "false"));

			pw.println("UserAccount=" + USER_ACCOUNT);
			pw.println("UserPass=" + USER_PASS);
			pw.println("SaveUserPass=" + (SAVE_USER_PASS ? "true" : "false"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static class BProperties extends Properties
	{
		private static final long serialVersionUID = 1L;

		public BProperties(String name) throws IOException
		{
			try (FileInputStream fis = new FileInputStream(name))
			{
				load(fis);
			}
		}

		@Override
		public void load(InputStream inStream) throws IOException
		{
			try (InputStreamReader isr = new InputStreamReader(inStream, Charset.defaultCharset()))
			{
				super.load(isr);
			}
			finally
			{
				inStream.close();
			}
		}
	}
}
