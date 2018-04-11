package de.simonsator.partyandfriends.broadcast.gui;

import de.simonsator.partyandfriendsgui.inventory.PAFClickManager;
import de.simonsator.partyandfriendsgui.inventory.tasks.inventoryassignment.SettingsMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastSettingPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		if (getConfig().getBoolean("Settings.ReceiveBroadcastsSetting.Use")) {
			ItemStack receiveItem = getGreenLoamStackWithName("Settings.ReceiveBroadcastsSetting.LowerItem.ReceiveBroadcastsItem.Text");
			ItemStack doNotReceiveItem = getRedLoamStackWithName("Settings.ReceiveBroadcastsSetting.LowerItem.ReceiveBroadcastsItem.Text");
			ItemStack broadcastReceiveSettingTopItem = new ItemStack(Material.valueOf(getConfig().getString("Settings.ReceiveBroadcastsSetting.TopItem.ItemData")), 1, (short) getConfig().getInt("Settings.ReceiveBroadcastsSetting.TopItem.MetaData"));
			ItemMeta topItemMeta = broadcastReceiveSettingTopItem.getItemMeta();
			topItemMeta.setDisplayName(getConfig().getString("Settings.ReceiveBroadcastsSetting.TopItem.Text"));
			broadcastReceiveSettingTopItem.setItemMeta(topItemMeta);
			getServer().getPluginManager().registerEvents(new BroadcastSettingsMenuExtension(broadcastReceiveSettingTopItem, receiveItem, doNotReceiveItem), this);
			PAFClickManager.getInstance().getTask(SettingsMenu.class).addTask(new SettingItemClick(receiveItem, doNotReceiveItem));
		}
	}

	private ItemStack getGreenLoamStackWithName(String pIdentifier) {
		return getLoamStackWithName(pIdentifier, (short) 5);
	}

	private ItemStack getRedLoamStackWithName(String pIdentifier) {
		return getLoamStackWithName(pIdentifier, (short) 14);
	}


	private ItemStack getLoamStackWithName(String pIdentifier, short pShort) {
		return setDisplayName(pIdentifier, new ItemStack(Material.STAINED_CLAY, 1, pShort));
	}

	private ItemStack setDisplayName(String pIdentifier, ItemStack pItemStack) {
		ItemMeta meta = pItemStack.getItemMeta();
		meta.setDisplayName(getConfig().getString(pIdentifier));
		pItemStack.setItemMeta(meta);
		return pItemStack;
	}


}
