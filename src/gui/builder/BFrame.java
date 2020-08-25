package gui.builder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.rootpane.WebFrame;

import gui.look.fissban.FPanelDegrade;
import main.connection.ServerConnection;
import main.managers.ConnectionManager;
import main.managers.ThreadManager;
import main.network.login.LoginServerToClientCodes;
import main.util.Img;
import main.util.Util;

/**
 * Class extending BotFrame to save code in the interface
 *
 * @author fissban
 */
public class BFrame extends WebFrame
{
	private static final long serialVersionUID = 1L;

	public WebProgressBar progressBar;

	public BFrame()
	{
		super("L2 - Robot ][");
		setBounds(0, 0, 1024, 683);
		setIconImage(Img.create("general/icons/robot.png").getImage());
		setName("L2 - Robot ][");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		WebLabel splash = new WebLabel("");
		splash.setVerticalAlignment(SwingConstants.TOP);
		splash.setHorizontalAlignment(SwingConstants.CENTER);
		splash.setBackground(new Color(0, 0, 0));
		splash.setIcon(Img.create("general/splash.png", 588, 172));
		splash.setBounds(0, 11, 1008, 172);
		add(splash);

		WebLabel www = new WebLabel("www.l2devsadmins.net");
		www.setShadeColor(new Color(173, 216, 230));
		www.setHorizontalAlignment(SwingConstants.CENTER);
		www.setForeground(new Color(255, 255, 255));
		www.setBounds(859, 627, 149, 14);
		add(www);

		WebLabel copyright = new WebLabel("Copyright \u00A9 2018 by fissban");
		copyright.setShadeColor(new Color(173, 210, 230));
		copyright.setHorizontalAlignment(SwingConstants.CENTER);
		copyright.setForeground(new Color(255, 255, 255));
		copyright.setBounds(0, 605, 1008, 40);
		add(copyright);

		FPanelDegrade footer = new FPanelDegrade();
		footer.setBounds(0, 605, 1008, 60);
		add(footer);

		if (ConnectionManager.getLogin() != null)
		{
			ConnectionManager.getLogin().setActualFrame(this);
		}
	}

	public void initWaitingAndRunState(ServerConnection con, WebFrame frame, int state, Class<?> nextFrame)
	{
		ThreadManager.execute(new Waiting(con, state, nextFrame, frame));
	}

	private class Waiting implements Runnable
	{
		ServerConnection con;
		int state;
		Class<?> nextFrame;
		WebFrame wframe;

		public Waiting(ServerConnection c, int s, Class<?> nf, WebFrame f)
		{
			con = c;
			state = s;
			nextFrame = nf;
			wframe = f;
			ConnectionManager.getLogin().setActualFrame(f);
		}

		@Override
		public void run()
		{
			progressBar.setVisible(true);
			progressBar.setValue(100);
			progressBar.setIndeterminate(true);

			while (con.getState() == state)
			{
				// a minimum wait is added between each iteration to avoid saturating the VM
				Util.sleep(100);

				switch (state)
				{
					case LoginServerToClientCodes.LOGIN_FAILED:
					{
						// in this case "LoginConnect" show "confirm dialog" and open Connect frame
						WebOptionPane.showConfirmDialog(wframe, "Login failed", "Login", JOptionPane.WARNING_MESSAGE);
						return;
					}

					case LoginServerToClientCodes.PLAY_FAILED:
					{
						// in this case "LoginConnect" show "confirm dialog" and open Connect frame
						WebOptionPane.showConfirmDialog(wframe, "play failed", "Login", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}

				// check connection state
				if (!con.isConnected())
				{
					WebOptionPane.showConfirmDialog(wframe, "Login not connect", "Login", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}

			progressBar.setIndeterminate(false);
			progressBar.setVisible(false);

			// close actual frame
			wframe.setVisible(false);
			wframe.dispose();
			Util.openLoginFrame(nextFrame);
		}
	}
}
