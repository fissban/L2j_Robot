package main.enums;

/**
 * @author fissban
 */
public enum ESay
{
	ALL("000000"),
	SHOUT("ff9900"), // !
	TELL("FF00FF"),
	PARTY("00FB00"), // #
	CLAN("7D75FF"), // @
	GM("000000"),
	PETITION_PLAYER("000000"),
	PETITION_GM("000000"),
	TRADE("FF04E8"), // +
	ALLIANCE("77FB99"), // $
	ANNOUNCEMENT("09ACD4"),
	BOAT("000000"),
	L2FRIEND("000000"), //000000
	MSNCHAT("000000"),
	PARTYMATCH_ROOM("000000"),
	PARTYROOM_COMMANDER("C8CA07"), // (Yellow)
	PARTYROOM_ALL("E80808"), // (Red)
	HERO_VOICE("408AFF"),
	CRITICAL_ANNOUNCE("09ACD4");

	String hexColor;

	ESay(String color)
	{
		hexColor = color;
	}

	public String getColor()
	{
		return hexColor;
	}
}
