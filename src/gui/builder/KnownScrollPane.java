package gui.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

import com.alee.laf.scroll.WebScrollPane;

/**
 * Class extending WebScrollPane to save code in the interface
 *
 * @author fissban
 */
public class KnownScrollPane extends WebScrollPane
{
	private static final long serialVersionUID = 1L;

	public KnownScrollPane(JList<String> list)
	{
		super(list);

		setBackground(new Color(0, 51, 102));
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		getWebVerticalScrollBar().setAutoscrolls(true);
		getWebVerticalScrollBar().setPreferredSize(new Dimension(12, 50));
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setShadeWidth(0);
		setRound(0);
	}
}
