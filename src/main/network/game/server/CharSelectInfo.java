package main.network.game.server;

import java.util.HashMap;
import java.util.Map;

import gui.frames.game.windows.ConsoleWindow;
import main.enums.EPaperdoll;
import main.managers.ChronicleManager;
import main.managers.ConnectionManager;
import main.model.CharSelectInfoPackage;
import main.model.EquipmentModel;
import main.network.IChronicle;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class CharSelectInfo extends ServerNetwork implements IChronicle
{
	private Map<Integer, CharSelectInfoPackage> characterList;

	public CharSelectInfo(byte[] data)
	{
		super(data);
		characterList = new HashMap<>();
		init();
	}

	@Override
	public void read()
	{
		try
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
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error CharSelection", e);
		}
	}

	@Override
	public void c4()
	{
		int pjsNumber = readD();

		for (int i = 0; i < pjsNumber; ++i)
		{
			String charName = readS();
			int charId = readD();
			String loginName = readS();
			int sessionId = readD();
			int clanId = readD();

			readD(); // This will be 0x00

			int sex = readD();
			int race = readD();
			int baseClassId = readD();

			readD(); // This will be 0x01. Active ??

			int x = readD();
			int y = readD();
			int z = readD();

			Double currentHp = readF();
			Double currentMp = readF();

			int sp = readD();
			int exp = readD();
			int level = readD();

			int karma = readD();
			int pkKills = readD(); // in L2jAdmins don't use value
			int pvpKills = readD();// in L2jAdmins don't use value

			readD();
			readD();
			readD();
			readD();
			readD();
			readD();
			readD();

			// 34
			EquipmentModel equipment = new EquipmentModel();

			// 16
			equipment.get(EPaperdoll.UNDER).setObjectId(readD());//PAPERDOLLHAIRALL
			equipment.get(EPaperdoll.REAR).setObjectId(readD());
			equipment.get(EPaperdoll.LEAR).setObjectId(readD());
			equipment.get(EPaperdoll.NECK).setObjectId(readD());
			equipment.get(EPaperdoll.RFINGER).setObjectId(readD());
			equipment.get(EPaperdoll.LFINGER).setObjectId(readD());
			equipment.get(EPaperdoll.HEAD).setObjectId(readD());
			equipment.get(EPaperdoll.RHAND).setObjectId(readD());
			equipment.get(EPaperdoll.LHAND).setObjectId(readD());
			equipment.get(EPaperdoll.GLOVES).setObjectId(readD());
			equipment.get(EPaperdoll.CHEST).setObjectId(readD());
			equipment.get(EPaperdoll.LEGS).setObjectId(readD());
			equipment.get(EPaperdoll.FEET).setObjectId(readD());
			equipment.get(EPaperdoll.BACK).setObjectId(readD());
			readD();//PAPERDOLLRHAND
			equipment.get(EPaperdoll.HAIR).setObjectId(readD());
			//equipment.get(EPaperdoll.FACE).setObjectId(readD());

			equipment.get(EPaperdoll.UNDER).setItemId(readD());//PAPERDOLLHAIRALL
			equipment.get(EPaperdoll.REAR).setItemId(readD());
			equipment.get(EPaperdoll.LEAR).setItemId(readD());
			equipment.get(EPaperdoll.NECK).setItemId(readD());
			equipment.get(EPaperdoll.RFINGER).setItemId(readD());
			equipment.get(EPaperdoll.LFINGER).setItemId(readD());
			equipment.get(EPaperdoll.HEAD).setItemId(readD());
			equipment.get(EPaperdoll.RHAND).setItemId(readD());
			equipment.get(EPaperdoll.LHAND).setItemId(readD());
			equipment.get(EPaperdoll.GLOVES).setItemId(readD());
			equipment.get(EPaperdoll.CHEST).setItemId(readD());
			equipment.get(EPaperdoll.LEGS).setItemId(readD());
			equipment.get(EPaperdoll.FEET).setItemId(readD());
			equipment.get(EPaperdoll.BACK).setItemId(readD());
			readD();//PAPERDOLLRHAND
			equipment.get(EPaperdoll.HAIR).setItemId(readD());
			//equipment.get(EPaperdoll.FACE).setItemId(readD());

			int hairStyle = readD();
			int hairColor = readD();
			int face = readD();

			double maxHp = readF();
			double maxMp = readF();

			int daysToDelete = readD();

			int classId = readD();

			int lastUsed = readD();

			int enchantEffect = readC(); // Not usefull for us.

			CharSelectInfoPackage character = new CharSelectInfoPackage();
			character.setBaseClassId(baseClassId);
			character.setCharId(charId);
			character.setClanId(clanId);
			character.setClassId(classId);
			character.setCurrentHp(currentHp.intValue());
			character.setCurrentMp(currentMp.intValue());
			character.setDeleteTimer(daysToDelete);
			character.setExp(exp);
			character.setFace(face);
			character.setHairColor(hairColor);
			character.setHairStyle(hairStyle);
			character.setMaxHp((int) maxHp);
			character.setMaxMp((int) maxMp);
			character.setName(charName);
			character.setRace(race);
			character.setSex(sex);
			character.setSp(sp);
			character.setLastAccess(lastUsed);
			character.setLevel(level);

			characterList.put(i, character);
		}
	}

	@Override
	public void c6()
	{
		int pjsNumber = readD();

		for (int i = 0; i < pjsNumber; ++i)
		{
			String charName = readS();
			int charId = readD();
			String loginName = readS();
			int sessionId = readD();
			int clanId = readD();

			readD(); // This will be 0x00

			int sex = readD();
			int race = readD();
			int baseClassId = readD();

			readD(); // This will be 0x01. Active ??

			int x = readD();
			int y = readD();
			int z = readD();

			Double currentHp = readF();
			Double currentMp = readF();

			int sp = readD();
			long exp = readQ();
			int level = readD();

			int karma = readD();
			int pkKills = readD();
			int pvpKills = readD();

			readD();
			readD();
			readD();
			readD();
			readD();
			readD();
			readD();

			// 34
			EquipmentModel equipment = new EquipmentModel();
			equipment.get(EPaperdoll.UNDER).setObjectId(readD());//PAPERDOLLHAIRALL
			equipment.get(EPaperdoll.REAR).setObjectId(readD());
			equipment.get(EPaperdoll.LEAR).setObjectId(readD());
			equipment.get(EPaperdoll.NECK).setObjectId(readD());
			equipment.get(EPaperdoll.RFINGER).setObjectId(readD());
			equipment.get(EPaperdoll.LFINGER).setObjectId(readD());
			equipment.get(EPaperdoll.HEAD).setObjectId(readD());
			equipment.get(EPaperdoll.RHAND).setObjectId(readD());
			equipment.get(EPaperdoll.LHAND).setObjectId(readD());
			equipment.get(EPaperdoll.GLOVES).setObjectId(readD());
			equipment.get(EPaperdoll.CHEST).setObjectId(readD());
			equipment.get(EPaperdoll.LEGS).setObjectId(readD());
			equipment.get(EPaperdoll.FEET).setObjectId(readD());
			equipment.get(EPaperdoll.BACK).setObjectId(readD());
			readD();//PAPERDOLLRHAND
			equipment.get(EPaperdoll.HAIR).setObjectId(readD());
			equipment.get(EPaperdoll.FACE).setObjectId(readD());

			equipment.get(EPaperdoll.UNDER).setItemId(readD());//PAPERDOLLHAIRALL
			equipment.get(EPaperdoll.REAR).setItemId(readD());
			equipment.get(EPaperdoll.LEAR).setItemId(readD());
			equipment.get(EPaperdoll.NECK).setItemId(readD());
			equipment.get(EPaperdoll.RFINGER).setItemId(readD());
			equipment.get(EPaperdoll.LFINGER).setItemId(readD());
			equipment.get(EPaperdoll.HEAD).setItemId(readD());
			equipment.get(EPaperdoll.RHAND).setItemId(readD());
			equipment.get(EPaperdoll.LHAND).setItemId(readD());
			equipment.get(EPaperdoll.GLOVES).setItemId(readD());
			equipment.get(EPaperdoll.CHEST).setItemId(readD());
			equipment.get(EPaperdoll.LEGS).setItemId(readD());
			equipment.get(EPaperdoll.FEET).setItemId(readD());
			equipment.get(EPaperdoll.BACK).setItemId(readD());
			readD();//PAPERDOLLRHAND
			equipment.get(EPaperdoll.HAIR).setItemId(readD());
			equipment.get(EPaperdoll.FACE).setItemId(readD());

			int hairStyle = readD();
			int hairColor = readD();
			int face = readD();

			double maxHp = readF();
			double maxMp = readF();

			int daysToDelete = readD();

			int classId = readD();

			int lastUsed = readD();

			int enchantEffect = readC(); // Not usefull for us.
			int augmentationId = readD(); // Not usefull for us.

			CharSelectInfoPackage character = new CharSelectInfoPackage();
			character.setBaseClassId(baseClassId);
			character.setCharId(charId);
			character.setClanId(clanId);
			character.setClassId(classId);
			character.setCurrentHp(currentHp.intValue());
			character.setCurrentMp(currentMp.intValue());
			character.setDeleteTimer(daysToDelete);
			character.setExp(exp);
			character.setFace(face);
			character.setHairColor(hairColor);
			character.setHairStyle(hairStyle);
			character.setMaxHp((int) maxHp);
			character.setMaxMp((int) maxMp);
			character.setName(charName);
			character.setRace(race);
			character.setSex(sex);
			character.setSp(sp);
			character.setLastAccess(lastUsed);
			character.setLevel(level);

			characterList.put(i, character);
		}

	}

	@Override
	public void write()
	{
		// remove old list
		ConnectionManager.getGame().getCharList().clear();
		// add new list
		ConnectionManager.getGame().getCharList().putAll(characterList);
	}
}
