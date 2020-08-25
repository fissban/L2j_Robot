package main.connection.bot.objects.managers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import main.managers.ThreadManager;
import main.model.EffectModel;

/**
 * @author fissban
 */
public class Effects implements Runnable
{
	private HashMap<Integer, EffectModel> effects = new LinkedHashMap<>();

	public Effects()
	{
		// Task decrease duration for all effects
		ThreadManager.scheduleAtFixedRate(this, 100, 1000);
	}

	/**
	 * Add new effect (buff, debuff)
	 * @param e
	 */
	public synchronized void add(EffectModel e)
	{
		effects.put(e.getSkillId(), e);
	}

	public void clear()
	{
		effects.clear();
	}

	/**
	 * Get effect by skill id
	 * @param id
	 * @return
	 */
	public synchronized EffectModel get(int id)
	{
		return effects.getOrDefault(id, null);
	}

	/**
	 * Remove effect by skill id
	 * @param id
	 */
	public synchronized void remove(int id)
	{
		effects.remove(id);
	}

	/**
	 * Get all effects(buff, debuff)
	 * @return
	 */
	public synchronized Collection<EffectModel> getAll()
	{
		return effects.values();
	}

	@Override
	public void run()
	{
		// Decrease effect duration
		Iterator<Entry<Integer, EffectModel>> it = effects.entrySet().iterator();

		while (it.hasNext())
		{
			EffectModel e = it.next().getValue();

			// If effect duration < 0 remove this
			if (e.getDuration() < 0)
			{
				// TODO 
				//it.remove();
			}
			else
			{
				e.decreaseDuration();
			}
		}
	}
}
