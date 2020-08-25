package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;

import gui.frames.game.GameFrame;
import gui.frames.game.windows.PetStatsWindow;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FProgressBar;
import main.connection.bot.objects.BSummon;
import main.managers.ObjectsManager;
import main.util.Img;

/**
 * @author fissban
 */
public class PetInfoPanel extends FPanelDegrade
{
	private final WebLabel petTop = new WebLabel("Pet");
	private final FProgressBar hpPet = new FProgressBar();
	private final FProgressBar mpPet = new FProgressBar();
	private final FProgressBar fedPet = new FProgressBar();

	public PetInfoPanel()
	{
		super();

		setBounds(0, 152, 267, 49);

		petTop.setDrawShade(true);
		petTop.setShadeColor(new Color(0, 102, 153));
		petTop.setForeground(new Color(255, 255, 255));
		petTop.setFont(new Font("Tahoma", Font.BOLD, 9));
		petTop.setHorizontalTextPosition(SwingConstants.CENTER);
		petTop.setHorizontalAlignment(SwingConstants.CENTER);
		petTop.setBorder(null);
		petTop.setBoldFont(true);
		petTop.setBackground(new Color(102, 153, 255));
		petTop.setBounds(1, 1, 264, 14);
		add(petTop);

		hpPet.setFont(new Font("Arial", Font.PLAIN, 9));
		hpPet.setValue(10);
		hpPet.setString("HP: 0 / 100");
		hpPet.setProgressTopColor(new Color(220, 20, 60));
		hpPet.setProgressBottomColor(new Color(255, 99, 71));
		hpPet.setHighlightWhite(new Color(220, 20, 60));
		hpPet.setHighlightDarkWhite(new Color(255, 99, 71));
		hpPet.setBounds(2, 16, 263, 12);
		hpPet.setInnerRound(1);
		add(hpPet);

		mpPet.setFont(new Font("Arial", Font.PLAIN, 9));
		mpPet.setValue(10);
		mpPet.setString("MP: 0 / 100");
		mpPet.setProgressTopColor(new Color(30, 144, 255));
		mpPet.setProgressBottomColor(new Color(0, 191, 255));
		mpPet.setHighlightWhite(new Color(30, 144, 255));
		mpPet.setHighlightDarkWhite(new Color(0, 191, 255));
		mpPet.setBounds(2, 26, 263, 12);
		mpPet.setInnerRound(1);
		add(mpPet);

		fedPet.setValue(10);
		fedPet.setString("");
		fedPet.setProgressTopColor(new Color(50, 205, 50));
		fedPet.setProgressBottomColor(new Color(0, 139, 139));
		fedPet.setHighlightWhite(new Color(0, 139, 139));
		fedPet.setHighlightDarkWhite(new Color(50, 205, 50));
		fedPet.setBounds(2, 36, 263, 12);
		add(fedPet);

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
				PetStatsWindow.getInstance().setLocationRelativeTo(GameFrame.getInstance());
				PetStatsWindow.getInstance().open();
			}
		});
	}

	public void updateStats()
	{
		SwingUtilities.invokeLater(() ->
		{
			BSummon summon = ObjectsManager.getSummon();
			if (summon == null)
			{
				// ignore this
			}
			petTop.setText(summon.getName() + " Lv " + summon.getLevel());
			//HP
			hpPet.setMaximum(summon.getMaxHp());
			hpPet.setValue(summon.getCurrentHp());
			hpPet.setString("HP: " + summon.getCurrentHp() + " / " + summon.getMaxHp());
			//MP
			mpPet.setMaximum(summon.getMaxMp());
			mpPet.setValue(summon.getCurrentMp());
			mpPet.setString("MP: " + summon.getCurrentMp() + " / " + summon.getMaxMp());
			// Feed or remaintimming
			fedPet.setMaximum(summon.getMaxFed());
			fedPet.setValue(summon.getCurFeed());
		});
	}
}
