package main.network.game.server;

import com.alee.laf.optionpane.WebOptionPane;

import gui.frames.login.CharacterList;
import main.network.ServerNetwork;

/**
 * @author fissban
 */
public class CharCreateFail extends ServerNetwork
{
	public enum ECharCreateFail
	{
		CREATION_FAILED("Your character creation has failed."),
		TOO_MANY_CHARACTERS("You cannot create another character. Please delete the existing character and try again."),
		NAME_ALREADY_EXISTS("This name already exists."),
		ENG_CHARS_16("Your title cannot exceed 16 characters in length. Please try again."),
		INCORRECT_NAME("Incorrect name. Please try again."),
		CREATE_NOT_ALLOWED("Characters cannot be created from this server."),
		CHOOSE_ANOTHER_SVR("Unable to create character. You are unable to create a new character on the selected server. A restriction is in place which restricts users from creating characters on different servers where no previous character exists.");

		String description;

		ECharCreateFail(String value)
		{
			description = value;
		}

		public String getDescription()
		{
			return description;
		}
	}

	private ECharCreateFail error;

	public CharCreateFail(byte[] data)
	{
		super(data);
		init();
	}

	@Override
	public void read()
	{
		error = ECharCreateFail.values()[readD()];
	}

	@Override
	public void write()
	{
		try
		{
			CharacterList.getInstance().init();
			// Show message error
			WebOptionPane.showMessageDialog(CharacterList.getInstance(), error.getDescription(), "Create character", WebOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}