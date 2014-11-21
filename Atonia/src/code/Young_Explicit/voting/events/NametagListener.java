package code.Young_Explicit.voting.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.AsyncPlayerReceiveNameTagEvent;

public class NametagListener implements Listener {
	
	@EventHandler
	public void onNameTag(AsyncPlayerReceiveNameTagEvent e) {
		Player p = e.getNamedPlayer();
		if (p.hasPermission("tag.default")) {
			e.setTag("§7" + p.getName());
		}
		else if (p.hasPermission("tag.youtube")) {
			e.setTag("§7§o" + p.getName());
		}
		else if (p.hasPermission("tag.youtube+")) {
			e.setTag("§7§o" + p.getName());
		}
		else if (p.hasPermission("tag.premium")) {
			e.setTag("§9" + p.getName());
		}
		else if (p.hasPermission("tag.vip")) {
			e.setTag("§2" + p.getName());
		}
		else if (p.hasPermission("tag.pro")) {
			e.setTag("§6" + p.getName());
		}
		else if (p.hasPermission("tag.legend")) {
			e.setTag("§a" + p.getName());
		}
		else if (p.hasPermission("tag.master")) {
			e.setTag("§e" + p.getName());
		}
		else if (p.hasPermission("tag.infamous")) {
			e.setTag("§d" + p.getName());
		}
		else if (p.hasPermission("tag.advanced")) {
			e.setTag("§b§l" + p.getName());
		}
		else if (p.hasPermission("tag.extreme")) {
			e.setTag("§6§l" + p.getName());
		}
		else if (p.hasPermission("tag.mod")) {
			e.setTag("§5" + p.getName());
		}
		else if (p.hasPermission("tag.admin")) {
			e.setTag("§c" + p.getName());
		}
		else if (p.hasPermission("tag.owner")) {
			e.setTag("§4" + p.getName());
		}
		else if (p.hasPermission("tag.dev")) {
			e.setTag("§b§o" + p.getName());
		}
		if (p.getName().equalsIgnoreCase("SasukeGabe") || p.getName().equalsIgnoreCase("Dawhn") || p.getName().equalsIgnoreCase("dbluk")) {
			e.setTag("§4" + p.getName());
		}
	}
}
