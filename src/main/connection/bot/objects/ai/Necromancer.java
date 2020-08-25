package main.connection.bot.objects.ai;

import main.connection.bot.objects.BItem;
import main.enums.EIntention;
import main.util.Util;

/**
 * @author fissban
 */
public class Necromancer extends Mystic
{
	// Nuker skill
	private static final int DHEAT_SPIKE = 1148;
	private static final int CORPSE_LIFE_DRAIN = 1151; // only use if hp is down
	// Debuff skill
	private static final int CURSE_FEAR = 1169;
	private static final int CURSE_GLOOM = 1263;
	// Items create
	private static final int SUMMON_CURSED_BONE = 1387; // skill
	private static final int CURSED_BONE = 2508;

	private static boolean useGloom = false;

	public Necromancer()
	{
		setNukerSkill(DHEAT_SPIKE);
		setDebuffsSkill(CURSE_FEAR);
	}

	@Override
	public void doActionPVE()
	{
		if (hasIntention(EIntention.NONE))
		{
			useGloom = false;

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
		// Check if it is necessary to find a new target
		else if (hasIntention(EIntention.ATTACKING))
		{
			if (!hasTarget())
			{
				useGloom = false;
				setIntention(EIntention.NONE);
				return;
			}

			int skillId = -1;

			if (!useGloom)
			{
				skillId = CURSE_GLOOM;
			}

			if (skillId < 0)
			{
				int distance = (int) Util.calculateDistance(getBot(), getTarget(), false);

				// If the character is very close, fear will be used
				if (distance < 100)
				{
					skillId = searchDebuffSkill();
				}
			}

			// Search skill don't has reuse delay
			if (skillId < 0)
			{
				skillId = searchNukerSkill();
			}

			// Magic attack
			if (skillId > 0)
			{
				attackMagic(getTarget(), skillId);
			}
		}
	}

	@Override
	public void doActionPVP()
	{
		super.doActionPVP();
	}
}
