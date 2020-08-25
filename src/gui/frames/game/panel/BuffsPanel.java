package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;

import gui.look.fissban.FPanelDegrade;
import main.managers.ObjectsManager;
import main.model.EffectModel;
import main.util.Img;
import main.util.Util;

/**
 * @author fissban
 */
public class BuffsPanel extends FPanelDegrade
{
	// lista objetos creados en el live map
	private Map<Integer, WebLabel> buffsObjects = new HashMap<>();

	public BuffsPanel()
	{
		super();

		setBounds(0, 89, 267, 62);

		WebLabel buffsTop = new WebLabel("Buffs");
		buffsTop.setDrawShade(true);
		buffsTop.setShadeColor(new Color(0, 102, 153));
		buffsTop.setForeground(new Color(255, 255, 255));
		buffsTop.setFont(new Font("Tahoma", Font.BOLD, 9));
		buffsTop.setBorder(null);
		buffsTop.setBoldFont(true);
		buffsTop.setHorizontalTextPosition(SwingConstants.CENTER);
		buffsTop.setHorizontalAlignment(SwingConstants.CENTER);
		buffsTop.setBackground(new Color(102, 153, 255));
		buffsTop.setBounds(1, 1, 262, 14);
		add(buffsTop);
	}

	public void generateBuffs()
	{
		SwingUtilities.invokeLater(() ->
		{
			// se remueven todos los objetos "buffs"
			buffsObjects.forEach((k, v) ->
			{
				remove(v);
			});

			buffsObjects.clear();

			// the new list of buffs is created
			int line = 0;
			int pos = 0;
			for (EffectModel e : ObjectsManager.getBot().getEffects().getAll())
			{
				WebLabel buff = new WebLabel();
				buff.setBorder(null);
				buff.setIcon(Img.create("skills/" + Util.getSkillIcon(e.getSkillId()) + ".png", 24, 24));
				buff.setVisible(true);
				// set pos in windows
				buff.setBounds(pos * 24 + 3, (line * 24) + 14, 24, 24);

				TooltipManager.setTooltip(buff, e.getName() + " Duration: " + e.getDuration() + " secs", TooltipWay.down, 0);
				buff.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseEntered(MouseEvent event)
					{
						// Remove old tool tip
						TooltipManager.removeTooltips(buff);
						// Add new tool tip
						TooltipManager.setTooltip(buff, "Duration: " + e.getDuration() + " secs", TooltipWay.down, 0);
					}
				});
				buff.repaint();

				add(buff, 0);
				buffsObjects.put(e.getSkillId(), buff);
				pos++;
				if (pos % 10 == 0)
				{
					line++;
					pos = 0;
				}
			}

			repaint();
		});
	}
}
