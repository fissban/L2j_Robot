package gui.builder;

import java.awt.Color;

import javax.swing.border.LineBorder;

import com.alee.laf.label.WebLabel;

/**
 * Class extending WebLabel to save code in the inventory interface
 * @author fissban
 */
public class InvLabel extends WebLabel
{
	private static final long serialVersionUID = 1L;

	public InvLabel(String text, int x, int y)
	{
		super(text);
		setBorder(new LineBorder(new Color(102, 153, 255)));
		setForeground(Color.DARK_GRAY);
		setOpaque(true);
		setBackground(Color.BLACK);
		setBounds(x, y, 32, 32);
	}
}
