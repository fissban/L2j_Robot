package gui.look.fissban;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import main.managers.ThreadManager;
import main.util.Img;

/**
 * @author fissban
 */
public class FStart extends JComponent implements Runnable
{
	/** La foto */
	private ImageIcon icono = null;
	/** Cuanto queremos que se rote la foto, en radianes. */
	private double rotacion = 0.0;

	public FStart()
	{
		icono = Img.create("general/start.png", 16, 16);

		ThreadManager.schedule(this, 100);
	}

	/**
	 * Devuelve como tamaño preferido el de la foto.
	 */
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(icono.getIconWidth(), icono.getIconHeight());
	}

	/**
	 * Dibujo de la foto rotandola.
	 */
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		// AffineTransform realiza el giro, usando como eje de giro el centro
		// de la foto (width/2, height/2) y el angulo que indica el atributo
		// rotacion.
		AffineTransform tx = AffineTransform.getRotateInstance(rotacion, icono.getIconWidth() / 2, icono.getIconHeight() / 2);
		// dibujado con la AffineTransform de rotacion
		g2d.drawImage(icono.getImage(), tx, this);
	}

	@Override
	public void run()
	{
		if (rotacion >= 1.0)
		{
			rotacion = rotacion - 0.1;
		}
		else
		{
			rotacion = rotacion + 0.1;
		}
		repaint();
	}

	/**
	 * Devuelve la rotacion actual.
	 * @return rotacion en radianes
	 */
	public double getRotacion()
	{
		return rotacion;
	}

	/**
	 * Se le pasa la rotación deseada.
	 * @param rotacion La rotacion en radianes.
	 */
	public void setRotacion(double rotacion)
	{
		this.rotacion = rotacion;
	}
}
