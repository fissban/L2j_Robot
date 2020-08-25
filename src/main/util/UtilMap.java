package main.util;

import javax.swing.ImageIcon;

import main.managers.ObjectsManager;

/**
 * @author fissban
 */
public class UtilMap
{
	//private static final int WORLD_Y_MAX = 262144;
	//private static final int WORLD_X_MAX = 229376;
	/** minimum position of x on the map */
	public static final int WORLD_X_MIN = -131072;
	/** minimum position of y on the map */
	public static final int WORLD_Y_MIN = -262144;
	/** size of each quadrant of the map */
	public static final int TILE_SIZE = 32768;

	/**
	 * You get the position in the client indicated in the GUI map
	 * @param destX
	 * @param destY
	 * @return
	 */
	public static final int[] getPosInClient(int destX, int destY)
	{
		// Get the position of the bot
		int playerX = ObjectsManager.getBot().getX();
		int playerY = ObjectsManager.getBot().getY();

		int addX = WORLD_X_MIN;
		int addY = WORLD_Y_MIN;
		// It looks for how much to add "x" to get to the current map
		while (addX + TILE_SIZE < playerX)
		{
			addX += TILE_SIZE;
		}
		// It looks for how much to add "y" to get to the current map
		while (addY + TILE_SIZE < playerY)
		{
			addY += TILE_SIZE;
		}
		// 3 simple rule calculating from the map and the indicated destination
		int x = addX + (Math.round(TILE_SIZE / 504) * destX);
		int y = addY + (Math.round(TILE_SIZE / 462) * destY);

		int[] pos =
		{
				x, y
		};

		return pos;
	}

	/**
	 * The position within the GUI map is obtained according to the position of the game
	 * @param playerX
	 * @param playerY
	 * @return
	 */
	public static final int[] getPosInLiveMap(int playerX, int playerY)
	{
		playerX -= WORLD_X_MIN;
		playerY -= WORLD_Y_MIN;

		while (playerX > TILE_SIZE)
		{
			playerX -= TILE_SIZE;
		}

		while (playerY > TILE_SIZE)
		{
			playerY -= TILE_SIZE;
		}

		int x = Math.round(playerX / Math.round((TILE_SIZE / 504)));
		int y = Math.round(playerY / Math.round((TILE_SIZE / 462)));

		int[] pos =
		{
				x, y
		};

		return pos;
	}

	/**
	 * Get the map where the bot is
	 * @param posX
	 * @param posY
	 * @return
	 */
	public static final ImageIcon getMapRegion(int posX, int posY)
	{
		int x = getMapRegionX(posX);
		int y = getMapRegionY(posY);

		String mapX = x >= 10 ? (x + "") : "0" + x;
		String mapY = y >= 10 ? (y + "") : "0" + y;

		return Img.create("maps/" + mapX + "_" + mapY + ".jpg", 504, 462);
	}

	private static final int getMapRegionX(int posX)
	{
		return (posX >> 15) + 4 + 16;
	}

	private static final int getMapRegionY(int posY)
	{
		return (posY >> 15) + 8 + 10;
	}
}
