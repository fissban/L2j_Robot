package gui.frames.game.menu;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import com.alee.laf.label.WebLabel;

import gui.builder.MenuPopUp;
import gui.look.fissban.FButton;
import main.connection.bot.objects.BItem;
import main.connection.bot.objects.BNpc;
import main.connection.bot.objects.BObject;
import main.managers.ObjectsManager;

/**
 * @author fissban
 */
public class MapMenu extends MenuPopUp
{
	private BObject object;
	private int button = 0;

	public MapMenu(Component component, BObject o, int mouseEvent)
	{
		super(component);
		object = o;
		button = mouseEvent;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// right click
		if (e.getButton() == button)
		{
			super.mouseClicked(e);
			// init frame
			popOver.getContentPane().removeAll();

			init();
			addCloseButton();

			addCenterPanel(new WebLabel("Actions"));

			addCenterPanel(new FButton("Go", (ActionListener) e1 ->
			{
				// TODO missing action
				popOver.dispose();
			}));

			if (object instanceof BItem)
			{
				addCenterPanel(new FButton("Loot", (ActionListener) e1 ->
				{
					ObjectsManager.getBot().getAi().interact(object);
					popOver.dispose();
				}));
			}
			else if (object instanceof BNpc)
			{
				if (((BNpc) object).isAttackable())
				{
					addCenterPanel(new FButton("Attack", (ActionListener) e1 ->
					{
						//TODO solo attack mele?
						ObjectsManager.getBot().getAi().attackMele(object);
						popOver.dispose();
					}));
				}
				else
				{
					addCenterPanel(new FButton("Talk", (ActionListener) e1 ->
					{
						ObjectsManager.getBot().getAi().talk(object);
						popOver.dispose();
					}));
				}
			}

			// show frame
			show();
		}
	}
}
