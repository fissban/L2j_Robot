package gui.frames.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.SwingConstants;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;

import gui.builder.BFrame;
import gui.frames.game.GameFrame;
import gui.look.fissban.FButton;
import gui.look.fissban.FPanelDegrade;
import main.managers.ConnectionManager;
import main.model.CharSelectInfoPackage;
import main.network.game.client.NewCharacter;
import main.util.Img;
import main.util.Util;

/**
 * @author fissban
 */
public class CharacterList extends BFrame
{
	private static final long serialVersionUID = 1L;
	// buttons
	private FButton button0;
	private FButton button1;
	private FButton button2;
	private FButton button3;
	private FButton button4;
	private FButton button5;
	private FButton button6;

	private WebPanel panel;

	public CharacterList()
	{
		super();

		progressBar = new WebProgressBar(0, 100);
		progressBar.setVisible(false);
		progressBar.setBorderPainted(false);
		progressBar.setBorder(null);
		progressBar.setOpaque(true);
		progressBar.setAutoscrolls(true);
		progressBar.setBackground(new Color(0, 0, 139));
		progressBar.setBounds(379, 477, 264, 14);
		getContentPane().add(progressBar);

		WebLabel lblSelectCharacter = new WebLabel("Select Character");
		lblSelectCharacter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectCharacter.setBounds(207, 206, 600, 25);
		lblSelectCharacter.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectCharacter.setBoldFont(true);
		lblSelectCharacter.setBorder(null);
		lblSelectCharacter.setForeground(new Color(51, 204, 255));
		getContentPane().add(lblSelectCharacter);

		FButton btnNewCharacter = new FButton("New Character");
		btnNewCharacter.addActionListener(e -> createCharacter());
		btnNewCharacter.setBounds(849, 576, 139, 23);
		getContentPane().add(btnNewCharacter);

		init();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void init()
	{
		// Remove old buttons
		removeComponent(button0);
		removeComponent(button1);
		removeComponent(button2);
		removeComponent(button3);
		removeComponent(button4);
		removeComponent(button5);
		removeComponent(button6);
		removeComponent(panel);

		CharSelectInfoPackage slot = null;
		//  SLOT 0 ------------------------------------------------------------------------------------------------------
		slot = ConnectionManager.getGame().getCharList().get(0);
		button0 = new FButton(getSlotText(slot));
		button0.setBackground(Color.WHITE);
		button0.setBounds(286, 248, 450, 23);
		button0.setEnabled(slot != null);
		button0.addActionListener(a -> selectCharacter(button0.getText().split(" ")[0]));
		getContentPane().add(button0);
		//  SLOT 1 ------------------------------------------------------------------------------------------------------
		slot = ConnectionManager.getGame().getCharList().get(1);
		button1 = new FButton(getSlotText(slot));
		button1.setBackground(Color.WHITE);
		button1.setBounds(286, 274, 450, 23);
		button1.setEnabled(slot != null);
		button1.addActionListener(a -> selectCharacter(button1.getText().split(" ")[0]));
		getContentPane().add(button1);
		//  SLOT 2 ------------------------------------------------------------------------------------------------------
		slot = ConnectionManager.getGame().getCharList().get(2);
		button2 = new FButton(getSlotText(slot));
		button2.setBackground(Color.WHITE);
		button2.setBounds(286, 301, 450, 23);
		button2.setEnabled(slot != null);
		button2.addActionListener(a -> selectCharacter(button2.getText().split(" ")[0]));
		getContentPane().add(button2);
		//  SLOT 3 ------------------------------------------------------------------------------------------------------
		slot = ConnectionManager.getGame().getCharList().get(3);
		button3 = new FButton(getSlotText(slot));
		button3.setBackground(Color.WHITE);
		button3.setBounds(286, 327, 450, 23);
		button3.setEnabled(slot != null);
		button3.addActionListener(a -> selectCharacter(button3.getText().split(" ")[0]));
		getContentPane().add(button3);
		//  SLOT 4 ------------------------------------------------------------------------------------------------------
		slot = ConnectionManager.getGame().getCharList().get(4);
		button4 = new FButton(getSlotText(slot));
		button4.setBackground(Color.WHITE);
		button4.setBounds(286, 353, 450, 23);
		button4.setEnabled(slot != null);
		button4.addActionListener(a -> selectCharacter(button4.getText().split(" ")[0]));
		getContentPane().add(button4);
		//  SLOT 5 ------------------------------------------------------------------------------------------------------
		slot = ConnectionManager.getGame().getCharList().get(5);
		button5 = new FButton(getSlotText(slot));
		button5.setBackground(Color.WHITE);
		button5.setBounds(286, 379, 450, 23);
		button5.setEnabled(slot != null);
		button5.addActionListener(a -> selectCharacter(button5.getText().split(" ")[0]));
		remove(button5);
		getContentPane().add(button5);
		//  SLOT 6 ------------------------------------------------------------------------------------------------------
		slot = ConnectionManager.getGame().getCharList().get(6);
		button6 = new FButton(getSlotText(slot));
		button6.setBackground(Color.WHITE);
		button6.setBounds(286, 406, 450, 23);
		button6.setEnabled(slot != null);
		button6.addActionListener(a -> selectCharacter(button6.getText().split(" ")[0]));
		getContentPane().add(button6);

		FPanelDegrade panel = new FPanelDegrade();
		panel.setBounds(200, 200, 617, 245);
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

		setVisible(true);
	}

	public void createCharacter()
	{
		try
		{
			// It tells the server that we are going to create a new character.
			ConnectionManager.getGame().sendPacket(new NewCharacter());
			setVisible(false);

			// You should receive the ChatTemplates package but do not send anything relevant
			Util.openLoginFrame(CreateCharacter.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void selectCharacter(String playerName)
	{
		ConnectionManager.getGame().setSelectPlayerName(playerName);

		// Write CHARACTER_SELECT
		ConnectionManager.getGame().writePacket();
		ConnectionManager.getGame().readPacket();
		// Write ENTER_WORLD
		ConnectionManager.getGame().writePacket();

		// The connection starts and the first packages client<->server are loaded
		if (ConnectionManager.initBot())
		{
			// Close actual windows
			setVisible(false);
			dispose();

			GameFrame.getInstance();
		}
	}

	/**
	 * Simple remove old element if add in pane
	 * @param c
	 */
	private void removeComponent(Component c)
	{
		if (c != null)
		{
			remove(c);
		}
	}

	/**
	 * Simple format button name
	 * @param s
	 * @return
	 */
	private String getSlotText(CharSelectInfoPackage s)
	{
		return (s != null) ? (s.getName() + " [Lv" + s.getLevel() + "]") : "NoName [Lv 0]";
	}

	public static CharacterList getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final CharacterList INSTANCE = new CharacterList();
	}
}
