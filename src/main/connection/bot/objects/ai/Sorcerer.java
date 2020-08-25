package main.connection.bot.objects.ai;

/**
 * @author fissban
 */
public class Sorcerer extends Mystic
{
	// Nuker skill
	private static final int PROMINENCE = 1230;
	// Debuff skill
	private static final int CURSE_FEAR = 1169;
	// Buffs skills
	private static final int BLAZING_SKIN = 1232;
	private static final int CONCENTRATION = 1078;
	private static final int SEED_OF_FIRE = 1285;

	public Sorcerer()
	{
		setNukerSkill(PROMINENCE);
		setDebuffsSkill(CURSE_FEAR);
		setBuffsSkill(BLAZING_SKIN, CONCENTRATION, SEED_OF_FIRE);
	}

	@Override
	public void doActionPVE()
	{
		super.doActionPVE();
	}

	@Override
	public void doActionPVP()
	{
		super.doActionPVP();
	}
}
