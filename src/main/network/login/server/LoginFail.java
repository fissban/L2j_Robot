package main.network.login.server;

import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class LoginFail extends ServerNetwork
{
	public static enum LoginFailReason
	{
		NONE(0x00),
		REASON_SYSTEM_ERROR(0x01),
		REASON_PASS_WRONG(0x02),
		REASON_USER_OR_PASS_WRONG(0x03),
		REASON_ACCESS_FAILED(0x04),
		REASON_ACCOUNT_IN_USE(0x07),
		REASON_SERVER_OVERLOADED(0x0f),
		REASON_SERVER_MAINTENANCE(0x10),
		REASON_TEMP_PASS_EXPIRED(0x11),
		REASON_DUAL_BOX(0x23);

		private final int _code;

		LoginFailReason(int code)
		{
			_code = code;
		}

		public final int getCode()
		{
			return _code;
		}
	}

	LoginFailReason reason = LoginFailReason.NONE;

	public String getReason()
	{
		return reason.toString().replaceAll("_", "");
	}

	public LoginFail(byte[] data)
	{
		super(data);

		init();
	}

	@Override
	public void read()
	{
		int r = readD();

		for (LoginFailReason lf : LoginFailReason.values())
		{
			if (lf.getCode() == r)
			{
				reason = lf;
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
