package main.connection.bot.objects.ai;

/**
 * @author fissban
 */
public class Archmage extends Sorcerer
{
	// Nuker skill
	private static final int FIRE_VORTEX = 1339;
	// Toggle
	private static final int ARCANE_POWER = 337;

	public Archmage()
	{
		super();

		setNukerSkill(FIRE_VORTEX);
	}

	@Override
	public void doActionPVE()
	{
		if (getPorcentageHpCurrent() > 40)
		{
			attackMagic(null, ARCANE_POWER);
		}
		else
		{
			attackMagic(null, ARCANE_POWER);
		}

		super.doActionPVE();
	}

	@Override
	public void doActionPVP()
	{
		super.doActionPVP();
	}
}
