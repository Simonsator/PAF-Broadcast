package de.simonsator.partyandfriends.broadcast.commands;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.broadcast.BCMain;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author Simonsator
 * @version 1.0.0 13.12.16
 */
public class BroadcastCommand extends FriendSubCommand {
	private final ConfigurationCreator MESSAGES;
	private final int TIME;
	private final BCMain PLUGIN;
	private Set<UUID> uuidList = null;

	public BroadcastCommand(List<String> pCommands, int pPriority, String pHelp, String pPermission, ConfigurationCreator pConfig, BCMain pPlugin) {
		super(pCommands, pPriority, pHelp, pPermission);
		PLUGIN = pPlugin;
		MESSAGES = pConfig;
		if (pConfig.getBoolean("CoolDown.Activated"))
			uuidList = new HashSet<>();
		TIME = pConfig.getInt("CoolDown.Time");
	}

	@Override
	public void onCommand(final OnlinePAFPlayer pPlayer, String[] args) {
		if (args.length < 2) {
			sendError(pPlayer, new TextComponent(PREFIX + MESSAGES.getString("Messages.NoMessage")));
			return;
		}
		List<PAFPlayer> friends = pPlayer.getFriends();
		if (friends.isEmpty()) {
			sendError(pPlayer, new TextComponent(PREFIX + MESSAGES.getString("Messages.NoFriends")));
			return;
		}
		if (uuidList != null) {
			if (uuidList.contains(pPlayer.getUniqueId())) {
				sendError(pPlayer, new TextComponent(PREFIX + MESSAGES.getString("Messages.CoolDownActive")));
				return;
			}
			if (pPlayer.hasPermission(MESSAGES.getString("CoolDown.ByPassPermission")))
				return;
			uuidList.add(pPlayer.getUniqueId());
			ProxyServer.getInstance().getScheduler().schedule(PLUGIN, new Runnable() {
				public void run() {
					uuidList.remove(pPlayer.getUniqueId());
				}
			}, TIME, TimeUnit.SECONDS);
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			builder.append(MESSAGES.getString("Messages.Splitter")).append(args[i]);
		}
		String message = MESSAGES.getString("Messages.Output").
				replace("[MESSAGE]", builder.toString()).replace("[PLAYER]", pPlayer.getDisplayName());
		for (PAFPlayer friend : friends)
			if (friend.getSettingsWorth(30) == 0)
				friend.sendMessage(message);
		pPlayer.sendMessage(MESSAGES.getString("Messages.Output").
				replace("[MESSAGE]", builder.toString()).replace("[PLAYER]", pPlayer.getDisplayName()));
	}
}
