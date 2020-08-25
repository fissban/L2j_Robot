package main.network.game.client.ingame;

import main.enums.ESay;
import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class Say2 extends ClientToGameServerMessage
{
	private ESay sayType = ESay.ALL;
	private String text;
	private String target = null;

	public Say2(String value)
	{
		text = value;

		if (text.startsWith("!"))
		{
			sayType = ESay.SHOUT;
			text.replaceFirst("!", "");
		}
		else if (text.startsWith("\""))
		{
			sayType = ESay.TELL;
			text.replaceFirst("\"", "");
			target = text.split(" ")[0];
			text = text.split(" ")[1];
		}
		else if (text.startsWith("#"))
		{
			sayType = ESay.PARTY;
			text.replaceFirst("#", "");
		}
		else if (text.startsWith("@"))
		{
			sayType = ESay.PARTY;
			text.replaceFirst("@", "");
		}
		else if (text.startsWith("+"))
		{
			sayType = ESay.TRADE;
			text.replaceFirst("+", "");
		}
		else if (text.startsWith("$"))
		{
			sayType = ESay.ALLIANCE;
			text.replaceFirst("$", "");
		}
		else if (text.startsWith("%"))
		{
			sayType = ESay.HERO_VOICE;
			text.replaceFirst("%", "");
		}

		writeC(0x38);
		writeS(text);
		writeD(sayType.ordinal());
		if (sayType == ESay.TELL)
		{
			writeS(target);
		}
	}
}
