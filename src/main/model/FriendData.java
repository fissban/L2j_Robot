package main.model;

/**
 * @author fissban
 */
public class FriendData
{
	/**
	 * The objectId in server
	 */
	private int objectId;

	/**
	 * The friend name
	 */
	private String name;

	/**
	 * True if online, false if offline
	 */
	private boolean online;

	/**
	 * @param objId
	 * @param n
	 * @param o
	 */
	public FriendData(int objId, String n, boolean o)
	{
		objectId = objId;
		name = n;
		online = o;
	}

	@Override
	public String toString()
	{
		return name + (online ? "(Online)" : "(Offline)");
	}
}
