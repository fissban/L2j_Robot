package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebEditorPane;

import gui.builder.WindowFrame;
import main.managers.ObjectsManager;
import main.network.game.client.ingame.RequestBypassToServer;

/**
 * @author fissban
 */
public class HtmlWindow extends WindowFrame
{
	private static final long serialVersionUID = 1L;

	private final List<String> bypassList = new ArrayList<>();

	private WebEditorPane paneHtml;

	public HtmlWindow()
	{
		super("Html");

		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		setBounds(0, 0, 435, 450);

		paneHtml = new WebEditorPane("text/html", "");
		paneHtml.setDragEnabled(true);
		paneHtml.setFont(new Font("Tahoma", Font.PLAIN, 10));
		paneHtml.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		paneHtml.setEditable(false);
		paneHtml.setBackground(new Color(245, 245, 245));
		paneHtml.setBounds(0, 0, 435, 450);

		WebScrollPane scrollPane = new WebScrollPane(paneHtml);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getWebHorizontalScrollBar().setPreferredSize(new Dimension(50, 8));
		scrollPane.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPane.setBounds(0, 0, 429, 421);
		panel.add(scrollPane);
	}

	public void open()
	{
		setVisible(!isVisible());
	}

	public void generateHtml(String text)
	{
		paneHtml.setText("");
		// se limpian los bypass
		bypassList.clear();
		// se obtienen todos los bypass
		for (int i = 0; i < text.length(); i++)
		{
			int start = text.indexOf("\"bypass ", i);
			int finish = text.indexOf("\"", start + 1);
			if (start < 0 || finish < 0)
			{
				break;
			}

			if (text.substring(start + 8, start + 10).equals("-h"))
			{
				start += 11;
			}
			else
			{
				start += 8;
			}

			String bypass = text.substring(start, finish);
			if (!bypassList.contains(bypass))
			{
				bypassList.add(bypass);
			}
		}

		//String read = "";
		//boolean end = false;
		//int line = 0;
		//int endLine = 50;
		//while (!end)
		//{

		//	String l = text.substring(endLine * line, (text.length() - 1) < (endLine * (line + 1)) ? text.length() - 1 : (endLine * (line + 1)));

		//	if (l.contains("bypass") || l.contains("img src=") || l.contains("<button"))
		//	{

		//	}
		//	if (l.contains("</html>"))
		//	{
		//		read += l;
		//		end = true;
		//	}
		//	else if (l.contains("<br>"))
		//	{
		//		read += l;
		//		line++;
		//	}
		//	else if (l.length() > 0)
		//	{
		//		int last = l.lastIndexOf(" ", 43);
		//		read += l.substring(0, last - 1) + "<br>";
		//		read += l.substring(last - 1, l.length() - 1);
		//		line++;
		//	}
		//}

		// se remplaza los customs links del L2 por los de los navegadores
		text = text.replaceAll("<a action=\"bypass -h ", "<a href=\"http://");
		text = text.replaceAll("<a action=\"bypass ", "<a href=\"http://");

		paneHtml.setText(text);
		paneHtml.addHyperlinkListener(e ->
		{
			if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType()))
			{
				dispose();
				ObjectsManager.getBot().sendPacket(new RequestBypassToServer(e.getURL().toString().replaceAll("http://", "")));
			}
		});
		paneHtml.repaint();

		open();
	}

	public static HtmlWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final HtmlWindow INSTANCE = new HtmlWindow();
	}
}
