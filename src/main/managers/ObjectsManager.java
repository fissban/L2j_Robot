package main.managers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.BObject;
import main.connection.bot.objects.BPlayer;
import main.connection.bot.objects.BSummon;

/**
 * @author fissban
 */
public class ObjectsManager
{
	private static BPlayer bot = null;
	private static BSummon summon = null;

	private static Map<Integer, BObject> objects = new LinkedHashMap<>();

	/**
	 * Get all BObjects from, memory
	 * @return
	 */
	public static synchronized Map<Integer, BObject> getAll()
	{
		return objects;
	}

	/**
	 * Get all BObjects from memory by type
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static synchronized <A> List<A> getAll(Class<A> type)
	{
		return (List<A>) objects.values().stream().filter(c -> c != null && !c.isDead() && type.isAssignableFrom(c.getClass())).collect(Collectors.toList());
	}

	/**
	 * Get BObjects from memory
	 * @param objectId
	 * @return
	 */
	public static synchronized BObject get(int objectId)
	{
		return objects.getOrDefault(objectId, null);
	}

	/**
	 * Remove BObjects from memory and interface<br>
	 * Remove BObjects from Bot.removeKnownList()<br>
	 * Remove BObjects from Bot.removeObjectMapLive()<br>
	 *
	 * @param objectId
	 */
	public static synchronized void remove(int objectId, boolean die)
	{
		BObject object = get(objectId);
		if (object != null)
		{
			if (die)
			{
				if (object.equals(bot.getAi().getTarget()))
				{
					if (object instanceof BItem)
					{
						GameFrame.getInstance().getActions().addActionBot("Pickup item " + object.getName());
					}
					else
					{
						bot.getAi().notifyDieTarget();
					}
				}
				// Remove from interface
				GameFrame.getInstance().getKnown().removeKnown(objectId);
				GameFrame.getInstance().getLiveMap().removeObject(objectId);
			}

			objects.remove(objectId);
		}
	}

	/**
	 * Add BObjects in memory
	 * @param actor
	 */
	public static synchronized void add(BObject actor)
	{
		objects.put(actor.getObjectId(), actor);
	}

	/**
	 * Get actual bot
	 * @return
	 */
	public static BPlayer getBot()
	{
		return bot;
	}

	/**
	 * Create new bot
	 * @param objectId
	 */
	public static void setBot(int objectId)
	{
		bot = new BPlayer(objectId);
	}

	public static BSummon getSummon()
	{
		return summon;
	}

	public static void setSummon(int objectId)
	{
		summon = new BSummon(objectId);
	}

	public static void removeSummon()
	{
		summon = null;
	}
}
