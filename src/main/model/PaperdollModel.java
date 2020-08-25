package main.model;

import javax.swing.ImageIcon;

import main.connection.bot.objects.BItem;
import main.data.IconData;
import main.data.ItemData;
import main.managers.ObjectsManager;
import main.util.Img;

/**
 * @author fissban
 */
public class PaperdollModel
{
	private int itemId = 0;
	private int objectId = 0;

	public PaperdollModel()
	{
		//
	}

	public void set(int id, int objId)
	{
		// TODO no se de q tanto servira esto
		// set equiped new item
		BItem item = ObjectsManager.getBot().getInventory().getItem(objId);

		if (item != null)
		{
			item.setEquiped(true);

			// set not equiped old item
			if (objectId != 0)
			{
				ObjectsManager.getBot().getInventory().getItem(objectId).setEquiped(false);
			}
		}

		itemId = id;
		objectId = objId;
	}

	public void setItemId(int id)
	{
		itemId = id;
	}

	public void setObjectId(int value)
	{
		objectId = value;
	}

	public int getItemId()
	{
		return itemId;
	}

	public int getObjectId()
	{
		return objectId;
	}

	public String getName()
	{
		if (itemId > 0)
		{
			return ItemData.getName(itemId);
		}

		return "";
	}

	public int getEnchantLvl()
	{
		if (objectId > 0)
		{
			return ObjectsManager.getBot().getInventory().getItem(objectId).getEnchantLevel();
		}

		return 0;
	}

	/**
	 * Se obtiene una descripcion breve del item con formato html equipado en determinada posicion
	 * @param position
	 * @return
	 */
	public String getDescription()
	{
		if (itemId > 0 && objectId > 0)
		{
			return "<html><body><font color=FFFF00>" + getName() + "</font><br>Enchant Lvl: +" + getEnchantLvl() + "</body></html>";
		}

		return "";
	}

	/**
	 * Se obtiene el icono de un item equipado en determinada posicion
	 * @param position
	 * @return
	 */
	public ImageIcon getIcon()
	{
		if (itemId > 0)
		{
			String icon = IconData.getIconByItemId(itemId);

			if (icon == null)
			{
				return Img.create("NOIMAGE.png", 32, 32);
			}
			else
			{
				return Img.create("items/" + IconData.getIconByItemId(itemId) + ".png", 32, 32);
			}
		}
		return null;
	}
}
