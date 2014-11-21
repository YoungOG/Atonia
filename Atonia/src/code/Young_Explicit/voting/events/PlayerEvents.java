package code.Young_Explicit.voting.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import code.Young_Explicit.voting.Main;

public class PlayerEvents implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				if (!(Main.getDatabaseManager().getPlayerVotes(p) > 0)) {
					p.sendMessage("§b§l§m§n---------------------------------------------");
					p.sendMessage("");
					p.sendMessage("§d§lIt appears you haven't voted yet!");
					p.sendMessage("§d§l/vote to receive a voting key, a kit, and $50!");
					p.sendMessage("");
					p.sendMessage("§b§l§m§n---------------------------------------------");
				}
			}
		}, 10L);
	}
}
