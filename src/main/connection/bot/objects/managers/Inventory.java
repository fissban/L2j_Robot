package main.connection.bot.objects.managers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import main.connection.bot.objects.BItem;
import main.enums.EPaperdoll;
import main.model.PaperdollModel;

/**
 * @author fissban
 */
public class Inventory
{
	// Items in the inventory
	private volatile Map<Integer, BItem> items = new LinkedHashMap<>();
	// Equipped items and their positions
	private volatile Map<EPaperdoll, PaperdollModel> paperdoll = new HashMap<>();

	public Inventory()
	{
		// init equipped items
		for (EPaperdoll p : EPaperdoll.values())
		{
			paperdoll.put(p, new PaperdollModel());
		}
	}

	/**
	 * Add item
	 * @param item
	 */
	public void addItem(BItem item)
	{
		items.put(item.getObjectId(), item);
	}

	/**
	 * Remove item
	 * @param objectId
	 */
	public void removeItem(int objectId)
	{
		items.remove(objectId);
	}

	/**
	 * All Items.
	 * @return
	 */
	public Collection<BItem> getItems()
	{
		return items.values();
	}

	/**
	 * You get an item from your objectId.
	 * @param objectId
	 * @return
	 */
	public BItem getItem(int objectId)
	{
		if (items.containsKey(objectId))
		{
			return items.get(objectId);
		}

		return null;
	}

	/**
	 * Check if there is a certain item.
	 * @param objectId
	 * @return
	 */
	public BItem getItemById(int id)
	{
		for (BItem item : items.values())
		{
			if (item.getId() == id)
			{
				return item;
			}
		}
		return null;
	}

	/**
	 * Equipped items and their position
	 * @param position
	 * @return
	 */
	public PaperdollModel getPaperdoll(EPaperdoll position)
	{
		return paperdoll.get(position);
	}
}
