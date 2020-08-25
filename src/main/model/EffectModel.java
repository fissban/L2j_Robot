package main.model;

import main.data.SkillData;

/**
 * @author fissban
 */
public class EffectModel
{
	private int skillId;
	private int level;
	private int duration;

	public EffectModel(int pSkillId, int pLevel, int pDuration)
	{
		skillId = pSkillId;
		level = pLevel;
		duration = pDuration;
	}

	public int getSkillId()
	{
		return skillId;
	}

	public String getName()
	{
		return SkillData.getName(skillId);
	}

	public int getLevel()
	{
		return level;
	}

	public int getDuration()
	{
		return duration;
	}

	public void decreaseDuration()
	{
		duration--;
	}
}
