package main.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import gui.frames.game.windows.ConsoleWindow;
import main.connection.bot.objects.BPlayer;
import main.managers.ObjectsManager;

/**
 * @author fissban
 */
public class Img
{
	public Img()
	{
		//
	}

	public static ImageIcon create(String image, int width, int height)
	{
		return new ImageIcon(create(image).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
	}

	public static ImageIcon create(Image image, int width, int height)
	{
		return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
	}

	public static ImageIcon create(String image)
	{
		try
		{
			return new ImageIcon(Img.class.getResource("/gui/look/img/" + image));
		}
		catch (Exception e)
		{
			ConsoleWindow.getInstance().print("missing -> " + "/gui/look/img/" + image, null);
			return new ImageIcon(Img.class.getResource("/gui/look/img/NOIMAGE.png"));
		}
	}

	public static ImageIcon scaleAdd(ImageIcon img, int zoom)
	{
		int newImageWidth = img.getIconWidth() * zoom;
		int newImageHeight = img.getIconHeight() * zoom;
		return resize(img, newImageWidth, newImageHeight);
	}

	public static ImageIcon scaleSub(ImageIcon img, int zoom)
	{
		int newImageWidth = img.getIconWidth() / zoom;
		int newImageHeight = img.getIconHeight() / zoom;
		return resize(img, newImageWidth, newImageHeight);
	}

	private static ImageIcon resize(ImageIcon img, int newImageWidth, int newImageHeight)
	{
		BPlayer p = ObjectsManager.getBot();
		int pos[] = UtilMap.getPosInLiveMap(p.getX(), p.getY());

		BufferedImage resizedImage = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(img.getImage(), pos[0], pos[1], newImageWidth, newImageHeight, null);
		g.dispose();
		return new ImageIcon(resizedImage);
	}
}
