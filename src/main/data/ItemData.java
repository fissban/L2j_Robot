package main.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import gui.frames.game.windows.ConsoleWindow;
import main.util.XMLDocumentFactory;

/**
 * @author fissban
 */
public class ItemData
{
	private static Map<Integer, String> items = new HashMap<>();

	public static void load()
	{
		try
		{
			Document doc = XMLDocumentFactory.getInstance().loadDocument(new File("./data/ItemData.xml"));

			for (Node d = doc.getFirstChild().getFirstChild(); d != null; d = d.getNextSibling())
			{
				if (d.getNodeName().equalsIgnoreCase("item"))
				{
					NamedNodeMap attrs = d.getAttributes();

					int id = Integer.valueOf(attrs.getNamedItem("id").getNodeValue());
					String name = attrs.getNamedItem("name").getNodeValue();

					items.put(id, name);
				}
			}
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error load items", e);
		}
	}

	/**
	 * Se obtiene el nombre de un item segun su id
	 * @param id
	 * @return
	 */
	public static String getName(int id)
	{
		return items.getOrDefault(id, "NoName");
	}
}
