package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.alee.laf.label.WebLabel;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;

import gui.builder.InvLabel;
import gui.builder.WindowFrame;
import gui.frames.game.menu.InventoryMenu;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FProgressBar;
import gui.look.fissban.FScrollPane;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.managers.Inventory;
import main.data.IconData;
import main.enums.EPaperdoll;
import main.managers.ObjectsManager;
import main.util.Img;
import main.util.Util;

/**
 * @author fissban
 */
public class InventoryWindow extends WindowFrame
{
	private static final long serialVersionUID = 1L;

	private final List<WebLabel> objects = new ArrayList<>();
	private final InvLabel invREar = new InvLabel("RHead", 10, 21);
	private final InvLabel invHead = new InvLabel("Head", 52, 21);
	private final InvLabel invLEar = new InvLabel("LHear", 94, 21);
	private final InvLabel invChest = new InvLabel("Chest", 52, 64);
	private final InvLabel invGloves = new InvLabel("Gloves", 10, 107);
	private final InvLabel label7 = new InvLabel("New label", 52, 107);
	private final InvLabel invFeet = new InvLabel("Feet", 94, 107);
	private final InvLabel invRHand = new InvLabel("RHand", 10, 147);
	private final InvLabel invLHand = new InvLabel("LHand", 94, 150);
	private final InvLabel label11 = new InvLabel("", 10, 194);
	private final InvLabel label12 = new InvLabel("", 52, 194);
	private final InvLabel label13 = new InvLabel("", 94, 194);
	private final InvLabel label14 = new InvLabel("", 10, 231);
	private final InvLabel label15 = new InvLabel("", 52, 231);
	private final InvLabel label16 = new InvLabel("", 94, 231);
	private final InvLabel label17 = new InvLabel("", 10, 294);
	private final InvLabel label18 = new InvLabel("", 52, 294);
	private final InvLabel label19 = new InvLabel("", 94, 294);

	private final WebLabel invAdena = new WebLabel("Adena");

	private final FProgressBar load = new FProgressBar();

	private FPanelDegrade itemsGeneral = new FPanelDegrade();
	private FScrollPane scrollGeneral = new FScrollPane(itemsGeneral);

	private FPanelDegrade itemsQuest = new FPanelDegrade();
	private FScrollPane scrollQuest = new FScrollPane(itemsQuest);

	public InventoryWindow()
	{
		super("Inventory");
		panel.setBounds(0, -34, 453, 414);

		setBounds(0, 0, 459, 437);

		panel.add(invREar);
		panel.add(invHead);
		panel.add(invLEar);
		panel.add(invChest);
		panel.add(invGloves);
		panel.add(label7);
		panel.add(invFeet);
		panel.add(invRHand);
		panel.add(invLHand);
		panel.add(label11);
		panel.add(label12);
		panel.add(label13);
		panel.add(label14);
		panel.add(label15);
		panel.add(label16);
		panel.add(label17);
		panel.add(label18);
		panel.add(label19);

		invAdena.setOpaque(true);
		invAdena.setDrawShade(true);
		invAdena.setBoldFont(true);
		invAdena.setBackground(new Color(0, 153, 255));
		invAdena.setShadeColor(Color.LIGHT_GRAY);
		invAdena.setBounds(35, 347, 89, 14);
		panel.add(invAdena);

		WebLabel adenaIcon = new WebLabel("");
		adenaIcon.setBackground(new Color(0, 153, 255));
		adenaIcon.setOpaque(true);
		adenaIcon.setHorizontalAlignment(SwingConstants.CENTER);
		adenaIcon.setBounds(12, 347, 24, 14);
		adenaIcon.setIcon(Img.create("items/Adena.png", 12, 12));
		panel.add(adenaIcon);

		load.setShadeWidth(0);
		load.setIndeterminateBorder(new Color(245, 245, 245));
		load.setBgTop(new Color(255, 165, 0));
		load.setProgressBottomColor(new Color(165, 42, 42));
		load.setProgressTopColor(new Color(165, 42, 42));
		load.setBgBottom(new Color(222, 184, 135));
		load.setBackground(new Color(245, 245, 245));
		load.setBounds(10, 372, 116, 14);
		load.setMaximum(100);
		panel.add(load);

		//
		itemsGeneral.setRadius(0);
		itemsGeneral.setOpaque(true);
		//
		scrollGeneral.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollGeneral.setRound(0);
		scrollGeneral.setRadius(0);
		scrollGeneral.setShadeWidth(0);
		scrollGeneral.setOpaque(true);
		scrollGeneral.setDrawBorder(false);
		scrollGeneral.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollGeneral.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		//
		itemsQuest.setRadius(0);
		itemsQuest.setOpaque(true);
		//
		scrollQuest.getWebVerticalScrollBar().setPreferredSize(new Dimension(8, 50));
		scrollQuest.setRound(0);
		scrollQuest.setRadius(0);
		scrollQuest.setShadeWidth(0);
		scrollQuest.setOpaque(true);
		scrollQuest.setDrawBorder(false);
		scrollQuest.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollQuest.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		WebTabbedPane tabbedPane = new WebTabbedPane(JTabbedPane.TOP);
		tabbedPane.setShadeWidth(0);
		tabbedPane.setRound(0);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(136, 21, 302, 370);
		panel.add(tabbedPane);

		tabbedPane.addTab("Item", null, scrollGeneral, null);
		tabbedPane.addTab("Quest", null, scrollQuest, null);
	}

	public void open()
	{
		setVisible(!isVisible());
	}

	public void updateInventory()
	{
		SwingUtilities.invokeLater(() ->
		{
			// TODO borrar todos los objetos y volves a crearlos no es una form a muy eficiente :S

			//Inventory
			Inventory inv = ObjectsManager.getBot().getInventory();

			TooltipManager.setTooltip(invREar, inv.getPaperdoll(EPaperdoll.REAR).getDescription(), TooltipWay.right, 0);
			TooltipManager.setTooltip(invLEar, inv.getPaperdoll(EPaperdoll.LEAR).getDescription(), TooltipWay.right, 0);
			TooltipManager.setTooltip(invHead, inv.getPaperdoll(EPaperdoll.HEAD).getDescription(), TooltipWay.right, 0);
			TooltipManager.setTooltip(invChest, inv.getPaperdoll(EPaperdoll.CHEST).getDescription(), TooltipWay.right, 0);
			TooltipManager.setTooltip(invGloves, inv.getPaperdoll(EPaperdoll.GLOVES).getDescription(), TooltipWay.right, 0);
			TooltipManager.setTooltip(invFeet, inv.getPaperdoll(EPaperdoll.FEET).getDescription(), TooltipWay.right, 0);
			TooltipManager.setTooltip(invRHand, inv.getPaperdoll(EPaperdoll.RHAND).getDescription(), TooltipWay.right, 0);
			TooltipManager.setTooltip(invLHand, inv.getPaperdoll(EPaperdoll.LHAND).getDescription(), TooltipWay.right, 0);

			// clear all objects
			objects.forEach(o ->
			{
				itemsGeneral.remove(o);
				TooltipManager.removeTooltips(o);
			});
			objects.clear();

			int MAX_COLUMN = 8;
			int MAX_FILE = 9;

			int slots = MAX_FILE * MAX_COLUMN;

			int column = 0;
			int file = 0;

			for (BItem item : inv.getItems())
			{
				if (item.isEquiped())
				{
					continue;
				}

				// Adena
				if (item.getId() == 57)
				{
					invAdena.setText(Util.formatAdena(item.getCount()));
				}

				WebLabel wl = new WebLabel("");
				wl.setIcon(Img.create("items/" + IconData.getIconByItemId(item.getId()) + ".png"));
				wl.setBounds((column * 32 + (column * 3) + 6), (file * 32 + (file * 3) + 6), 32, 32);
				wl.setBorder(new LineBorder(new Color(102, 153, 255)));
				wl.setVisible(true);

				// action handler
				wl.addMouseListener(new InventoryMenu(wl, item));

				// popup
				TooltipManager.setTooltip(wl, "<html><body><font color=FFFF00>" + item.getName() + "</font><br>count: " + item.getCount() + "<br>enchant: +" + item.getEnchantLevel() + "</body></html>", (column < MAX_COLUMN / 2) ? TooltipWay.right : TooltipWay.left, 0);

				objects.add(wl);
				itemsGeneral.add(wl, 0);

				column++;
				slots--;
				if (column % MAX_COLUMN == 0)
				{
					file++;
					column = 0;
				}
			}

			// se rellenan las filas q faltan para dar la apariencia de inventario xD
			for (int i = 0; i < slots; i++)
			{
				WebLabel wl = new WebLabel("");
				wl.setBounds((column * 32 + (column * 3)) + 6, (file * 32 + (file * 3)) + 6, 32, 32);
				wl.setBorder(new LineBorder(new Color(102, 153, 255)));
				wl.setVisible(true);

				objects.add(wl);
				itemsGeneral.add(wl, 0);

				column++;
				if (column % MAX_COLUMN == 0)
				{
					file++;
					column = 0;
				}
			}

			// se define en una barra de progreso el peso que lleva el personaje
			load.setValue(ObjectsManager.getBot().getMaxLoad() * 100 / ObjectsManager.getBot().getCurrentLoad());

			//itemsGeneral.repaint();
		});
	}

	public void updateEquipmentItems()
	{
		SwingUtilities.invokeLater(() ->
		{
			Inventory inv = ObjectsManager.getBot().getInventory();

			// se define el icono de los items equipados
			invREar.setIcon(inv.getPaperdoll(EPaperdoll.REAR).getIcon());
			invLEar.setIcon(inv.getPaperdoll(EPaperdoll.LEAR).getIcon());
			invHead.setIcon(inv.getPaperdoll(EPaperdoll.HEAD).getIcon());
			invChest.setIcon(inv.getPaperdoll(EPaperdoll.CHEST).getIcon());
			invGloves.setIcon(inv.getPaperdoll(EPaperdoll.GLOVES).getIcon());
			invFeet.setIcon(inv.getPaperdoll(EPaperdoll.FEET).getIcon());
			invRHand.setIcon(inv.getPaperdoll(EPaperdoll.RHAND).getIcon());
			invLHand.setIcon(inv.getPaperdoll(EPaperdoll.LHAND).getIcon());
			// TODO se podrian agregar listeners para equipar-desequipar
		});
	}

	public static InventoryWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final InventoryWindow INSTANCE = new InventoryWindow();
	}
}
