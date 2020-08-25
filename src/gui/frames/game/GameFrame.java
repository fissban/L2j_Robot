package gui.frames.game;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.laf.label.WebLabel;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.separator.WebSeparator;

import gui.frames.game.panel.ActionBotPanel;
import gui.frames.game.panel.BotInfoPanel;
import gui.frames.game.panel.BuffsPanel;
import gui.frames.game.panel.ChatPanel;
import gui.frames.game.panel.KnownListPanel;
import gui.frames.game.panel.MapPanel;
import gui.frames.game.panel.PartyPanel;
import gui.frames.game.panel.PetInfoPanel;
import gui.frames.game.panel.TargetInfoPanel;
import gui.frames.game.windows.AiSettingsWindow;
import gui.frames.game.windows.AuthorWindow;
import gui.frames.game.windows.BotStatsWindow;
import gui.frames.game.windows.ConsoleWindow;
import gui.frames.game.windows.InventoryWindow;
import gui.frames.game.windows.SkillsWindow;
import gui.look.fissban.FMenuItem;
import main.managers.ObjectsManager;
import main.managers.ThreadManager;
import main.network.game.client.Logout;
import main.network.game.client.ingame.RequestSkillList;
import main.util.Img;

/**
 * @author fissban
 */
public class GameFrame extends WebFrame
{
	// CHATS --------------------------------------------------------------------------
	private ChatPanel chat = new ChatPanel();

	public ChatPanel getChat()
	{
		return chat;
	}

	// MAP ----------------------------------------------------------------------------
	private MapPanel map = new MapPanel();

	public MapPanel getLiveMap()
	{
		return map;
	}

	// KNOWNLIST ----------------------------------------------------------------------
	private KnownListPanel known = new KnownListPanel();

	public KnownListPanel getKnown()
	{
		return known;
	}

	// BOT INFO -----------------------------------------------------------------------
	private BotInfoPanel botInfo = new BotInfoPanel();

	public BotInfoPanel getBotInfo()
	{
		return botInfo;
	}

	// PET INFO -----------------------------------------------------------------------
	private PetInfoPanel petInfo = new PetInfoPanel();

	public PetInfoPanel getPetInfo()
	{
		return petInfo;
	}

	// TARGET INFO --------------------------------------------------------------------
	private TargetInfoPanel targetInfo = new TargetInfoPanel();

	public TargetInfoPanel getTargetInfo()
	{
		return targetInfo;
	}

	// BUFFS --------------------------------------------------------------------------
	private BuffsPanel buffs = new BuffsPanel();

	public BuffsPanel getBuffs()
	{
		return buffs;
	}

	// ACTION BOT ---------------------------------------------------------------------
	private ActionBotPanel actions = new ActionBotPanel();

	public ActionBotPanel getActions()
	{
		return actions;
	}

	// PARTY -----------------------------------------------------------------------------

	private PartyPanel party = new PartyPanel();

	public PartyPanel getParty()
	{
		return party;
	}
	// ======================================================================================================= //

	/**
	 * Create the frame.
	 */
	public GameFrame()
	{
		super("L2 - Robot ][");
		setVisible(true);
		setBounds(0, 0, 1024, 683);
		setResizable(false);
		setName("L2 - Robot ][");
		setIconImage(Img.create("general/icons/robot.png").getImage());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				close();
				dispose();
				// se fuerza el cierre de la aplicacion y de todos los threads.
				System.exit(0);
			}
		});

		// MENU START -----------------------------------------------------------------
		WebMenuBar menu = new WebMenuBar();
		menu.setUndecorated(true);
		menu.setBackground(new Color(248, 248, 255));
		menu.setFont(new Font("Tahoma", Font.PLAIN, 10));
		menu.setRound(0);
		menu.setBorderColor(SystemColor.textHighlight);

		WebMenu menuCharacter = new WebMenu("Character");
		WebMenu menuPackets = new WebMenu("Packets");
		WebMenu menuBot = new WebMenu("Bot");

		FMenuItem subMenuSkills = new FMenuItem("Skills");
		FMenuItem subMenuInventory = new FMenuItem("Inventory");
		FMenuItem subMenuStats = new FMenuItem("Stats");
		FMenuItem subMenuConfigBot = new FMenuItem("Config Ai");
		FMenuItem subMenuSniffer = new FMenuItem("Sniffer");

		WebMenu mnOther = new WebMenu("Other");
		FMenuItem subMenuContact = new FMenuItem("Contact");
		FMenuItem subMenuDonate = new FMenuItem("Donate");
		WebSeparator separator = new WebSeparator();
		FMenuItem subMenuInfo = new FMenuItem("Info");
		FMenuItem submenuAuthor = new FMenuItem("Author");
		FMenuItem mntmConsole = new FMenuItem("Console");

		subMenuStats.setIcon(Img.create("ch3/mainwndtabicon3.png", 24, 24));
		subMenuSkills.setIcon(Img.create("ch3/mainwndtabicon2.png", 24, 24));
		subMenuInventory.setIcon(Img.create("ch3/menuicon2.png", 24, 24));
		subMenuConfigBot.setIcon(new ImageIcon(GameFrame.class.getResource("/com/alee/extended/style/icons/editor/settings.png")));
		submenuAuthor.setIcon(new ImageIcon(GameFrame.class.getResource("/gui/look/img/general/icons/user-worker-boss.png")));
		subMenuInfo.setIcon(new ImageIcon(GameFrame.class.getResource("/com/alee/extended/filechooser/icons/forward.png")));
		subMenuDonate.setIcon(new ImageIcon(GameFrame.class.getResource("/gui/look/img/general/icons/money-bag-dollar.png")));
		subMenuContact.setIcon(new ImageIcon(GameFrame.class.getResource("/com/alee/extended/label/icons/email.png")));
		subMenuSniffer.setIcon(new ImageIcon(GameFrame.class.getResource("/com/alee/extended/ninepatch/icons/redo.png")));
		mntmConsole.setIcon(new ImageIcon(GameFrame.class.getResource("/gui/look/img/general/icons/application-terminal.png")));

		menu.add(menuCharacter);
		menu.add(menuBot);
		menu.add(menuPackets);
		menu.add(mnOther);
		mnOther.add(submenuAuthor);

		menuCharacter.add(subMenuStats);
		menuCharacter.add(subMenuSkills);
		menuCharacter.add(subMenuInventory);

		menuPackets.add(subMenuSniffer);

		menuBot.add(subMenuConfigBot);

		WebSeparator separator1 = new WebSeparator();
		mnOther.add(separator1);

		mnOther.add(subMenuContact);
		mnOther.add(separator);
		mnOther.add(subMenuDonate);
		mnOther.add(subMenuInfo);

		mnOther.add(mntmConsole);

		mntmConsole.addActionListener(e ->
		{
			ConsoleWindow.getInstance().setLocationRelativeTo(this);
			ConsoleWindow.getInstance().open();
		});
		subMenuContact.addActionListener(e ->
		{
			try
			{
				Desktop.getDesktop().browse(new URI("http://l2devsadmins.net"));
			}
			catch (Exception ex)
			{
				//
			}
		});
		subMenuSniffer.addActionListener(e ->
		{
			// 
		});
		submenuAuthor.addActionListener(e ->
		{
			AuthorWindow.getInstance().setLocationRelativeTo(this);
			AuthorWindow.getInstance().open();
		});
		subMenuInventory.addActionListener(e ->
		{
			InventoryWindow.getInstance().setLocationRelativeTo(this);
			InventoryWindow.getInstance().open();
		});
		subMenuSkills.addActionListener(e ->
		{
			SkillsWindow.getInstance().setLocationRelativeTo(this);
			SkillsWindow.getInstance().open();
		});
		subMenuConfigBot.addActionListener(e ->
		{
			AiSettingsWindow.getInstance().setLocationRelativeTo(this);
			AiSettingsWindow.getInstance().open();
		});
		subMenuStats.addActionListener(e ->
		{
			BotStatsWindow.getInstance().setLocationRelativeTo(this);
			BotStatsWindow.getInstance().open();
		});

		setJMenuBar(menu);

		// MENU END -------------------------------------------------------------------

		getContentPane().add(map);
		getContentPane().add(chat);
		getContentPane().add(known);
		botInfo.setLocation(0, 0);
		getContentPane().add(botInfo);
		getContentPane().add(petInfo);
		getContentPane().add(targetInfo);
		getContentPane().add(buffs);

		// Memory bar without border and background
		WebMemoryBar memoryBar3 = new WebMemoryBar();
		memoryBar3.setShadeColor(new Color(100, 149, 237));
		memoryBar3.setRound(1);
		memoryBar3.setShadeWidth(1);
		memoryBar3.setBounds(780, 458, 228, 22);
		getContentPane().add(memoryBar3);
		memoryBar3.setUsedBorderColor(new Color(100, 149, 237));
		memoryBar3.setShowMaximumMemory(false);
		memoryBar3.setPreferredWidth(memoryBar3.getPreferredSize().width + 20);
		getContentPane().add(actions);
		getContentPane().add(party);

		WebLabel image = new WebLabel();
		image.setBounds(0, 0, getWidth(), getHeight());
		image.setIcon(Img.create("general/2.png", getWidth(), getHeight()));
		getContentPane().add(image);

		// Send RequestSkillList
		ObjectsManager.getBot().sendPacket(new RequestSkillList());
	}

	public void clientClose(String reason)
	{
		int option = JOptionPane.CANCEL_OPTION;

		while (option == JOptionPane.CANCEL_OPTION)
		{
			option = JOptionPane.showConfirmDialog(this, "Server Close reason: " + reason, "Game", JOptionPane.WARNING_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				close();
			}
		}
	}

	private void close()
	{
		ObjectsManager.getBot().sendPacket(Logout.STATIC_PACKET);
		ThreadManager.shutdown();
		SwingUtilities.invokeLater(() ->
		{
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});
	}

	public static GameFrame getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final GameFrame INSTANCE = new GameFrame();
	}
}
