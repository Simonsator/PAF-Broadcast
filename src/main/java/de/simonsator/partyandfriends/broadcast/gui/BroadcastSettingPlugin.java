package de.simonsator.partyandfriends.broadcast.gui;

import de.simonsator.partyandfriendsgui.inventory.PAFClickManager;
import de.simonsator.partyandfriendsgui.inventory.tasks.inventoryassignment.SettingsMenu;
import de.simonsator.partyandfriendsgui.manager.ItemManager;
import de.simonsator.partyandfriendsgui.utilities.Material112Converter;
import de.simonsator.partyandfriendsgui.utilities.Material113Converter;
import de.simonsator.partyandfriendsgui.utilities.MaterialConverter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastSettingPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		for (String path : getConfig().getKeys(true))
			if (getConfig().isString(path))
				getConfig().set(path, ChatColor.translateAlternateColorCodes('&', getConfig().getString(path)));
		if (getConfig().getBoolean("Settings.ReceiveBroadcastsSetting.Use")) {
			ItemStack receiveItem = getGreenLoamStackWithName("Settings.ReceiveBroadcastsSetting.LowerItem.ReceiveBroadcastsItem.Text");
			ItemStack doNotReceiveItem = getRedLoamStackWithName("Settings.ReceiveBroadcastsSetting.LowerItem.ReceiveBroadcastsItem.Text");
			MaterialConverter materialConverter;
			if (Bukkit.getServer().getVersion().contains("1.7") || Bukkit.getServer().getVersion().contains("1.8") || Bukkit.getServer().getVersion().contains("1.9") || Bukkit.getServer().getVersion().contains("1.10") || Bukkit.getServer().getVersion().contains("1.11") || Bukkit.getServer().getVersion().contains("1.12"))
				materialConverter = new Material112Converter();
			else
				materialConverter = new Material113Converter();
			ItemStack broadcastReceiveSettingTopItem = new ItemStack(materialConverter.getMaterial(getConfig().getString("Settings.ReceiveBroadcastsSetting.TopItem.ItemData")), 1, (short) getConfig().getInt("Settings.ReceiveBroadcastsSetting.TopItem.MetaData"));
			ItemMeta topItemMeta = broadcastReceiveSettingTopItem.getItemMeta();
			topItemMeta.setDisplayName(getConfig().getString("Settings.ReceiveBroadcastsSetting.TopItem.Text"));
			broadcastReceiveSettingTopItem.setItemMeta(topItemMeta);
			getServer().getPluginManager().registerEvents(new BroadcastSettingsMenuExtension(broadcastReceiveSettingTopItem, receiveItem, doNotReceiveItem, getConfig().getInt("Settings.ReceiveBroadcastsSetting.Priority")), this);
			PAFClickManager.getInstance().getTask(SettingsMenu.class).addTask(new SettingItemClick(receiveItem, doNotReceiveItem));
		}
	}

	private ItemStack getGreenLoamStackWithName(String pIdentifier) {
		return setDisplayName(pIdentifier, ItemManager.getInstance().GREEN_TERRACOTTA.clone());
	}

	private ItemStack getRedLoamStackWithName(String pIdentifier) {
		return setDisplayName(pIdentifier, ItemManager.getInstance().RED_TERRACOTTA.clone());
	}


	private ItemStack setDisplayName(String pIdentifier, ItemStack pItemStack) {
		ItemMeta meta = pItemStack.getItemMeta();
		meta.setDisplayName(getConfig().getString(pIdentifier));
		pItemStack.setItemMeta(meta);
		return pItemStack;
	}


}
