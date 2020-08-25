package main.network.game.client.ingame;

import main.network.game.ClientToGameServerMessage;

/**
 * TODO Los comandos definidos en commandname-e.dat no son todos manejados por este paquete en L2j
 * @author fissban
 */
public class RequestUserCommand extends ClientToGameServerMessage
{
	public enum UserCommandType
	{
		LOC(0),
		UNSTUCK(52),
		TIME(77),
		SIEGE_STATUS(99);

		int id;

		private UserCommandType(int value)
		{
			id = value;
		}

		public int getId()
		{
			return id;
		}
	}

	public RequestUserCommand(UserCommandType value)
	{
		writeC(0xaa);
		writeD(value.getId());
	}
}
