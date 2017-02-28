package de.simonsator.partyandfriends.broadcast;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.friends.commands.Friends;
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
			config = new BCConfig(new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Friends.getInstance().addCommand(new BroadcastCommand(config.getCreatedConfiguration().getStringList("Names").toArray(new String[0]),
				config.getCreatedConfiguration().getInt("Priority"), config.getCreatedConfiguration().getString("Messages.Help"), config.getCreatedConfiguration(), this));
		Main.getInstance().registerExtension(this);
	}

	@Override
	public void reload() {
		onEnable();
	}
}
