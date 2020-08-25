package gui.builder;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;

import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.SingleAlignPanel;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.label.WebLabel;

import gui.look.fissban.FButton;

/**
 * class that extends Web PopOver to save code in PopUp that work as menu
 * @author fissban
 */
public class MenuPopUp implements MouseListener
{
	private Object source;
	public WebPopOver popOver;

	Component comp;

	public MenuPopUp(Component comp)
	{
		super();
		this.comp = comp;
		init();
	}

	public void init()
	{
		popOver = new WebPopOver(comp);
		//popOver.setTransparency(0.9f);
		popOver.setModal(true);
		popOver.setMargin(5);
		popOver.setMovable(false);
	}

	/**
	 * Add center panel
	 * -> SingleAlignPanel(SwingConstants.CENTER)
	 * @param components
	 */
	public void addCenterPanel(Component... components)
	{
		for (Component c : components)
		{
			SingleAlignPanel p = new SingleAlignPanel(c, SwingConstants.CENTER);
			p.setMargin(0, 0, 0, 0);
			popOver.getContentPane().add(p);
		}
	}

	/**
	 * Add to pane close Buton -> "x" 
	 */
	public void addCloseButton()
	{
		popOver.getContentPane().setLayout(new VerticalFlowLayout());
		popOver.getContentPane().add(new SingleAlignPanel(new FButton("X", (ActionListener) e1 -> popOver.dispose()), SwingConstants.RIGHT).setMargin(0, 0, 0, 0));
	}

	public void show()
	{
		if (source instanceof WebLabel)
		{
			popOver.show((WebLabel) source);
		}
		else if (source instanceof FButton)
		{
			popOver.show((FButton) source);
		}
	}
	// LISTENER MOUSE ------------------------------------------------------------------------

	@Override
	public void mouseClicked(MouseEvent e)
	{
		source = e.getSource();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		//
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		//
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		//
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		popOver.dispose();
	}
}
