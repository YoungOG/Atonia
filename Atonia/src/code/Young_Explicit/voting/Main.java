package code.Young_Explicit.voting;

import me.spoony.JSONChatLib.JSONChatClickEventType;
import me.spoony.JSONChatLib.JSONChatColor;
import me.spoony.JSONChatLib.JSONChatFormat;
import me.spoony.JSONChatLib.JSONChatHoverEventType;
import me.spoony.chatlib.ChatPart;
import me.spoony.chatlib.MessageSender;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import code.Young_Explicit.voting.events.NametagListener;
import code.Young_Explicit.voting.events.PlayerEvents;
import code.Young_Explicit.voting.events.VoteHandler;
import code.Young_Explicit.voting.utils.Cooldowns;
import code.Young_Explicit.voting.utils.ParticleEffect;

public class Main extends JavaPlugin {

	private static DatabaseManager dbmanager;
	private static int restartTID = 0;
	private static int restartTime = 43200;
	
	public void onLoad() {
		dbmanager = new DatabaseManager();
	}
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
		getServer().getPluginManager().registerEvents(new VoteManager(), this);
		getServer().getPluginManager().registerEvents(new VoteHandler(), this);
		getServer().getPluginManager().registerEvents(new NametagListener(), this);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					if (dbmanager.getPlayerVotes(all) == 0) {
						all.sendMessage("§b§l§m§n---------------------------------------------");
						all.sendMessage("");
						all.sendMessage("§d§lIt appears you haven't voted yet!");
						all.sendMessage("§d§l/vote to receive a voting key, a kit, and $50!");
						all.sendMessage("");
						all.sendMessage("§b§l§m§n---------------------------------------------");
					}
				}
			}
		}, 0L, 150*20);
		
		scheduleRestart();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				Location loc = new Location(Bukkit.getWorld("world"), -12.5, 68, 2.5);
				ParticleEffect.FIREWORKS_SPARK.display(0.003F, 0.003F, 0.003F, 0.2F, 2, loc, 100);
			}
		}, 0L, 5);
	}
	
	public void onDisable() {
		getDatabaseManager().jc.save();
	}
    
    public static DatabaseManager getDatabaseManager() {
    	return dbmanager;
    }
    
    public static void scheduleRestart() {
    	Bukkit.getScheduler().cancelTask(restartTID);
		restartTID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				if (restartTime > 0) {
					restartTime--;
				}
				if (restartTime == 300) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 5 minutes.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 240) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 4 minutes.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 180) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 3 minutes.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 120) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 2 minutes.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 60) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 1 minute.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 30) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 30 seconds.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 20) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 20 seconds.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 10) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in 10 seconds.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime < 6 && restartTime > 1) {
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§e§lScheduled server reboot in " + restartTime + " seconds.");
					Bukkit.broadcastMessage("§r");
					Bukkit.broadcastMessage("§c§m§n------------------------------------");
				}
				else if (restartTime == 1) {
					Bukkit.savePlayers();
					for (World w : Bukkit.getWorlds()) {
						w.save();
					}
					Bukkit.getServer().shutdown();
				}
			}
		}, 0, 20);
    }
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		final Player p = (Player) sender;
		if (args.length == 0) {
			if (cmd.getName().equalsIgnoreCase("vote")) {
				p.sendMessage("§b§l§m§n---------------------------------------------");
				p.sendMessage("");
				MessageSender.sendMessage(p,
						new ChatPart("Voting Link 1", JSONChatColor.GREEN, new JSONChatFormat[] {JSONChatFormat.BOLD})
				.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "§bClick here to vote")
				.setClickEvent(JSONChatClickEventType.OPEN_URL, "http://minecraftservers.org/vote/182573"));
				
				MessageSender.sendMessage(p,
						new ChatPart("Voting Link 2", JSONChatColor.GREEN, new JSONChatFormat[] {JSONChatFormat.BOLD})
				.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "§bClick here to vote")
				.setClickEvent(JSONChatClickEventType.OPEN_URL, "http://topg.org/Minecraft/in-396658"));
				
				MessageSender.sendMessage(p,
						new ChatPart("Voting Link 3", JSONChatColor.GREEN, new JSONChatFormat[] {JSONChatFormat.BOLD})
				.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "§bClick here to vote")
				.setClickEvent(JSONChatClickEventType.OPEN_URL, "https://minestatus.net/124331-advancedpvp/vote"));
				p.sendMessage("");
				p.sendMessage("§b§l§m§n---------------------------------------------");
			}
			if (cmd.getName().equalsIgnoreCase("keys")) {
				p.sendMessage("§b§l§m§n---------------------------------------------");
				p.sendMessage("");
				p.sendMessage("§a§lYou currently have §d§l" + getDatabaseManager().getPlayerKeys(p) + " §a§lVoting Keys.");
				p.sendMessage("§a§lUse them under spawn at the §d§lVote Chest§a§l!");
				p.sendMessage("");
				p.sendMessage("§b§l§m§n---------------------------------------------");
			}
			if (cmd.getName().equalsIgnoreCase("cleardb")) {
				if (p.hasPermission("cleardb.clear")) {
					Main.getDatabaseManager().clearVotes();
					p.sendMessage("§aThe database has been cleared.");
				}
			}
			if (cmd.getName().equalsIgnoreCase("votekit")) {
				if (getDatabaseManager().hasVoted(p)) {
					if (Cooldowns.tryCooldown(p, "voteKit", 396000)) {
						p.sendMessage("§aKit Vote loaded.");
						
						p.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
				
						ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
						chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
						p.getInventory().addItem(chestplate);
					
						p.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
						
						p.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
						
						ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
						sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
						p.getInventory().addItem(sword);
						
						ItemStack p1 = new ItemStack(Material.POTION);
						p1.setDurability((short) 8226);
						p.getInventory().addItem(p1);
						
						ItemStack p2 = new ItemStack(Material.POTION);
						p2.setDurability((short) 8233);
						p.getInventory().addItem(p2);
						
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
						p.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
					} else {
						p.sendMessage("§cYou have already used this kit for today.");
						return false;
					}
				} else {
					p.sendMessage("§cYou have not voted yet. Type /vote!");
					return false;
				}
			}
		} else {
			if (args.length == 2) {
				if (cmd.getName().equalsIgnoreCase("givekey")) {
					if (p.hasPermission("voting.givekey")) {
						Main.getDatabaseManager().setPlayerKeys(Bukkit.getPlayer(args[0]), Main.getDatabaseManager().getPlayerKeys(Bukkit.getPlayer(args[0])) + Integer.parseInt(args[1]));
						p.sendMessage("§aGiving §6" + Integer.parseInt(args[1]) + " §akeys to " + Bukkit.getPlayer(args[0]).getName() + "§a.");
					}
				}
			}
		}
		return false;
	}
}
