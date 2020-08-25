package main.network.game.server.ingame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gui.frames.game.windows.InventoryWindow;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.managers.Inventory;
import main.managers.ChronicleManager;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class InventoryUpdate extends ServerNetwork implements IChronicle
{
	private static final int UNCHANGED = 0;
	private static final int ADDED = 1;
	private static final int MODIFIED = 2;
	private static final int REMOVED = 3;

	private Map<Integer, List<BItem>> items;

	public InventoryUpdate(byte data[])
	{
		super(data);
		items = new HashMap<>();
		items.put(ADDED, new ArrayList<>());
		items.put(MODIFIED, new ArrayList<>());
		items.put(REMOVED, new ArrayList<>());

		init();
	}

	@Override
	public void read()
	{
		switch (ChronicleManager.get())
		{
			case C4:
				c4();
				break;
			case C6:
				c6();
				break;
		}

	}

	@Override
	public void write()
	{
		Inventory inv = getBot().getInventory();

		for (Entry<Integer, List<BItem>> entry : items.entrySet())
		{
			int modified = entry.getKey();
			List<BItem> item = entry.getValue();

			switch (modified)
			{
				case UNCHANGED:
				case ADDED:
				case MODIFIED:
					item.forEach(i -> inv.addItem(i));
					break;
				case REMOVED:
					item.forEach(i -> inv.removeItem(i.getObjectId()));
					break;
			}
		}

		InventoryWindow.getInstance().updateEquipmentItems();
		InventoryWindow.getInstance().updateInventory();
	}

	@Override
	public void c4()
	{
		int size = readH();

		for (int i = 0; i < size; i++)
		{
			int modified = readH();
			readH();//type1
			int objectId = readD();
			int id = readD();
			int count = readD();

			readH();//type2
			readH();//customType1
			boolean isEquiped = readH() == 0 ? false : true;
			readD();//bodyPart
			int enchantLevel = readH();
			readH();//customType2
			//int augmentBoni = readD();
			//int mana = readD();

			BItem im = new BItem(objectId);
			im.setId(id);
			im.setCount(count);
			im.setEquiped(isEquiped);
			im.setEnchantLevel(enchantLevel);
			//im.setMana(mana);

			items.get(modified).add(im);
		}
	}

	@Override
	public void c6()
	{
		int size = readH();

		for (int i = 0; i < size; i++)
		{
			int modified = readH();
			readH();//type1
			int objectId = readD();
			int id = readD();
			int count = readD();

			readH();//type2
			readH();//customType1
			boolean isEquiped = readH() == 0 ? false : true;
			readD();//bodyPart
			int enchantLevel = readH();
			readH();//customType2
			readD();//augmentBoni
			int mana = readD();

			BItem im = new BItem(objectId);
			im.setId(id);
			im.setCount(count);
			im.setEquiped(isEquiped);
			im.setEnchantLevel(enchantLevel);
			im.setMana(mana);

			items.get(modified).add(im);
		}
	}
}
