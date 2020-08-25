package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestSkillList extends ClientToGameServerMessage
{
	/**
	 * @param objectId
	 * @param shiftPressed
	 */
	public RequestSkillList()
	{
		writeC(0x3f);
	}
}
