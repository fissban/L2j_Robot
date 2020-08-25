package main.network.game.server.ingame;

import java.util.ArrayList;
import java.util.List;

import gui.frames.game.GameFrame;
import main.data.SkillData;
import main.enums.ESystemMessage;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class SystemMessage extends ServerNetwork
{
	private static final byte TYPE_ZONE_NAME = 7;
	private static final byte TYPE_ITEM_NUMBER = 6;
	private static final byte TYPE_CASTLE_NAME = 5;
	private static final byte TYPE_SKILL_NAME = 4;
	private static final byte TYPE_ITEM_NAME = 3;
	private static final byte TYPE_NPC_NAME = 2;
	private static final byte TYPE_NUMBER = 1;
	private static final byte TYPE_TEXT = 0;

	private int msgId;
	private int typesSize;

	private List<String> replacement;

	public SystemMessage(byte[] data)
	{
		super(data);
		replacement = new ArrayList<>();
		init();
	}

	@Override
	public void read()
	{
		msgId = readD();
		typesSize = readD();

		for (int i = 0; i < typesSize; ++i)
		{
			int msgType = readD();
			switch (msgType)
			{
				case TYPE_TEXT:
				{
					String msg = readS().trim();
					replacement.add(msg);
					break;
				}
				case TYPE_ITEM_NUMBER:
				case TYPE_ITEM_NAME:
				case TYPE_CASTLE_NAME:
				case TYPE_NUMBER:
				case TYPE_NPC_NAME:
				{
					int number = readD();
					replacement.add(number + "");
					break;
				}
				case TYPE_SKILL_NAME: //TODO falta definir el nombre del skill
				{
					int id = readD();
					int lvl = readD();
					replacement.add(SkillData.getName(id));
					//replacement.add(lvl + "");
					break;
				}
				case TYPE_ZONE_NAME://TODO falta definir el nombre de la zona
				{
					int x = readD();
					int y = readD();
					int z = readD();

					replacement.add(x + "");
					replacement.add(y + "");
					replacement.add(z + "");
					break;
				}
			}
		}
	}

	@Override
	public void write()
	{
		String msg = "<b>[System]</b> ";

		// se obtiene el mensaje en cuestion
		for (ESystemMessage s : ESystemMessage.values())
		{
			if (s.getId() == msgId)
			{
				// remplazo los "_" por " " y convierto todo en minusculas
				msg += s.name();
				break;
			}
		}

		// remplazo los S1 S2 S3
		int cont = 1;
		for (String re : replacement)
		{
			msg = msg.replace("S" + cont, re);
			cont++;
		}

		msg = msg.replaceAll("_", " ").toLowerCase();

		GameFrame.getInstance().getChat().addChat(msg);
	}
}
