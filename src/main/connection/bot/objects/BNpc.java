package main.connection.bot.objects;

import main.managers.ObjectsManager;
import main.util.Util;

/**
 * @author fissban
 */
public class BNpc extends BCreature
{
	protected boolean isAttackable;
	protected int idTemplate;
	protected boolean isSummon;

	public BNpc(int objectId)
	{
		super(objectId);
	}

	public boolean isAttackable()
	{
		return isAttackable;
	}

	public void setAttackable(boolean value)
	{
		isAttackable = value;
	}

	public int getIdTemplate()
	{
		return idTemplate;
	}

	public void setIdTemplate(int value)
	{
		idTemplate = value;
	}

	/**
	 * if return <b>true</b> this npc is my summon or pet
	 * @return
	 */
	public boolean isSummon()
	{
		return isSummon;
	}

	/**
	 * Define if this npc is my summon or pet
	 * @param value
	 */
	public void setSummon(boolean value)
	{
		isSummon = value;
	}

	/**
	 * A brief description of char is shown.
	 * Used for interface knownlist
	 *
	 * @return
	 */
	@Override
	public String getDescription()
	{
		return "<html><font color=82030E>[" + getObjectId() + "]</font>  " + getName() + " " + getTitle() + " <b>Dist:" + (int) Util.calculateDistance(this, ObjectsManager.getBot(), false) + "</b></html>";
	}
}
