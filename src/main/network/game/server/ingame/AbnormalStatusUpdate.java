package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.model.EffectModel;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class AbnormalStatusUpdate extends ServerNetwork
{
	public AbnormalStatusUpdate(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		getBot().getEffects().clear();

		int size = readH();
		for (int i = 0; i < size; i++)
		{
			int id = readD();
			int level = readH();
			int duration = readD();

			// if duration equals -1 effect is removed from buff list
			if (duration == -1)
			{
				getBot().getEffects().remove(id);
			}
			else
			{
				getBot().getEffects().add(new EffectModel(id, level, duration));
			}
		}
	}

	@Override
	public void write()
	{
		// Generate buffs in the interface
		GameFrame.getInstance().getBuffs().generateBuffs();
	}
}
