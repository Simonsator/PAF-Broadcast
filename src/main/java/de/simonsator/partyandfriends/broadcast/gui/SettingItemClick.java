package de.simonsator.partyandfriends.broadcast.gui;

import de.simonsator.partyandfriendsgui.Main;
import de.simonsator.partyandfriendsgui.api.PartyFriendsAPI;
import de.simonsator.partyandfriendsgui.inventory.tasks.executeclicktask.InventoryTask;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author simonbrungs
 * @version 1.0.0 20.12.16
 */
public class SettingItemClick extends InventoryTask {
	private final ItemStack TOP_ITEM;
	private final ItemStack RECEIVE_ITEM;

	public SettingItemClick(ItemStack TOP_ITEM, ItemStack pReceive) {
		this.TOP_ITEM = TOP_ITEM;
		this.RECEIVE_ITEM = pReceive;
	}

	@Override
	public void execute(InventoryClickEvent pEvent) {
		PartyFriendsAPI.changeSetting((Player) pEvent.getWhoClicked(), "receivebroadcast");
		if (Main.getInstance().getConfig()
				.getBoolean("SettingsInventory.CloseSettingsInventoryAutomatically"))
			pEvent.getWhoClicked().closeInventory();
		else
			PartyFriendsAPI.openSettingsInventory((Player) pEvent.getWhoClicked());
	}

	@Override
	public boolean isApplicable(InventoryClickEvent pEvent) {
		return pEvent.getCurrentItem().equals(TOP_ITEM) || pEvent.getCurrentItem().equals(RECEIVE_ITEM);
	}
}
