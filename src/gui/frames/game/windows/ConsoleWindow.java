package gui.frames.game.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

import gui.look.fissban.FButton;
import main.util.Img;

/**
 * Here you try to emulate the console "cmd" and thus be able to get all the errors generated from the try and avoid using a bat to see them
 * @author fissban
 */
public class ConsoleWindow extends WebFrame
{
	private static final long serialVersionUID = 1L;

	private WebTextArea console;

	// Misc
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension frameSize = new Dimension(screenSize.width / 2, screenSize.height / 2);

	private boolean pause = false;
	private boolean trace = true;

	public ConsoleWindow()
	{
		super("Console");

		initialize();

		setType(Type.POPUP);
		setIconImage(Img.create("general/icons/robot.png").getImage());

		int x = frameSize.width - (frameSize.width / 3);
		int y = frameSize.height - (frameSize.height / 2);
		setBounds(x, y, 805, 360);
		setResizable(false);
		setBackground(new Color(248, 248, 255));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(248, 248, 255));
		getContentPane().setLayout(null);

		console = new WebTextArea();
		console.setBounds(0, 0, 624, 321);
		console.setEditable(false);
		console.setFont(new Font("Tahoma", Font.PLAIN, 12));
		console.setWrapStyleWord(true);
		console.setBackground(new Color(0, 0, 0));
		console.setForeground(new Color(245, 245, 245));
		((DefaultCaret) console.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		WebScrollPane scrollPane = new WebScrollPane(console);
		scrollPane.setShadeWidth(0);
		scrollPane.setRound(0);
		scrollPane.getWebVerticalScrollBar().setPreferredSize(new Dimension(12, 50));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 23, 789, 298);
		getContentPane().add(scrollPane);

		FButton btnPause = new FButton("Pause / Start");
		btnPause.setBounds(700, 0, 89, 23);
		btnPause.addActionListener(e ->
		{
			pause = !pause;
		});
		getContentPane().add(btnPause);

		FButton btnTrace = new FButton("Pause / Start");
		btnTrace.setText("Trace On/Off");
		btnTrace.setBounds(604, 0, 89, 23);
		btnTrace.addActionListener(e ->
		{
			trace = !trace;
		});
		getContentPane().add(btnTrace);

		WebLabel image = new WebLabel();
		image.setBounds(0, 0, 799, 331);
		image.setIcon(Img.create("general/2.png", getWidth(), getHeight()));
		getContentPane().add(image);
		//setVisible(true);
	}

	/**
	 * It will be used in <b>try & catch</b> to capture all messages generated from errors.<br>
	 * Any other message that you want to appear in the console using <b>System.out.println()</b>
	 * @param msg
	 * @param e
	 */
	public void print(String msg, Exception e)
	{
		if (e != null)
		{
			e.printStackTrace();
		}
		if (pause)
		{
			return;
		}

		SwingUtilities.invokeLater(() ->
		{
			if (e != null)
			{
				console.append(msg + " " + e + ":\n");
				if (!trace)
				{
					return;
				}
				console.append("trace:\n");
				for (StackTraceElement track : e.getStackTrace())
				{
					console.append(track.toString() + "\n");
				}
			}
			else
			{
				console.append(msg + ":\n");
			}
		});
	}

	public void open()
	{
		setVisible(!isVisible());
	}

	public static ConsoleWindow getInstance()
	{
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder
	{
		protected static final ConsoleWindow INSTANCE = new ConsoleWindow();
	}
}
