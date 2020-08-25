package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;
import com.alee.laf.separator.WebSeparator;

import gui.builder.WindowFrame;
import gui.look.fissban.FProgressBar;
import main.connection.bot.objects.BSummon;
import main.managers.ObjectsManager;
import main.managers.ThreadManager;

/**
 * @author fissban
 */
public class PetStatsWindow extends WindowFrame
{
	private FProgressBar hp = new FProgressBar();
	private FProgressBar mp = new FProgressBar();
	private final WebLabel name = new WebLabel("name");
	private final WebLabel title = new WebLabel("title");
	private final WebLabel lvl = new WebLabel("lv ");

	private final WebLabel pAtkValue = new WebLabel("0");
	private final WebLabel pDefValue = new WebLabel("0");
	private final WebLabel accuracyValue = new WebLabel("0");
	private final WebLabel critRateValue = new WebLabel("0");
	private final WebLabel atkSpdValue = new WebLabel("0");

	private final WebLabel mAtkValue = new WebLabel("0");
	private final WebLabel mDefValue = new WebLabel("0");
	private final WebLabel evasionValue = new WebLabel("0");
	private final WebLabel speedValue = new WebLabel("0");
	private final WebLabel castingSpdValue = new WebLabel("0");

	public PetStatsWindow()
	{
		super("Stats");
		setTitle("Pet Stats");

		setBounds(0, 0, 380, 409);

		// HP
		hp.setProgressBottomColor(new Color(255, 99, 71));
		hp.setProgressTopColor(new Color(220, 20, 60));
		hp.setHighlightDarkWhite(new Color(255, 99, 71));
		hp.setHighlightWhite(new Color(220, 20, 60));
		hp.setBounds(4, 33, 359, 18);
		// MP
		mp.setProgressBottomColor(new Color(0, 191, 255));
		mp.setProgressTopColor(new Color(30, 144, 255));
		mp.setHighlightDarkWhite(new Color(0, 191, 255));
		mp.setHighlightWhite(new Color(30, 144, 255));
		mp.setBounds(4, 51, 359, 18);

		panel.add(hp);
		panel.add(mp);
		name.setForeground(Color.WHITE);

		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setBounds(72, 2, 126, 20);
		panel.add(name);
		lvl.setText("Lv ");
		lvl.setForeground(Color.WHITE);

		lvl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lvl.setBounds(10, 2, 62, 20);
		panel.add(lvl);

		WebLabel pAtk = new WebLabel("P Atk:");
		pAtk.setForeground(Color.WHITE);
		pAtk.setBounds(14, 93, 83, 14);
		panel.add(pAtk);

		WebLabel pDef = new WebLabel("P Def:");
		pDef.setForeground(Color.WHITE);
		pDef.setBounds(14, 107, 83, 14);
		panel.add(pDef);

		WebLabel accuracy = new WebLabel("Accuracy:");
		accuracy.setForeground(Color.WHITE);
		accuracy.setBounds(14, 121, 83, 14);
		panel.add(accuracy);

		WebLabel critRate = new WebLabel("Crit Rate:");
		critRate.setForeground(Color.WHITE);
		critRate.setBounds(14, 135, 83, 14);
		panel.add(critRate);

		WebLabel atkSpd = new WebLabel("Atk Spd:");
		atkSpd.setForeground(Color.WHITE);
		atkSpd.setBounds(14, 149, 83, 14);
		panel.add(atkSpd);

		WebLabel mAtk = new WebLabel("M Atk:");
		mAtk.setForeground(Color.WHITE);
		mAtk.setBounds(195, 93, 83, 14);
		panel.add(mAtk);

		WebLabel mDef = new WebLabel("M Def:");
		mDef.setForeground(Color.WHITE);
		mDef.setBounds(195, 107, 83, 14);
		panel.add(mDef);

		WebLabel evasion = new WebLabel("Evasion:");
		evasion.setForeground(Color.WHITE);
		evasion.setBounds(195, 121, 83, 14);
		panel.add(evasion);

		WebLabel speed = new WebLabel("Speed:");
		speed.setForeground(Color.WHITE);
		speed.setBounds(195, 135, 83, 14);
		panel.add(speed);

		WebLabel castingSpd = new WebLabel("Casting Spd:");
		castingSpd.setForeground(Color.WHITE);
		castingSpd.setBounds(195, 149, 83, 14);
		panel.add(castingSpd);

		pAtkValue.setBounds(107, 93, 79, 14);
		panel.add(pAtkValue);

		pDefValue.setBounds(107, 107, 79, 14);
		panel.add(pDefValue);

		accuracyValue.setBounds(107, 121, 79, 14);
		panel.add(accuracyValue);

		critRateValue.setBounds(107, 135, 79, 14);
		panel.add(critRateValue);

		atkSpdValue.setBounds(107, 149, 79, 14);
		panel.add(atkSpdValue);

		mAtkValue.setBounds(288, 93, 79, 14);
		panel.add(mAtkValue);

		mDefValue.setBounds(288, 107, 79, 14);
		panel.add(mDefValue);

		evasionValue.setBounds(288, 121, 79, 14);
		panel.add(evasionValue);

		speedValue.setBounds(288, 135, 79, 14);
		panel.add(speedValue);

		castingSpdValue.setBounds(288, 149, 79, 14);
		panel.add(castingSpdValue);

		// SEPARATORS -----------------------------------------------------------------
		WebSeparator separator1 = new WebSeparator();
		separator1.setBounds(4, 80, 363, 2);
		panel.add(separator1);

		WebSeparator separator3 = new WebSeparator();
		separator3.setBounds(4, 178, 363, 2);
		panel.add(separator3);

		WebSeparator separator2 = new WebSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(185, 80, 1, 100);
		panel.add(separator2);

		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		title.setBounds(208, 2, 126, 20);
		panel.add(title);

		ThreadManager.scheduleAtFixedRate(() -> updateGUI(), 0, 1000);
	}

	private void updateGUI()
	{
		if (!isVisible())
		{
			return;
		}

		SwingUtilities.invokeLater(() ->
		{
			BSummon summon = ObjectsManager.getSummon();

			if (summon != null)
			{
				hp.setMaximum(summon.getMaxHp());
				hp.setValue(summon.getCurrentHp());
				hp.setString(summon.getGuiHp());

				mp.setMaximum(summon.getMaxMp());
				mp.setValue(summon.getCurrentMp());
				mp.setString(summon.getGuiMp());

				lvl.setText("Lv " + summon.getLevel());
				name.setText(summon.getName());
				title.setText(summon.getTitle());

				pAtkValue.setText(summon.getPAtk() + "");
				pDefValue.setText(summon.getPDef() + "");
				accuracyValue.setText(summon.getAccuracy() + "");
				critRateValue.setText(summon.getCriticalRate() + "");
				atkSpdValue.setText(summon.getPAtkSpd() + "");

				mAtkValue.setText(summon.getMAtk() + "");
				mDefValue.setText(summon.getMDef() + "");
				evasionValue.setText(summon.getEvasion() + "");
				speedValue.setText(summon.getRunSpd() + ""); // ??
				castingSpdValue.setText(summon.getMAtkSpd() + "");
			}
			else
			{
				hp.setMaximum(100);
				hp.setValue(0);
				hp.setString("HP: 0 / 0");

				mp.setMaximum(100);
				mp.setValue(0);
				mp.setString("MP: 0 / 0");

				lvl.setText("Lv ");
				name.setText("...");
				title.setText("...");

				pAtkValue.setText("0");
				pDefValue.setText("0");
				accuracyValue.setText("0");
				critRateValue.setText("0");
				atkSpdValue.setText("0");

				mAtkValue.setText("0");
				mDefValue.setText("0");
				evasionValue.setText("0");
				speedValue.setText("0"); // ??
				castingSpdValue.setText("0");
			}

		});
	}

	public void open()
	{
		setVisible(!isVisible());
	}

	public static PetStatsWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final PetStatsWindow INSTANCE = new PetStatsWindow();
	}
}
