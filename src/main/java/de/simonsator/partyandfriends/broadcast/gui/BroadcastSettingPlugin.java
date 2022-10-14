package de.simonsator.partyandfriends.broadcast.gui;

import de.simonsator.partyandfriendsgui.api.PartyFriendsAPI;
import de.simonsator.partyandfriendsgui.api.menu.settings.GUISetting;
import de.simonsator.partyandfriendsgui.api.menu.settings.GUISettingsManager;
import de.simonsator.partyandfriendsgui.manager.ItemManager;
import de.simonsator.partyandfriendsgui.manager.ItemManagerSetupHelper;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class BroadcastSettingPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		Configuration config = getConfig();
		config.options().copyDefaults(true);
		if (getServer().getBukkitVersion().contains("1.7") || getServer().getBukkitVersion().contains("1.8") ||
				getServer().getBukkitVersion().contains("1.9") || getServer().getBukkitVersion().contains("1.10") ||
				getServer().getBukkitVersion().contains("1.11") || getServer().getBukkitVersion().contains("1.12")) {
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyEnabledItem.ItemData",
					"STAINED_CLAY");
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyEnabledItem.MetaData",
					5);
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyDisabledItem.ItemData",
					"STAINED_CLAY");
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyDisabledItem.MetaData",
					14);
			setDefaults("Settings.ReceiveBroadcastsSetting.TopItem.ItemData",
					"BOOK_AND_QUILL");
		} else {
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyEnabledItem.ItemData",
					"GREEN_TERRACOTTA");
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyEnabledItem.MetaData",
					0);
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyDisabledItem.ItemData",
					"RED_TERRACOTTA");
			setDefaults("Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyDisabledItem.MetaData",
					0);
			setDefaults("Settings.ReceiveBroadcastsSetting.TopItem.ItemData",
					"WRITABLE_BOOK");
		}
		saveConfig();
		for (String path : config.getKeys(true))
			if (getConfig().isString(path))
				getConfig().set(path, ChatColor.translateAlternateColorCodes('&',
						Objects.requireNonNull(config.getString(path))));
		if (getConfig().getBoolean("Settings.ReceiveBroadcastsSetting.Use")) {
			ItemManagerSetupHelper setupHelper = new ItemManagerSetupHelper(config, ItemManager.getInstance().PLAYER_HEAD);
			String itemPart = "Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyEnabledItem.";
			ItemStack receiveItem = setupHelper.getItemStack(config.getString(itemPart + "Text"),
					itemPart + "ItemData", itemPart + "MetaData", itemPart +
							"UseCustomTexture", itemPart + "Base64CustomTexture", itemPart +
							"CustomModelData");
			itemPart = "Settings.ReceiveBroadcastsSetting.LowerItem.SettingCurrentlyDisabledItem.";
			ItemStack doNotReceiveItem = setupHelper.getItemStack(config.getString(itemPart + "Text"),
					itemPart + "ItemData", itemPart + "MetaData", itemPart +
							"UseCustomTexture", itemPart + "Base64CustomTexture", itemPart +
							"CustomModelData");
			itemPart = "Settings.ReceiveBroadcastsSetting.TopItem.";
			ItemStack broadcastReceiveSettingTopItem =
					setupHelper.getItemStack(getConfig().getString(itemPart + "Text"),
							itemPart + "ItemData", itemPart + "MetaData", itemPart +
									"UseCustomTexture", itemPart + "Base64CustomTexture", itemPart +
									"CustomModelData");
			GUISettingsManager.getInstance().registerSetting(new GUISetting(30,
					broadcastReceiveSettingTopItem, new ItemStack[]{receiveItem, doNotReceiveItem},
					getConfig().getInt("Settings.ReceiveBroadcastsSetting.Priority"),
					getConfig().getString("Settings.ReceiveBroadcastsSetting.Permission"),
					pPlayer -> PartyFriendsAPI.changeSetting(pPlayer, "receivebroadcast")));
		}
	}

	private void setDefaults(String pEntry, Object pValue) {
		if (getConfig().get(pEntry) == null)
			getConfig().set(pEntry, pValue);
	}
}
