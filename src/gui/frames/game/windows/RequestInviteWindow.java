package gui.frames.game.windows;

import java.awt.Color;
import java.util.concurrent.ScheduledFuture;

import javax.swing.SwingConstants;

import com.alee.laf.label.WebLabel;
import com.alee.laf.progressbar.WebProgressBar;

import gui.builder.WindowFrame;
import gui.frames.game.GameFrame;
import gui.look.fissban.FButton;
import main.managers.ObjectsManager;
import main.managers.ThreadManager;
import main.network.game.client.ingame.RequestAnswerJoinParty;

/**
 * @author fissban
 */
public class RequestInviteWindow extends WindowFrame
{
	private ScheduledFuture<?> taskRequest = null;

	private WebProgressBar progressBar = new WebProgressBar();

	private FButton accept = new FButton("Accept");
	private FButton deny = new FButton("Deny");

	private WebLabel text = new WebLabel("Text");

	public RequestInviteWindow(String title)
	{
		super(title);
		setUndecorated(true);
		setBounds(0, 0, 300, 100);

		accept.setBounds(31, 66, 89, 23);
		accept.addActionListener(e ->
		{
			ObjectsManager.getBot().sendPacket(new RequestAnswerJoinParty(1));
			GameFrame.getInstance().getActions().addActionBot("Accept party");
			close();
		});
		panel.add(accept);

		deny.setBounds(166, 66, 89, 23);
		deny.addActionListener(e ->
		{
			ObjectsManager.getBot().sendPacket(new RequestAnswerJoinParty(0));
			GameFrame.getInstance().getActions().addActionBot("Deny party");
			close();
		});
		panel.add(deny);
		progressBar.setShadeWidth(0);
		progressBar.setRound(5);

		progressBar.setBounds(10, 50, 280, 8);
		progressBar.setValue(100);
		panel.add(progressBar);

		text.setForeground(new Color(51, 153, 255));
		text.setHorizontalTextPosition(SwingConstants.CENTER);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setBounds(10, 11, 280, 33);
		panel.add(text);

	}

	public void start()
	{
		taskRequest = ThreadManager.scheduleAtFixedRate(() ->
		{
			if (progressBar.getValue() == 0)
			{
				close();
			}
			else
			{
				progressBar.setValue(progressBar.getValue() - 10);
			}
		}, 1500, 100);
	}

	private void close()
	{
		if (taskRequest != null)
		{
			taskRequest.cancel(false);
			taskRequest = null;
			dispose();
		}
	}

	public void setText(String text)
	{
		this.text.setText(text);
	}
}
