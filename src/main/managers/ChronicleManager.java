package main.managers;

import main.enums.EChronicle;

/**
 * @author fissban
 */
public class ChronicleManager
{
	private static EChronicle chronicle;

	public ChronicleManager()
	{
		// 
	}

	public static void set(EChronicle echronicle)
	{
		chronicle = echronicle;
	}

	public static EChronicle get()
	{
		return chronicle;
	}
}
