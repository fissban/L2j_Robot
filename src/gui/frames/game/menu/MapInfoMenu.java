package gui.frames.game.menu;

import java.awt.Component;
import java.awt.event.MouseEvent;

import com.alee.laf.label.WebLabel;

import gui.builder.MenuPopUp;
import main.util.Img;

/**
 * @author fissban
 */
public class MapInfoMenu extends MenuPopUp
{
	public MapInfoMenu(Component component)
	{
		super(component);

		addCloseButton();
		// create frame
		addCenterPanel(new TipAux("Bot", "IconGreen"));
		//addCenterPanel(new TipAux("Items", "IconOrange"));
		addCenterPanel(new TipAux("Players", "IconYellow"));
		addCenterPanel(new TipAux("Monsters", "IconRed"));
		addCenterPanel(new TipAux("Npcs", "IconGrey"));
		addCenterPanel(new TipAux("Items", "IconBlue"));
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		super.mouseClicked(e);
		// show frame
		show();
	}

	private static class TipAux extends WebLabel
	{
		private static final long serialVersionUID = 1L;

		public TipAux(String text, String icon)
		{
			super(text);
			setIcon(Img.create("general/" + icon + ".png", 8, 8));
		}
	}
}
