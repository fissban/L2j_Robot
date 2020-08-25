package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestMagicSkillUse extends ClientToGameServerMessage
{
	public RequestMagicSkillUse(int skillId, boolean ctrlPressed, boolean shiftPressed)
	{
		writeC(0x2f);
		writeD(skillId);
		writeD(ctrlPressed ? 1 : 0);
		writeD(shiftPressed ? 1 : 0);
	}
}
