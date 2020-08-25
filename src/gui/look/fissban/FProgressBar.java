package gui.look.fissban;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.SystemColor;

import com.alee.laf.progressbar.WebProgressBar;

import gui.Constant;

/**
 * @author fissban
 */
public class FProgressBar extends WebProgressBar
{
	//colors
	private Color colorContorno = Constant.COLOR_CONTORN;
	private int radius = 2;

	public FProgressBar()
	{
		super();

		setFont(new Font("Arial", Font.PLAIN, 10));
		setBgTop(Color.LIGHT_GRAY);
		setBgBottom(new Color(230, 230, 250));
		setOpaque(false);
		setShadeWidth(1);
		setRound(1);
		setInnerRound(1);
		setValue(50);
		setBackground(new Color(0, 51, 102));
		setForeground(new Color(0, 0, 0));
		setBorder(null);
		setStringPainted(true);
		setBorderPainted(true);
		setProgressTopColor(SystemColor.textHighlight);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

		// paint border 1
		g2.setPaint(new GradientPaint(0.0f, 0.0f, colorContorno, 0.0f, getWidth(), colorContorno));
		g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);
		// paint border 2
		g2.setPaint(new GradientPaint(0.0f, 0.0f, colorContorno.brighter(), 0.0f, getWidth(), colorContorno.brighter()));
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

		g2.setPaint(oldPaint);

		// paint text
		g2 = (Graphics2D) g.create();
		g2.drawString(getString(), getXText(g2), getYText(g2));

	}

	/**
	 * Center text Y
	 * @param g2d
	 * @return
	 */
	private int getXText(Graphics2D g2d)
	{
		FontMetrics fm = g2d.getFontMetrics();
		return (getWidth() - fm.stringWidth(getString())) / 2;
	}

	/**
	 * Center text Y
	 * @param g2d
	 * @return
	 */
	private int getYText(Graphics2D g2d)
	{
		FontMetrics fm = g2d.getFontMetrics();
		return (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
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
