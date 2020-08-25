package main.connection.bot.objects.ai;

import java.util.Arrays;
import java.util.List;

import main.connection.bot.objects.BItem;
import main.model.SkillModel;

/**
 * @author fissban
 */
public class DarkAvenger extends Fighter
{
	// buffs
	private static final int MAJESTY = 82;
	private static final int DEFLECT_ARROW = 112;
	private static final int IRON_WILL = 72;
	// nuker
	private static final int JUDGMENT = 401;
	// special
	private static final int LIFE_SCAVENGE = 46;//Drains HP from corpses and uses it to restore your HP.
	// summon
	private static final int DARK_PANTHER = 283;
	// miscs
	private static final int CRYSTAL_GRADE_C = 1459;
	private static final List<Integer> CYSTAL_GRADE_C_COUNT = Arrays.asList(1, 3, 3, 2, 4, 2, 4);

	public DarkAvenger()
	{
		super();
		setNukerSkill(JUDGMENT);
		setBuffsSkill(MAJESTY, DEFLECT_ARROW, IRON_WILL);
	}

	@Override
	public void doActionPVE()
	{
		// check if has panther
		if (getSummon() == null)
		{
			// check if have crystals grade C
			BItem item = getBot().getInventory().getItemById(CRYSTAL_GRADE_C);
			if (item == null)
			{
				super.doActionPVE();
				return;
			}

			// check if have skill for summon
			SkillModel skill = getBot().getSkills().get(DARK_PANTHER);
			if (skill == null)
			{
				super.doActionPVE();
				return;
			}

			// check if have item count for summon
			if (item.getCount() > CYSTAL_GRADE_C_COUNT.get(skill.getLevel() - 1))
			{
				useMagic(null, DARK_PANTHER);
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

	@Override
	public void onDieTarget()
	{
		// Drain HP from corpse
		if (getPorcentageHpCurrent() < 70)
		{
			useMagic(getTarget(), LIFE_SCAVENGE);
		}
	}
}
