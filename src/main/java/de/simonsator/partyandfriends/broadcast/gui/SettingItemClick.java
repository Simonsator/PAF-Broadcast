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
	private final ItemStack DO_NOT_RECEIVE_ITEM;
	private final ItemStack RECEIVE_ITEM;

	public SettingItemClick(ItemStack pReceive, ItemStack pDoNotReceiveItem) {
		this.DO_NOT_RECEIVE_ITEM = pDoNotReceiveItem;
		this.RECEIVE_ITEM = pReceive;
	}

	@Override
	public void execute(InventoryClickEvent pEvent) {
		PartyFriendsAPI.changeSetting((Player) pEvent.getWhoClicked(), "receivebroadcast");
		if (Main.getInstance().getConfig()
				.getBoolean("SettingsInventory.CloseSettingsInventoryAutomatically"))
			pEvent.getWhoClicked().closeInventory();
		else {
			if (pEvent.getCurrentItem().equals(RECEIVE_ITEM))
				pEvent.getClickedInventory().setItem(pEvent.getSlot(), DO_NOT_RECEIVE_ITEM);
			else
				pEvent.getClickedInventory().setItem(pEvent.getSlot(), RECEIVE_ITEM);
		}
	}

	@Override
	public boolean isApplicable(InventoryClickEvent pEvent) {
		return pEvent.getCurrentItem().equals(DO_NOT_RECEIVE_ITEM) || pEvent.getCurrentItem().equals(RECEIVE_ITEM);
	}
}
