package de.Ste3et_C0st.FurnitureLib.Utilitis.cache;

import java.util.*;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class OfflinePlayerCache {

	HashMap<UUID, DiceOfflinePlayer> offlinePlayerCache = Maps.newHashMap();
	
	public OfflinePlayerCache() {
		for(OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if(player.hasPlayedBefore()) {
				offlinePlayerCache.put(player.getUniqueId(), new DiceOfflinePlayer(player));
			}
		}
	}
	
	public boolean contains(UUID uuid) {
		return offlinePlayerCache.containsKey(uuid);
	}
	
	public void addPlayer(OfflinePlayer offlinePlayer) {
		if(!contains(offlinePlayer.getUniqueId())) {
			offlinePlayerCache.put(offlinePlayer.getUniqueId(), new DiceOfflinePlayer(offlinePlayer));
		}
	}
	
	public Optional<DiceOfflinePlayer> getPlayer(UUID uuid){
		return Optional.of(offlinePlayerCache.get(uuid));
	}
	
	public Optional<DiceOfflinePlayer> getPlayer(String userName){
		for(DiceOfflinePlayer diceOfflinePlayer : offlinePlayerCache.values()) {
			if(diceOfflinePlayer.getName().equalsIgnoreCase(userName)) {
				return Optional.of(diceOfflinePlayer);
			}
		}
		return Optional.empty();
	}
	
}
