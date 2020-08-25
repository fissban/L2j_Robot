package gui.frames.game.windows;

import java.awt.Color;

import javax.swing.WindowConstants;

import com.alee.extended.window.ComponentMoveAdapter;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import main.util.Img;

/**
 * @author fissban
 */
public class SnifferPacketsWindows extends WebFrame
{
	private static final long serialVersionUID = 1L;
	private WebTable _table;

	public SnifferPacketsWindows()
	{
		super("Author");

		initialize();

		setType(Type.POPUP);
		setIconImage(Img.create("general/icons/robot.png").getImage());

		setBounds(0, 0, 836, 409);
		setResizable(false);
		setBackground(new Color(248, 248, 255));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(null);

		_table = new WebTable();

		WebScrollPane scrollPane = new WebScrollPane(_table);
		scrollPane.setBounds(0, 0, 820, 311);
		getContentPane().add(scrollPane);

		ComponentMoveAdapter.install(getRootPane(), this);

	}

	public void add(String packet, String code)
	{

	}

	public void open()
	{
		setVisible(!isVisible());
	}

	public static SnifferPacketsWindows getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final SnifferPacketsWindows INSTANCE = new SnifferPacketsWindows();
	}
}
