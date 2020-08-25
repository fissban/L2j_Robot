package main.connection.bot.objects.ai;

/**
 * @author fissban
 */
public class TempleKnight extends Fighter
{
	// Buffs
	private static final int DEFLECT_ARROW = 112;
	private static final int SPIRIT_BARRIER = 123;
	private static final int SPRINT = 230;
	// Nuker skills
	private static final int TRIBUNAL = 400;
	// Special
	private static final int ULTIMATE_DEFENSE = 110;
	// Cubic
	private static final int SUMMON_LIFE_CUBIC = 67;
	// TODO missing cubics working

	public TempleKnight()
	{
		setNukerSkill(TRIBUNAL);
		setBuffsSkill(DEFLECT_ARROW, SPIRIT_BARRIER, SPRINT);
	}

	@Override
	public void doActionPVE()
	{
		// Check if need use special skill
		if (getPorcentageHpCurrent() < 30)
		{
			// It does not really require a target but in this way we do not lose the attack thread
			useMagic(getTarget(), ULTIMATE_DEFENSE);
		}

		super.doActionPVE();
	}

	@Override
	public void doActionPVP()
	{
		// Check if need use special skill
		if (getPorcentageHpCurrent() < 30)
		{
			// It does not really require a target but in this way we do not lose the attack thread
			useMagic(getTarget(), ULTIMATE_DEFENSE);
		}

		super.doActionPVP();
	}
}
