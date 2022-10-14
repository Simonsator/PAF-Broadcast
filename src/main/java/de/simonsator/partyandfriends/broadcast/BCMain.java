package de.simonsator.partyandfriends.broadcast;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.broadcast.commands.BroadcastCommand;
import de.simonsator.partyandfriends.broadcast.configuration.BCConfig;
import de.simonsator.partyandfriends.broadcast.extended.AddBroadcastGUISetting;
import de.simonsator.partyandfriends.broadcast.settings.ReceiveBroadcastSettings;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.friends.subcommands.Settings;

import java.io.File;
import java.io.IOException;

public class BCMain extends PAFExtension {

	@Override
	public void onEnable() {
		try {
			BCConfig config = new BCConfig(new File(getConfigFolder(), "config.yml"), this);
			Friends.getInstance().addCommand(new BroadcastCommand(config.getStringList("Commands.Broadcast.Names"),
					config.getInt("Commands.Broadcast.Priority"), config.getString("Messages.Help"),
					config.getString("Commands.Broadcast.Permission"), config, this));
			Settings settingsCommand = (Settings) Friends.getInstance().getSubCommand(Settings.class);
			if (settingsCommand != null) {
				if (config.getCreatedConfiguration().getBoolean("Settings.Broadcast.Enabled")) {
					settingsCommand.registerSetting(new ReceiveBroadcastSettings(config.getStringList("Settings.Broadcast.Names"),
							config.getString("Settings.Broadcast.Permission"),
							config.getCreatedConfiguration().getInt("Settings.Broadcast.Priority"), config.getCreatedConfiguration()));
				}
			}
			try {
				Class.forName("de.simonsator.partyandfriends.api.events.communication.spigot.SendSettingsDataEvent");
				getAdapter().registerListener(new AddBroadcastGUISetting(), this);
			} catch (ClassNotFoundException ignored) {
			}
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
