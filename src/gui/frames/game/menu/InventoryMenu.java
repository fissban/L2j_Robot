package gui.frames.game.menu;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import com.alee.laf.label.WebLabel;

import gui.builder.MenuPopUp;
import gui.look.fissban.FButton;
import main.connection.bot.objects.BItem;
import main.managers.ObjectsManager;
import main.network.game.client.ingame.RequestDestroyItem;
import main.network.game.client.ingame.RequestDropItem;
import main.network.game.client.ingame.UseItem;

/**
 * @author fissban
 */
public class InventoryMenu extends MenuPopUp
{
	private BItem item;

	public InventoryMenu(Component component, BItem it)
	{
		super(component);
		item = it;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// click derecho
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			super.mouseClicked(e);

			// init frame
			popOver.getContentPane().removeAll();

			init();
			addCloseButton();

			popOver.getContentPane().add(new WebLabel("Item Options"));

			// Equip Item
			addCenterPanel(new FButton("Equip", (ActionListener) e1 ->
			{
				ObjectsManager.getBot().sendPacket(new UseItem(item));
				popOver.dispose();
			}));

			// Destroy item
			addCenterPanel(new FButton("Destroy", (ActionListener) e1 ->
			{
				ObjectsManager.getBot().sendPacket(new RequestDestroyItem(item));
				popOver.dispose();
			}));

			// Drop item
			addCenterPanel(new FButton("Drop", (ActionListener) e1 ->
			{
				ObjectsManager.getBot().sendPacket(new RequestDropItem(item));
				popOver.dispose();
			}));

			// show frame
			show();
		}
	}
}
