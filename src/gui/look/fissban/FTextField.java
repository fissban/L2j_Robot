package gui.look.fissban;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.Constant;

/**
 * @author fissban
 */
public class FTextField extends JTextField
{
	private Color colorContorno = Constant.COLOR_CONTORN;
	private int radius = 3;

	public FTextField()
	{
		this("", true);
	}

	public FTextField(String text, boolean alignCenter)
	{
		super(text);

		//setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setBorder(null);
		setOpaque(false);
		setColumns(10);
		setSelectedTextColor(Color.BLUE);
		setHorizontalAlignment(alignCenter ? SwingConstants.CENTER : SwingConstants.LEADING);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.clip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, Constant.COLOR_1.darker(), 0.0f, getHeight() / 2, new Color(255, 255, 255, 0)));
		g2.fillRect(0, 0, getWidth(), getHeight());

		// paint border 1
		g2.setStroke(new BasicStroke(1.5f));
		g2.setPaint(new GradientPaint(1.0f, 0.0f, colorContorno.darker(), 0.0f, getHeight() / 2, colorContorno.brighter()));
		g2.drawRoundRect(2, 1, getWidth() - 4, getHeight() - 4, radius, radius);
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
