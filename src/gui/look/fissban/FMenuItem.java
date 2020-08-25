package gui.look.fissban;

import java.awt.Color;

import com.alee.laf.menu.WebMenuItem;

/**
 * @author fissban
 */
public class FMenuItem extends WebMenuItem
{

	public FMenuItem(String text)
	{
		super(text);

		setSideSpacing(0);
		setSelectedBottomBg(new Color(102, 153, 255));
		setSelectedTopBg(new Color(153, 204, 255));
	}

}
