package main.connection.bot.objects.ai;

/**
 * @author fissban
 */
public class Spellsinger extends Mystic
{
	// nuker skill
	private static final int HYDRO_BLAST = 1235;
	private static final int SOLAR_FLARE = 1265;
	// debuff skill
	private static final int CURSE_FEAR = 1169;
	// buffs skills
	private static final int FREEZING_SKIN = 1238;

	public Spellsinger()
	{
		setNukerSkill(HYDRO_BLAST, SOLAR_FLARE);
		setDebuffsSkill(CURSE_FEAR);
		setBuffsSkill(FREEZING_SKIN);
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
