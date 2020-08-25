package main.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import gui.frames.game.windows.ConsoleWindow;
import main.managers.ChronicleManager;
import main.util.XMLDocumentFactory;

/**
 * @author fissban
 */
public class NpcData
{
	private static Map<Integer, String> npc = new HashMap<>();

	public static void load()
	{
		try
		{
			String file = "";

			switch (ChronicleManager.get())
			{
				case C4:
					file = "./data/NpcDataC4.xml";
					break;
				case C6:
					file = "./data/NpcDataC6.xml";
					break;
			}

			Document doc = XMLDocumentFactory.getInstance().loadDocument(new File(file));

			for (Node d = doc.getFirstChild().getFirstChild(); d != null; d = d.getNextSibling())
			{
				if (d.getNodeName().equalsIgnoreCase("npc"))
				{
					NamedNodeMap attrs = d.getAttributes();

					int id = Integer.valueOf(attrs.getNamedItem("id").getNodeValue());
					String name = attrs.getNamedItem("name").getNodeValue();

					npc.put(id, name);
				}
			}
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error load npc", e);
		}
	}

	/**
	 * Se obtiene el nombre de un npc segun su id
	 * @param id
	 * @return
	 */
	public static String getName(int id)
	{
		return npc.get(id);
	}
}
