package main.managers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fissban
 */
public class SnifferManager
{
	// paquetes recibidos
	private static List<String> received = new ArrayList<>();
	// paquetes enviados
	private static List<String> sent = new ArrayList<>();

	public static void received(String packetName, byte[] data)
	{
		// falta detectar el tipo de paquete q es.
		received.add(packetName + "-" + byteArrayToHexString(data));

		// se remueve el ultimo paquete
		if (received.size() > 50)
		{
			received.remove(received.size() - 1);
		}
	}

	public static void sent(String packetName, byte[] data)
	{
		// falta detectar el tipo de paquete q es.
		sent.add(packetName + "-" + byteArrayToHexString(data));

		if (sent.size() > 50)
		{
			sent.remove(sent.size() - 1);
		}
	}

	private static String byteArrayToHexString(byte in[])
	{
		String rslt = "";
		String thes = "";
		for (byte element : in)
		{
			int wtrf = element;
			if (wtrf < 0)
			{
				wtrf = 256 + wtrf;
			}
			thes = Integer.toHexString(wtrf);
			if (thes.length() < 2)
			{
				thes = "0" + thes;
			}
			rslt += " " + thes;
		}
		return rslt.toUpperCase();
	}
}
