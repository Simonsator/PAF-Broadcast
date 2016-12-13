package de.simonsator.partyandfriends.broadcast;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

import java.util.List;


/**
 * @author simonbrungs
 * @version 1.0.0 13.12.16
 */
public class BroadcastCommand extends FriendSubCommand {
	private final Configuration MESSAGES;

	protected BroadcastCommand(String[] pCommands, int pPriority, String pHelp, Configuration pMessages) {
		super(pCommands, pPriority, pHelp);
		MESSAGES = pMessages;
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (args.length < 2) {
			sendError(pPlayer, new TextComponent(MESSAGES.getString("Messages.NoMessage")));
			return;
		}
		List<PAFPlayer> friends = pPlayer.getFriends();
		if (friends.isEmpty()) {
			sendError(pPlayer, new TextComponent(MESSAGES.getString("Messages.NoFriends")));
			return;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			builder.append(MESSAGES.getString("Messages.Splitter") + args[i]);
		}
		String message = MESSAGES.getString("Messages.Output").
				replace("[MESSAGE]", builder.toString()).replace("[PLAYER]", pPlayer.getDisplayName());
		for (PAFPlayer friend : friends)
			friend.sendMessage(message);
		pPlayer.sendMessage(MESSAGES.getString("Messages.Output").
				replace("[MESSAGE]", builder.toString()).replace("[PLAYER]", pPlayer.getDisplayName()));
	}
}
