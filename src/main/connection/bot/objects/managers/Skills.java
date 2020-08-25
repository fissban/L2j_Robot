package main.connection.bot.objects.managers;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import main.model.SkillModel;

/**
 * @author fissban
 */
public class Skills
{
	private Map<Integer, SkillModel> skills = new LinkedHashMap<>();

	public Skills()
	{
		//
	}

	/**
	 * Initialize the list of skills
	 * @param sk
	 */
	public synchronized void init(List<SkillModel> sk)
	{
		sk.forEach(s -> add(s));
	}

	/**
	 * Add a new skill
	 * @param sk
	 */
	public synchronized void add(SkillModel sk)
	{
		skills.put(sk.getId(), sk);
	}

	/**
	 * Search for a skill by its id
	 * @param id
	 * @return
	 */
	public synchronized SkillModel get(int id)
	{
		return skills.getOrDefault(id, null);
	}

	public Collection<SkillModel> getAll()
	{
		return skills.values();
	}
}
