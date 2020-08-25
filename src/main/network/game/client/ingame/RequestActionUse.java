package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * @author fissban
 */
public class RequestActionUse extends ClientToGameServerMessage
{
	//TODO missing several actions to be defined within RequestActionUseType
	public enum RequestActionUseType
	{
		SIT_OR_STAND(0),
		WALK_OR_RUN(1),
		PRIVATE_STORE_SELL(10),
		PRIVATE_STORE_BUY(28),
		PET_FOLLOW_OR_STOP(21), // 15 - 21
		PET_ATTACK(22); // 16 - 22

		int actionId;

		RequestActionUseType(int value)
		{
			actionId = value;
		}

		public int getActionId()
		{
			return actionId;
		}
	}

	public RequestActionUse(RequestActionUseType type)
	{
		writeC(0x45);
		writeD(type.getActionId());
		writeD(0x00);//ctrlPressed -> 0x00(false), 0x01(true)
		writeC(0x00);//shiftPressed -> 0x00(false), 0x01(true)
	}
}
