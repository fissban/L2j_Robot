package main.connection.bot.objects.ai;

import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BObject;
import main.enums.EIntention;
import main.network.game.client.ingame.RequestActionUse;
import main.network.game.client.ingame.RequestActionUse.RequestActionUseType;

/**
 * @author fissban
 */
public class Fighter extends Ai
{
	public Fighter()
	{
		super();
	}

	@Override
	public void doActionPVE()
	{
		// Check if it is necessary to find a new target
		BNpc monster = null;
		if (hasIntention(EIntention.NONE) || !hasTarget())
		{
			// Search for a target
			monster = searchNextTarget();
		}
		else
		{
			monster = (BNpc) getTarget();
		}

		if (monster != null)
		{
			attack(monster);
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
		// Search skill don't has reuse delay
		int skillId = searchNukerSkill();

		// Magic attack
		if (skillId > 0)
		{
			attackMagic(target, skillId);
			return;
		}

		if (!hasIntention(EIntention.ATTACKING))
		{
			// Attack monster
			attackMele(target);
			// Check if target has summon or pet
			if (getSummon() != null)
			{
				// Summon attack target
				getBot().sendPacket(new RequestActionUse(RequestActionUseType.PET_ATTACK));
			}
		}
	}
}
