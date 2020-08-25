package gui.builder;

import javax.swing.WindowConstants;

import com.alee.laf.rootpane.WebFrame;

import gui.look.fissban.FPanelDegrade;
import main.util.Img;

/**
 * @author fissban
 */
public class WindowFrame extends WebFrame
{
	public FPanelDegrade panel;

	public WindowFrame(String title)
	{
		super(title);

		initialize();
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setIconImage(Img.create("general/icons/robot.png").getImage());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);

		getContentPane().setLayout(null);

		panel = new FPanelDegrade();
		add(panel);
	}

	@Override
	public void setBounds(int x, int y, int width, int height)
	{
		panel.setBounds(0, 0, width - 16, height - 39);
		super.setBounds(0, 0, width, height);
	}
}
