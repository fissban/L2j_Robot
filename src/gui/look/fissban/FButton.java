package gui.look.fissban;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import gui.Constant;

/**
 * @author fissban
 */
public class FButton extends JButton
{
	private Color color1 = new Color(102, 153, 204, 240);
	private Color color2 = new Color(248, 248, 255, 240);
	private Color colorContorno = Constant.COLOR_CONTORN;

	private ImageIcon icon;

	private int radius = 2;

	public FButton(String text)
	{
		super(text);

		setBackground(null);
		setBorder(null);
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(false);

		setFont(new Font("Tahoma", Font.BOLD, 11));

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				color1 = color1.brighter();
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				color1 = color1.darker();
				repaint();
			}
		});
	}

	public FButton(String text, ActionListener actionListener)
	{
		this(text);
		addActionListener(actionListener);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g.create();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Paint oldPaint = g2.getPaint();

		g2.clip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
		g2.setPaint(new GradientPaint(0.0f, 0.0f, getColor1().darker(), 0.0f, getHeight() / 2, getColor2()));
		g2.fillRect(0, 0, getWidth(), getHeight());

		// paint border 1
		g2.setStroke(new BasicStroke(1.3f));
		g2.setPaint(new GradientPaint(1.0f, 0.0f, colorContorno.darker(), 0.0f, getHeight() / 2, colorContorno.brighter()));
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

		g2.setPaint(oldPaint);

		// paint text
		g2 = (Graphics2D) g.create();
		g2.drawString(getText(), getXText(g2), getYText(g2));

		if (icon != null)
		{
			g2.drawImage(icon.getImage(), 12, 4, icon.getIconWidth(), icon.getIconHeight(), null);
		}
	}

	/**
	 * Center text Y
	 * @param g2d
	 * @return
	 */
	private int getXText(Graphics2D g2d)
	{
		FontMetrics fm = g2d.getFontMetrics();
		return (getWidth() - fm.stringWidth(getText())) / 2;
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

	/**
	 * @return the icon
	 */
	@Override
	public ImageIcon getIcon()
	{
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageIcon icon)
	{
		this.icon = icon;
		repaint();
	}
}
