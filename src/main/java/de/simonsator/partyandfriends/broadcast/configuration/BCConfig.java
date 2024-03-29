package de.simonsator.partyandfriends.broadcast.configuration;

import de.simonsator.partyandfriends.broadcast.BCMain;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class BCConfig extends ConfigurationCreator {
	public BCConfig(File file, BCMain plugin) throws IOException {
		super(file, plugin, true);
		readFile();
		loadDefaultValues();
		saveFile();
		process();
	}

	private void loadDefaultValues() {
		set("Commands.Broadcast.Names", "broadcast", "bc");
		set("Commands.Broadcast.Priority", 100);
		set("Commands.Broadcast.Permission", "");
		set("CoolDown.Activated", false);
		set("CoolDown.Time", 10);
		set("CoolDown.ByPassPermission", "partyandfriends.broadcast.bypasscooldown");
		set("Settings.Broadcast.Names", "broadcast", "receivebroadcast");
		set("Settings.Broadcast.Enabled", true);
		set("Settings.Broadcast.Permission", "");
		set("Settings.Broadcast.Priority", 3);
		set("Messages.Help", "&8/&5friend broadcast &8- &7Sends a message to all your friends");
		set("Messages.NoMessage", " &7You need to provide a message.");
		set("Messages.NoFriends", " &7Until now you do not have any friends.");
		set("Messages.CoolDownActive", " &7At the moment a cooldown is active. You can only Broadcast every 10 seconds.");
		set("Messages.Output", "&8[&5&lFriend-Broadcast&8] &e[PLAYER]&7:[MESSAGE]");
		set("Messages.Splitter", " &7");
		set("Messages.Settings.ReceivingBroadCasts",
				"&7At the moment you are going to receive broadcasts from your &afriends&7. To change this setting use &6/friend settings broadcast&7.");
		set("Messages.Settings.NotReceivingBroadCasts",
				"&7At the moment you are &cnot going to &7receive broadcasts of your friends. To change this setting use &6/friend settings broadcast&7.");
		set("Messages.Settings.NowReceivingBroadCasts", " &7Now you are &agoing to &7receive broadcasts from your friends.");
		set("Messages.Settings.NowNotReceivingBroadCasts", " &7Now you are &cnot going to &7receive broadcasts from your friends anymore.");
	}
}
