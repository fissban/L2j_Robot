package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.Future;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;
import com.alee.laf.separator.WebSeparator;

import gui.builder.WindowFrame;
import gui.look.fissban.FProgressBar;
import main.connection.bot.objects.BPlayer;
import main.data.ExperienceData;
import main.managers.ObjectsManager;
import main.managers.ThreadManager;

/**
 * @author fissban
 */
public class BotStatsWindow extends WindowFrame
{
	private static Future<?> taskUpdate = null;

	private FProgressBar cp = new FProgressBar();
	private FProgressBar hp = new FProgressBar();
	private FProgressBar mp = new FProgressBar();
	private FProgressBar exp = new FProgressBar();
	private final WebLabel name = new WebLabel("name");
	private final WebLabel title = new WebLabel("title");
	private final WebLabel lvl = new WebLabel("lv ");
	private final WebLabel race = new WebLabel("Race:");

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

	private final WebLabel strValue = new WebLabel("0");
	private final WebLabel intValue = new WebLabel("0");

	private final WebLabel dexValue = new WebLabel("0");
	private final WebLabel witValue = new WebLabel("0");

	private final WebLabel conValue = new WebLabel("0");
	private final WebLabel menValue = new WebLabel("0");

	public BotStatsWindow()
	{
		super("Stats");
		setTitle("Bot Stats");

		setBounds(0, 0, 380, 409);

		// CP
		cp.setProgressBottomColor(new Color(255, 204, 102));
		cp.setProgressTopColor(new Color(184, 134, 11));
		cp.setHighlightDarkWhite(new Color(255, 204, 102));
		cp.setHighlightWhite(new Color(184, 134, 11));
		cp.setBgBottom(new Color(230, 230, 250));
		cp.setBounds(4, 55, 359, 18);
		// HP
		hp.setProgressBottomColor(new Color(255, 99, 71));
		hp.setProgressTopColor(new Color(220, 20, 60));
		hp.setHighlightDarkWhite(new Color(255, 99, 71));
		hp.setHighlightWhite(new Color(220, 20, 60));
		hp.setBounds(4, 73, 359, 18);
		// MP
		mp.setProgressBottomColor(new Color(0, 191, 255));
		mp.setProgressTopColor(new Color(30, 144, 255));
		mp.setHighlightDarkWhite(new Color(0, 191, 255));
		mp.setHighlightWhite(new Color(30, 144, 255));
		mp.setBounds(4, 91, 359, 18);
		// EXP
		exp.setProgressBottomColor(new Color(248, 248, 255));
		exp.setProgressTopColor(new Color(211, 211, 211));
		exp.setHighlightWhite(new Color(230, 230, 250));
		exp.setHighlightDarkWhite(new Color(248, 248, 255));
		exp.setBounds(4, 109, 359, 18);

		panel.add(cp);
		panel.add(hp);
		panel.add(mp);
		panel.add(exp);
		name.setForeground(Color.WHITE);

		name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name.setBounds(72, 2, 126, 20);
		panel.add(name);
		lvl.setText("Lv ");
		lvl.setForeground(Color.WHITE);

		lvl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lvl.setBounds(10, 2, 62, 20);
		panel.add(lvl);
		race.setForeground(Color.WHITE);

		race.setFont(new Font("Tahoma", Font.PLAIN, 16));
		race.setBounds(10, 26, 160, 20);
		panel.add(race);

		WebLabel pAtk = new WebLabel("P Atk:");
		pAtk.setForeground(Color.WHITE);
		pAtk.setBounds(10, 151, 83, 14);
		panel.add(pAtk);

		WebLabel pDef = new WebLabel("P Def:");
		pDef.setForeground(Color.WHITE);
		pDef.setBounds(10, 165, 83, 14);
		panel.add(pDef);

		WebLabel accuracy = new WebLabel("Accuracy:");
		accuracy.setForeground(Color.WHITE);
		accuracy.setBounds(10, 179, 83, 14);
		panel.add(accuracy);

		WebLabel critRate = new WebLabel("Crit Rate:");
		critRate.setForeground(Color.WHITE);
		critRate.setBounds(10, 193, 83, 14);
		panel.add(critRate);

		WebLabel atkSpd = new WebLabel("Atk Spd:");
		atkSpd.setForeground(Color.WHITE);
		atkSpd.setBounds(10, 207, 83, 14);
		panel.add(atkSpd);

		WebLabel mAtk = new WebLabel("M Atk:");
		mAtk.setForeground(Color.WHITE);
		mAtk.setBounds(191, 151, 83, 14);
		panel.add(mAtk);

		WebLabel mDef = new WebLabel("M Def:");
		mDef.setForeground(Color.WHITE);
		mDef.setBounds(191, 165, 83, 14);
		panel.add(mDef);

		WebLabel evasion = new WebLabel("Evasion:");
		evasion.setForeground(Color.WHITE);
		evasion.setBounds(191, 179, 83, 14);
		panel.add(evasion);

		WebLabel speed = new WebLabel("Speed:");
		speed.setForeground(Color.WHITE);
		speed.setBounds(191, 193, 83, 14);
		panel.add(speed);

		WebLabel castingSpd = new WebLabel("Casting Spd:");
		castingSpd.setForeground(Color.WHITE);
		castingSpd.setBounds(191, 207, 83, 14);
		panel.add(castingSpd);

		pAtkValue.setBounds(103, 151, 79, 14);
		panel.add(pAtkValue);

		pDefValue.setBounds(103, 165, 79, 14);
		panel.add(pDefValue);

		accuracyValue.setBounds(103, 179, 79, 14);
		panel.add(accuracyValue);

		critRateValue.setBounds(103, 193, 79, 14);
		panel.add(critRateValue);

		atkSpdValue.setBounds(103, 207, 79, 14);
		panel.add(atkSpdValue);

		mAtkValue.setBounds(284, 151, 79, 14);
		panel.add(mAtkValue);

		mDefValue.setBounds(284, 165, 79, 14);
		panel.add(mDefValue);

		evasionValue.setBounds(284, 179, 79, 14);
		panel.add(evasionValue);

		speedValue.setBounds(284, 193, 79, 14);
		panel.add(speedValue);

		castingSpdValue.setBounds(284, 207, 79, 14);
		panel.add(castingSpdValue);

		WebLabel pDex = new WebLabel("DEX:");
		pDex.setForeground(Color.WHITE);
		pDex.setBounds(152, 242, 31, 14);
		panel.add(pDex);

		WebLabel pWit = new WebLabel("WIT:");
		pWit.setForeground(Color.WHITE);
		pWit.setBounds(153, 259, 31, 14);
		panel.add(pWit);

		WebLabel pStr = new WebLabel("STR:");
		pStr.setForeground(Color.WHITE);
		pStr.setBounds(25, 242, 31, 14);
		panel.add(pStr);

		WebLabel pInt = new WebLabel("INT:");
		pInt.setForeground(Color.WHITE);
		pInt.setBounds(26, 259, 31, 14);
		panel.add(pInt);

		WebLabel pCon = new WebLabel("CON:");
		pCon.setForeground(Color.WHITE);
		pCon.setBounds(264, 242, 31, 14);
		panel.add(pCon);

		WebLabel pMen = new WebLabel("MEN:");
		pMen.setForeground(Color.WHITE);
		pMen.setBounds(265, 259, 31, 14);
		panel.add(pMen);

		// POINT -----------------------------------------------------------------------
		strValue.setBounds(57, 242, 46, 14);
		panel.add(strValue);

		intValue.setBounds(57, 259, 46, 14);
		panel.add(intValue);

		dexValue.setBounds(184, 242, 46, 14);
		panel.add(dexValue);

		witValue.setBounds(184, 259, 46, 14);
		panel.add(witValue);

		conValue.setBounds(296, 242, 46, 14);
		panel.add(conValue);

		menValue.setBounds(296, 259, 46, 14);
		panel.add(menValue);

		// SEPARATORS -----------------------------------------------------------------
		WebSeparator separator1 = new WebSeparator();
		separator1.setBounds(0, 138, 363, 2);
		panel.add(separator1);

		WebSeparator separator2 = new WebSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(113, 238, 1, 35);
		panel.add(separator2);

		WebSeparator separator3 = new WebSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBounds(240, 238, 1, 35);
		panel.add(separator3);

		WebSeparator separator4 = new WebSeparator();
		separator4.setBounds(0, 275, 363, 2);
		panel.add(separator4);

		WebSeparator separator5 = new WebSeparator();
		separator5.setBounds(0, 236, 363, 2);
		panel.add(separator5);

		WebSeparator separator6 = new WebSeparator();
		separator6.setOrientation(SwingConstants.VERTICAL);
		separator6.setBounds(181, 138, 1, 100);
		panel.add(separator6);

		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		title.setBounds(216, 2, 126, 20);
		panel.add(title);

	}

	private void updateGUI()
	{
		if (!isVisible())
		{
			return;
		}

		SwingUtilities.invokeLater(() ->
		{
			BPlayer p = ObjectsManager.getBot();

			cp.setMaximum(p.getMaxCp());
			cp.setValue(p.getCurrentCp());
			cp.setString(p.getGuiCp());

			hp.setMaximum(p.getMaxHp());
			hp.setValue(p.getCurrentHp());
			hp.setString(p.getGuiHp());

			mp.setMaximum(p.getMaxMp());
			mp.setValue(p.getCurrentMp());
			mp.setString(p.getGuiMp());

			long expNextLevel = ExperienceData.LEVEL[p.getLevel() + 1] - ExperienceData.LEVEL[p.getLevel()];
			long expNeed = p.getExp() - ExperienceData.LEVEL[p.getLevel() + 1];
			int e = (int) (expNextLevel * 100 / expNeed);
			exp.setMaximum(100);
			exp.setValue(e + 100);
			exp.setString("EXP: " + e + "%");

			lvl.setText("Lv " + p.getLevel());
			name.setText(p.getName());
			title.setText(p.getTitle());
			race.setText(p.getRace().toString().toLowerCase());

			strValue.setText(p.getSTR() + "");
			intValue.setText(p.getINT() + "");

			dexValue.setText(p.getDEX() + "");
			witValue.setText(p.getWIT() + "");

			conValue.setText(p.getCON() + "");
			menValue.setText(p.getMEN() + "");

			pAtkValue.setText(p.getPAtk() + "");
			pDefValue.setText(p.getPDef() + "");
			accuracyValue.setText(p.getAccuracy() + "");
			critRateValue.setText(p.getCriticalRate() + "");
			atkSpdValue.setText(p.getPAtkSpd() + "");

			mAtkValue.setText(p.getMAtk() + "");
			mDefValue.setText(p.getMDef() + "");
			evasionValue.setText(p.getEvasion() + "");
			speedValue.setText(p.getRunSpd() + ""); // ??
			castingSpdValue.setText(p.getMAtkSpd() + "");
		});
	}

	public void open()
	{
		setVisible(!isVisible());

		if (isVisible())
		{
			taskUpdate = ThreadManager.scheduleAtFixedRate(() -> updateGUI(), 0, 1000);
		}
		else if (taskUpdate != null)
		{
			taskUpdate.cancel(true);
			taskUpdate = null;
		}
	}

	public static BotStatsWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final BotStatsWindow INSTANCE = new BotStatsWindow();
	}
}
