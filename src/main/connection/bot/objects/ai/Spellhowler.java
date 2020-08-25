package main.connection.bot.objects.ai;

import main.connection.bot.objects.BItem;
import main.enums.EIntention;

/**
 * @author fissban
 */
public class Spellhowler extends Mystic
{
	// Nuker skill
	private static final int HURRICANE = 1239;
	private static final int SHADOW_FLARE = 1267;
	private static final int DHEAT_SPIKE = 1148;
	private static final int CORPSE_LIFE_DRAIN = 1151; // only use if hp is down
	// Debuff skill
	private static final int CURSE_FEAR = 1169;
	// Items create
	private static final int SUMMON_CURSED_BONE = 1387; // skill
	private static final int CURSED_BONE = 2508;

	public Spellhowler()
	{
		setNukerSkill(DHEAT_SPIKE, SHADOW_FLARE, HURRICANE);
		setDebuffsSkill(CURSE_FEAR);
	}

	@Override
	public void doActionPVE()
	{
		if (hasIntention(EIntention.NONE))
		{
			// Check if has cursed bones
			BItem bones = getBot().getInventory().getItemById(CURSED_BONE);
			if (bones == null || bones.getCount() < 20)
			{
				useMagic(null, SUMMON_CURSED_BONE);
				return;
			}

			// Check hp
			if (getPorcentageHpCurrent() < 90)
			{
				useMagic(null, CORPSE_LIFE_DRAIN);
				return;
			}
		}

		super.doActionPVE();
	}

	@Override
	public void doActionPVP()
	{
		super.doActionPVP();
	}
}
