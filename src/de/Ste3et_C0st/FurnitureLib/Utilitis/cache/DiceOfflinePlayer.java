package de.Ste3et_C0st.FurnitureLib.Utilitis.cache;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class DiceOfflinePlayer {

	private final UUID uuid;
	private String name;
	private long lastSeen;
	
	public DiceOfflinePlayer(OfflinePlayer offlinePlayer) {
		this.uuid = offlinePlayer.getUniqueId();
		this.name = offlinePlayer.getName();
		this.setLastSeen(offlinePlayer.getLastPlayed());
	}

	public String getName() {
		return name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public OfflinePlayer getOfflinePlayer() {
		return Bukkit.getOfflinePlayer(this.uuid);
	}

	public long getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(long lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	public boolean isOnline() {
		return Bukkit.getServer().getPlayer(this.uuid).isOnline();
	}
	
	public void update(OfflinePlayer player) {
		this.name = player.getName();
		this.lastSeen = player.getLastPlayed();
	}
	
}
