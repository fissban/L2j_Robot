package main.model;

import main.data.SkillData;
import main.managers.ThreadManager;

/**
 * @author fissban
 */
public class SkillModel
{
	private int id;
	private int level;
	private boolean isPasive;
	private boolean isDisable;
	private int reuseTime = 0;

	public SkillModel(int id, int level, boolean isPasive, boolean isDisable)
	{
		this.id = id;
		this.level = level;
		this.isPasive = isPasive;
		this.isDisable = isDisable;
	}

	public int getId()
	{
		return id;
	}

	public int getLevel()
	{
		return level;
	}

	public String getName()
	{
		return SkillData.getName(id);
	}

	public void setLevel(int value)
	{
		level = value;
	}

	public boolean isDisable()
	{
		return isDisable;
	}

	public void setDisable(boolean value)
	{
		isDisable = value;
	}

	public int getReuseTime()
	{
		return reuseTime;
	}

	public void setReuseTime(int value)
	{
		reuseTime = value;

		if (reuseTime > 0)
		{
			ThreadManager.schedule(() -> setReuseTime(reuseTime - 1000), 1000);
		}
		else
		{
			// prevent negative values
			reuseTime = 0;
		}
	}

	public boolean isPasive()
	{
		return isPasive;
	}

	public void setPasive(boolean value)
	{
		isPasive = value;
	}
}
