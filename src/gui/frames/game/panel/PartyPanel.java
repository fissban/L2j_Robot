package gui.frames.game.panel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.alee.laf.label.WebLabel;

import gui.look.fissban.FPanelDegrade;
import gui.look.fissban.FProgressBar;
import main.connection.bot.objects.BPlayer;
import main.managers.ObjectsManager;

/**
 * @author fissban
 */
public class PartyPanel extends FPanelDegrade
{
	private static final int X = 2;

	// member 1
	FProgressBar member1Hp = new FProgressBar();
	FProgressBar member1Mp = new FProgressBar();
	// member 2
	FProgressBar member2Hp = new FProgressBar();
	FProgressBar member2Mp = new FProgressBar();
	// member 3
	FProgressBar member3Hp = new FProgressBar();
	FProgressBar member3Mp = new FProgressBar();
	// member 4
	FProgressBar member4Hp = new FProgressBar();
	FProgressBar member4Mp = new FProgressBar();
	// member 5
	FProgressBar member5Hp = new FProgressBar();
	FProgressBar member5Mp = new FProgressBar();
	// member 6
	FProgressBar member6Hp = new FProgressBar();
	FProgressBar member6Mp = new FProgressBar();
	// member 7
	FProgressBar member7Hp = new FProgressBar();
	FProgressBar member7Mp = new FProgressBar();

	List<Integer> allMembers = new ArrayList<>(7);

	public PartyPanel()
	{
		super();

		setBounds(0, 202, 267, 278);

		addTitle();
	}

	public void initWindows(List<BPlayer> members)
	{
		SwingUtilities.invokeLater(() ->
		{
			removeAll();
			allMembers.clear();

			addTitle();

			int y = 0;

			for (BPlayer m : members)
			{
				allMembers.add(m.getObjectId());

				y += 27;

				JLabel name = new JLabel(m.getName());
				name.setForeground(new Color(204, 102, 0));
				name.setBounds(X + 4, y - 12, getWidth() - 4, 14);

				FProgressBar[] data = getHpMp(allMembers.size());

				FProgressBar hp = data[0];
				FProgressBar mp = data[1];

				hp.setFont(new Font("Tahoma", Font.PLAIN, 9));
				hp.setProgressBottomColor(new Color(255, 99, 71));
				hp.setProgressTopColor(new Color(220, 20, 60));
				hp.setHighlightDarkWhite(new Color(255, 99, 71));
				hp.setHighlightWhite(new Color(220, 20, 60));
				hp.setBounds(X, y, getWidth() - 4, 10);
				hp.setMaximum(m.getMaxHp());
				hp.setValue(m.getCurrentHp());
				hp.setString(m.getGuiHp());

				y += 9;

				mp.setFont(new Font("Tahoma", Font.PLAIN, 9));
				mp.setProgressTopColor(new Color(30, 144, 255));
				mp.setProgressBottomColor(new Color(0, 191, 255));
				mp.setHighlightWhite(new Color(30, 144, 255));
				mp.setHighlightDarkWhite(new Color(0, 191, 255));
				mp.setBounds(X, y, getWidth() - 4, 10);
				mp.setMaximum(m.getMaxMp());
				mp.setValue(m.getCurrentMp());
				mp.setString(m.getGuiMp());

				add(mp);
				add(hp);
				add(name);
			}
		});
	}

	public void updateHpMp(BPlayer member)
	{
		SwingUtilities.invokeLater(() ->
		{
			FProgressBar[] data = getHpMp(allMembers.indexOf(member.getObjectId()));

			FProgressBar hp = data[0];
			FProgressBar mp = data[1];

			hp.setMaximum(member.getMaxHp());
			hp.setValue(member.getCurrentHp());
			hp.setString(member.getGuiHp());

			mp.setMaximum(member.getMaxMp());
			mp.setValue(member.getCurrentMp());
			mp.setString(member.getGuiMp());
		});
	}

	public void removeMember(Integer objectId)
	{
		allMembers.remove(objectId);

		List<BPlayer> list = new ArrayList<>();

		allMembers.forEach(objId ->
		{
			list.add((BPlayer) ObjectsManager.get(objId));
		});

		initWindows(list);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private FProgressBar[] getHpMp(int id)
	{
		//@formatter:off
		switch (id)
		{
			case 1:
				return new FProgressBar[]{member1Hp, member1Mp};
			case 2:
				return new FProgressBar[]{member2Hp, member2Mp};
			case 3:
				return new FProgressBar[]{member3Hp, member3Mp};
			case 4:
				return new FProgressBar[]{member4Hp, member4Mp};
			case 5:
				return new FProgressBar[]{member5Hp, member5Mp};
			case 6:
				return new FProgressBar[]{member6Hp, member6Mp};
			case 7:
				return new FProgressBar[]{member7Hp, member7Mp};
		}
		//@formatter:on

		return null;
	}

	private void addTitle()
	{
		WebLabel top = new WebLabel("Party");
		top.setFont(new Font("Tahoma", Font.BOLD, 9));
		top.setDrawShade(true);
		top.setShadeColor(new Color(0, 102, 153));
		top.setForeground(new Color(255, 255, 255));
		top.setHorizontalTextPosition(SwingConstants.CENTER);
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.setBorder(null);
		top.setBoldFont(true);
		top.setBackground(new Color(102, 153, 255));
		top.setBounds(1, 1, 264, 14);
		add(top);
	}
}
