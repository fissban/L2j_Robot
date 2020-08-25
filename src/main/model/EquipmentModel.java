package main.model;

import java.util.HashMap;
import java.util.Map;

import main.enums.EPaperdoll;

/**
 * @author fissban
 */
public class EquipmentModel
{
	protected Map<EPaperdoll, PaperdollModel> paperdoll = new HashMap<>(17);

	public EquipmentModel()
	{
		for (EPaperdoll p : EPaperdoll.values())
		{
			paperdoll.put(p, new PaperdollModel());
		}
	}

	public PaperdollModel get(EPaperdoll position)
	{
		return paperdoll.get(position);
	}
}
