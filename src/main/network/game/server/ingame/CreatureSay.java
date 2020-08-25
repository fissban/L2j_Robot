package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.enums.ESay;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class CreatureSay extends ServerNetwork
{
	private int objectId;
	private ESay textType;
	private String charName;
	private String text;

	public CreatureSay(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		try
		{
			objectId = readD();
			textType = ESay.values()[readD()];
			charName = readS();
			text = readS();
		}
		catch (Exception e)
		{
			// TODO mejorar este paquete.....eh visto valores que me descolocaron xD
			textType = ESay.values()[0];
			charName = "null";
			text = "null";
			e.printStackTrace();
		}
	}

	@Override
	public void write()
	{
		String type = textType.name().replace("_", " ");

		GameFrame.getInstance().getChat().addChat("<font color=" + textType.getColor() + "><b>[" + type + "]</b> " + charName + ": " + text.substring(0, text.length()) + "</font>");
	}
}
