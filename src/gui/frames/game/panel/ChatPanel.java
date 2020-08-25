package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.TabStretchType;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;

import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FTextField;
import main.managers.ObjectsManager;
import main.util.Img;

/**
 * @author fissban
 */
public class ChatPanel extends FPanelDegrade
{
	private static final int MAX_CHATS = 30;

	// all
	private final SayLabel all = new SayLabel();
	private WebScrollPane scrollAll = new WebScrollPane(all);
	// trade
	private final SayLabel trade = new SayLabel();
	private WebScrollPane scrollTrade = new WebScrollPane(trade);
	// party
	private final SayLabel party = new SayLabel();
	private WebScrollPane scrollParty = new WebScrollPane(party);
	// clan
	private final SayLabel clan = new SayLabel();
	private WebScrollPane scrollClan = new WebScrollPane(clan);
	// alliance
	private final SayLabel alliance = new SayLabel();
	private WebScrollPane scrollAlliance = new WebScrollPane(alliance);

	private FTextField chatSay;

	public ChatPanel()
	{
		super();

		setBounds(0, 482, 530, 137);

		WebTabbedPane chatsPanel = new WebTabbedPane(SwingConstants.BOTTOM);
		chatsPanel.setOpaque(true);
		chatsPanel.setTabStretchType(TabStretchType.never);
		chatsPanel.setBounds(3, 3, 524, 108);
		chatsPanel.setTabbedPaneStyle(TabbedPaneStyle.attached);
		chatsPanel.setFont(new Font("Tahoma", Font.BOLD, 10));
		chatsPanel.setRound(0);
		chatsPanel.setTabOverlay(2);
		chatsPanel.setTabRunIndent(1);
		chatsPanel.setTabInsets(new Insets(3, 2, 3, 2));
		chatsPanel.setBoldFont(true);
		chatsPanel.setTabBorderColor(Color.LIGHT_GRAY);
		chatsPanel.setSelectedTopBg(new Color(240, 248, 255));
		chatsPanel.setSelectedBottomBg(new Color(211, 211, 211));
		chatsPanel.setContentBorderColor(new Color(204, 204, 204));
		chatsPanel.setBottomBg(new Color(248, 248, 255));
		chatsPanel.setBackgroundColor(new Color(248, 248, 255));
		chatsPanel.setBackground(Color.LIGHT_GRAY);
		chatsPanel.setTopBg(new Color(169, 169, 169));
		chatsPanel.setFontSize(10);
		chatsPanel.setFocusTraversalKeysEnabled(false);
		chatsPanel.setBackground(new Color(248, 248, 255));
		chatsPanel.setAutoscrolls(true);
		chatsPanel.setInheritsPopupMenu(true);
		chatsPanel.setDoubleBuffered(true);
		chatsPanel.setFocusTraversalKeysEnabled(false);
		chatsPanel.setDoubleBuffered(true);

		add(chatsPanel);

		// CHAT ALL
		scrollAll.getWebVerticalScrollBar().setPreferredSize(new Dimension(12, 50));
		scrollAll.setShadeWidth(1);
		scrollAll.setRound(1);
		scrollAll.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollAll.setForeground(Color.DARK_GRAY);
		scrollAll.setBackground(Color.LIGHT_GRAY);
		scrollAll.setAutoscrolls(true);
		scrollAll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollAll.setBorder(null);
		chatsPanel.addTab("All     ", Img.create("general/IconGrey.png", 8, 8), scrollAll, null);
		// CHAT TRADE
		scrollTrade.getWebVerticalScrollBar().setPreferredSize(new Dimension(12, 50));
		scrollTrade.setShadeWidth(1);
		scrollTrade.setRound(1);
		scrollTrade.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollTrade.setForeground(Color.BLACK);
		scrollTrade.setBackground(Color.LIGHT_GRAY);
		scrollTrade.setAutoscrolls(true);
		scrollTrade.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTrade.setBorder(null);
		chatsPanel.addTab("<html><font color=000000>Trade</font></html>   ", Img.create("general/IconGrey.png", 8, 8), scrollTrade, null);
		// CHAT PARTY
		scrollParty.getWebVerticalScrollBar().setPreferredSize(new Dimension(12, 50));
		scrollParty.setShadeWidth(1);
		scrollParty.setRound(1);
		scrollParty.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollParty.setForeground(Color.BLACK);
		scrollParty.setBackground(Color.LIGHT_GRAY);
		scrollParty.setAutoscrolls(true);
		scrollParty.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollParty.setBorder(null);
		chatsPanel.addTab("Party   ", Img.create("general/IconGrey.png", 8, 8), scrollParty, null);
		// CHAT CLAN
		scrollClan.getWebVerticalScrollBar().setPreferredSize(new Dimension(12, 50));
		scrollClan.setShadeWidth(1);
		scrollClan.setRound(1);
		scrollClan.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollClan.setForeground(Color.BLACK);
		scrollClan.setBackground(Color.LIGHT_GRAY);
		scrollClan.setAutoscrolls(true);
		scrollClan.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollClan.setBorder(null);
		chatsPanel.addTab("Clan    ", Img.create("general/IconGrey.png", 8, 8), scrollClan, null);
		// CHAT ALLIANCE
		scrollAlliance.getWebVerticalScrollBar().setPreferredSize(new Dimension(12, 50));
		scrollAlliance.setShadeWidth(1);
		scrollAlliance.setRound(1);
		scrollAlliance.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollAlliance.setForeground(Color.BLACK);
		scrollAlliance.setBackground(Color.LIGHT_GRAY);
		scrollAlliance.setAutoscrolls(true);
		scrollAlliance.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollAlliance.setBorder(null);
		chatsPanel.addTab("Alliance", Img.create("general/IconGrey.png", 8, 8), scrollAlliance, null);

		chatSay = new FTextField("Say", false);
		chatSay.setHorizontalAlignment(SwingConstants.CENTER);
		chatSay.setOpaque(true);
		chatSay.setForeground(new Color(0, 0, 0));
		chatSay.setCaretPosition(1);
		chatSay.setBounds(2, 112, 526, 22);
		chatSay.setBackground(new Color(248, 248, 255));
		chatSay.setSelectedTextColor(Color.LIGHT_GRAY);
		chatSay.setColumns(10);
		chatSay.requestFocus();
		chatSay.addActionListener(a ->
		{
			ObjectsManager.getBot().getAi().say(chatSay.getText());
			chatSay.setText("");
		});
		add(chatSay);
	}

	/**
	 * Add texts to the chat and filter depending on the type of text. (TRADE, ALLIANCE, ALL, CLAN ....)
	 * @param text
	 */
	public void addChat(String text)
	{
		SwingUtilities.invokeLater(() ->
		{
			JLabel select = null;
			JScrollBar vertical = null;

			int s1 = text.indexOf("[") + 1;
			int s2 = text.indexOf("]");

			switch (text.substring(s1, s2))
			{
				case "TRADE":
					select = trade;
					vertical = scrollTrade.getVerticalScrollBar();
					break;
				case "ALLIANCE":
					select = alliance;
					vertical = scrollAlliance.getVerticalScrollBar();
					break;
				case "CLAN":
					select = clan;
					vertical = scrollClan.getVerticalScrollBar();
					break;
				case "PARTY":
					select = party;
					vertical = scrollParty.getVerticalScrollBar();
					break;
			}

			// A certain window is filtered
			if (select != null)
			{
				String aux = select.getText().replace("</html>", "").replace("<html>", "");
				aux += text + "<br>";
				// old chats are erased
				int size = aux.split("<br>").length;
				if (size > MAX_CHATS)
				{
					int pos = aux.indexOf("<br>");
					aux = aux.substring(pos + 4, aux.length() - 1);
				}
				select.setText("<html>" + aux + "</html>");
				// auto scroll :D
				vertical.setValue(vertical.getMaximum() + 1);
			}

			// it applies to the window all ---------------------------
			String auxAll = all.getText().replace("</html>", "").replace("<html>", "");
			auxAll += text + "<br>";
			// old chats are erased
			int sizeAll = auxAll.split("<br>").length;
			if (sizeAll > MAX_CHATS)
			{
				int pos = auxAll.indexOf("<br>");
				auxAll = auxAll.substring(pos + 4, auxAll.length() - 1);
			}
			all.setText("<html>" + auxAll + "</html>");
			// auto scroll :D
			JScrollBar verticalAll = scrollAll.getVerticalScrollBar();
			verticalAll.setValue(verticalAll.getMaximum() + 1);
		});
	}

	// XXX ------------------------------------------------------------------------

	public class SayLabel extends WebLabel
	{
		public SayLabel()
		{
			super("<html></html>");

			setFontSize(10);
			setDrawShade(true);
			setAlignmentX(Component.CENTER_ALIGNMENT);
			setOpaque(false);
			setAutoscrolls(true);
			setFocusTraversalPolicyProvider(true);
			setInheritsPopupMenu(false);
			setVerticalTextPosition(SwingConstants.BOTTOM);
			setVerticalAlignment(SwingConstants.BOTTOM);
			setHorizontalAlignment(SwingConstants.LEFT);
			setHorizontalTextPosition(SwingConstants.LEFT);
			setFont(new Font("Tahoma", Font.PLAIN, 10));
			setForeground(Color.BLACK);
		}
	}
}
