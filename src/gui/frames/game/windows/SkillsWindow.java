package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.alee.laf.label.WebLabel;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;

import gui.builder.WindowFrame;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FScrollPane;
import main.managers.ObjectsManager;
import main.model.SkillModel;
import main.network.game.client.ingame.RequestSkillList;
import main.util.Img;
import main.util.Util;

/**
 * @author fissban
 */
public class SkillsWindow extends WindowFrame
{
	private static final long serialVersionUID = 1L;

	private final List<WebLabel> objects = new ArrayList<>();
	private FPanelDegrade panelPasives = new FPanelDegrade();
	private FScrollPane pasives = new FScrollPane(panelPasives);
	private FPanelDegrade panelActives = new FPanelDegrade();
	private FScrollPane actives = new FScrollPane(panelActives);

	public SkillsWindow()
	{
		super("Skills");
		setBounds(0, 0, 317, 400);

		WebTabbedPane tabbedPane = new WebTabbedPane(SwingConstants.TOP);
		tabbedPane.setBounds(0, 2, 299, 356);
		tabbedPane.setShadeWidth(0);
		tabbedPane.setRound(0);
		tabbedPane.setBorder(null);
		panel.add(tabbedPane);

		pasives.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		pasives.setDrawBorder(false);
		pasives.setRadius(2);
		pasives.setShadeWidth(0);
		pasives.setRound(0);
		pasives.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pasives.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		actives.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		actives.setDrawBorder(false);
		actives.setRadius(2);
		actives.setShadeWidth(0);
		actives.setRound(0);
		actives.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		actives.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		tabbedPane.addTab("Actives", null, actives, null);
		tabbedPane.addTab("Pasives", null, pasives, null);
	}

	public void open()
	{
		setVisible(!isVisible());

		if (isVisible())
		{
			ObjectsManager.getBot().sendPacket(new RequestSkillList());
		}
	}

	public void update(List<SkillModel> skills)
	{
		SwingUtilities.invokeLater(() ->
		{
			// clear all objects
			objects.forEach(o ->
			{
				remove(o);
				TooltipManager.removeTooltips(o);
			});
			objects.clear();

			int MAX_FILE = 8;

			int file = 0;
			int colum = 0;

			for (SkillModel s : skills)
			{
				if (s.isPasive())
				{
					addSkill(s, file, colum);

					file++;
					if (file % MAX_FILE == 0)
					{
						colum++;
						file = 0;
					}
				}
			}

			file = 0;
			colum = 0;

			for (SkillModel s : skills)
			{
				if (!s.isPasive())
				{
					addSkill(s, file, colum);

					file++;
					if (file % MAX_FILE == 0)
					{
						colum++;
						file = 0;
					}
				}
			}

			repaint();
		});
	}

	private void addSkill(SkillModel s, int file, int colum)
	{
		WebLabel wl = new WebLabel("");
		wl.setBorder(null);
		wl.setIcon(Img.create("skills/" + Util.getSkillIcon(s.getId()) + ".png", 32, 32));
		wl.setBounds((file * 32 + (file * 3)) + 6, (colum * 32 + (colum * 3)) + 6, 32, 32);
		wl.setBorder(new LineBorder(new Color(102, 153, 255)));
		wl.setVisible(true);

		// TODO agregar su propio handler
		//InventoryMenu handler = new InventoryMenu(wl);
		//wl.addMouseListener(handler);
		//wl.addMouseMotionListener(handler);

		TooltipManager.setTooltip(wl, "<html><body><font color=FFFF00>" + s.getName() + "</font> Lvl: <font color=FFFF00>" + s.getLevel() + "</font></body></html>", TooltipWay.down, 0);

		objects.add(wl);

		if (s.isPasive())
		{
			panelPasives.add(wl);
			panelPasives.repaint();
		}
		else
		{
			panelActives.add(wl);
			panelActives.repaint();
		}
	}

	public static SkillsWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final SkillsWindow INSTANCE = new SkillsWindow();
	}
}
