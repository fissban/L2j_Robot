package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;

import gui.look.fissban.FButton;
import gui.look.fissban.FPanelDegrade;
import main.managers.ObjectsManager;
import main.util.Img;

/**
 * @author fissban
 */
public class ActionBotPanel extends FPanelDegrade
{
	private final int MAX_CHATS = 50;

	private WebLabel body = new WebLabel("");

	private final FButton startAi = new FButton("Start Ai");
	private final FButton stopAi = new FButton("Stop Ai");

	public ActionBotPanel()
	{
		super();

		setBounds(780, 54, 228, 402);

		WebLabel top = new WebLabel("Actions Ai Bot");
		top.setDrawShade(true);
		top.setShadeColor(new Color(0, 102, 153));
		top.setForeground(new Color(255, 255, 255));
		top.setFont(new Font("Tahoma", Font.BOLD, 9));
		top.setHorizontalTextPosition(SwingConstants.CENTER);
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.setBorder(null);
		top.setBoldFont(true);
		top.setBackground(new Color(248, 248, 255));
		top.setAlignmentX(0.5f);
		top.setBounds(0, 0, 229, 14);
		add(top);
		body.setForeground(Color.WHITE);

		body.setVerticalAlignment(SwingConstants.TOP);
		body.setFontSize(10);
		body.setFont(new Font("Tahoma", Font.BOLD, 10));
		body.setHorizontalAlignment(SwingConstants.LEFT);
		body.setBounds(2, 17, 225, 350);
		add(body);
		startAi.setBounds(4, 374, 111, 23);
		startAi.setIcon(Img.create("general/icons/control-start.png"));
		stopAi.setEnabled(true);
		startAi.setForeground(new Color(0, 100, 0));
		startAi.addActionListener(e ->
		{
			addActionBot("START AI");
			ObjectsManager.getBot().getAi().startLevelUp();

			stopAi.setEnabled(true);
			startAi.setEnabled(false);
		});
		add(startAi);

		stopAi.setBounds(114, 374, 111, 23);
		stopAi.setIcon(Img.create("general/icons/control-pause.png"));
		stopAi.setEnabled(false);
		stopAi.setForeground(new Color(128, 0, 0));
		stopAi.addActionListener(e ->
		{
			addActionBot("STOP AI");
			ObjectsManager.getBot().getAi().stopLevelUp();

			stopAi.setEnabled(false);
			startAi.setEnabled(true);
		});
		add(stopAi);
	}

	public void addActionBot(String action)
	{
		SwingUtilities.invokeLater(() ->
		{
			String auxAll = body.getText().replace("</html>", "").replace("<html>", "");
			auxAll += action + "<br>";
			// se borran los chats viejos
			int sizeAll = auxAll.split("<br>").length;
			if (sizeAll > MAX_CHATS)
			{
				int pos = auxAll.indexOf("<br>");
				auxAll = auxAll.substring(pos + 4, auxAll.length() - 1);
			}
			body.setText("<html>" + auxAll + "</html>");
		});
	}
}
