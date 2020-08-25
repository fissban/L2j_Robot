package main.model;

/**
 * @author fissban
 */
public class CharSelectInfoPackage
{
	private String name;
	private int objectId = 0;
	private int charId = 0;
	private long exp = 0;
	private int sp = 0;
	private int clanId = 0;
	private int race = 0;
	private int classId = 0;
	private int baseClassId = 0;
	private int deleteTimer = 0;
	private long lastAccess = 0L;
	private int face = 0;
	private int hairStyle = 0;
	private int hairColor = 0;
	private int sex = 0;
	private int level = 1;
	private int maxHp = 0;
	private int currentHp = 0;
	private int maxMp = 0;
	private int currentMp = 0;

	public CharSelectInfoPackage()
	{
		//
	}

	public int getObjectId()
	{
		return objectId;
	}

	public void setObjectId(int value)
	{
		objectId = value;
	}

	public int getCharId()
	{
		return charId;
	}

	public void setCharId(int value)
	{
		charId = value;
	}

	public int getClanId()
	{
		return clanId;
	}

	public void setClanId(int value)
	{
		clanId = value;
	}

	public int getClassId()
	{
		return classId;
	}

	public int getBaseClassId()
	{
		return baseClassId;
	}

	public void setClassId(int value)
	{
		classId = value;
	}

	public void setBaseClassId(int value)
	{
		baseClassId = value;
	}

	public int getCurrentHp()
	{
		return currentHp;
	}

	public void setCurrentHp(int value)
	{
		currentHp = value;
	}

	public int getCurrentMp()
	{
		return currentMp;
	}

	public void setCurrentMp(int value)
	{
		currentMp = value;
	}

	public int getDeleteTimer()
	{
		return deleteTimer;
	}

	public void setDeleteTimer(int value)
	{
		deleteTimer = value;
	}

	public long getLastAccess()
	{
		return lastAccess;
	}

	public void setLastAccess(long value)
	{
		lastAccess = value;
	}

	public long getExp()
	{
		return exp;
	}

	public void setExp(long value)
	{
		exp = value;
	}

	public int getFace()
	{
		return face;
	}

	public void setFace(int value)
	{
		face = value;
	}

	public int getHairColor()
	{
		return hairColor;
	}

	public void setHairColor(int value)
	{
		hairColor = value;
	}

	public int getHairStyle()
	{
		return hairStyle;
	}

	public void setHairStyle(int value)
	{
		hairStyle = value;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int value)
	{
		level = value;
	}

	public int getMaxHp()
	{
		return maxHp;
	}

	public void setMaxHp(int value)
	{
		maxHp = value;
	}

	public int getMaxMp()
	{
		return maxMp;
	}

	public void setMaxMp(int value)
	{
		maxMp = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String value)
	{
		name = value;
	}

	public int getRace()
	{
		return race;
	}

	public void setRace(int value)
	{
		race = value;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(int value)
	{
		sex = value;
	}

	public int getSp()
	{
		return sp;
	}

	public void setSp(int value)
	{
		sp = value;
	}

	@Override
	public String toString()
	{
		StringBuffer string = new StringBuffer();

		string.append("Name: " + name + " | ");
		string.append("Lvl: " + level + " | ");
		string.append("Race: " + race + " | ");

		return string.toString();
	}
}
