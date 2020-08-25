package main.connection.bot.objects.ai;

import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BObject;
import main.enums.EIntention;
import main.util.Util;

/**
 * @author fissban
 */
public class Mystic extends Ai
{
	private static final int WIND_STRIKE = 1177;

	public Mystic()
	{
		super();

		setNukerSkill(WIND_STRIKE);
	}

	@Override
	public void doActionPVE()
	{
		if (hasIntention(EIntention.NONE))
		{
			// Search for a target
			BNpc monster = searchNextTarget();

			if (monster != null)
			{
				attack(monster);
			}
		}
		else if (hasIntention(EIntention.ATTACKING))
		{
			attack(getTarget());
		}
	}

	@Override
	public void doActionPVP()
	{
		attack(getTarget());
	}

	private void attack(BObject target)
	{
		// Check if it is necessary to find a new target
		if (target == null)
		{
			setIntention(EIntention.NONE);
			return;
		}

		int distance = (int) Util.calculateDistance(getBot(), getTarget(), false);

		int skillId = -1;

		// If the character is very close, fear will be used
		if (distance < 100)
		{
			skillId = searchDebuffSkill();
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
