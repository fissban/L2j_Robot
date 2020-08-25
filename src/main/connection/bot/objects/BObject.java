package main.connection.bot.objects;

/**
 * @author fissban
 */
public class BObject
{
	private int objectId;
	private String name = "NoName";
	private int x;
	private int y;
	private int z;

	public BObject(int value)
	{
		objectId = value;
	}

	public int getObjectId()
	{
		return objectId;
	}

	public void setObjectId(int value)
	{
		objectId = value;
	}

	/**
	 * Get object name
	 *
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set object Name
	 *
	 * @param name
	 */
	public void setName(String value)
	{
		name = value;
	}

	public void setXYZ(int posX, int posY, int posZ)
	{
		x = posX;
		y = posY;
		z = posZ;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getZ()
	{
		return z;
	}

	public boolean isDead()
	{
		return false;
	}

	/**
	 * A brief description of char is shown.
	 * Used for interface knownlist
	 *
	 * @return
	 */
	public String getDescription()
	{
		return "[" + objectId + "]";
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof BObject)
		{
			if (((BObject) obj).getObjectId() == objectId)
			{
				return true;
			}
		}

		return false;
	}
}
