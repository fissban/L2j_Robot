package main.connection.bot.objects;

/**
 * @author fissban
 */
public class BCreature extends BObject
{
	private String title = "";

	private int level;

	private int heading;

	private int maxHp;
	private int currentHp;
	private int maxMp;
	private int currentMp;

	private int pAtk;
	private int pDef;
	private int mAtk;
	private int mDef;
	private int pAtkSpd;
	private int mAtkSpd;
	private int evasion;
	private int accuracy;
	private int criticalRate;
	private int runSpd;

	private boolean isDead;

	private boolean isInvisible;

	public BCreature(int objectId)
	{
		super(objectId);
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String value)
	{
		title = value;
	}

	public int getHeading()
	{
		return heading;
	}

	public void setHeading(int value)
	{
		heading = value;
	}

	public int getMaxHp()
	{
		return maxHp;
	}

	public void setMaxHp(int value)
	{
		maxHp = value;
	}

	public int getCurrentHp()
	{
		return currentHp;
	}

	public void setCurrentHp(int value)
	{
		currentHp = value;
	}

	public int getMaxMp()
	{
		return maxMp;
	}

	public void setMaxMp(int value)
	{
		maxMp = value;
	}

	public int getCurrentMp()
	{
		return currentMp;
	}

	public void setCurrentMp(int value)
	{
		currentMp = value;
	}

	public int getPAtk()
	{
		return pAtk;
	}

	public void setPAtk(int value)
	{
		pAtk = value;
	}

	public int getPDef()
	{
		return pDef;
	}

	public void setPDef(int value)
	{
		pDef = value;
	}

	public int getMAtk()
	{
		return mAtk;
	}

	public void setMAtk(int value)
	{
		mAtk = value;
	}

	public int getMDef()
	{
		return mDef;
	}

	public void setMDef(int value)
	{
		mDef = value;
	}

	public int getPAtkSpd()
	{
		return pAtkSpd;
	}

	public void setPAtkSpd(int value)
	{
		pAtkSpd = value;
	}

	public int getMAtkSpd()
	{
		return mAtkSpd;
	}

	public void setMAtkSpd(int value)
	{
		mAtkSpd = value;
	}

	public int getEvasion()
	{
		return evasion;
	}

	public void setEvasion(int value)
	{
		evasion = value;
	}

	public int getAccuracy()
	{
		return accuracy;
	}

	public void setAccuracy(int value)
	{
		accuracy = value;
	}

	public int getCriticalRate()
	{
		return criticalRate;
	}

	public int getRunSpd()
	{
		return runSpd;
	}

	public void setRunSpd(int value)
	{
		runSpd = value;
	}

	public void setCriticalRate(int value)
	{
		criticalRate = value;
	}

	@Override
	public boolean isDead()
	{
		return isDead;
	}

	public void setDead(boolean value)
	{
		isDead = value;

		if (isDead)
		{

		}
		else
		{

		}
	}

	public boolean isInvisible()
	{
		return isInvisible;
	}

	public void setInvisible(boolean value)
	{
		isInvisible = value;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int l)
	{
		level = l;
	}

	public String getGuiMp()
	{
		return "MP: " + getCurrentMp() + " / " + getMaxMp();
	}

	public String getGuiHp()
	{
		return "HP: " + getCurrentHp() + " / " + getMaxHp();
	}
}
