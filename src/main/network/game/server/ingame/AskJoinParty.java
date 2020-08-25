package main.network.game.server.ingame;

import gui.frames.game.GameFrame;
import gui.frames.game.windows.RequestInviteWindow;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class AskJoinParty extends ServerNetwork
{
	String requestorName;
	int itemDistribution;

	public AskJoinParty(byte data[])
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		requestorName = readS();
		itemDistribution = readD();
	}

	@Override
	public void write()
	{
		RequestInviteWindow ask = new RequestInviteWindow("Party Invite");
		ask.setText(requestorName + " is inviting you to a party.");
		ask.setLocationRelativeTo(GameFrame.getInstance());
		ask.setVisible(true);
		ask.start();
	}
}
