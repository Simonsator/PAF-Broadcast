package de.simonsator.partyandfriends.broadcast.settings;

import de.simonsator.partyandfriends.api.SimpleSetting;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.util.List;

/**
 * @author Simonsator
 * @version 1.0.0 26.05.17
 */
public class ReceiveBroadcastSettings extends SimpleSetting {
	private final ConfigurationCreator CONFIG;

	public ReceiveBroadcastSettings(List<String> pSettingNames, String pPermission, int pPriority, ConfigurationCreator pConfiguration) {
		super(pSettingNames, pPermission, pPriority, "receivebroadcast");
		CONFIG = pConfiguration;
	}

	@Override
	protected String getMessage(OnlinePAFPlayer pPlayer) {
		String identifier;
		if (pPlayer.getSettingsWorth(30) == 0) {
			identifier = "Messages.Settings.ReceivingBroadCasts";
		} else {
			identifier = "Messages.Settings.NotReceivingBroadCasts";
		}
		return CONFIG.getString(identifier);
	}

	@Override
	public void changeSetting(OnlinePAFPlayer pPlayer, String[] args) {
		int newWorth = pPlayer.changeSettingsWorth(30);
		if (newWorth == 0) {
			pPlayer.sendMessage(Friends.getInstance().getPrefix() + CONFIG.getString("Messages.Settings.NowReceivingBroadCasts"));
		} else {
			pPlayer.sendMessage(Friends.getInstance().getPrefix() + CONFIG.getString("Messages.Settings.NowNotReceivingBroadCasts"));
		}
	}
}
