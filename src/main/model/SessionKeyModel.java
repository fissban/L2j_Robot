package main.model;

/**
 * @author fissban
 */
public class SessionKeyModel
{
	public int playKey1;
	public int playKey2;
	public int loginKey1;
	public int loginKey2;

	public SessionKeyModel(int loginKey1, int loginKey2, int playKey1, int playKey2)
	{
		this.loginKey1 = loginKey1;
		this.loginKey2 = loginKey2;
		this.playKey1 = playKey1;
		this.playKey2 = playKey2;
	}

	@Override
	public String toString()
	{
		return "PlayOk: " + playKey1 + " " + playKey2 + " LoginOk:" + loginKey1 + " " + loginKey2;
	}
}
