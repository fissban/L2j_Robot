package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class PartySmallWindowDelete extends ServerNetwork
{
	int memberObjectId;

	public PartySmallWindowDelete(byte data[])
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		memberObjectId = readD();
		readS(); // name
	}

	@Override
	public void write()
	{
		GameFrame.getInstance().getParty().removeMember(memberObjectId);
	}
}
