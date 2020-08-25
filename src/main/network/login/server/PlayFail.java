package main.network.login.server;

import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class PlayFail extends ServerNetwork
{
	public static enum PlayFailReason
	{
		REASON_SYSTEM_ERROR(0x01),
		REASON_USER_OR_PASS_WRONG(0x02),
		REASON3(0x03),
		REASON4(0x04),
		REASON_TOO_MANY_PLAYERS(0x0f);

		private final int _code;

		PlayFailReason(int value)
		{
			_code = value;
		}

		public final int getCode()
		{
			return _code;
		}
	}

	private PlayFailReason reason = PlayFailReason.REASON_SYSTEM_ERROR;

	public PlayFail(byte[] data)
	{
		super(data);

		init();
	}

	public String getReason()
	{
		return reason.toString().replaceAll("_", "");
	}

	@Override
	public void read()
	{
		int r = readD();

		for (PlayFailReason pl : PlayFailReason.values())
		{
			if (pl.getCode() == r)
			{
				reason = pl;
				break;
			}
		}
	}

	@Override
	public void write()
	{
		// 
	}
}
