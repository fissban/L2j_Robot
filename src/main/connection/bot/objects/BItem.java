package main.connection.bot.objects;

import main.data.ItemData;

/**
 * @author fissban
 */
public class BItem extends BObject
{
	private int id = 0;
	private int ownerObjectId = 0;
	private int count = 0;

	private int mana = 0;
	private int enchantLevel = 0;
	private boolean isEquiped = false;

	public BItem(int objectId)
	{
		super(objectId);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int itemId)
	{
		id = itemId;
	}

	@Override
	public String getName()
	{
		return ItemData.getName(id);
	}

	/**
	 * owner item drop
	 *
	 * @return
	 */
	public int getOwnerObjectId()
	{
		return ownerObjectId;
	}

	/**
	 * set owner item drop
	 *
	 * @param ownerObjectId
	 */
	public void setOwnerObjectId(int value)
	{
		ownerObjectId = value;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int value)
	{
		count = value;
	}

	public int getMana()
	{
		return mana;
	}

	public void setMana(int value)
	{
		mana = value;
	}

	public int getEnchantLevel()
	{
		return enchantLevel;
	}

	public void setEnchantLevel(int value)
	{
		enchantLevel = value;
	}

	public boolean isEquiped()
	{
		return isEquiped;
	}

	public void setEquiped(boolean value)
	{
		isEquiped = value;
	}

	/**
	 * A brief description of char is shown.
	 * Used for interface knownlist
	 *
	 * @return
	 */
	@Override
	public String getDescription()
	{
		return "<html><font color=1F3E65>[" + getObjectId() + "]</font>  " + getName() + "(" + getCount() + ")</html>";
	}
}
