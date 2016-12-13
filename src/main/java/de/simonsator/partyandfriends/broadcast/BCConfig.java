package de.simonsator.partyandfriends.broadcast;

import de.simonsator.partyandfriends.utilities.ConfigLoader;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

/**
 * @author simonbrungs
 * @version 1.0.0 13.12.16
 */
public class BCConfig extends ConfigurationCreator {
	protected BCConfig(File file) throws IOException {
		super(file);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("Names", "broadcast", "bc");
		set("Priority", 100);
		set("Messages.Help", "&8/&5friend broadcast &8- &7Sends a message to all your friends");
		set("Messages.NoMessage", " &7you need to provide a message.");
		set("Messages.NoFriends", " &7Until now you do not have any friends.");
		set("Messages.Output", "&8[&5&lFriend-Broadcast&8] &e[PLAYER]&7:[MESSAGE]");
		set("Messages.Splitter", " &7");
	}

	@Override
	public void reloadConfiguration() throws IOException {
		configuration = (new ConfigLoader(FILE)).getCreatedConfiguration();
	}
}
