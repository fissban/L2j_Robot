package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.list.WebList;
import com.alee.laf.list.WebListModel;
import com.alee.laf.tabbedpane.TabStretchType;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;

import gui.builder.KnownList;
import gui.builder.KnownScrollPane;
import gui.look.fissban.FButton;
import gui.look.fissban.FPanelDegrade;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BObject;
import main.connection.bot.objects.BPlayer;
import main.managers.ObjectsManager;
import main.util.Img;

/**
 * @author fissban
 */
public class KnownListPanel extends FPanelDegrade
{
	private final Map<String, WebList> scrollKnownlist = new HashMap<>();
	private final FButton actionKnownlist = new FButton("Action");

	@SuppressWarnings("unchecked")
	public KnownListPanel()
	{
		super();

		// init scrolls
		scrollKnownlist.put("BMonster", new KnownList());
		scrollKnownlist.put("BNpc", new KnownList());
		scrollKnownlist.put("BPlayer", new KnownList());
		scrollKnownlist.put("BItem", new KnownList());

		setBounds(533, 482, 475, 137);

		WebTabbedPane knownlistTab = new WebTabbedPane(SwingConstants.TOP);
		knownlistTab.setBorder(null);
		knownlistTab.setBounds(3, 3, 469, 108);
		knownlistTab.setTabStretchType(TabStretchType.never);
		knownlistTab.setTabbedPaneStyle(TabbedPaneStyle.attached);
		knownlistTab.setFont(new Font("Tahoma", Font.BOLD, 10));
		knownlistTab.setRound(0);
		knownlistTab.setTabOverlay(2);
		knownlistTab.setTabRunIndent(1);
		knownlistTab.setOpaque(true);
		knownlistTab.setTabPlacement(JTabbedPane.BOTTOM);
		knownlistTab.setTabInsets(new Insets(3, 2, 3, 2));
		knownlistTab.setShadeWidth(0);
		knownlistTab.setBoldFont(true);
		knownlistTab.setTabBorderColor(Color.LIGHT_GRAY);
		knownlistTab.setSelectedTopBg(new Color(240, 248, 255));
		knownlistTab.setSelectedBottomBg(new Color(211, 211, 211));
		knownlistTab.setContentBorderColor(new Color(204, 204, 204));
		knownlistTab.setBottomBg(new Color(248, 248, 255));
		knownlistTab.setBackgroundColor(new Color(248, 248, 255));
		knownlistTab.setBackground(Color.LIGHT_GRAY);
		knownlistTab.setTopBg(new Color(169, 169, 169));
		knownlistTab.setFontSize(10);
		knownlistTab.setFocusTraversalKeysEnabled(false);
		knownlistTab.setBackground(new Color(248, 248, 255));
		knownlistTab.setAutoscrolls(true);
		knownlistTab.setInheritsPopupMenu(true);
		knownlistTab.setDoubleBuffered(true);
		KnownScrollPane knownScrollPane = new KnownScrollPane(scrollKnownlist.get("BMonster"));
		knownScrollPane.setBorder(null);
		knownlistTab.addTab("Monsters", Img.create("general/IconRed.png", 8, 8), knownScrollPane, null);
		KnownScrollPane knownScrollPane_1 = new KnownScrollPane(scrollKnownlist.get("BNpc"));
		knownScrollPane_1.setBorder(null);
		knownlistTab.addTab("Npc     ", Img.create("general/IconGrey.png", 8, 8), knownScrollPane_1, null);
		KnownScrollPane knownScrollPane_2 = new KnownScrollPane(scrollKnownlist.get("BPlayer"));
		knownScrollPane_2.setBorder(null);
		knownlistTab.addTab("Pc      ", Img.create("general/IconYellow.png", 8, 8), knownScrollPane_2, null);
		KnownScrollPane knownScrollPane_3 = new KnownScrollPane(scrollKnownlist.get("BItem"));
		knownScrollPane_3.setBorder(null);
		knownlistTab.addTab("Items   ", Img.create("general/IconBlue.png", 8, 8), knownScrollPane_3, null);
		add(knownlistTab);

		actionKnownlist.setBackground(new Color(204, 204, 204));
		actionKnownlist.setOpaque(true);
		actionKnownlist.setAlignmentX(Component.CENTER_ALIGNMENT);
		actionKnownlist.setBorderPainted(true);
		actionKnownlist.setBounds(2, 112, 472, 22);
		actionKnownlist.addActionListener(e ->
		{
			WebList scroll = null;
			switch (knownlistTab.getSelectedIndex())
			{
				case 0:
					scroll = scrollKnownlist.get("BMonster");
					break;
				case 1:
					scroll = scrollKnownlist.get("BNpc");
					break;
				case 2:
					scroll = scrollKnownlist.get("BPlayer");
					break;
				case 3:
					scroll = scrollKnownlist.get("BItem");
					break;
			}

			if (scroll.getSelectedIndex() == -1)
			{
				JOptionPane.showConfirmDialog(this, "Need select object in list", "Game", JOptionPane.WARNING_MESSAGE);
				return;
			}

			String selected = scroll.getSelectedValue().toString();

			int start = selected.indexOf("[");
			int end = selected.indexOf("]");

			BObject object = ObjectsManager.get(Integer.parseInt(selected.substring(start + 1, end)));

			if (object instanceof BItem)
			{
				ObjectsManager.getBot().getAi().interact(object);
			}
			else if (object instanceof BNpc)
			{
				if (((BNpc) object).isAttackable())
				{
					//TODO only attack mele?
					ObjectsManager.getBot().getAi().attackMele(object);
				}
				else
				{
					ObjectsManager.getBot().getAi().talk(object);
				}
			}
		});

		add(actionKnownlist);
	}

	/**
	 * Removes an item from the knownlist
	 */
	@SuppressWarnings("unchecked")
	public void removeKnown(int objectId)
	{
		SwingUtilities.invokeLater(() ->
		{
			scrollKnownlist.forEach((k, model) ->
			{
				WebListModel<String> m1 = model.getWebModel();

				m1.getElements().stream().forEach(e ->
				{
					if (e.contains("" + objectId))
					{
						m1.remove(e);
						model.repaint();
						return;
					}
				});
			});

			repaint();
		});
	}

	/**
	 * Add an item from the knownlist
	 */
	@SuppressWarnings("unchecked")
	public void addKnown(BObject c)
	{
		SwingUtilities.invokeLater(() ->
		{
			if (c instanceof BPlayer)
			{
				scrollKnownlist.get("BPlayer").getWebModel().addElement(c.getDescription());
				scrollKnownlist.get("BPlayer").repaint();
			}
			else if (c instanceof BItem)
			{
				scrollKnownlist.get("BItem").getWebModel().addElement(c.getDescription());
				scrollKnownlist.get("BItem").repaint();
			}
			else if (c instanceof BNpc)
			{
				if (((BNpc) c).isAttackable())
				{
					scrollKnownlist.get("BMonster").getWebModel().addElement(c.getDescription());
					scrollKnownlist.get("BMonster").repaint();
				}
				else
				{
					scrollKnownlist.get("BNpc").getWebModel().addElement(c.getDescription());
					scrollKnownlist.get("BNpc").repaint();
				}
			}

			repaint();
		});
	}
}
