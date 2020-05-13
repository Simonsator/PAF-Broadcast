package de.simonsator.partyandfriends.broadcast;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.broadcast.commands.BroadcastCommand;
import de.simonsator.partyandfriends.broadcast.configuration.BCConfig;
import de.simonsator.partyandfriends.broadcast.extended.AddBroadcastGUISetting;
import de.simonsator.partyandfriends.broadcast.settings.ReceiveBroadcastSettings;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.friends.subcommands.Settings;
import de.simonsator.partyandfriends.main.Main;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 13.12.16
 */
public class BCMain extends PAFExtension {

	@Override
	public void onEnable() {
		BCConfig config = null;
		try {
			config = new BCConfig(new File(getConfigFolder(), "config.yml"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Friends.getInstance().addCommand(new BroadcastCommand(config.getStringList("Commands.Broadcast.Names"),
				config.getInt("Commands.Broadcast.Priority"), config.getString("Messages.Help"), config.getString("Commands.Broadcast.Permission"), config, this));
		Settings settingsCommand = (Settings) Friends.getInstance().getSubCommand(Settings.class);
		if (settingsCommand != null)
			if (config.getCreatedConfiguration().getBoolean("Settings.Broadcast.Enabled"))
				settingsCommand.registerSetting(new ReceiveBroadcastSettings(config.getStringList("Settings.Broadcast.Names"), config.getString("Settings.Broadcast.Permission"), config.getCreatedConfiguration().getInt("Settings.Broadcast.Priority"), config.getCreatedConfiguration()));
		try {
			Class.forName("de.simonsator.partyandfriends.api.events.communication.spigot.SendSettingsDataEvent");
			getProxy().getPluginManager().registerListener(this, new AddBroadcastGUISetting());
		} catch (ClassNotFoundException ignored) {
		}
		Main.getInstance().registerExtension(this);
	}
}
