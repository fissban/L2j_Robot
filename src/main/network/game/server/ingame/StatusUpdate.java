package main.network.game.server.ingame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BCreature;
import main.connection.bot.objects.BObject;
import main.connection.bot.objects.BPlayer;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class StatusUpdate extends ServerNetwork
{
	public static final int LEVEL = 0x01;
	public static final int EXP = 0x02;
	public static final int STR = 0x03;
	public static final int DEX = 0x04;
	public static final int CON = 0x05;
	public static final int INT = 0x06;
	public static final int WIT = 0x07;
	public static final int MEN = 0x08;

	public static final int CURHP = 0x09;
	public static final int MAXHP = 0x0a;
	public static final int CURMP = 0x0b;
	public static final int MAXMP = 0x0c;

	public static final int SP = 0x0d;
	public static final int CURLOAD = 0x0e;
	public static final int MAXLOAD = 0x0f;

	public static final int PATK = 0x11;
	public static final int ATKSPD = 0x12;
	public static final int PDEF = 0x13;
	public static final int EVASION = 0x14;
	public static final int ACCURACY = 0x15;
	public static final int CRITICAL = 0x16;
	public static final int MATK = 0x17;
	public static final int CASTSPD = 0x18;
	public static final int MDEF = 0x19;
	public static final int PVPFLAG = 0x1a;
	public static final int KARMA = 0x1b;

	public static final int CURCP = 0x21;
	public static final int MAXCP = 0x22;

	private int objectId;
	private List<Attribute> attributes;

	class Attribute
	{
		public int id;
		public int value;

		Attribute(int pId, int pValue)
		{
			this.id = pId;
			this.value = pValue;
		}
	}

	public StatusUpdate(byte[] data)
	{
		super(data);
		attributes = new ArrayList<>();
		init();
	}

	@Override
	public void read()
	{
		objectId = readD();
		int nSize = readD();
		for (int i = 0; i < nSize; ++i)
		{
			int id = readD();
			int value = readD();

			attributes.add(new Attribute(id, value));
		}
	}

	@Override
	public void write()
	{
		boolean isBot = false;
		BObject c;

		if (getBot().getObjectId() == objectId)
		{
			c = getBot();
			isBot = true;
		}
		else
		{
			c = ObjectsManager.get(objectId);
		}

		if (c == null)
		{
			return;
		}

		if (c instanceof BPlayer)
		{
			BPlayer p = (BPlayer) c;

			Iterator<Attribute> i = attributes.iterator();
			while (i.hasNext())
			{
				Attribute a = i.next();
				int value = a.value;
				// This switch really... sucks.
				switch (a.id)
				{
					case EXP:
						p.setExp(value);
						break;
					case STR:
						p.setSTR(value);
						break;
					case DEX:
						p.setDEX(value);
						break;
					case CON:
						p.setCON(value);
						break;
					case INT:
						p.setINT(value);
						break;
					case WIT:
						p.setWIT(value);
						break;
					case MEN:
						p.setMEN(value);
						break;
					case SP:
						p.setSp(value);
						break;
					case CURLOAD:
						p.setCurrentLoad(value);
						break;
					case MAXLOAD:
						p.setMaxLoad(value);
						break;
					case PVPFLAG:
						p.setPvpFlag(value == 0 ? false : true);
						break;
					case KARMA:
						p.setKarma(value);
						break;
					case CURCP:
						p.setCurrentCp(value);
						break;
					case MAXCP:
						p.setMaxCp(value);
						break;

					case LEVEL:
						p.setLevel(value);
						break;
					case CURHP:
						p.setCurrentHp(value);
						break;
					case MAXHP:
						p.setMaxHp(value);
						break;
					case CURMP:
						p.setCurrentMp(value);
						break;
					case MAXMP:
						p.setMaxMp(value);
						break;
					case PATK:
						p.setPAtk(value);
						break;
					case ATKSPD:
						p.setPAtkSpd(value);
						break;
					case PDEF:
						p.setPDef(value);
						break;
					case EVASION:
						p.setEvasion(value);
						break;
					case ACCURACY:
						p.setAccuracy(value);
						break;
					case CRITICAL:
						p.setCriticalRate(value);
						break;
					case MATK:
						p.setMAtk(value);
						break;
					case CASTSPD:
						p.setMAtkSpd(value);
						break;
					case MDEF:
						p.setMDef(value);
						break;
				}

				if (p.isInParty())
				{
					GameFrame.getInstance().getParty().updateHpMp(p);
				}
			}
		}
		else if (c instanceof BCreature)
		{

			BCreature b = (BCreature) c;
			Iterator<Attribute> i = attributes.iterator();
			while (i.hasNext())
			{
				Attribute a = i.next();
				int value = a.value;
				// This switch really... sucks.
				switch (a.id)
				{
					case LEVEL:
						b.setLevel(value);
						break;
					case CURHP:
						b.setCurrentHp(value);
						break;
					case MAXHP:
						b.setMaxHp(value);
						break;
					case CURMP:
						b.setCurrentMp(value);
						break;
					case MAXMP:
						b.setMaxMp(value);
						break;
					case PATK:
						b.setPAtk(value);
						break;
					case ATKSPD:
						b.setPAtkSpd(value);
						break;
					case PDEF:
						b.setPDef(value);
						break;
					case EVASION:
						b.setEvasion(value);
						break;
					case ACCURACY:
						b.setAccuracy(value);
						break;
					case CRITICAL:
						b.setCriticalRate(value);
						break;
					case MATK:
						b.setMAtk(value);
						break;
					case CASTSPD:
						b.setMAtkSpd(value);
						break;
					case MDEF:
						b.setMDef(value);
						break;
				}
			}
		}

		if (isBot)
		{
			GameFrame.getInstance().getBotInfo().updateStats();
		}
		else if (getBot().getAi().hasTarget() && getBot().getAi().getTarget() instanceof BCreature && getBot().getAi().getTargetId() == objectId)
		{
			GameFrame.getInstance().getTargetInfo().updateStats((BCreature) getBot().getAi().getTarget());
		}
	}
}
