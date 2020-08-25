package gui.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;

/**
 * https://stackoverflow.com/questions/20361432/translucent-jlabel-not-properly-showing-background
 */
public class CircleLabel extends JLabel
{
	private String label = "";

	public CircleLabel()
	{
		setOpaque(false);
	}

	/**
	 * Constructs a CircleLabel with the specified label.
	 * @param label the label of the button
	 */
	public CircleLabel(String label)
	{
		this.label = label;

		setOpaque(false);
	}

	/**
	 * gets the label
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * sets the label
	 */
	public void setLabel(String label)
	{
		this.label = label;
		invalidate();
		repaint();
	}

	/**
	 * Set position and radio in livemap
	 * @param x
	 * @param y
	 * @param radio
	 */
	public void setPos(int x, int y, int radio)
	{
		super.setBounds((x - (radio - 10) / 2), (y - (radio - 10) / 2), radio, radio);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		// YOU MUST CALL super.paintXxx THERE IS NO EXCUSE NOT TO, EVER!!
		super.paintComponent(g);

		int s = Math.min(getSize().width - 2, getSize().height - 2);

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		Color highlight = new Color(0, 255, 0, 100);
		Color darklight = new Color(0, 255, 0, 100);

		LinearGradientPaint lgp = new LinearGradientPaint(new Point(0, 0), new Point(0, s), new float[]
		{
				0, 1f
		}, new Color[]
		{
				highlight, darklight
		});

		Ellipse2D shape = new Ellipse2D.Float(1, 1, s, s);
		g2d.setPaint(lgp);
		g2d.fill(shape);

		// draw the perimeter of the button
		g2d.setColor(getBackground());

		g2d.draw(shape);
		// draw the label centered in the button
		Font f = getFont();
		if (f != null)
		{
			FontMetrics fm = getFontMetrics(getFont());
			g2d.setColor(getForeground());
			g2d.drawString(label, s / 2 - fm.stringWidth(label) / 2, s / 2 + fm.getMaxDescent());
		}
		g2d.dispose();
	}

	/**
	 * The preferred size of the button.
	 */
	@Override
	public Dimension getPreferredSize()
	{
		Font f = getFont();
		if (f != null)
		{
			FontMetrics fm = getFontMetrics(getFont());
			int max = Math.max(fm.stringWidth(label) + 40, fm.getHeight() + 40);
			return new Dimension(max, max);
		}
		else
		{
			return new Dimension(100, 100);
		}
	}

	/**
	 * The minimum size of the button.
	 */
	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(100, 100);
	}

	/**
	 * Determine if click was inside round button.
	 */
	@Override
	public boolean contains(int x, int y)
	{
		// This needs to work more on the actual painted shape...
		int mx = getSize().width / 2;
		int my = getSize().height / 2;
		return (((mx - x) * (mx - x) + (my - y) * (my - y)) <= mx * mx);
	}
}