package main.network.login;

/**
 * @author fissban
 */
public class LoginServerToClientCodes
{
	public final static byte INIT = 0x00;
	public final static byte LOGIN_FAILED = 0x01;
	public final static byte LOGIN_OK = 0x03;
	public final static byte SERVER_LIST = 0x04;
	public final static byte PLAY_OK = 0x07;
	public final static byte PLAY_FAILED = 0x06;
	public final static byte GAME_GUARD_OK = 0x0b;
}
