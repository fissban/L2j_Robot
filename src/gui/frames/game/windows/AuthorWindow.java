package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import com.alee.laf.label.WebLabel;

import gui.builder.WindowFrame;
import main.util.Img;

/**
 * @author fissban
 */
public class AuthorWindow extends WindowFrame
{
	private static final long serialVersionUID = 1L;

	public AuthorWindow()
	{
		super("Author");
		setBounds(0, 0, 341, 380);

		setBackground(new Color(248, 248, 255));
		getContentPane().setBackground(new Color(248, 248, 255));

		WebLabel lblPhoto = new WebLabel();
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setIcon(Img.create("general/avatar.jpg"));
		lblPhoto.setBounds(0, 35, 335, 130);
		panel.add(lblPhoto);

		WebLabel lblFissban = new WebLabel("Fissban");
		lblFissban.setHorizontalAlignment(SwingConstants.CENTER);
		lblFissban.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
		lblFissban.setBounds(0, 165, 335, 35);
		panel.add(lblFissban);

		WebLabel lblSkype = new WebLabel("Skype:");
		lblSkype.setForeground(Color.WHITE);
		lblSkype.setIcon(Img.create("general/icons/skype.png"));
		lblSkype.setBounds(10, 210, 96, 20);
		panel.add(lblSkype);

		WebLabel lblEmail = new WebLabel("Email:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setIcon(Img.create("general/icons/address-book.png"));
		lblEmail.setBounds(10, 236, 96, 20);
		panel.add(lblEmail);

		WebLabel lblWeb = new WebLabel("Web:");
		lblWeb.setForeground(Color.WHITE);
		lblWeb.setIcon(Img.create("general/icons/globe.png"));
		lblWeb.setBounds(10, 262, 96, 20);
		panel.add(lblWeb);

		WebLabel lblFacebook = new WebLabel("Facebook:");
		lblFacebook.setForeground(Color.WHITE);
		lblFacebook.setIcon(Img.create("general/icons/facebook.png"));
		lblFacebook.setBounds(10, 288, 96, 20);
		panel.add(lblFacebook);

		WebLabel lblSkypeInfo = new WebLabel("fissban");
		lblSkypeInfo.setForeground(Color.WHITE);
		lblSkypeInfo.setBounds(106, 211, 229, 20);
		panel.add(lblSkypeInfo);

		WebLabel lblEmailInfo = new WebLabel("marco.faccio@gmail.com");
		lblEmailInfo.setForeground(Color.WHITE);
		lblEmailInfo.setBounds(106, 237, 225, 20);
		panel.add(lblEmailInfo);

		WebLabel lblWebInfo = new WebLabel("www.l2devsadmins.net/fissban");
		lblWebInfo.setForeground(Color.WHITE);
		lblWebInfo.setBounds(106, 263, 225, 20);
		panel.add(lblWebInfo);

		WebLabel lblFacebookInfo = new WebLabel("www.facebook.com/l2jdeveloper");
		lblFacebookInfo.setForeground(Color.WHITE);
		lblFacebookInfo.setBounds(106, 289, 229, 20);
		panel.add(lblFacebookInfo);
	}

	public void open()
	{
		setVisible(!isVisible());
	}

	public static AuthorWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final AuthorWindow INSTANCE = new AuthorWindow();
	}
}
