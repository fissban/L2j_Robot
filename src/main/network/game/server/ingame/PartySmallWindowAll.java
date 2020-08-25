package main.network.game.server.ingame;

import java.util.ArrayList;
import java.util.List;

import gui.frames.game.GameFrame;
import main.connection.bot.objects.BPlayer;
import main.enums.EClassId;
import main.enums.ERace;
import main.managers.ObjectsManager;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class PartySmallWindowAll extends ServerNetwork
{
	int leaderObjId = -1;
	List<BPlayer> members;

	public PartySmallWindowAll(byte data[])
	{
		super(data);
		members = new ArrayList<>();
		init();
	}

	// TODO el leader debe ir primero
	// nosotros vamos segundo?

	@Override
	public void read()
	{
		// 0x4e
		leaderObjId = readD();

		((BPlayer) ObjectsManager.get(leaderObjId)).setLeader(true);
		((BPlayer) ObjectsManager.get(leaderObjId)).setInParty(true);

		readD(); // party.getLootRule().ordinal()
		int membersCount = readD();

		for (int i = 0; i < membersCount; i++)
		{
			int objectId = readD();

			BPlayer member = (BPlayer) ObjectsManager.get(objectId);

			if (member == null)
			{
				member = new BPlayer(objectId);
			}

			members.add(member);

			member.setInParty(true);

			member.setName(readS());
			member.setCurrentCp(readD());
			member.setMaxCp(readD());

			member.setCurrentHp(readD());
			member.setMaxHp(readD());

			member.setCurrentMp(readD());
			member.setMaxMp(readD());

			member.setClassId(EClassId.values()[readD()]);
			readD(); // 0x00 in L2j
			member.setRace(ERace.values()[readD()]);
		}
	}

	@Override
	public void write()
	{
		GameFrame.getInstance().getParty().initWindows(members);
	}

}
