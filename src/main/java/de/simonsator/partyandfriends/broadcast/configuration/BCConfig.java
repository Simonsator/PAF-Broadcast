package de.simonsator.partyandfriends.broadcast.configuration;

import de.simonsator.partyandfriends.utilities.ConfigLoader;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 13.12.16
 */
public class BCConfig extends ConfigurationCreator {
	public BCConfig(File file) throws IOException {
		super(file);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Names", "broadcast", "bc");
		set("Priority", 100);
		set("CoolDown.Activated", false);
		set("CoolDown.Time", 10);
		set("CoolDown.ByPassPermission", "partyandfriends.broadcast.bypasscooldown");
		set("Settings.Broadcast.Names", "broadcast", "receivebroadcast");
		set("Settings.Broadcast.Enabled", true);
		set("Settings.Broadcast.Permission", "");
		set("Settings.Broadcast.Priority", 3);
		set("Messages.Help", "&8/&5friend broadcast &8- &7Sends a message to all your friends");
		set("Messages.NoMessage", " &7you need to provide a message.");
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

	@Override
	public void reloadConfiguration() throws IOException {
		configuration = (new ConfigLoader(FILE)).getCreatedConfiguration();
	}
}
