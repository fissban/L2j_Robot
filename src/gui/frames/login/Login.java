package gui.frames.login;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.progressbar.WebProgressBar;

import gui.builder.BFrame;
import gui.look.fissban.FButton;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FTextField;
import main.connection.login.LoginConnection;
import main.data.LoginData;
import main.managers.ConnectionManager;
import main.network.login.LoginServerToClientCodes;
import main.util.Img;

/**
 * @author fissban
 */
public class Login extends BFrame
{
	private static final long serialVersionUID = 1L;
	private FTextField account;
	private FTextField password;
	private WebLabel lblAccount;
	private WebLabel lblPassword;

	public Login()
	{
		super();

		try
		{
			init();

			setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void init()
	{
		account = new FTextField(LoginData.USER_ACCOUNT, true);
		account.setHorizontalAlignment(SwingConstants.CENTER);
		account.setBounds(435, 260, 149, 20);
		getContentPane().add(account);
		account.setColumns(10);

		password = new FTextField(LoginData.USER_PASS, true);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setColumns(10);
		password.setBounds(435, 307, 149, 20);
		getContentPane().add(password);

		FButton btnLogin = new FButton("Login");
		btnLogin.setBounds(464, 338, 89, 23);
		getContentPane().add(btnLogin);

		lblAccount = new WebLabel("Account:");
		lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAccount.setBounds(206, 240, 600, 15);
		lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccount.setBoldFont(true);
		lblAccount.setBackground(new Color(248, 248, 255));
		lblAccount.setBorder(null);
		lblAccount.setForeground(new Color(240, 248, 255));
		getContentPane().add(lblAccount);

		lblPassword = new WebLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(206, 290, 600, 15);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBoldFont(true);
		lblPassword.setBackground(new Color(248, 248, 255));
		lblPassword.setBorder(null);
		lblPassword.setForeground(new Color(240, 248, 255));
		getContentPane().add(lblPassword);

		progressBar = new WebProgressBar(0, 100);
		progressBar.setVisible(false);
		progressBar.setBorderPainted(false);
		progressBar.setBorder(null);
		progressBar.setOpaque(true);
		progressBar.setAutoscrolls(true);
		progressBar.setBackground(new Color(0, 0, 139));
		progressBar.setBounds(381, 357, 264, 14);
		getContentPane().add(progressBar);

		WebCheckBox record = new WebCheckBox("Record Account and Password");
		record.setRound(4);
		record.setShadeWidth(0);
		record.setBoldFont(true);
		record.setHorizontalAlignment(SwingConstants.CENTER);
		record.setSelected(LoginData.SAVE_USER_PASS);
		record.setOpaque(false);
		record.setForeground(new Color(240, 248, 255));
		record.setBounds(206, 372, 600, 23);
		record.addActionListener(e ->
		{
			LoginData.SAVE_USER_PASS = !LoginData.SAVE_USER_PASS;
		});
		getContentPane().add(record);

		WebLabel wblblLogin = new WebLabel("Login");
		wblblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		wblblLogin.setForeground(new Color(51, 204, 255));
		wblblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		wblblLogin.setBorder(null);
		wblblLogin.setBoldFont(true);
		wblblLogin.setBackground(new Color(51, 204, 255));
		wblblLogin.setBounds(199, 206, 615, 25);
		getContentPane().add(wblblLogin);

		// button action "Login"
		btnLogin.addActionListener(a ->
		{
			// Save login.properties
			if (LoginData.SAVE_USER_PASS)
			{
				LoginData.USER_ACCOUNT = account.getText();
				LoginData.USER_PASS = password.getText();
				LoginData.save();
			}

			LoginConnection login = ConnectionManager.getLogin();

			login.setAccount(account.getText());
			login.setPassword(password.getText());

			// write(GAME_GUARD_OK) & read(LOGIN_OK or SERVER_LIST)
			login.writePacket();

			// if the license is shown the acceptance of the same is sent so leaves the selection of server
			if (login.getState() == LoginServerToClientCodes.LOGIN_OK)
			{
				login.writePacket();
			}

			initWaitingAndRunState(login, this, LoginServerToClientCodes.LOGIN_OK, ServerList.class);
		});

		FPanelDegrade panel = new FPanelDegrade();
		panel.setBounds(200, 200, 617, 209);
		getContentPane().add(panel);

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
