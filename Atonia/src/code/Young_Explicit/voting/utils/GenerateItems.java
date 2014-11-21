package code.Young_Explicit.voting.utils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GenerateItems {

	public HashMap<UUID, Integer> TID = new HashMap<UUID, Integer>();
	
	public Plugin plugin;
    public Player p;
	public Inventory inv;
	public InventoryHolder holder;
	public ItemStack[] items;
	public int index;
	public int slot;
    
    public GenerateItems(Plugin plugin, final Player p, Inventory inv, int slot, int time, ItemStack... item) {
        this.plugin = plugin;
        this.p = p;
        this.inv = inv;
        this.slot = slot;
        this.index = 0;
        this.items = item;
        this.holder = inv.getHolder();
        inv.setItem(this.slot, this.get());
        
        startScheduler(p);
        
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
        	public void run() {
        		stopScheduler(p);
        	}
        }, time*20);
    }

	public void startScheduler(Player p) {
		final int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				 next();
				 ItemStack item = inv.getItem(slot);
				 if (item == null || prev().equals(item)) {
					 Player p = (Player) holder;
					 p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
					 inv.setItem(slot, get());
				 }
			}
		}, 5, 5);
		TID.put(p.getUniqueId(), id);
	}
	
	public void stopScheduler(Player p) {
		if (TID.containsKey(p.getUniqueId())) {
			int id = TID.get(p.getUniqueId());
			Bukkit.getScheduler().cancelTask(id);
			TID.remove(p.getUniqueId());
		}
	}
	
    private void next() {
        index ++;
        index %= this.items.length;      
    }

    public ItemStack get() {
        return items[this.index];
    }

    public ItemStack prev() {
        return this.items[(this.items.length + this.index - 1) % this.items.length];
    }
}