package gui.frames.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.progressbar.WebProgressBar;

import gui.builder.BFrame;
import gui.frames.game.windows.ConsoleWindow;
import gui.look.fissban.FButton;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FTextField;
import main.data.IconData;
import main.data.ItemData;
import main.data.LoginData;
import main.data.NpcData;
import main.data.SkillData;
import main.enums.EChronicle;
import main.managers.ChronicleManager;
import main.managers.ConnectionManager;
import main.managers.ThreadManager;
import main.network.login.LoginServerToClientCodes;
import main.util.Img;

/**
 * @author fissban
 */
public class Connect extends BFrame
{
	private FTextField serverIp;

	private boolean selectChronicle = false;

	public Connect()
	{
		super();

		ThreadManager.init();
		LoginData.load();
		//NpcData.load();
		ItemData.load();
		IconData.load();
		SkillData.load();

		init();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void init()
	{
		getContentPane().setLayout(null);
		serverIp = new FTextField("127.0.0.1", true);
		serverIp.setAlignmentX(Component.LEFT_ALIGNMENT);
		serverIp.setAlignmentY(Component.TOP_ALIGNMENT);
		serverIp.setText(LoginData.SERVER_IP);
		serverIp.setBounds(432, 242, 149, 20);
		getContentPane().add(serverIp);

		FButton btnConnect = new FButton("Connect");
		btnConnect.setBounds(462, 273, 89, 23);
		getContentPane().add(btnConnect);

		progressBar = new WebProgressBar(0, 100);
		progressBar.setVisible(false);
		progressBar.setBorderPainted(false);
		progressBar.setBorder(null);
		progressBar.setOpaque(true);
		progressBar.setAutoscrolls(true);
		progressBar.setBackground(new Color(0, 0, 139));
		progressBar.setBounds(380, 336, 264, 14);
		getContentPane().add(progressBar);

		WebCheckBox record = new WebCheckBox("Record IP");
		record.setRound(4);
		record.setShadeWidth(0);
		record.setBoldFont(true);
		record.setHorizontalAlignment(SwingConstants.CENTER);
		record.setSelected(LoginData.SAVE_IP);
		record.setForeground(new Color(255, 255, 255));
		record.setBounds(201, 307, 615, 23);
		record.addActionListener(e -> LoginData.SAVE_IP = !LoginData.SAVE_IP);
		getContentPane().add(record);

		btnConnect.addActionListener(a -> btnConnect());

		WebLabel lblServerIp = new WebLabel("Server IP");
		lblServerIp.setShadeColor(new Color(102, 153, 204));
		lblServerIp.setDrawShade(true);
		lblServerIp.setBounds(200, 206, 615, 25);
		lblServerIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblServerIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerIp.setBoldFont(true);
		lblServerIp.setBorder(null);
		lblServerIp.setForeground(new Color(255, 255, 255));
		getContentPane().add(lblServerIp);

		WebLabel lblNewLabel = new WebLabel("Please, select the chronicle you wish to use.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setDrawShade(true);
		lblNewLabel.setShadeColor(new Color(25, 25, 112));
		lblNewLabel.setBounds(201, 391, 615, 25);
		getContentPane().add(lblNewLabel);

		WebLabel c4Icon = new WebLabel("C4");
		c4Icon.setBackground(new Color(1, 50, 67));
		c4Icon.setDrawShade(true);
		c4Icon.setShadeColor(new Color(153, 102, 0));
		c4Icon.setFont(new Font("Tahoma", Font.PLAIN, 22));
		c4Icon.setHorizontalTextPosition(SwingConstants.CENTER);
		c4Icon.setHorizontalAlignment(SwingConstants.CENTER);
		c4Icon.setForeground(new Color(51, 204, 0));
		c4Icon.setBounds(391, 420, 64, 64);
		c4Icon.setIcon(Img.create("general/icons/cronical2.png", c4Icon.getWidth(), c4Icon.getHeight()));
		getContentPane().add(c4Icon);

		WebLabel c4Name = new WebLabel("Scions of Destiny");
		c4Name.setDrawShade(true);
		c4Name.setShadeColor(new Color(0, 0, 0));
		c4Name.setFont(new Font("Tahoma", Font.BOLD, 11));
		c4Name.setForeground(new Color(0, 204, 0));
		c4Name.setHorizontalTextPosition(SwingConstants.CENTER);
		c4Name.setHorizontalAlignment(SwingConstants.CENTER);
		c4Name.setBounds(376, 483, 100, 20);
		getContentPane().add(c4Name);

		WebLabel c6Icon = new WebLabel("C6");
		c6Icon.setBackground(new Color(1, 50, 67));
		c6Icon.setDrawShade(true);
		c6Icon.setShadeColor(new Color(153, 102, 0));
		c6Icon.setFont(new Font("Tahoma", Font.PLAIN, 22));
		c6Icon.setHorizontalTextPosition(SwingConstants.CENTER);
		c6Icon.setHorizontalAlignment(SwingConstants.CENTER);
		c6Icon.setForeground(new Color(51, 204, 0));
		c6Icon.setBounds(556, 420, 64, 64);
		c6Icon.setIcon(Img.create("general/icons/cronical2.png", c6Icon.getWidth(), c6Icon.getHeight()));
		getContentPane().add(c6Icon);

		WebLabel c6Name = new WebLabel("Interlude");
		c6Name.setShadeColor(new Color(0, 0, 0));
		c6Name.setDrawShade(true);
		c6Name.setFont(new Font("Tahoma", Font.BOLD, 11));
		c6Name.setForeground(new Color(0, 153, 255));
		c6Name.setHorizontalTextPosition(SwingConstants.CENTER);
		c6Name.setHorizontalAlignment(SwingConstants.CENTER);
		c6Name.setBounds(537, 483, 100, 20);
		getContentPane().add(c6Name);

		c4Icon.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				ChronicleManager.set(EChronicle.C4);
				selectChronicle = true;

				SwingUtilities.invokeLater(() ->
				{
					c4Icon.setOpaque(true);
					c6Icon.setOpaque(false);
					c6Icon.repaint();
					c4Icon.repaint();
				});
			}
		});

		c6Icon.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				ChronicleManager.set(EChronicle.C6);
				selectChronicle = true;

				SwingUtilities.invokeLater(() ->
				{
					c6Icon.setOpaque(true);
					c4Icon.setOpaque(false);
					c6Icon.repaint();
					c4Icon.repaint();
				});
			}
		});

		// Memory bar without border and background
		WebMemoryBar memoryBar3 = new WebMemoryBar();
		memoryBar3.setShowMaximumMemory(false);
		memoryBar3.setShadeWidth(1);
		memoryBar3.setUsedBorderColor(new Color(100, 149, 237));
		memoryBar3.setBounds(10, 580, 218, 20);
		memoryBar3.setPreferredWidth(memoryBar3.getPreferredSize().width + 20);
		getContentPane().add(memoryBar3);

		FPanelDegrade panel = new FPanelDegrade();
		panel.setRadius(8);
		panel.setForeground(new Color(102, 153, 255));
		panel.setBounds(201, 209, 617, 148);
		getContentPane().add(panel);

		WebLabel image = new WebLabel();
		image.setBounds(0, 0, getWidth(), getHeight());
		image.setIcon(Img.create("general/2.png", getWidth(), getHeight()));
		getContentPane().add(image);
	}

	private void btnConnect()
	{
		SwingUtilities.invokeLater(() ->
		{
			if (!selectChronicle)
			{
				WebOptionPane.showMessageDialog(this, "Select your chronicle!", "Login", JOptionPane.WARNING_MESSAGE);
				return;
			}

			//aninmation.cancel(true);
			//aninmation = null;

			// Load npc info
			NpcData.load();

			// Save login.properties
			if (LoginData.SAVE_IP)
			{
				LoginData.SERVER_IP = serverIp.getText();
				LoginData.save();
			}
			// the connection is started and the first packet
			if (ConnectionManager.initLogin(serverIp.getText()))
			{
				// the first packet is sent
				ConnectionManager.getLogin().writePacket();
				// read next packet and wait for next frame
				initWaitingAndRunState(ConnectionManager.getLogin(), this, LoginServerToClientCodes.INIT, Login.class);
			}
			else
			{
				WebOptionPane.showMessageDialog(this, "LOGIN DISCONNECT", "Login", JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() ->
		{
			try
			{
				StyleConstants.darkBorderColor = new Color(204, 102, 0);

				//ConsoleWindow.getInstance();

				WebLookAndFeel.install();

				//CoreManagers.initialize();
				WebLookAndFeel.initializeManagers();
				// Enabling frame decoration
				//WebLookAndFeel.setDecorateFrames(true);
				//WebLookAndFeel.setDecorateAllWindows(true);

				WebLookAndFeel.setDecorateDialogs(true);

				new Connect();
			}
			catch (Exception e)
			{
				ConsoleWindow.getInstance().print("Error load connect", e);
			}
		});
	}
}
