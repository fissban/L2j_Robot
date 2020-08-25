package gui.frames.login;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.progressbar.WebProgressBar;

import gui.builder.BFrame;
import gui.look.fissban.FButton;
import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FTextField;
import main.connection.server.GameConnection;
import main.enums.EClassId;
import main.enums.ERace;
import main.enums.ESex;
import main.managers.ConnectionManager;
import main.network.game.client.CharacterCreate;
import main.util.Img;

/**
 * @author fissban
 */
public class CreateCharacter extends BFrame
{
	// Need values for sendPacket CharacterCreate
	private WebComboBox race;
	private WebComboBox sex;
	private WebComboBox classId;

	public CreateCharacter()
	{
		super();

		try
		{
			// read CharTemplate
			ConnectionManager.getGame().readPacket();

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
	@SuppressWarnings("unchecked")
	private void init()
	{
		progressBar = new WebProgressBar(0, 100);
		progressBar.setVisible(false);
		progressBar.setBorderPainted(false);
		progressBar.setBorder(null);
		progressBar.setOpaque(true);
		progressBar.setAutoscrolls(true);
		progressBar.setBackground(new Color(0, 0, 139));
		progressBar.setBounds(379, 477, 264, 14);
		getContentPane().add(progressBar);

		WebLabel blblCreateCharacter = new WebLabel("Create Character");
		blblCreateCharacter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blblCreateCharacter.setBounds(207, 206, 600, 25);
		blblCreateCharacter.setHorizontalAlignment(SwingConstants.CENTER);
		blblCreateCharacter.setBoldFont(true);
		blblCreateCharacter.setBackground(new Color(248, 248, 255));
		blblCreateCharacter.setBorder(null);
		blblCreateCharacter.setForeground(new Color(51, 204, 255));
		getContentPane().add(blblCreateCharacter);

		FTextField name = new FTextField();
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setText("name");
		name.setBounds(400, 247, 224, 30);
		getContentPane().add(name);
		name.setColumns(10);

		race = new WebComboBox(ERace.values());
		race.setRound(4);
		race.setShadeWidth(0);
		race.setSelectedIndex(0);
		race.setBounds(400, 288, 224, 20);
		race.addActionListener(l ->
		{
			classId.removeAllItems();
			ERace r = ERace.valueOf(race.getSelectedItem().toString());

			for (EClassId c : EClassId.values())
			{
				if (c.getRace() == r && c.getParent() == null)
				{
					classId.addItem(c);
				}
			}
			classId.setSelectedIndex(0);
		});
		getContentPane().add(race);

		sex = new WebComboBox(ESex.values());
		sex.setRound(4);
		sex.setShadeWidth(0);
		sex.setSelectedIndex(0);
		sex.setBounds(400, 319, 224, 20);
		getContentPane().add(sex);

		// default value is HUMAN!
		List<EClassId> list = new ArrayList<>();

		for (EClassId c : EClassId.values())
		{
			if (c.getRace() == ERace.HUMAN && c.getParent() == null)
			{
				list.add(c);
			}
		}

		classId = new WebComboBox(list.toArray());
		classId.setRound(4);
		classId.setShadeWidth(0);
		classId.setBounds(400, 350, 224, 20);
		getContentPane().add(classId);

		FButton btnCreate = new FButton("Create");
		btnCreate.setBounds(469, 381, 89, 23);
		btnCreate.addActionListener(e ->
		{
			try
			{
				int s = ESex.valueOf(sex.getSelectedItem().toString()).ordinal();
				int r = ERace.valueOf(race.getSelectedItem().toString()).ordinal();
				int c = EClassId.valueOf(classId.getSelectedItem().toString()).ordinal();

				GameConnection game = ConnectionManager.getGame();

				game.sendPacket(new CharacterCreate(name.getText(), r, s, c));
				// Read next packet (CharacterCreateOk || CahracterCreateFail)
				game.readPacket();

				// Close actual windows
				dispose();

				// if read CharacterCreateOk
				if (game.getState() == 0x19)
				{
					// Read CharSelectInfo for new character created
					game.readPacket();
					// Reload CharacterList
					CharacterList.getInstance().init();
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		});

		getContentPane().add(btnCreate);

		FPanelDegrade panel = new FPanelDegrade();
		panel.setBounds(200, 200, 617, 226);
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
