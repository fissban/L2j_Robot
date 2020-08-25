package gui.look.fissban;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import com.alee.laf.scroll.WebScrollPane;

import gui.Constant;

/**
 * @author fissban
 */
public class FScrollPane extends WebScrollPane
{
	//colors
	private Color color1 = Constant.COLOR_1;
	private Color color2 = Constant.COLOR_2;
	private Color colorContorno = Constant.COLOR_CONTORN;
	// radio
	private int radius = 4;
	// vars
	private boolean paintBackground = true;
	private boolean paintBorder = true;

	public FScrollPane(Component view)
	{
		super(view);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

		if (paintBackground)
		{
			g2.clip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
			g2.setPaint(new GradientPaint(0.0f, 0.0f, getColor1().darker(), 0.0f, getHeight() / 2, getColor2()));
			g2.fillRect(0, 0, getWidth(), getHeight());
			g2.setStroke(new BasicStroke(0.9f));
		}

		if (paintBorder)
		{
			// paint border 1
			g2.setPaint(new GradientPaint(0.0f, 0.0f, colorContorno, 0.0f, getHeight(), colorContorno));
			g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);
			// paint border 2
			g2.setPaint(new GradientPaint(0.0f, 0.0f, colorContorno.brighter(), 0.0f, getHeight(), colorContorno.brighter()));
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
		}

		g2.setPaint(oldPaint);

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
