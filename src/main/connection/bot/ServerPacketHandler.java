package main.connection.bot;

import java.util.concurrent.locks.ReentrantLock;

import main.network.game.server.ingame.AbnormalStatusUpdate;
import main.network.game.server.ingame.ActionFailed;
import main.network.game.server.ingame.AskJoinParty;
import main.network.game.server.ingame.ChangeWait;
import main.network.game.server.ingame.CharInfo;
import main.network.game.server.ingame.CreatureSay;
import main.network.game.server.ingame.DeleteObject;
import main.network.game.server.ingame.Die;
import main.network.game.server.ingame.DropItem;
import main.network.game.server.ingame.EtcStatusUpdate;
import main.network.game.server.ingame.GetItem;
import main.network.game.server.ingame.InventoryUpdate;
import main.network.game.server.ingame.ItemList;
import main.network.game.server.ingame.MagicSkillLaunched;
import main.network.game.server.ingame.MagicSkillUse;
import main.network.game.server.ingame.MoveToLocation;
import main.network.game.server.ingame.MoveToPawn;
import main.network.game.server.ingame.NpcHtmlMessage;
import main.network.game.server.ingame.NpcInfo;
import main.network.game.server.ingame.PartySmallWindowAll;
import main.network.game.server.ingame.PartySmallWindowDelete;
import main.network.game.server.ingame.PetInfo;
import main.network.game.server.ingame.PetStatusUpdate;
import main.network.game.server.ingame.Revive;
import main.network.game.server.ingame.ServerClose;
import main.network.game.server.ingame.SkillList;
import main.network.game.server.ingame.SpawnItem;
import main.network.game.server.ingame.StatusUpdate;
import main.network.game.server.ingame.StopMove;
import main.network.game.server.ingame.SystemMessage;
import main.network.game.server.ingame.UserInfo;

/**
 * @author fissban
 */
public class ServerPacketHandler
{
	private final static ReentrantLock lock = new ReentrantLock();

	public static void proceed(byte[] data)
	{
		lock.lock();
		try
		{
			System.out.println("server -> " + (data[0] & 0xFF));
			byte opCode = data[0];

			switch (opCode & 0xFF)
			{
				case 0x4a:
					new CreatureSay(data);
					break;
				case 0xd5:
					//new Snoop(data);
					break;
				case 0x16:
					new NpcInfo(data);
					break;
				case 0x64:
					new SystemMessage(data);
					break;
				case 0x04:
					new UserInfo(data);
					break;
				case 0x03:
					new CharInfo(data);
					break;
				case 0xfa:
					//new FriendList(data);
					break;
				case 0x01:
					new MoveToLocation(data);
					break;
				case 0x0e:
					new StatusUpdate(data);
					break;
				case 0x47:
					new StopMove(data);
					break;
				case 0x25:
					new ActionFailed(data);
					break;
				case 0x12:
					new DeleteObject(data);
					break;
				case 0x26:
					new ServerClose(data);
					break;
				case 0x7f:
					new AbnormalStatusUpdate(data);
					break;
				case 0x1b:
					new ItemList(data);
					break;
				case 0x27:
					new InventoryUpdate(data);
					break;
				case 0x0c:
					new DropItem(data);
					break;
				case 0x0b:
					new SpawnItem(data);
					break;
				case 0x0d:
					new GetItem(data);
					break;
				case 0x06:
					new Die(data);
					break;
				case 0x58:
					new SkillList(data);
					break;
				case 0x0f:
					new NpcHtmlMessage(data);
					break;
				case 0x60:
					new MoveToPawn(data);
					break;
				case 0x07:
					new Revive(data);
					break;
				case 0x48:
					new MagicSkillUse(data);
					break;
				case 0x2f:
					new ChangeWait(data);
					break;
				case 0xf3:
					new EtcStatusUpdate(data);
					break;
				case 0xb5:
					new PetStatusUpdate(data);
					break;
				case 0x76:
					new MagicSkillLaunched(data);
					break;
				case 0x39:
					new AskJoinParty(data);
					break;
				case 0x4e:
					new PartySmallWindowAll(data);
					break;
				case 0x51:
					new PartySmallWindowDelete(data);
					break;
				case 0xb1:
					new PetInfo(data);
					break;
			}
		}
		finally
		{
			lock.unlock();
		}
	}
}
