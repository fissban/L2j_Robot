package main.connection.bot.objects;

/**
 * @author fissban
 */
public class BSummon extends BNpc
{
	private int curFed;
	private int maxFed;
	private int type;

	public BSummon(int objectId)
	{
		super(objectId);
	}

	public void init(BNpc npc)
	{
		setName(npc.getName());
		setTitle(npc.getTitle());
		setIdTemplate(npc.getIdTemplate());

		setAttackable(npc.isAttackable());

		setXYZ(npc.getX(), npc.getY(), npc.getZ());
		setHeading(npc.getHeading());

		setPAtkSpd(npc.getPAtkSpd());
		setMAtkSpd(npc.getMAtkSpd());
		setRunSpd(npc.getRunSpd());
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getType()
	{
		return type;
	}

	public int getCurFeed()
	{
		return curFed;
	}

	public void setCurFed(int curFed)
	{
		this.curFed = curFed;
	}

	public int getMaxFed()
	{
		return maxFed;
	}

	public void setMaxFed(int maxFed)
	{
		this.maxFed = maxFed;
	}
}
