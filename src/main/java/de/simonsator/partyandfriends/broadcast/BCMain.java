package de.simonsator.partyandfriends.broadcast;

import de.simonsator.partyandfriends.friends.commands.Friends;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 13.12.16
 */
public class BCMain extends Plugin {

	@Override
	public void onEnable() {
		BCConfig config = null;
		try {
			config = new BCConfig(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Friends.getInstance().addCommand(new BroadcastCommand(config.getCreatedConfiguration().getStringList("Names").toArray(new String[0]),
				config.getCreatedConfiguration().getInt("Priority"), config.getCreatedConfiguration().getString("Messages.Help"), config.getCreatedConfiguration()));
	}
}
