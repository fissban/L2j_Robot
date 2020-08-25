package main.network.game.server.ingame;

import java.util.ArrayList;
import java.util.List;

import gui.frames.game.windows.InventoryWindow;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.managers.Inventory;
import main.managers.ChronicleManager;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class ItemList extends ServerNetwork implements IChronicle
{
	private List<BItem> items;

	public ItemList(byte data[])
	{
		super(data);
		items = new ArrayList<>();
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
	public void c4()
	{
		readH(); // showWindow
		int size = readH();

		for (int i = 0; i < size; i++)
		{
			int type1 = readH();
			int objectId = readD();
			int id = readD();
			int count = readD();

			int type2 = readH();
			int customType1 = readH();
			boolean isEquiped = readH() == 0 ? false : true;
			int bodyPart = readD();
			int enchantLevel = readH();//
			int customType2 = readH();

			BItem im = new BItem(objectId);
			im.setId(id);
			im.setCount(count);
			im.setEquiped(isEquiped);
			im.setEnchantLevel(enchantLevel);
			im.setMana(0);

			items.add(im);
		}
	}

	@Override
	public void c6()
	{
		readH(); // showWindow
		int size = readH();

		for (int i = 0; i < size; i++)
		{
			int type1 = readH();
			int objectId = readD();
			int id = readD();
			int count = readD();

			int type2 = readH();
			int customType1 = readH();
			boolean isEquiped = readH() == 0 ? false : true;
			int bodyPart = readD();
			int enchantLevel = readH();//
			int customType2 = readH();
			int augmentBoni = readD();
			int mana = readD();//

			BItem im = new BItem(objectId);
			im.setId(id);
			im.setCount(count);
			im.setEquiped(isEquiped);
			im.setEnchantLevel(enchantLevel);
			im.setMana(mana);

			items.add(im);
		}
	}

	@Override
	public void write()
	{
		Inventory inv = getBot().getInventory();

		inv.getItems().clear();

		items.forEach(i -> inv.addItem(i));

		InventoryWindow.getInstance().updateEquipmentItems();
		InventoryWindow.getInstance().updateInventory();
	}
}
