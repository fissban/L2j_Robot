package main.network.game.server.ingame;

import gui.frames.game.windows.HtmlWindow;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class NpcHtmlMessage extends ServerNetwork
{
	private String html;

	public NpcHtmlMessage(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		readD();//npcObjId
		html = readS();
		readD();//itemId
	}

	@Override
	public void write()
	{
		HtmlWindow.getInstance().generateHtml(html);
	}
}
