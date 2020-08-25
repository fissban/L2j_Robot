package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.WebList;
import com.alee.laf.list.WebListModel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.tabbedpane.TabStretchType;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;

import gui.builder.WindowFrame;
import gui.frames.game.GameFrame;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FTextField;
import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BPlayer;
import main.data.SkillData;
import main.managers.ObjectsManager;
import main.util.Img;
import main.util.Util;

/**
 * @author fissban
 */
public class AiSettingsWindow extends WindowFrame
{
	private WebCheckBox wbchckbxSitdownIsMP = new WebCheckBox("Sit down is MP is very slow");
	private WebCheckBox wbchckbxSitdownIsHP = new WebCheckBox("Sit down is HP is very slow");

	// Left-Right mobs
	private WebList leftMobs = new WebList(new WebListModel<String>());
	private WebList rightMobs = new WebList(new WebListModel<String>());

	// Left-Right skills
	private WebList leftSkills = new WebList(new WebListModel<String>());
	private WebList rightSkills = new WebList(new WebListModel<String>());

	// Left-Right debuffs
	private WebList leftDebuffs = new WebList(new WebListModel<String>());
	private WebList rightDebuffs = new WebList(new WebListModel<String>());

	// Left-Right buffs
	private WebList leftBuffs = new WebList(new WebListModel<String>());
	private WebList rightBuffs = new WebList(new WebListModel<String>());

	public AiSettingsWindow()
	{
		super("Ai Settings");

		setBounds(0, 0, 743, 395);

		panel.setBorder(null);
		panel.setBackground(new Color(139, 69, 19));
		panel.setBounds(0, 0, 743, 366);

		createListMobs();
		createListSkills();
		createListDebuffs();
		createListBuffs();

		WebTabbedPane tabbedPane = new WebTabbedPane();
		tabbedPane.setBorder(null);
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 0, 736, 366);
		tabbedPane.setTabStretchType(TabStretchType.never);
		tabbedPane.setTabbedPaneStyle(TabbedPaneStyle.attached);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		tabbedPane.setRound(0);
		tabbedPane.setTabOverlay(2);
		tabbedPane.setTabRunIndent(1);
		tabbedPane.setTabInsets(new Insets(3, 2, 3, 2));
		tabbedPane.setShadeWidth(0);
		tabbedPane.setBoldFont(true);
		tabbedPane.setTabBorderColor(Color.LIGHT_GRAY);
		tabbedPane.setSelectedTopBg(new Color(240, 248, 255));
		tabbedPane.setSelectedBottomBg(new Color(211, 211, 211));
		tabbedPane.setContentBorderColor(new Color(204, 204, 204));
		tabbedPane.setBottomBg(new Color(248, 248, 255));
		tabbedPane.setBackgroundColor(new Color(153, 51, 0));
		tabbedPane.setBackground(new Color(153, 51, 0));
		tabbedPane.setTopBg(new Color(169, 169, 169));
		tabbedPane.setFontSize(10);
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setBackground(new Color(248, 248, 255));
		tabbedPane.setAutoscrolls(true);
		tabbedPane.setInheritsPopupMenu(true);
		tabbedPane.setDoubleBuffered(true);
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setDoubleBuffered(true);
		panel.add(tabbedPane);

		FPanelDegrade menuGeneral = new FPanelDegrade();
		menuGeneral.setBackground(new Color(139, 69, 19));
		menuGeneral.setForeground(new Color(0, 0, 51));
		menuGeneral.setBounds(0, 0, 400, 380);
		menuGeneral.setLayout(null);

		FPanelDegrade menuMobsAttack = new FPanelDegrade();
		menuMobsAttack.setForeground(new Color(0, 0, 51));
		menuMobsAttack.setBounds(0, 0, 400, 380);
		menuMobsAttack.setLayout(null);

		FPanelDegrade menuSkillsUse = new FPanelDegrade();
		menuSkillsUse.setForeground(new Color(0, 0, 51));
		menuSkillsUse.setBounds(0, 0, 400, 380);
		menuSkillsUse.setLayout(null);

		tabbedPane.addTab("General", Img.create("general/IconBlack.png", 8, 8), menuGeneral, null);
		tabbedPane.addTab("Mobs to attack", Img.create("general/IconBlack.png", 8, 8), menuMobsAttack, null);
		tabbedPane.addTab("Skills to use", Img.create("general/IconBlack.png", 8, 8), menuSkillsUse, null);

		// TODO panel1 ---------------------------------------------------------------------------------------------------------------------------------------------

		WebCheckBox lootAllItems = new WebCheckBox("Loot all items");
		lootAllItems.setShadeWidth(0);
		lootAllItems.setRound(4);
		lootAllItems.setForeground(Color.WHITE);
		lootAllItems.setSelected(true);
		lootAllItems.setBackground(new Color(248, 248, 255));
		lootAllItems.setBounds(10, 11, 110, 23);
		lootAllItems.addActionListener(e ->
		{
			ObjectsManager.getBot().getAi().LOOT_ALL_ITEMS = !ObjectsManager.getBot().getAi().LOOT_ALL_ITEMS;
			WebOptionPane.showMessageDialog(this, "Auto loot items " + (ObjectsManager.getBot().getAi().LOOT_ALL_ITEMS ? "enable" : "disable"), "Change", WebOptionPane.INFORMATION_MESSAGE);
		});
		menuGeneral.add(lootAllItems);

		WebCheckBox lootAdena = new WebCheckBox("Loot adena");
		lootAdena.setRound(4);
		lootAdena.setShadeWidth(0);
		lootAdena.setForeground(Color.WHITE);
		lootAdena.setSelected(true);
		lootAdena.setBackground(new Color(248, 248, 255));
		lootAdena.setBounds(175, 11, 110, 23);
		lootAdena.addActionListener(e ->
		{
			ObjectsManager.getBot().getAi().LOOT_ADENA = !ObjectsManager.getBot().getAi().LOOT_ADENA;
			WebOptionPane.showMessageDialog(this, "Auto loot Adena " + (ObjectsManager.getBot().getAi().LOOT_ADENA ? "enable" : "disable"), "Change", WebOptionPane.INFORMATION_MESSAGE);
		});
		menuGeneral.add(lootAdena);

		WebCheckBox lootEnchants = new WebCheckBox("Loot enchants");
		lootEnchants.setShadeWidth(0);
		lootEnchants.setRound(4);
		lootEnchants.setForeground(Color.WHITE);
		lootEnchants.setSelected(true);
		lootEnchants.setBackground(new Color(248, 248, 255));
		lootEnchants.setBounds(345, 11, 110, 23);
		lootEnchants.addActionListener(e ->
		{
			ObjectsManager.getBot().getAi().LOOT_ENCHANTS = !ObjectsManager.getBot().getAi().LOOT_ENCHANTS;
			WebOptionPane.showMessageDialog(this, "Auto loot Enchants " + (ObjectsManager.getBot().getAi().LOOT_ENCHANTS ? "enable" : "disable"), "Change", WebOptionPane.INFORMATION_MESSAGE);
		});
		menuGeneral.add(lootEnchants);

		WebCheckBox lootHerbs = new WebCheckBox("Loot Herbs");
		lootHerbs.setForeground(Color.WHITE);
		lootHerbs.setSelected(true);
		lootHerbs.setBackground(new Color(248, 248, 255));
		lootHerbs.setBounds(500, 11, 110, 23);
		lootHerbs.addActionListener(e ->
		{
			ObjectsManager.getBot().getAi().LOOT_HERBS = !ObjectsManager.getBot().getAi().LOOT_HERBS;
			WebOptionPane.showMessageDialog(this, "Auto loot Herbs " + (ObjectsManager.getBot().getAi().LOOT_HERBS ? "enable" : "disable"), "Change", WebOptionPane.INFORMATION_MESSAGE);
		});
		menuGeneral.add(lootHerbs);

		WebCheckBox boxAutoPots = new WebCheckBox("Auto Pots");
		boxAutoPots.setShadeWidth(0);
		boxAutoPots.setRound(4);
		boxAutoPots.setForeground(Color.WHITE);
		boxAutoPots.setSelected(true);
		boxAutoPots.setBackground(new Color(248, 248, 255));
		boxAutoPots.setBounds(10, 52, 83, 23);
		boxAutoPots.addActionListener(e ->
		{
			ObjectsManager.getBot().getAi().AUTO_POTS = !ObjectsManager.getBot().getAi().AUTO_POTS;
			WebOptionPane.showMessageDialog(this, "Auto pots " + (ObjectsManager.getBot().getAi().AUTO_POTS ? "enable" : "disable"), "Change", WebOptionPane.INFORMATION_MESSAGE);
		});

		WebSeparator separator2 = new WebSeparator();
		separator2.setBounds(10, 45, 636, 2);
		menuGeneral.add(separator2);
		menuGeneral.add(boxAutoPots);

		FTextField boxHpAutoPot = new FTextField();
		boxHpAutoPot.setHorizontalAlignment(SwingConstants.CENTER);
		boxHpAutoPot.setText(ObjectsManager.getBot().getAi().HP_USE_POT + "");
		boxHpAutoPot.setBounds(121, 53, 50, 20);
		boxHpAutoPot.setColumns(10);
		boxHpAutoPot.addCaretListener(e ->
		{
			try
			{
				ObjectsManager.getBot().getAi().HP_USE_POT = Integer.parseInt(boxHpAutoPot.getText());
			}
			catch (Exception ex)
			{
				WebOptionPane.showMessageDialog(this, "Invalid value!", "Error", WebOptionPane.ERROR);
			}
		});
		menuGeneral.add(boxHpAutoPot);

		FTextField boxMpAutoPot = new FTextField();
		boxMpAutoPot.setHorizontalAlignment(SwingConstants.CENTER);
		boxMpAutoPot.setText(ObjectsManager.getBot().getAi().MP_USE_POT + "");
		boxMpAutoPot.setColumns(10);
		boxMpAutoPot.setBounds(201, 52, 50, 20);
		boxMpAutoPot.addCaretListener(e ->
		{
			try
			{
				ObjectsManager.getBot().getAi().MP_USE_POT = Integer.parseInt(boxMpAutoPot.getText());
			}
			catch (Exception ex)
			{
				WebOptionPane.showMessageDialog(this, "Invalid value!", "Error", WebOptionPane.ERROR);
			}
		});
		menuGeneral.add(boxMpAutoPot);

		FTextField boxCpAutoPot = new FTextField();
		boxCpAutoPot.setHorizontalAlignment(SwingConstants.CENTER);
		boxCpAutoPot.setText(ObjectsManager.getBot().getAi().CP_USE_POT + "");
		boxCpAutoPot.setColumns(10);
		boxCpAutoPot.setBounds(278, 52, 50, 20);
		boxCpAutoPot.addCaretListener(e ->
		{
			try
			{
				ObjectsManager.getBot().getAi().CP_USE_POT = Integer.parseInt(boxCpAutoPot.getText());
			}
			catch (Exception ex)
			{
				WebOptionPane.showMessageDialog(this, "Invalid value!", "Error", WebOptionPane.ERROR);
			}
		});
		menuGeneral.add(boxCpAutoPot);

		WebLabel hoAutoPot = new WebLabel("HP:");
		hoAutoPot.setForeground(Color.WHITE);
		hoAutoPot.setBounds(103, 56, 22, 14);
		menuGeneral.add(hoAutoPot);

		WebLabel mpAutoPot = new WebLabel("MP:");
		mpAutoPot.setForeground(Color.WHITE);
		mpAutoPot.setBounds(181, 56, 22, 14);
		menuGeneral.add(mpAutoPot);

		WebLabel cpAutoPot = new WebLabel("CP:");
		cpAutoPot.setForeground(Color.WHITE);
		cpAutoPot.setBounds(260, 56, 22, 14);
		menuGeneral.add(cpAutoPot);
		wbchckbxSitdownIsMP.setShadeWidth(0);
		wbchckbxSitdownIsMP.setRound(4);
		wbchckbxSitdownIsMP.setForeground(Color.WHITE);

		wbchckbxSitdownIsMP.setSelected(ObjectsManager.getBot().getAi().SIT_DOWN_IS_MP_VERY_SLOW);
		wbchckbxSitdownIsMP.setBackground(new Color(248, 248, 255));
		wbchckbxSitdownIsMP.setBounds(10, 79, 402, 23);
		wbchckbxSitdownIsMP.addActionListener(e ->
		{
			ObjectsManager.getBot().getAi().SIT_DOWN_IS_MP_VERY_SLOW = !ObjectsManager.getBot().getAi().SIT_DOWN_IS_MP_VERY_SLOW;
			WebOptionPane.showMessageDialog(this, "Sit down if MP is very slow:" + (ObjectsManager.getBot().getAi().SIT_DOWN_IS_MP_VERY_SLOW ? "enable" : "disable"), "Error", WebOptionPane.INFORMATION_MESSAGE);
		});
		menuGeneral.add(wbchckbxSitdownIsMP);
		wbchckbxSitdownIsHP.setShadeWidth(0);
		wbchckbxSitdownIsHP.setRound(4);
		wbchckbxSitdownIsHP.setForeground(Color.WHITE);

		wbchckbxSitdownIsHP.setSelected(ObjectsManager.getBot().getAi().SIT_DOWN_IS_HP_VERY_SLOW);
		wbchckbxSitdownIsHP.setBackground(new Color(248, 248, 255));
		wbchckbxSitdownIsHP.setBounds(10, 106, 402, 23);
		wbchckbxSitdownIsMP.addActionListener(e ->
		{
			ObjectsManager.getBot().getAi().SIT_DOWN_IS_HP_VERY_SLOW = !ObjectsManager.getBot().getAi().SIT_DOWN_IS_HP_VERY_SLOW;
			WebOptionPane.showMessageDialog(this, "Sit down if HP is very slow:" + (ObjectsManager.getBot().getAi().SIT_DOWN_IS_HP_VERY_SLOW ? "enable" : "disable"), "Error", WebOptionPane.INFORMATION_MESSAGE);
		});
		menuGeneral.add(wbchckbxSitdownIsHP);
		//pane1.add(image1);
		// TODO panel2 ---------------------------------------------------------------------------------------------------------------------------------------------

		WebLabel questionRadioAttack = new WebLabel("");
		questionRadioAttack.setIcon(Img.create("general/icons/question.png", 16, 16));
		questionRadioAttack.setBounds(10, 14, 16, 16);
		menuMobsAttack.add(questionRadioAttack);
		TooltipManager.setTooltip(questionRadioAttack, "<html><body>The attack radius of the bot is indicated when activating the ObjectsManager.getBot().getAi().</body></html>", TooltipWay.down, 0);

		WebLabel lblRadioAttack = new WebLabel("Radio Attack");
		lblRadioAttack.setForeground(Color.WHITE);
		lblRadioAttack.setBounds(34, 14, 120, 14);
		menuMobsAttack.add(lblRadioAttack);

		FTextField fRadioAttack = new FTextField();
		fRadioAttack.setHorizontalAlignment(SwingConstants.CENTER);
		fRadioAttack.putClientProperty(GroupPanel.FILL_CELL, true);
		fRadioAttack.setText(ObjectsManager.getBot().getAi().RADIO_ATTACK + "");
		fRadioAttack.setBounds(153, 9, 86, 24);
		fRadioAttack.addCaretListener(e ->
		{
			try
			{
				ObjectsManager.getBot().getAi().RADIO_ATTACK = Integer.parseInt(fRadioAttack.getText());
				GameFrame.getInstance().getLiveMap().updateRadiusAttack();
			}
			catch (Exception e2)
			{
				WebOptionPane.showMessageDialog(this, "Invalid value!", "Error", WebOptionPane.ERROR_MESSAGE);
			}
		});
		menuMobsAttack.add(fRadioAttack);

		FTextField mobsTitleIgnore = new FTextField();
		mobsTitleIgnore.setText(ObjectsManager.getBot().getAi().getIgnoreTitleAttack());
		mobsTitleIgnore.setBounds(153, 35, 451, 24);
		mobsTitleIgnore.addCaretListener(e ->
		{
			ObjectsManager.getBot().getAi().setIgnoreTitleAttack(mobsTitleIgnore.getText());
		});
		menuMobsAttack.add(mobsTitleIgnore);

		WebLabel questionToIgnore = new WebLabel("");
		questionToIgnore.setIcon(Img.create("general/icons/question.png", 16, 16));
		questionToIgnore.setBounds(10, 40, 16, 16);
		menuMobsAttack.add(questionToIgnore);
		TooltipManager.setTooltip(questionToIgnore, "<html><body>list of titles in the mobs that the bot ignored,<br>these should be separated by \",\"</body></html>", TooltipWay.up, 0);

		WebLabel lblIgnoreMobs = new WebLabel("Ignore mobs Titles");
		lblIgnoreMobs.setForeground(Color.WHITE);
		lblIgnoreMobs.setBounds(34, 40, 120, 14);
		menuMobsAttack.add(lblIgnoreMobs);

		WebLabel lblMobsAttack = new WebLabel("Mobs To Attack");
		lblMobsAttack.setForeground(Color.WHITE);
		lblMobsAttack.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMobsAttack.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobsAttack.setBounds(0, 72, 328, 14);
		menuMobsAttack.add(lblMobsAttack);

		// listeners
		rightMobs.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = rightMobs.getSelectedValue().toString();

				leftMobs.getWebModel().add(select);
				rightMobs.getWebModel().remove(select);

				int idTemplate = Integer.parseInt(select.split(" ")[0]);
				ObjectsManager.getBot().getAi().removeIgnoreIdAttack(idTemplate);
			}
		});
		leftMobs.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = leftMobs.getSelectedValue().toString();

				rightMobs.getWebModel().add(select);
				leftMobs.getWebModel().remove(select);

				int idTemplate = Integer.parseInt(select.split(" ")[0]);
				ObjectsManager.getBot().getAi().addIgnoreIdAttack(idTemplate);
			}
		});

		WebLabel attackQuestion = new WebLabel("");
		attackQuestion.setBounds(100, 72, 16, 16);
		attackQuestion.setIcon(Img.create("general/icons/question.png", 16, 16));
		menuMobsAttack.add(attackQuestion);
		TooltipManager.setTooltip(attackQuestion, "<html><body>In this list are all the mobs that are in the attack radius that the bot will attack,<br>double click to ignore any.</body></html>", TooltipWay.up, 0);

		WebLabel lblMobsIgnore = new WebLabel("Mobs to Ignore");
		lblMobsIgnore.setForeground(Color.WHITE);
		lblMobsIgnore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMobsIgnore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMobsIgnore.setBounds(340, 72, 306, 14);
		menuMobsAttack.add(lblMobsIgnore);

		WebLabel ignoreQuestion = new WebLabel("");
		ignoreQuestion.setBounds(432, 72, 16, 16);
		ignoreQuestion.setIcon(Img.create("general/icons/question.png", 16, 16));
		menuMobsAttack.add(ignoreQuestion);
		TooltipManager.setTooltip(ignoreQuestion, "<html><body>In this list you can find all the ignored mobs of the attack radius of the bot,<br>double click to add them.</body></html>", TooltipWay.up, 0);

		// Split
		// Scroll Left
		WebScrollPane scrollPaneLeft = new WebScrollPane(leftMobs);
		scrollPaneLeft.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneLeft.setRound(1);
		scrollPaneLeft.setShadeWidth(0);
		scrollPaneLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// Scroll Right
		WebScrollPane scrollPaneRight = new WebScrollPane(rightMobs);
		scrollPaneRight.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneRight.setRound(1);
		scrollPaneRight.setShadeWidth(0);
		scrollPaneRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		WebSplitPane splitPane = new WebSplitPane(SwingConstants.VERTICAL, scrollPaneLeft, scrollPaneRight);
		splitPane.setBounds(10, 91, 630, 253);
		splitPane.setDividerLocation(splitPane.getWidth() / 2);
		menuMobsAttack.add(splitPane);
		// TODO panel3 ---------------------------------------------------------------------------------------------------------------------------------------------

		WebTabbedPane tabbedPaneskills = new WebTabbedPane();
		tabbedPaneskills.setTabPlacement(JTabbedPane.LEFT);
		tabbedPaneskills.setBounds(0, 0, 645, 355);
		tabbedPaneskills.setTabStretchType(TabStretchType.never);
		tabbedPaneskills.setTabbedPaneStyle(TabbedPaneStyle.attached);
		tabbedPaneskills.setFont(new Font("Tahoma", Font.BOLD, 10));
		tabbedPaneskills.setRound(0);
		tabbedPaneskills.setTabOverlay(2);
		tabbedPaneskills.setTabRunIndent(1);
		tabbedPaneskills.setTabInsets(new Insets(3, 2, 3, 2));
		tabbedPaneskills.setShadeWidth(0);
		tabbedPaneskills.setBoldFont(true);
		tabbedPaneskills.setTabBorderColor(Color.LIGHT_GRAY);
		tabbedPaneskills.setSelectedTopBg(new Color(240, 248, 255));
		tabbedPaneskills.setSelectedBottomBg(new Color(211, 211, 211));
		tabbedPaneskills.setContentBorderColor(new Color(204, 204, 204));
		tabbedPaneskills.setBottomBg(new Color(248, 248, 255));
		tabbedPaneskills.setBackgroundColor(new Color(153, 51, 0));
		tabbedPaneskills.setBackground(new Color(153, 51, 0));
		tabbedPaneskills.setTopBg(new Color(169, 169, 169));
		tabbedPaneskills.setFontSize(10);
		tabbedPaneskills.setFocusTraversalKeysEnabled(false);
		tabbedPaneskills.setBackground(new Color(248, 248, 255));
		tabbedPaneskills.setAutoscrolls(true);
		tabbedPaneskills.setInheritsPopupMenu(true);
		tabbedPaneskills.setDoubleBuffered(true);
		tabbedPaneskills.setFocusTraversalKeysEnabled(false);
		tabbedPaneskills.setDoubleBuffered(true);
		menuSkillsUse.add(tabbedPaneskills);

		FPanelDegrade skillsUse = new FPanelDegrade();
		skillsUse.setForeground(new Color(0, 0, 51));
		skillsUse.setBounds(0, 0, 400, 380);
		skillsUse.setLayout(null);

		FPanelDegrade debuffsUse = new FPanelDegrade();
		//debuffsUse.setColor2(new Color(102, 51, 0));
		debuffsUse.setForeground(new Color(0, 0, 51));
		debuffsUse.setBounds(0, 0, 400, 380);
		debuffsUse.setLayout(null);

		FPanelDegrade buffsUse = new FPanelDegrade();
		//buffsUse.setColor2(new Color(102, 51, 0));
		buffsUse.setForeground(new Color(0, 0, 51));
		buffsUse.setBounds(0, 0, 400, 380);
		buffsUse.setLayout(null);

		tabbedPaneskills.addTab("Nukers", skillsUse);
		tabbedPaneskills.addTab("Debuff", debuffsUse);
		tabbedPaneskills.addTab("Buffs", buffsUse);
		scrollPaneLeft.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneLeft.setRound(1);
		scrollPaneLeft.setShadeWidth(0);
		scrollPaneLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRight.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneRight.setRound(1);
		scrollPaneRight.setShadeWidth(0);
		scrollPaneRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		WebLabel lblSkillsInUse = new WebLabel("Mobs To Attack");
		lblSkillsInUse.setText("Skills in use");
		lblSkillsInUse.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSkillsInUse.setHorizontalAlignment(SwingConstants.CENTER);
		lblSkillsInUse.setForeground(Color.WHITE);
		lblSkillsInUse.setBounds(5, 27, 313, 14);
		skillsUse.add(lblSkillsInUse);

		WebLabel iconSkillInUse = new WebLabel("");
		iconSkillInUse.setIcon(Img.create("general/icons/question.png", 16, 16));
		iconSkillInUse.setBounds(115, 27, 16, 16);
		skillsUse.add(iconSkillInUse);
		TooltipManager.setTooltip(iconSkillInUse, "<html><body>List of predefined skills that the Ai will use in farm or pvp.</body></html>", TooltipWay.up, 0);

		WebLabel lblSkillsNotUse = new WebLabel("Mobs to Ignore");
		lblSkillsNotUse.setText("Unused skills");
		lblSkillsNotUse.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSkillsNotUse.setHorizontalAlignment(SwingConstants.CENTER);
		lblSkillsNotUse.setForeground(Color.WHITE);
		lblSkillsNotUse.setBounds(328, 27, 307, 14);
		skillsUse.add(lblSkillsNotUse);

		WebLabel iconSkillsNotUse = new WebLabel("");
		iconSkillsNotUse.setIcon(Img.create("general/icons/question.png", 16, 16));
		iconSkillsNotUse.setBounds(432, 27, 16, 16);
		skillsUse.add(iconSkillsNotUse);
		TooltipManager.setTooltip(iconSkillInUse, "<html><body>List of skills available for this character that are not in use.</body></html>", TooltipWay.up, 0);

		WebLabel lblTitleSkills1 = new WebLabel("Nuker Skills");
		lblTitleSkills1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitleSkills1.setForeground(new Color(0, 204, 255));
		lblTitleSkills1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSkills1.setBounds(5, 11, 588, 14);
		skillsUse.add(lblTitleSkills1);

		// NUKERS ---------------------------------------------------------
		// Scroll Left
		WebScrollPane scrollSkillsLeft = new WebScrollPane(leftSkills); // izquierda
		// Scroll Right
		WebScrollPane scrollSkillsRight = new WebScrollPane(rightSkills); // derecha
		WebSplitPane splitPaneSkills = new WebSplitPane(SwingConstants.VERTICAL, scrollSkillsLeft, scrollSkillsRight);
		splitPaneSkills.setDrawDividerBorder(true);
		splitPaneSkills.setBounds(10, 48, 583, 303);
		splitPaneSkills.setDividerLocation(splitPaneSkills.getWidth() / 2);
		skillsUse.add(splitPaneSkills);

		// listeners
		rightSkills.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = rightSkills.getSelectedValue().toString();

				leftSkills.getWebModel().add(select);
				rightSkills.getWebModel().remove(select);

				ObjectsManager.getBot().getAi().removeNukkerSkill(SkillData.getId(select));
			}
		});
		leftSkills.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = leftSkills.getSelectedValue().toString();

				rightSkills.getWebModel().add(select);
				leftSkills.getWebModel().remove(select);

				ObjectsManager.getBot().getAi().setNukerSkill(SkillData.getId(select));
			}
		});

		// DEBUFFS --------------------------------------------------------

		WebLabel webLabel = new WebLabel("Debuff Skills");
		webLabel.setHorizontalAlignment(SwingConstants.CENTER);
		webLabel.setForeground(new Color(51, 204, 255));
		webLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		webLabel.setBounds(10, 11, 583, 14);
		debuffsUse.add(webLabel);
		scrollPaneLeft.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneLeft.setRound(1);
		scrollPaneLeft.setShadeWidth(0);
		scrollPaneLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRight.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneRight.setRound(1);
		scrollPaneRight.setShadeWidth(0);
		scrollPaneRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		WebLabel wblblDebuffsInUse = new WebLabel("Debuffs in use");
		wblblDebuffsInUse.setHorizontalTextPosition(SwingConstants.CENTER);
		wblblDebuffsInUse.setHorizontalAlignment(SwingConstants.CENTER);
		wblblDebuffsInUse.setForeground(Color.WHITE);
		wblblDebuffsInUse.setBounds(10, 27, 294, 14);
		debuffsUse.add(wblblDebuffsInUse);

		WebLabel wblblDebuffsSkills = new WebLabel("Unused Debuffs");
		wblblDebuffsSkills.setHorizontalTextPosition(SwingConstants.CENTER);
		wblblDebuffsSkills.setHorizontalAlignment(SwingConstants.CENTER);
		wblblDebuffsSkills.setForeground(Color.WHITE);
		wblblDebuffsSkills.setBounds(333, 27, 260, 14);
		debuffsUse.add(wblblDebuffsSkills);

		WebLabel lblIconDebuffsUse = new WebLabel("");
		lblIconDebuffsUse.setIcon(Img.create("general/icons/question.png", 16, 16));
		lblIconDebuffsUse.setBounds(104, 27, 16, 16);
		debuffsUse.add(lblIconDebuffsUse);
		TooltipManager.setTooltip(lblIconDebuffsUse, "<html><body>List of predefined debuffs that the Ai will use in farm or pvp.</body></html>", TooltipWay.up, 0);

		WebLabel lblIcopnDebuffsUnused = new WebLabel("");
		lblIcopnDebuffsUnused.setIcon(Img.create("general/icons/question.png", 16, 16));
		lblIcopnDebuffsUnused.setBounds(406, 27, 16, 16);
		debuffsUse.add(lblIcopnDebuffsUnused);
		TooltipManager.setTooltip(lblIcopnDebuffsUnused, "<html><body>List of debuffs available for this character that are not in use.</body></html>", TooltipWay.up, 0);

		// Scroll Left
		WebScrollPane scrollDebuffsLeft = new WebScrollPane(leftDebuffs); // izquierda
		// Scroll Right
		WebScrollPane scrollDebuffsRight = new WebScrollPane(rightDebuffs); // derecha
		WebSplitPane splitPaneDebuffs = new WebSplitPane(SwingConstants.VERTICAL, scrollDebuffsLeft, scrollDebuffsRight);
		splitPaneDebuffs.setDrawDividerBorder(true);
		splitPaneDebuffs.setBounds(10, 48, 583, 303);
		splitPaneDebuffs.setDividerLocation(splitPaneDebuffs.getWidth() / 2);
		debuffsUse.add(splitPaneDebuffs);

		// listeners
		rightDebuffs.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = rightDebuffs.getSelectedValue().toString();

				leftDebuffs.getWebModel().add(select);
				rightDebuffs.getWebModel().remove(select);

				ObjectsManager.getBot().getAi().removeDebuffsSkill(SkillData.getId(select));
			}
		});
		leftDebuffs.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = leftDebuffs.getSelectedValue().toString();

				rightDebuffs.getWebModel().add(select);
				leftDebuffs.getWebModel().remove(select);

				ObjectsManager.getBot().getAi().setDebuffsSkill(SkillData.getId(select));
			}
		});

		// XXX BUFFS ----------------------------------------------------------

		WebLabel webLabelDebuffs = new WebLabel("Buff Skills");
		webLabelDebuffs.setHorizontalAlignment(SwingConstants.CENTER);
		webLabelDebuffs.setForeground(new Color(51, 204, 255));
		webLabelDebuffs.setFont(new Font("Tahoma", Font.BOLD, 18));
		webLabelDebuffs.setBounds(10, 11, 583, 14);
		buffsUse.add(webLabelDebuffs);
		scrollPaneLeft.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneLeft.setRound(1);
		scrollPaneLeft.setShadeWidth(0);
		scrollPaneLeft.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRight.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollPaneRight.setRound(1);
		scrollPaneRight.setShadeWidth(0);
		scrollPaneRight.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		WebLabel wblblBuffsInUse = new WebLabel("Mobs To Attack");
		wblblBuffsInUse.setText("Debuffs in use");
		wblblBuffsInUse.setHorizontalTextPosition(SwingConstants.CENTER);
		wblblBuffsInUse.setHorizontalAlignment(SwingConstants.CENTER);
		wblblBuffsInUse.setForeground(Color.WHITE);
		wblblBuffsInUse.setBounds(10, 27, 294, 14);
		buffsUse.add(wblblBuffsInUse);

		WebLabel wblblBuffsSkills = new WebLabel("Unused Buffs");
		wblblBuffsSkills.setHorizontalTextPosition(SwingConstants.CENTER);
		wblblBuffsSkills.setHorizontalAlignment(SwingConstants.CENTER);
		wblblBuffsSkills.setForeground(Color.WHITE);
		wblblBuffsSkills.setBounds(333, 27, 260, 14);
		buffsUse.add(wblblBuffsSkills);

		WebLabel lblIconBuffsUse = new WebLabel("");
		lblIconBuffsUse.setIcon(Img.create("general/icons/question.png", 16, 16));
		lblIconBuffsUse.setBounds(104, 27, 16, 16);
		buffsUse.add(lblIconBuffsUse);
		TooltipManager.setTooltip(lblIconBuffsUse, "<html><body>List of predefined buffs that the Ai will use.</body></html>", TooltipWay.up, 0);

		WebLabel lblIcopnBuffsUnused = new WebLabel("");
		lblIcopnBuffsUnused.setIcon(Img.create("general/icons/question.png", 16, 16));
		lblIcopnBuffsUnused.setBounds(406, 27, 16, 16);
		buffsUse.add(lblIcopnBuffsUnused);
		TooltipManager.setTooltip(lblIcopnBuffsUnused, "<html><body>List of buffs available for this character that are not in use.</body></html>", TooltipWay.up, 0);

		// Scroll Left
		WebScrollPane scrollBuffsLeft = new WebScrollPane(leftBuffs); // derecha
		// Scroll Right
		WebScrollPane scrollBuffsRight = new WebScrollPane(rightBuffs); // izquierda

		WebSplitPane splitPaneBuffs = new WebSplitPane(SwingConstants.VERTICAL, scrollBuffsLeft, scrollBuffsRight);
		splitPaneBuffs.setDrawDividerBorder(true);
		splitPaneBuffs.setBounds(10, 48, 583, 303);
		splitPaneBuffs.setDividerLocation(splitPaneBuffs.getWidth() / 2);
		buffsUse.add(splitPaneBuffs);

		// listeners
		rightBuffs.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = rightBuffs.getSelectedValue().toString();

				leftBuffs.getWebModel().add(select);
				rightBuffs.getWebModel().remove(select);

				ObjectsManager.getBot().getAi().removeBuffsSkill(SkillData.getId(select));
			}
		});
		leftBuffs.addMouseListener(new MouseAdapter()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String select = leftBuffs.getSelectedValue().toString();

				rightBuffs.getWebModel().add(select);
				leftBuffs.getWebModel().remove(select);

				ObjectsManager.getBot().getAi().setBuffsSkill(SkillData.getId(select));
			}
		});
	}

	public void open()
	{
		//If any other code changes one of these values, we update it in this window.
		if (!isVisible())
		{
			wbchckbxSitdownIsMP.setSelected(ObjectsManager.getBot().getAi().SIT_DOWN_IS_MP_VERY_SLOW);
			wbchckbxSitdownIsHP.setSelected(ObjectsManager.getBot().getAi().SIT_DOWN_IS_HP_VERY_SLOW);
		}

		setVisible(!isVisible());
	}

	/**
	 * The initial list is created with the mobs that we can attack in our attack radius
	 */
	@SuppressWarnings("unchecked")
	private void createListMobs()
	{
		if (leftMobs.getWebModel().isEmpty() && rightMobs.getWebModel().isEmpty())
		{
			ObjectsManager.getAll(BNpc.class).stream().filter(npc -> npc.isAttackable() && Util.calculateDistance(ObjectsManager.getBot(), npc, true) < ObjectsManager.getBot().getAi().RADIO_ATTACK + 1000).forEach(npc ->
			{
				String text = npc.getIdTemplate() + " - " + npc.getName();
				if (!leftMobs.getWebModel().contains(text))
				{
					leftMobs.getWebModel().addElement(text);
				}
			});
			leftMobs.repaint();
		}
	}

	@SuppressWarnings("unchecked")
	private void createListSkills()
	{
		if (rightSkills.getWebModel().isEmpty())
		{
			BPlayer bot = ObjectsManager.getBot();

			bot.getAi().getAllNukerSkills().forEach(id ->
			{
				leftSkills.getWebModel().addElement(SkillData.getName(id));
			});

			bot.getSkills().getAll().forEach(s ->
			{
				if (!bot.getAi().getAllNukerSkills().contains(s.getId()) && !s.isPasive())
				{
					rightSkills.getWebModel().addElement(s.getName());
				}
			});

			rightSkills.updateUI();
			leftSkills.updateUI();
		}
	}

	@SuppressWarnings("unchecked")
	private void createListDebuffs()
	{
		if (rightDebuffs.getWebModel().isEmpty())
		{
			BPlayer bot = ObjectsManager.getBot();

			bot.getAi().getAllDebuffsSkills().forEach(id ->
			{
				leftDebuffs.getWebModel().addElement(SkillData.getName(id));
			});

			bot.getSkills().getAll().forEach(s ->
			{
				if (!bot.getAi().getAllDebuffsSkills().contains(s.getId()) && !s.isPasive())
				{
					rightDebuffs.getWebModel().addElement(s.getName());
				}
			});

			rightDebuffs.updateUI();
			leftDebuffs.updateUI();
		}
	}

	@SuppressWarnings("unchecked")
	private void createListBuffs()
	{
		if (rightBuffs.getWebModel().isEmpty())
		{
			BPlayer bot = ObjectsManager.getBot();

			bot.getAi().getAllBuffsSkills().forEach(id ->
			{
				leftBuffs.getWebModel().addElement(SkillData.getName(id));
			});

			bot.getSkills().getAll().forEach(s ->
			{
				if (!bot.getAi().getAllBuffsSkills().contains(s.getId()) && !s.isPasive())
				{
					rightBuffs.getWebModel().addElement(s.getName());
				}
			});

			leftBuffs.updateUI();
			rightBuffs.updateUI();
		}
	}

	public static AiSettingsWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final AiSettingsWindow INSTANCE = new AiSettingsWindow();
	}
}
