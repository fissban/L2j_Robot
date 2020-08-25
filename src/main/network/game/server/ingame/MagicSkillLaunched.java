package main.network.game.server.ingame;

import java.util.ArrayList;
import java.util.List;

import main.enums.EIntention;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class MagicSkillLaunched extends ServerNetwork
{
	private int charObjId;
	private int skillId;
	private int skillLevel;
	private List<Integer> targets;

	public MagicSkillLaunched(byte[] data)
	{
		super(data);
		targets = new ArrayList<>();
		init();
	}

	@Override
	public void read()
	{
		charObjId = readD();
		skillId = readD();
		skillLevel = readD();
		int numberOfTargets = readD();

		for (int i = 0; i < numberOfTargets; i++)
		{
			targets.add(readD());
		}
	}

	@Override
	public void write()
	{
		if (getBot().getObjectId() == charObjId)
		{
			// Finish cast
			getBot().getAi().setIntention(EIntention.NONE);
		}
	}
}
