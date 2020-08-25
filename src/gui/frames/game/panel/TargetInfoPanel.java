package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;

import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FProgressBar;
import main.connection.bot.objects.BCreature;

/**
 * @author fissban
 */
public class TargetInfoPanel extends FPanelDegrade
{
	// player name
	public WebLabel targetTop = new WebLabel("TargetName");
	private FProgressBar hp = new FProgressBar();
	private FProgressBar mp = new FProgressBar();

	public TargetInfoPanel()
	{
		super();

		setBounds(780, 0, 228, 53);

		targetTop.setBoldFont(true);
		targetTop.setFont(new Font("Tahoma", Font.BOLD, 9));
		targetTop.setDrawShade(true);
		targetTop.setShadeColor(new Color(0, 102, 153));
		targetTop.setForeground(new Color(255, 255, 255));
		targetTop.setAlignmentX(Component.CENTER_ALIGNMENT);
		targetTop.setBackground(new Color(248, 248, 255));
		targetTop.setBounds(1, 1, 226, 14);
		targetTop.setHorizontalTextPosition(SwingConstants.CENTER);
		targetTop.setHorizontalAlignment(SwingConstants.CENTER);
		targetTop.setBorder(null);
		add(targetTop);

		// HP
		hp.setProgressBottomColor(new Color(255, 99, 71));
		hp.setProgressTopColor(new Color(220, 20, 60));
		hp.setHighlightDarkWhite(new Color(255, 99, 71));
		hp.setHighlightWhite(new Color(220, 20, 60));
		hp.setString("HP: 0 / 100");
		hp.setBounds(2, 16, 224, 18);
		hp.setInnerRound(1);
		add(hp);

		// MP
		mp.setProgressBottomColor(new Color(0, 191, 255));
		mp.setProgressTopColor(new Color(30, 144, 255));
		mp.setHighlightDarkWhite(new Color(0, 191, 255));
		mp.setHighlightWhite(new Color(30, 144, 255));
		mp.setString("MP: 0 / 100");
		mp.setBounds(2, 33, 224, 18);
		mp.setInnerRound(1);
		add(mp);
	}

	public void updateStats(BCreature p)
	{
		SwingUtilities.invokeLater(() ->
		{
			targetTop.setText(p.getName() + " Lv " + p.getLevel());
			//HP
			hp.setMaximum(p.getMaxHp());
			hp.setValue(p.getCurrentHp());
			hp.setString("HP: " + p.getCurrentHp() + " / " + p.getMaxHp());
			//MP
			mp.setMaximum(p.getMaxMp());
			mp.setValue(p.getCurrentMp());
			mp.setString("MP: " + p.getCurrentMp() + " / " + p.getMaxMp());
		});
	}

}
