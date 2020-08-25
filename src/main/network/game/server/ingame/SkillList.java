package main.network.game.server.ingame;

import java.util.ArrayList;
import java.util.List;

import gui.frames.game.windows.SkillsWindow;
import main.managers.ChronicleManager;
import main.model.SkillModel;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class SkillList extends ServerNetwork implements IChronicle
{
	private final List<SkillModel> skills;

	public SkillList(byte[] data)
	{
		super(data);
		skills = new ArrayList<>();
		init();
	}

	@Override
	public void read()
	{
		switch (ChronicleManager.get())
		{
			case C4:
				c4();
				break;
			case C6:
				c6();
				break;
		}
	}

	@Override
	public void c4()
	{
		int size = readD();

		for (int i = 0; i < size; i++)
		{
			boolean isPasive = (readD() == 1) ? true : false;
			int level = readD();
			int id = readD();
			skills.add(new SkillModel(id, level, isPasive, false));
		}
	}

	@Override
	public void c6()
	{
		int size = readD();

		for (int i = 0; i < size; i++)
		{
			boolean isPasive = (readD() == 1) ? true : false;
			int level = readD();
			int id = readD();
			boolean isDisable = (readC() == 0) ? true : false;
			skills.add(new SkillModel(id, level, isPasive, isDisable));
		}
	}

	@Override
	public void write()
	{
		getBot().getSkills().init(skills);
		SkillsWindow.getInstance().update(skills);
	}
}
