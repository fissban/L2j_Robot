package main.enums;

/**
 * @author fissban
 */
public enum ERequestRestart
{
	// 1 -> To clanhall.
	// 2 -> To castle.
	// 3 -> To siege flag.
	// 4 -> Fixed 
	// 27-> Jail
	// teleport town

	CLAN_HALL(1),
	CASTLE(2),
	SIEGE_FLAG(3),
	FIXED(4),
	TOWN(5),
	JAIL(27);

	private int type;

	private ERequestRestart(int t)
	{
		type = t;
	}

	public int getType()
	{
		return type;
	}
}
