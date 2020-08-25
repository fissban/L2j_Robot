package main.util;

import main.connection.bot.objects.BObject;

/**
 * @author fissban
 */
public class Util
{
	public static void openLoginFrame(Class<?> frameName)
	{
		// open new frame
		try
		{
			Class.forName("gui.frames.login." + frameName.getSimpleName()).newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Thread sleep x milliseconds
	 * @param time
	 */
	public static void sleep(int time)
	{
		try
		{
			Thread.sleep(time);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static String formatAdena(int amount)
	{
		String s = "";
		int rem = amount % 1000;
		s = Integer.toString(rem);
		amount = (amount - rem) / 1000;
		while (amount > 0)
		{
			if (rem < 99)
			{
				s = '0' + s;
			}
			if (rem < 9)
			{
				s = '0' + s;
			}
			rem = amount % 1000;
			s = Integer.toString(rem) + "," + s;
			amount = (amount - rem) / 1000;
		}
		return s;
	}

	public static String getSkillIcon(int id)
	{
		String formato;
		if (id <= 9)
		{
			formato = "000" + id;
		}
		else if (id > 9 && id < 100)
		{
			formato = "00" + id;
		}
		else if (id > 99 && id < 1000)
		{
			formato = "0" + id;
		}
		else if (id == 1517)
		{
			formato = "1536";
		}
		else if (id == 1518)
		{
			formato = "1537";
		}
		else if (id == 1547)
		{
			formato = "0065";
		}
		else if (id == 2076)
		{
			formato = "0195";
		}
		else if (id > 4550 && id < 4555)
		{
			formato = "5739";
		}
		else if (id > 4698 && id < 4701)
		{
			formato = "1331";
		}
		else if (id > 4701 && id < 4704)
		{
			formato = "1332";
		}
		else if (id == 6049)
		{
			formato = "0094";
		}
		else
		{
			formato = String.valueOf(id);
		}
		return "skill" + formato;
	}

	public static double calculateDistance(int x1, int y1, int z1, int x2, int y2, int z2, boolean includeZAxis)
	{
		double dx = (double) x1 - x2;
		double dy = (double) y1 - y2;

		if (includeZAxis)
		{
			double dz = z1 - z2;
			return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
		}

		return Math.sqrt((dx * dx) + (dy * dy));
	}

	public static double calculateDistance(BObject obj1, BObject obj2, boolean includeZAxis)
	{
		if (obj1 == null || obj2 == null)
		{
			return 1000000;
		}

		return calculateDistance(obj1.getX(), obj1.getY(), obj1.getZ(), obj2.getX(), obj2.getY(), obj2.getZ(), includeZAxis);
	}

	public static double calculateDistance(int x, int y, BObject obj2, boolean includeZAxis)
	{
		if (obj2 == null)
		{
			return 1000000;
		}

		return calculateDistance(x, y, obj2.getZ(), obj2.getX(), obj2.getY(), obj2.getZ(), includeZAxis);
	}
}
