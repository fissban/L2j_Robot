package main.network;

import java.io.ByteArrayOutputStream;

import main.connection.bot.objects.BPlayer;
import main.managers.ObjectsManager;

/**
 * @author fissban
 */
public abstract class BaseNetwork
{
	protected byte[] content;
	protected ByteArrayOutputStream bao;

	public BaseNetwork()
	{
		bao = new ByteArrayOutputStream();
	}

	public byte[] getByteArray()
	{
		if (content == null)
		{
			content = bao.toByteArray();
		}

		return content;
	}

	public int getLength()
	{
		return getByteArray().length;
	}

	public BPlayer getBot()
	{
		return ObjectsManager.getBot();
	}
}
