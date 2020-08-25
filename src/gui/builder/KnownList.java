package gui.builder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ListSelectionModel;

import com.alee.laf.list.WebList;
import com.alee.laf.list.WebListModel;

/**
 * @author fissban
 */
public class KnownList extends WebList
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public KnownList()
	{
		super();

		setSelectionShadeWidth(1);
		setSelectionRound(1);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setValueIsAdjusting(true);
		setSelectionBackground(new Color(248, 248, 255));
		setVisibleRowCount(20);
		setFont(new Font("Tahoma", Font.PLAIN, 10));
		setBackground(new Color(248, 248, 255));
		setModel(new WebListModel<String>());
	}
}
