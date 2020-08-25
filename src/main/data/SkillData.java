package main.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import gui.frames.game.windows.ConsoleWindow;
import main.util.XMLDocumentFactory;

/**
 * @author fissban
 */
public class SkillData
{
	private static Map<Integer, String> skills = new HashMap<>();

	public static void load()
	{
		try
		{
			Document doc = XMLDocumentFactory.getInstance().loadDocument(new File("./data/SkillData.xml"));

			for (Node d = doc.getFirstChild().getFirstChild(); d != null; d = d.getNextSibling())
			{
				if (d.getNodeName().equalsIgnoreCase("skill"))
				{
					NamedNodeMap attrs = d.getAttributes();

					int id = Integer.valueOf(attrs.getNamedItem("id").getNodeValue());
					String name = attrs.getNamedItem("name").getNodeValue();

					skills.put(id, name);
				}
			}
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("Error load npc", e);
		}
	}

	public static String getName(int id)
	{
		return skills.get(id);
	}

	public static int getId(String name)
	{
		for (Entry<Integer, String> entry : skills.entrySet())
		{
			int key = entry.getKey();
			String value = entry.getValue();

			if (value.equals(name))
			{
				return key;
			}
		}

		return -1;
	}
}
