package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestAnswerJoinParty extends ClientToGameServerMessage
{

	/**
	 * @param response :
	 *       <li> 0 -> deny party</li>
	 *       <li> 1 -> accept party</li>
	 */
	public RequestAnswerJoinParty(int response)
	{
		writeC(0x2a);
		writeD(response);
	}
}
