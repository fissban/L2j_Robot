package gui.look.fissban;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.Future;

import javax.swing.JPanel;

import gui.Constant;
import main.managers.ThreadManager;

/**
 * @author fissban
 */
public class FPanelDegrade extends JPanel
{
	//colors
	private Color color1 = Constant.COLOR_1;
	private Color color2 = Constant.COLOR_2;
	private Color colorContorno = Constant.COLOR_CONTORN;
	// radio
	private int radius = 8;
	// vars
	private boolean paintBackground = true;
	private boolean paintBorder = true;

	boolean check = false;

	public FPanelDegrade()
	{
		super();

		setLayout(null);
		setOpaque(false);
	}

	public FPanelDegrade(boolean paintBackground, boolean paintBorder)
	{
		super();

		setLayout(null);
		setOpaque(false);

		this.paintBackground = paintBackground;
		this.paintBorder = paintBorder;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		//super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

		if (paintBackground)
		{
			g2.setStroke(new BasicStroke(1.2f));
			g2.clip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
			g2.setPaint(new GradientPaint(0.0f, 0.0f, getColor1().darker(), 0.0f, getHeight() / 6 * 8, getColor2()));
			g2.fillRect(0, 0, getWidth(), getHeight());
			
		}

		if (paintBorder)
		{
			g2.setStroke(new BasicStroke(1.2f));
			// paint border 1
			g2.setPaint(new GradientPaint(0.0f, 0.0f, colorContorno.darker(), 0.0f, getHeight() / 2, colorContorno.brighter()));
			g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);
			// paint border 2
			g2.setPaint(new GradientPaint(0.0f, 0.0f, colorContorno.darker(), 0.0f, getHeight() / 2, colorContorno.brighter()));
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
		}

		g2.setPaint(oldPaint);
	}

	public Future<?> startAnimation()
	{
		return ThreadManager.scheduleAtFixedRate(() ->
		{
			if (check)
			{
				colorContorno = colorContorno.darker();
				check = false;
			}
			else
			{
				colorContorno = colorContorno.brighter();
				check = true;
			}
			repaint();
		}, 1000, 200);
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

	public void setRadius(int value)
	{
		radius = value;
	}

	public int getRadius()
	{
		return radius;
	}
}
