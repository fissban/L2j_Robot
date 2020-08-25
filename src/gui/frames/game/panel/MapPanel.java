package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;

import gui.builder.CircleLabel;
import gui.frames.game.menu.MapInfoMenu;
import gui.frames.game.menu.MapMenu;
import gui.look.fissban.FPanelDegrade;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BObject;
import main.connection.bot.objects.BPlayer;
import main.connection.bot.objects.BSummon;
import main.managers.ObjectsManager;
import main.util.Img;
import main.util.UtilMap;

/**
 * @author fissban
 */
public class MapPanel extends FPanelDegrade
{
	// map image
	private final WebLabel mapLebel = new WebLabel("");
	private WebLabel botMapObject = new WebLabel();
	private CircleLabel botMapRadius = new CircleLabel();

	private WebLabel coords = new WebLabel("x: y:");

	// list objects created in the live map
	private Map<Integer, WebLabel> mapObjects = new HashMap<>();

	public MapPanel()
	{
		super();

		setBounds(267, 0, 512, 480);

		//Border comp = BorderFactory.createEtchedBorder(Color.RED, Color.CYAN);
		//TitledBorder border = BorderFactory.createTitledBorder(comp, "Map Live");
		//border.setTitleColor(Color.RED);
		//setBorder(border);

		WebLabel lupAdd = new WebLabel("");
		lupAdd.setBounds(479, 0, 14, 14);
		lupAdd.setIcon(Img.create("general/icons/add.png", 12, 12));
		add(lupAdd);

		WebLabel lupSub = new WebLabel("");
		lupSub.setBounds(493, 0, 14, 14);
		lupSub.setIcon(Img.create("general/icons/sub.png", 12, 12));
		add(lupSub);

		WebLabel mapQuestion = new WebLabel("");
		mapQuestion.setBounds(465, 0, 14, 14);
		mapQuestion.setIcon(Img.create("general/icons/question.png", 12, 12));
		add(mapQuestion);
		// listener para las acciones con click derecho
		mapQuestion.addMouseListener(new MapInfoMenu(mapQuestion));

		TooltipManager.setTooltip(mapQuestion, "Map Info", TooltipWay.trailing, 0);
		TooltipManager.setTooltip(lupAdd, "Zoom +", TooltipWay.trailing, 0);
		TooltipManager.setTooltip(lupSub, "Zoom -", TooltipWay.trailing, 0);

		WebLabel lblNewLabel = new WebLabel("if you click on the map you can move your character on it.");
		lblNewLabel.setDrawShade(true);
		lblNewLabel.setShadeColor(new Color(0, 102, 153));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblNewLabel.setBoldFont(true);
		lblNewLabel.setBounds(8, 14, 306, 36);
		add(lblNewLabel);

		WebLabel mapLiveTop = new WebLabel("Map Live");
		mapLiveTop.setDrawShade(true);
		mapLiveTop.setShadeColor(new Color(0, 102, 153));
		mapLiveTop.setForeground(new Color(255, 255, 255));
		mapLiveTop.setFont(new Font("Tahoma", Font.BOLD, 9));
		mapLiveTop.setBoldFont(true);
		mapLiveTop.setHorizontalTextPosition(SwingConstants.CENTER);
		mapLiveTop.setHorizontalAlignment(SwingConstants.CENTER);
		mapLiveTop.setBounds(0, 0, 509, 14);
		add(mapLiveTop);

		coords.setBounds(336, 455, 157, 14);
		coords.setDrawShade(true);
		coords.setShadeColor(new Color(0, 102, 153));
		coords.setForeground(new Color(255, 255, 255));
		coords.setFont(new Font("Tahoma", Font.BOLD, 9));
		coords.setBoldFont(true);
		coords.setHorizontalTextPosition(SwingConstants.CENTER);
		coords.setHorizontalAlignment(SwingConstants.LEFT);
		add(coords);

		mapLebel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mapLebel.setBorder(null);
		mapLebel.setBounds(4, 14, 504, 462);
		mapLebel.setForeground(new Color(248, 248, 255));
		mapLebel.setBackground(new Color(248, 248, 255));
		mapLebel.setIcon(Img.create("maps/17_25.jpg", mapLebel.getWidth(), mapLebel.getHeight()));
		mapLebel.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				SwingUtilities.invokeLater(() ->
				{
					if (e.getSource() == mapLebel)
					{
						int[] pos = UtilMap.getPosInClient(e.getX() + 5, e.getY() + 5);
						coords.setText("x:" + pos[0] + " y:" + pos[1]);
					}
				});
			}
		});
		mapLebel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				SwingUtilities.invokeLater(() ->
				{
					if (e.getSource() == mapLebel)
					{
						int[] pos = UtilMap.getPosInClient(e.getX(), e.getY());
						ObjectsManager.getBot().getAi().moveTo(pos[0], pos[1]);
					}
				});
			}
		});
		add(mapLebel);
	}

	/**
	 * Remove an object from the map
	 * @param objectId
	 */
	public void removeObject(int objectId)
	{
		SwingUtilities.invokeLater(() ->
		{
			if (mapObjects.containsKey(objectId))
			{
				WebLabel object = mapObjects.remove(objectId);
				TooltipManager.removeTooltips(object);
				remove(object);
			}
		});
	}

	/**
	 * Update or add a specific object to the map
	 */
	public void updateOrAddObject(BObject o)
	{
		SwingUtilities.invokeLater(() ->
		{
			WebLabel object;

			if (mapObjects.containsKey(o.getObjectId()))
			{
				object = mapObjects.get(o.getObjectId());
			}
			else
			{
				object = new WebLabel("");
				object.setBorder(null);

				// listener para las acciones con click derecho
				object.addMouseListener(new MapMenu(object, o, MouseEvent.BUTTON3));

				if (object.getIcon() == null)
				{
					String image = "";

					if (o instanceof BPlayer)
					{
						image = "IconYellow";
						TooltipManager.setTooltip(object, o.getName() + " [" + ((BPlayer) o).getTitle() + "]", TooltipWay.trailing, 0);
					}
					else if (o instanceof BNpc)
					{
						if (((BNpc) o).isAttackable())
						{
							image = "IconRed";
						}
						else
						{
							image = "IconGrey";
						}
						TooltipManager.setTooltip(object, o.getName() + " [" + ((BNpc) o).getTitle() + "]", TooltipWay.trailing, 0);
					}
					else if (o instanceof BItem)
					{
						image = "IconBlue";
						TooltipManager.setTooltip(object, "[" + o.getName() + "]", TooltipWay.trailing, 0);
					}
					else if (o instanceof BSummon)
					{
						image = "IconOrange";
						TooltipManager.setTooltip(object, "[Summon " + o.getName() + "]", TooltipWay.trailing, 0);
					}

					object.setIcon(Img.create("general/" + image + ".png", 8, 8));
				}

				//object.setVisible(true);
				mapObjects.put(o.getObjectId(), object);
			}

			// se posiciona en el mapa el objeto
			int pos[] = UtilMap.getPosInLiveMap(o.getX(), o.getY());

			//if (zoom > 0)
			//{
			//	BPlayer boot = ObjectsData.getBot();
			//	int posBot[] = UtilMap.getPosInLiveMap(boot.getX(), boot.getY(), mapLebel.getWidth(), mapLebel.getHeight());
			//
			//	pos[0] -= posBot[0];
			//	pos[1] -= posBot[1];
			//}
			object.setBounds(pos[0], pos[1], 8, 8);
			//object.repaint();

			object.addMouseMotionListener(new MouseMotionAdapter()
			{
				@Override
				public void mouseMoved(MouseEvent e)
				{
					SwingUtilities.invokeLater(() ->
					{
						if (e.getSource() == object)
						{
							coords.setText("x:" + o.getX() + " y:" + o.getY());
						}
					});
				}
			});

			try
			{
				remove(object);
			}
			catch (Exception e)
			{
				// ignore NPE
			}

			add(object, 1);
			//repaint();
		});

	}

	public WebLabel getMapLevel()
	{
		return mapLebel;
	}

	public int getZoom()
	{
		return 1;//zoom;
	}

	/**
	 * Update the position of the bot within the map
	 */
	public void updateBot()
	{
		SwingUtilities.invokeLater(() ->
		{
			BPlayer bot = ObjectsManager.getBot();

			int pos[] = UtilMap.getPosInLiveMap(bot.getX(), bot.getY());

			TooltipManager.setTooltip(botMapObject, bot.getName(), TooltipWay.trailing, 0);
			botMapObject.setBorder(null);
			botMapObject.setIcon(Img.create("general/IconGreen.png", 10, 10));
			botMapObject.setBounds(pos[0], pos[1], 10, 10);
			botMapObject.setVisible(true);
			botMapObject.repaint();

			int r = (mapLebel.getHeight() * bot.getAi().RADIO_ATTACK / UtilMap.TILE_SIZE);
			botMapRadius.setPos(pos[0], pos[1], r);
			botMapRadius.repaint();

			add(botMapObject, 0);
			add(botMapRadius, 1);
			repaint();

			mapObjects.put(bot.getObjectId(), botMapObject);
		});
	}

	/**
	 * Update the bot radius attack
	 */
	public void updateRadiusAttack()
	{
		SwingUtilities.invokeLater(() ->
		{
			BPlayer bot = ObjectsManager.getBot();
			int pos[] = UtilMap.getPosInLiveMap(bot.getX(), bot.getY());
			int radius = (mapLebel.getHeight() * bot.getAi().RADIO_ATTACK / UtilMap.TILE_SIZE);
			botMapRadius.setPos(pos[0], pos[1], radius);
			botMapRadius.repaint();
		});
	}

	/**
	 * Update the map image
	 */
	public void updateMapImage()
	{
		SwingUtilities.invokeLater(() ->
		{
			BPlayer bot = ObjectsManager.getBot();
			mapLebel.setIcon(UtilMap.getMapRegion(bot.getX(), bot.getY()));
			mapLebel.repaint();
		});
	}
}
