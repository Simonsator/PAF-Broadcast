package de.simonsator.partyandfriends.broadcast.extended;

import de.simonsator.partyandfriends.api.events.communication.spigot.SendSettingsDataEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class AddBroadcastGUISetting implements Listener {
	@EventHandler
	public void addBroadcastSetting(SendSettingsDataEvent pEvent) {
		pEvent.addSetting(30, pEvent.getPlayer().getSettingsWorth(30));
	}

}
