package gui.look.fissban;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import gui.Constant;
import main.util.Img;

/**
 * @author fissban
 */
public class FPanelBackground extends JPanel
{
	private Color color1 = Constant.COLOR_1;
	private Color color2 = Constant.COLOR_2;
	private Color colorContorno = Constant.COLOR_CONTORN;
	private int arcw = 4;
	private int arch = 4;

	public FPanelBackground()
	{
		super();

		setLayout(null);
		//setBorder(new LineBorder(new Color(100, 149, 237), 2, true));
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//Graphics2D g2 = (Graphics2D) g.create();

		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// paint object
		//Paint oldPaint = g2.getPaint();
		//g2.clip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight() - 1, getArcw(), getArch()));
		//g2.setPaint(new GradientPaint(0.0f, 0.0f, getColor1().darker(), 0.0f, getHeight(), getColor2()));
		//g2.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

		//g2.setStroke(new BasicStroke(0.8f));
		// paint border 1
		//g2.setPaint(new GradientPaint(0.0f, 0.0f, getColorContorno(), 0.0f, getHeight(), getColorContorno()));
		///g2.drawRoundRect(1, 1, getWidth() - 4, getHeight() - 4, 9, 9);
		// paint border 2
		//g2.setPaint(new GradientPaint(0.0f, 0.0f, getColorContorno().brighter(), 0.0f, getHeight(), getColorContorno().darker()));
		//g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 9, 9);

		// shadow???
		// paint border 2
		//g2.setStroke(new BasicStroke(2f));
		//g2.setPaint(new GradientPaint(0.0f, 0.0f, getColorContorno().darker().darker(), 0.0f, getHeight(), getColorContorno().brighter()));
		//g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 9, 9);

		//g2.setPaint(oldPaint);

	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(Img.create("general/panel617x418.png", getWidth(), getHeight()).getImage(), 0, 0, getWidth(), getHeight(), this);

		setOpaque(false);
		super.paint(g);
	}

	public Color getColor1()
	{
		return color1;
	}

	public void setColor1(Color value)
	{
		color1 = value;
	}

	public Color getColor2()
	{
		return color2;
	}

	public void setColor2(Color value)
	{
		color2 = value;
	}

	public Color getColorContorno()
	{
		return colorContorno;
	}

	public void setColorContorno(Color value)
	{
		colorContorno = value;
	}

	public int getArcw()
	{
		return arcw;
	}

	public void setArcw(int value)
	{
		arcw = value;
	}

	public int getArch()
	{
		return arch;
	}

	public void setArch(int value)
	{
		arch = value;
	}
}
