package main.connection.bot.objects.ai;

import main.model.SkillModel;

/**
 * @author fissban
 */
public class Gladiator extends Fighter
{
	// Buffs
	private static final int DUELIST_SPIRIT = 297; // Temporarily increases dual-sword weapon Atk. Spd. and ordinary/skill attack damage when in PVP
	// Nuker skills
	private static final int TRIPLE_SONIC_SLASH = 261;
	private static final int DOUBLE_SONIC_SLASH = 5;
	private static final int TRIPLE_SLASH = 1;
	// Misc
	private static final int SONIC_FOCUS = 8; // Channels force energy for use with other Sonic skill

	public Gladiator()
	{
		super();

		// By default in the warriors we will not let the bot feel if it runs out of mp,
		// but it can be changed from the configuration panel of the user
		this.SIT_DOWN_IS_MP_VERY_SLOW = false;

		setBuffsSkill(DUELIST_SPIRIT);
		setNukerSkill(TRIPLE_SONIC_SLASH, DOUBLE_SONIC_SLASH, TRIPLE_SLASH);
	}

	@Override
	public void doActionPVE()
	{
		// check if need use charges
		if (generateCharges())
		{
			return;
		}

		super.doActionPVE();
	}

	private boolean generateCharges()
	{
		if (getPorcentageMpCurrent() < 5)
		{
			return false;
		}
		// level skill
		SkillModel s = getBot().getSkills().get(SONIC_FOCUS);
		if (s != null)
		{
			// sonic focus lvl
			int lvlSonicFocus = s.getLevel();

			if (getBot().getCharges() < lvlSonicFocus)
			{
				// need more charges
				useMagic(null, s.getId());
				return true;
			}
		}

		return false;
	}

	@Override
	public void doActionPVP()
	{
		// check if need use charges
		if (generateCharges())
		{
			return;
		}

		super.doActionPVP();
	}
}
