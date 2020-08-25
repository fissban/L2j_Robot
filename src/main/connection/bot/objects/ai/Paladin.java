package main.connection.bot.objects.ai;

/**
 * @author fissban
 */
public class Paladin extends Fighter
{
	// Buffs
	private static final int MAJESTY = 82;
	private static final int DEFLECT_ARROW = 112;
	private static final int IRON_WILL = 72;
	// Nuker skills
	private static final int TRIBUNAL = 400;
	// Special
	private static final int ANGEL_ICON = 406;

	public Paladin()
	{
		setNukerSkill(TRIBUNAL);
		setBuffsSkill(MAJESTY, DEFLECT_ARROW, IRON_WILL);
	}

	@Override
	public void doActionPVE()
	{
		// check if need use special skill
		if (getPorcentageHpCurrent() < 30)
		{
			// it does not really require a target but in this way we do not lose the attack thread
			useMagic(getTarget(), ANGEL_ICON);
		}

		super.doActionPVE();
	}

	@Override
	public void doActionPVP()
	{
		// check if need use special skill
		if (getPorcentageHpCurrent() < 30)
		{
			// it does not really require a target but in this way we do not lose the attack thread
			useMagic(getTarget(), ANGEL_ICON);
		}

		super.doActionPVP();
	}
}
