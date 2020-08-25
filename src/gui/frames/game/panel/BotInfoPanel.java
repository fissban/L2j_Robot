package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;

import gui.frames.game.GameFrame;
import gui.frames.game.windows.BotStatsWindow;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FProgressBar;
import main.connection.bot.objects.BPlayer;
import main.data.ExperienceData;
import main.managers.ObjectsManager;
import main.util.Img;

/**
 * @author fissban
 */
public class BotInfoPanel extends FPanelDegrade
{
	// player name
	public WebLabel playerTop = new WebLabel("PlayerName");
	private FProgressBar cp = new FProgressBar();
	private FProgressBar hp = new FProgressBar();
	private FProgressBar mp = new FProgressBar();
	private FProgressBar exp = new FProgressBar();

	public BotInfoPanel()
	{
		super();

		setBounds(0, 0, 267, 88);

		playerTop.setFont(new Font("Tahoma", Font.BOLD, 9));
		playerTop.setDrawShade(true);
		playerTop.setShadeColor(new Color(0, 102, 153));
		playerTop.setForeground(new Color(255, 255, 255));
		playerTop.setBoldFont(true);
		playerTop.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerTop.setBackground(null);
		playerTop.setBounds(1, 1, 264, 14);
		playerTop.setHorizontalTextPosition(SwingConstants.CENTER);
		playerTop.setHorizontalAlignment(SwingConstants.CENTER);
		playerTop.setBorder(null);
		add(playerTop);

		// CP
		cp.setProgressBottomColor(new Color(255, 204, 102));
		cp.setProgressTopColor(new Color(184, 134, 11));
		cp.setHighlightDarkWhite(new Color(255, 204, 102));
		cp.setHighlightWhite(new Color(184, 134, 11));
		cp.setBgBottom(new Color(230, 230, 250));
		cp.setString("HP: 0 / 100");
		cp.setBounds(2, 14, 263, 18);
		cp.setInnerRound(1);
		add(cp);

		// HP
		hp.setProgressBottomColor(new Color(255, 99, 71));
		hp.setProgressTopColor(new Color(220, 20, 60));
		hp.setHighlightDarkWhite(new Color(255, 99, 71));
		hp.setHighlightWhite(new Color(220, 20, 60));
		hp.setString("HP: 0 / 100");
		hp.setBounds(2, 32, 263, 18);
		hp.setInnerRound(1);
		add(hp);

		// MP
		mp.setProgressBottomColor(new Color(0, 191, 255));
		mp.setProgressTopColor(new Color(30, 144, 255));
		mp.setHighlightDarkWhite(new Color(0, 191, 255));
		mp.setHighlightWhite(new Color(30, 144, 255));
		mp.setString("MP: 0 / 100");
		mp.setBounds(2, 50, 263, 18);
		mp.setInnerRound(1);
		add(mp);

		// EXP
		exp.setProgressBottomColor(new Color(248, 248, 255));
		exp.setProgressTopColor(new Color(211, 211, 211));
		exp.setHighlightWhite(new Color(230, 230, 250));
		exp.setHighlightDarkWhite(new Color(248, 248, 255));
		exp.setString("EXP: 50%");
		exp.setBounds(2, 68, 263, 18);
		exp.setInnerRound(1);
		add(exp);

		WebLabel info = new WebLabel("");
		info.setBounds(250, 1, 14, 14);
		info.setIcon(Img.create("general/icons/add.png", 12, 12));
		add(info);
		// tooltip
		TooltipManager.setTooltip(info, "more info", TooltipWay.trailing, 0);
		// action
		info.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				BotStatsWindow.getInstance().setLocationRelativeTo(GameFrame.getInstance());
				BotStatsWindow.getInstance().open();
			}
		});
	}

	public void updateStats()
	{
		SwingUtilities.invokeLater(() ->
		{
			BPlayer p = ObjectsManager.getBot();

			playerTop.setText("Lv " + p.getLevel() + " " + p.getName());
			//HP
			hp.setMaximum(p.getMaxHp());
			hp.setValue(p.getCurrentHp());
			hp.setString(p.getGuiHp());
			//MP
			mp.setMaximum(p.getMaxMp());
			mp.setValue(p.getCurrentMp());
			mp.setString(p.getGuiMp());
			//CP
			cp.setMaximum(p.getMaxCp());
			cp.setValue(p.getCurrentCp());
			cp.setString(p.getGuiCp());
			//EXP
			long expNextLevel = ExperienceData.LEVEL[p.getLevel() + 1] - ExperienceData.LEVEL[p.getLevel()];
			long expNeed = p.getExp() - ExperienceData.LEVEL[p.getLevel() + 1];
			int e = (int) (expNeed * 100 / expNextLevel);
			exp.setMaximum(100);
			exp.setValue(e);
			exp.setString("EXP: " + e + "%");
		});
	}
}
