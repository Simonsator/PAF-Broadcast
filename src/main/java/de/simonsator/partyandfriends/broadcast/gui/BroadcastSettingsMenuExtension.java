package de.simonsator.partyandfriends.broadcast.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.simonsator.datastructures.ItemPackage;
import de.simonsator.partyandfriendsgui.api.events.creation.menu.SettingsMenuCreationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * @author simonbrungs
 * @version 1.0.0 20.12.16
 */
public class BroadcastSettingsMenuExtension implements Listener {
	private final ItemStack TOP_ITEM;
	private final ItemStack RECEIVE_ITEM;
	private final ItemStack DO_NOT_RECEIVE_ITEM;

	public BroadcastSettingsMenuExtension(ItemStack TOP_ITEM, ItemStack pReceive, ItemStack pDoNotReceive) {
		this.TOP_ITEM = TOP_ITEM;
		this.RECEIVE_ITEM = pReceive;
		this.DO_NOT_RECEIVE_ITEM = pDoNotReceive;
	}

	@EventHandler
	public void extendSettingsMenu(SettingsMenuCreationEvent pEvent) {
		for (JsonElement element : pEvent.getData().get("settings").getAsJsonArray()) {
			JsonObject setting = element.getAsJsonObject();
			if (setting.get("id").getAsInt() == 30) {
				pEvent.addItemPackage(createSettingPackage(setting.get("worth").getAsInt()));
				break;
			}
		}
	}

	private ItemPackage createSettingPackage(int pWorth) {
		ItemStack itemToAdd;
		switch (pWorth) {
			case 0:
				itemToAdd = RECEIVE_ITEM;
				break;
			case 1:
				itemToAdd = DO_NOT_RECEIVE_ITEM;
				break;
			default:
				return null;
		}
		return new ItemPackage(TOP_ITEM, itemToAdd);

	}
}
