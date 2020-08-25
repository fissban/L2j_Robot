package gui.frames.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.WebList;
import com.alee.laf.list.WebListModel;
import com.alee.laf.progressbar.WebProgressBar;

import gui.builder.BFrame;
import gui.look.fissban.FButton;
import gui.look.fissban.FPanelDegrade;
import main.managers.ConnectionManager;
import main.model.ServerDataModel;
import main.network.login.LoginServerToClientCodes;
import main.util.Img;
import main.util.Util;

/**
 * @author fissban
 */
public class ServerList extends BFrame
{
	private static final long serialVersionUID = 1L;

	private WebList serverList;
	private WebLabel lblSelectServer;

	@SuppressWarnings("unchecked")
	public ServerList()
	{
		super();

		try
		{
			init();

			setVisible(true);

			progressBar.setVisible(true);

			while (ConnectionManager.getLogin().getServerList().isEmpty())
			{
				Util.sleep(200);
				System.out.println("server lsit empty");
			}

			progressBar.setValue(100);
			progressBar.setVisible(false);

			WebListModel<String> listModel = new WebListModel<>();
			// list
			for (ServerDataModel s : ConnectionManager.getLogin().getServerList().values())
			{
				listModel.addElement(s.toString());
			}

			serverList.setModel(listModel);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void init()
	{
		progressBar = new WebProgressBar(0, 100);
		progressBar.setBorderPainted(false);
		progressBar.setBorder(null);
		progressBar.setOpaque(true);
		progressBar.setVisible(false);
		progressBar.setAutoscrolls(true);
		progressBar.setBackground(new Color(0, 0, 139));
		progressBar.setBounds(382, 419, 264, 14);
		progressBar.setValue(100);
		progressBar.setIndeterminate(true);
		getContentPane().add(progressBar);

		serverList = new WebList();
		serverList.setSelectionShadeWidth(1);
		serverList.setSelectionRound(1);
		serverList.setSelectionBorderColor(new Color(51, 204, 255));
		serverList.setSelectionBackgroundColor(new Color(0, 51, 153));
		serverList.setSelectionBackground(new Color(0, 51, 102));
		serverList.setForeground(new Color(0, 0, 0));
		serverList.setBackground(new Color(240, 248, 255));
		serverList.setBounds(227, 242, 563, 147);
		serverList.setModel(new WebListModel<String>());
		getContentPane().add(serverList);

		FButton btnSelect = new FButton("Select");
		btnSelect.setBounds(474, 400, 89, 23);
		getContentPane().add(btnSelect);

		lblSelectServer = new WebLabel("Select Server");
		lblSelectServer.setBounds(210, 206, 600, 25);
		lblSelectServer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectServer.setBoldFont(true);
		lblSelectServer.setBackground(new Color(248, 248, 255));
		lblSelectServer.setBorder(null);
		lblSelectServer.setForeground(new Color(51, 204, 255));
		getContentPane().add(lblSelectServer);

		FPanelDegrade panel = new FPanelDegrade();
		panel.setBounds(200, 200, 617, 242);
		getContentPane().add(panel);

		// acciones de los botones
		btnSelect.addActionListener(a ->
		{
			int select = serverList.getSelectedIndex();

			if (select >= 0)
			{
				ConnectionManager.getLogin().setServerId(select + 1);
				ConnectionManager.getLogin().writePacket();

				// waiting for login confirmation
				progressBar.setValue(100);
				progressBar.setIndeterminate(true);

				while (ConnectionManager.getLogin().getState() != LoginServerToClientCodes.PLAY_OK)
				{
					Util.sleep(100);
				}

				// if restart connection or close
				if (ConnectionManager.getLogin().getState() == -1)
				{
					return;
				}

				// Disconnect from login
				ConnectionManager.getLogin().disconnect();

				// The connection is started and the first packets are read
				if (ConnectionManager.initGame())
				{
					setVisible(false);
					dispose();

					//nextFrame("CharacterList");
					CharacterList.getInstance();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Server Disable", "Login", JOptionPane.WARNING_MESSAGE);
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

		WebLabel image = new WebLabel();
		image.setBounds(0, 0, getWidth(), getHeight());
		image.setIcon(Img.create("general/2.png", getWidth(), getHeight()));
		getContentPane().add(image);
	}
}
