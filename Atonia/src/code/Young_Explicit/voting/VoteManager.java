package code.Young_Explicit.voting;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import code.Young_Explicit.voting.utils.GenerateItems;
import code.Young_Explicit.voting.utils.ItemUtill;

public class VoteManager implements Listener {

	public static Random chance = new Random();

	public static HashMap<UUID, Boolean> canTake = new HashMap<UUID, Boolean>();
	
	public static void openMainMenu(Player p) {
		Inventory main;
		
		main = Bukkit.createInventory(p, 27, "�b�lVoting Chest");
		main.setItem(10, ItemUtill.createItem(Material.DIAMOND_SWORD, "�aCombat", "�bClick here to unlock a combat chest."));
		main.setItem(12, ItemUtill.createItem(Material.DIAMOND_PICKAXE, "�aTools", "�bClick here to unlock a tools chest."));
		main.setItem(14, ItemUtill.createItem(Material.OBSIDIAN, "�aTracking", "�bClick here to unlock a tracking chest."));
		main.setItem(16, ItemUtill.createItem(Material.PORTAL, "�aRandom", "�bClick here to unlock a random chest."));
		
		main.setItem(0, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(1, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(2, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(3, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(4, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(5, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(6, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(7, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(8, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(9, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(11, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(13, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(15, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(17, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(18, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(19, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(20, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(21, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(22, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(23, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(24, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(25, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		main.setItem(26, ItemUtill.createItem(Material.STAINED_GLASS_PANE, (short) 7, 1, "", ""));
		
		p.openInventory(main);
		p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
	}
	
	public static void unlockCombat(final Player p) {
		final Inventory combat;
		if (Main.getDatabaseManager().doesHaveKey(p)) {
			if (Main.getDatabaseManager().getPlayerKeys(p) >= 1) {
				Main.getDatabaseManager().setPlayerKeys(p, Main.getDatabaseManager().getPlayerKeys(p) - 1);
				p.sendMessage("�a�lYou have used a vote key. �d�l" + Main.getDatabaseManager().getPlayerKeys(p) + " �a�l remaining.");
			} else {
				p.sendMessage("�c�lYou do not have anymore Voting Keys.");
				return;
			}
		} else {
			p.sendMessage("�c�lYou do not have anymore Voting Keys.");
			return;
		}
		
		combat = Bukkit.createInventory(p, 27, "�c�lCombat Voting Chest");
		
		canTake.put(p.getUniqueId(), false);
		
		final ItemStack ihelm = new ItemStack(Material.IRON_HELMET, 1);
		ihelm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		
		final ItemStack wsword = new ItemStack(Material.WOOD_SWORD, 1);
		wsword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
		
		final ItemStack ichest = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ichest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		
		final ItemStack ilegs = new ItemStack(Material.IRON_LEGGINGS, 1);
		ilegs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		
		final 	ItemStack isword = new ItemStack(Material.IRON_SWORD, 1);
		isword.addEnchantment(Enchantment.DAMAGE_ALL, 3);
		
		final ItemStack iboots = new ItemStack(Material.IRON_BOOTS, 1);
		iboots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		
		final ItemStack dsword1 = new ItemStack(Material.DIAMOND_SWORD, 1);
		dsword1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		
		final ItemStack bow = new ItemStack(Material.BOW, 1);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		final ItemStack dsword2 = new ItemStack(Material.DIAMOND_SWORD, 1);
		dsword2.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		
		final ItemStack dchest = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		dchest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		
		final ItemStack dboots = new ItemStack(Material.DIAMOND_BOOTS, 1);
		dboots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
		
		final ItemStack dsword3 = new ItemStack(Material.DIAMOND_SWORD, 1);
		dsword3.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		dsword3.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		
		final ItemStack p1 = new ItemStack(Material.POTION);
		p1.setDurability((short) 8233); 
		
		final ItemStack cchest = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		
		final ItemStack clegs = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
		
		final ItemStack p2 = new ItemStack(Material.POTION);
		p2.setDurability((short) 8226); 
		
		if (chance.nextInt(100) < 3) {
			new GenerateItems(Main.getPlugin(Main.class), p, combat, 9, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, dsword3, p1, cchest, clegs, p2);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 11, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, dsword3, p1, cchest, clegs, p2);
				}
			}, 1*20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 13, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, dsword3, p1, cchest, clegs, p2);
				}
			}, 2*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 15, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, dsword3, p1, cchest, clegs, p2);
				}
			}, 3*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 17, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, dsword3, p1, cchest, clegs, p2);
				}
			}, 4*20);
		} else {
			new GenerateItems(Main.getPlugin(Main.class), p, combat, 9, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, p1, cchest, clegs, p2);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 11, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, p1, cchest, clegs, p2);
				}
			}, 1*20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 13, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, p1, cchest, clegs, p2);
				}
			}, 2*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 15, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, p1, cchest, clegs, p2);
				}
			}, 3*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, combat, 17, (chance.nextInt(4) + 1), ihelm, wsword, ichest, ilegs, isword, iboots, dsword1, bow, dsword2, dchest, dboots, p1, cchest, clegs, p2);
				}
			}, 4*20);
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				canTake.put(p.getUniqueId(), true);
			}
		}, 11*20);
		
		p.openInventory(combat);
	}
	
	public static void unlockTools(final Player p) {
		final Inventory tools;
		if (Main.getDatabaseManager().doesHaveKey(p)) {
			if (Main.getDatabaseManager().getPlayerKeys(p) >= 1) {
				Main.getDatabaseManager().setPlayerKeys(p, Main.getDatabaseManager().getPlayerKeys(p) - 1);
				p.sendMessage("�a�lYou have used a vote key. �d�l" + Main.getDatabaseManager().getPlayerKeys(p) + " �a�l remaining.");
			} else {
				p.sendMessage("�c�lYou do not have anymore Voting Keys.");
				return;
			}
		} else {
			p.sendMessage("�c�lYou do not have anymore Voting Keys.");
			return;
		}
		tools = Bukkit.createInventory(p, 27, "�c�lTools Voting Chest");
		
		canTake.put(p.getUniqueId(), false);
		
		final ItemStack dpick1 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		dpick1.addEnchantment(Enchantment.SILK_TOUCH, 1);
		
		final ItemStack ipick1 = new ItemStack(Material.IRON_PICKAXE, 1);
		ipick1.addEnchantment(Enchantment.DIG_SPEED, 3);
		
		final ItemStack daxe1 = new ItemStack(Material.DIAMOND_AXE, 1);
		daxe1.addEnchantment(Enchantment.DIG_SPEED, 3);
		daxe1.addEnchantment(Enchantment.DURABILITY, 1);
		
		final ItemStack ipick2 = new ItemStack(Material.IRON_PICKAXE, 1);
		ipick2.addEnchantment(Enchantment.DIG_SPEED, 2);
		ipick2.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
		
		final ItemStack frod = new ItemStack(Material.FISHING_ROD, 1);
		frod.addEnchantment(Enchantment.LUCK, 3);
		frod.addEnchantment(Enchantment.LURE, 3);
		
		final ItemStack ishovel = new ItemStack(Material.IRON_SPADE, 1);
		ishovel.addEnchantment(Enchantment.DIG_SPEED, 4);
		ishovel.addEnchantment(Enchantment.DURABILITY, 2);
		
		final ItemStack dshovel1 = new ItemStack(Material.DIAMOND_SPADE, 1);
		dshovel1.addEnchantment(Enchantment.DIG_SPEED, 3);
		dshovel1.addEnchantment(Enchantment.DURABILITY, 1);
		
		final ItemStack iaxe = new ItemStack(Material.IRON_AXE, 1);
		iaxe.addEnchantment(Enchantment.DIG_SPEED, 4);
		iaxe.addEnchantment(Enchantment.DURABILITY, 2);
		
		final ItemStack dshovel2 = new ItemStack(Material.DIAMOND_SPADE, 1);
		
		final ItemStack daxe2 = new ItemStack(Material.DIAMOND_AXE, 1);
		
		final ItemStack dpick2 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		
		final ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL, 1);
		
		final ItemStack dpick3 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		dpick3.addEnchantment(Enchantment.DIG_SPEED, 4);
		dpick3.addEnchantment(Enchantment.DURABILITY, 1);
		
		if (chance.nextInt(100) < 3) {
			new GenerateItems(Main.getPlugin(Main.class), p, tools, 9, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint, dpick3);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 11, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint, dpick3);
				}
			}, 1*20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 13, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint, dpick3);
				}
			}, 2*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 15, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint, dpick3);
				}
			}, 3*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 17, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint, dpick3);
				}
			}, 4*20);
		} else {
			new GenerateItems(Main.getPlugin(Main.class), p, tools, 9, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 11, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint);
				}
			}, 1*20);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 13, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint);
				}
			}, 2*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 15, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint);
				}
			}, 3*20);
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
				public void run() {
					new GenerateItems(Main.getPlugin(Main.class), p, tools, 17, (chance.nextInt(4) + 1), dpick1, daxe1, ipick1, frod, ishovel, dshovel1, iaxe, dshovel2, daxe2, dpick2, flint);
				}
			}, 4*20);
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				canTake.put(p.getUniqueId(), true);
			}
		}, 11*20);
		
		p.openInventory(tools);
	}
	
	public static void unlockTrack(final Player p) {
		final Inventory tracking;
		if (Main.getDatabaseManager().doesHaveKey(p)) {
			if (Main.getDatabaseManager().getPlayerKeys(p) >= 1) {
				Main.getDatabaseManager().setPlayerKeys(p, Main.getDatabaseManager().getPlayerKeys(p) - 1);
				p.sendMessage("�a�lYou have used a vote key. �d�l" + Main.getDatabaseManager().getPlayerKeys(p) + " �a�l remaining.");
			} else {
				p.sendMessage("�c�lYou do not have anymore Voting Keys.");
				return;
			}
		} else {
			p.sendMessage("�c�lYou do not have anymore Voting Keys.");
			return;
		}
		tracking = Bukkit.createInventory(p, 27, "�c�lTracking Voting Chest");
		
		canTake.put(p.getUniqueId(), false);
		
		final ItemStack db = new ItemStack(Material.DIAMOND_BLOCK, 1);
		
		final ItemStack gb = new ItemStack(Material.GOLD_BLOCK, 2);
		
		final ItemStack dpick = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		dpick.addEnchantment(Enchantment.DIG_SPEED, 2);
		dpick.addEnchantment(Enchantment.DURABILITY, 2);
		
		final ItemStack ob1 = new ItemStack(Material.OBSIDIAN, 64);
		
		final ItemStack ob2 = new ItemStack(Material.OBSIDIAN, 32);
		
		final ItemStack ob3 = new ItemStack(Material.OBSIDIAN, 16);
		
		final ItemStack ob4 = new ItemStack(Material.OBSIDIAN, 8);
		
		final ItemStack ob5 = new ItemStack(Material.OBSIDIAN, 32);
		
		final ItemStack ob6 = new ItemStack(Material.OBSIDIAN, 16);
		
		final ItemStack ob7 = new ItemStack(Material.OBSIDIAN, 8);

		new GenerateItems(Main.getPlugin(Main.class), p, tracking, 9, (chance.nextInt(4) + 1), db, gb, dpick, ob1, ob2, ob3, ob4, ob5, ob6, ob7);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, tracking, 11, (chance.nextInt(4) + 1), db, gb, dpick, ob1, ob2, ob3, ob4, ob5, ob6, ob7);
			}
		}, 1*20);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, tracking, 13, (chance.nextInt(4) + 1), db, gb, dpick, ob1, ob2, ob3, ob4, ob5, ob6, ob7);
			}
		}, 2*20);
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, tracking, 15, (chance.nextInt(4) + 1), db, gb, dpick, ob1, ob2, ob3, ob4, ob5, ob6, ob7);
			}
		}, 3*20);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, tracking, 17, (chance.nextInt(4) + 1), db, gb, dpick, ob1, ob2, ob3, ob4, ob5, ob6, ob7);
			}
		}, 4*20);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				canTake.put(p.getUniqueId(), true);
			}
		}, 11*20);
		
		p.openInventory(tracking);
	}
	
	public static void unlockRandom(final Player p) {
		final Inventory random;
		if (Main.getDatabaseManager().doesHaveKey(p)) {
			if (Main.getDatabaseManager().getPlayerKeys(p) >= 1) {
				Main.getDatabaseManager().setPlayerKeys(p, Main.getDatabaseManager().getPlayerKeys(p) - 1);
				p.sendMessage("�a�lYou have used a vote key. �d�l" + Main.getDatabaseManager().getPlayerKeys(p) + " �a�l remaining.");
			} else {
				p.sendMessage("�c�lYou do not have anymore Voting Keys.");
				return;
			}
		} else {
			p.sendMessage("�c�lYou do not have anymore Voting Keys.");
			return;
		}
		random = Bukkit.createInventory(p, 27, "�c�lRandom Voting Chest");
		
		canTake.put(p.getUniqueId(), false);
		
		final ItemStack mc = new ItemStack(Material.MAGMA_CREAM, 4);
		
		final ItemStack pp = new ItemStack(Material.POTION, 1);
		pp.setDurability((short) 16420);
		
		final ItemStack eb = new ItemStack(Material.EXP_BOTTLE, 32);
		
		final ItemStack se = new ItemStack(Material.MONSTER_EGG, 4);
		se.setDurability((short) 50);
		
		final ItemStack d = new ItemStack(Material.DIAMOND, 4);
		
		final ItemStack i = new ItemStack(Material.IRON_INGOT, 16);
		
		final ItemStack g = new ItemStack(Material.GOLD_INGOT, 10);
		
		final ItemStack bp = new ItemStack(Material.BLAZE_POWDER, 16);
		
		final ItemStack s = new ItemStack(Material.SUGAR, 16);
		
		final ItemStack nv = new ItemStack(Material.POTION, 1);
		nv.setDurability((short) 15454);
		
		final ItemStack hp = new ItemStack(Material.POTION, 4);
		hp.setDurability((short) 16428);
		
		final ItemStack fw1 = ItemUtill.createItem(Material.FIREWORK, 8, "�oAngry Creeper");
		FireworkMeta fwm1 = (FireworkMeta) fw1.getItemMeta();
		Builder fwe1 = FireworkEffect.builder();
		fwe1.with(Type.CREEPER);
		fwe1.withColor(Color.RED);
		fwe1.withFade(Color.GREEN);
		fwe1.withTrail();
		fwm1.addEffect(fwe1.build());
		fwm1.setPower(1);
		fw1.setItemMeta(fwm1);
		
		final ItemStack fw2 = ItemUtill.createItem(Material.FIREWORK, 8, "�oStarry Night");
		FireworkMeta fwm2 = (FireworkMeta) fw2.getItemMeta();
		Builder fwe2 = FireworkEffect.builder();
		fwe2.with(Type.STAR);
		fwe2.withColor(Color.YELLOW);
		fwe2.withColor(Color.ORANGE);
		fwe2.withFade(Color.BLUE);
		fwe2.withFlicker();
		fwe2.withTrail();
		fwm2.addEffect(fwe2.build());
		fwm2.setPower(1);
		fw2.setItemMeta(fwm2);
		
		final ItemStack fw3 = ItemUtill.createItem(Material.FIREWORK, 8, "�oSolarWind");
		FireworkMeta fwm3 = (FireworkMeta) fw3.getItemMeta();
		Builder fwe3 = FireworkEffect.builder();
		fwe3.with(Type.BALL_LARGE);
		fwe3.withColor(Color.YELLOW);
		fwe3.withColor(Color.ORANGE);
		fwe3.withFade(Color.RED);
		fwe3.withFlicker();
		fwe3.with(Type.BALL);
		fwe3.withColor(Color.YELLOW);
		fwe3.withColor(Color.ORANGE);
		fwe3.withFade(Color.RED);
		fwe3.withTrail();
		fwe3.with(Type.STAR);
		fwe3.withColor(Color.RED);
		fwe3.withColor(Color.PURPLE);
		fwe3.withFade(Color.FUCHSIA);
		fwe3.withTrail();
		fwm3.addEffect(fwe3.build());
		fwm3.setPower(1);
		fw3.setItemMeta(fwm3);
		
		new GenerateItems(Main.getPlugin(Main.class), p, random, 9, (chance.nextInt(4) + 1), mc, pp, eb, se, d, i, g, bp, s, nv, hp, fw1, fw2, fw3);
			
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, random, 11, (chance.nextInt(4) + 1), mc, pp, eb, se, d, i, g, bp, s, nv, hp, fw1, fw2, fw3);
			}
		}, 1*20);
			
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, random, 13, (chance.nextInt(4) + 1), mc, pp, eb, se, d, i, g, bp, s, nv, hp, fw1, fw2, fw3);
			}
		}, 2*20);
			
			
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, random, 15, (chance.nextInt(4) + 1), mc, pp, eb, se, d, i, g, bp, s, nv, hp, fw1, fw2, fw3);
			}
		}, 3*20);
			
				
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				new GenerateItems(Main.getPlugin(Main.class), p, random, 17, (chance.nextInt(4) + 1), mc, pp, eb, se, d, i, g, bp, s, nv, hp, fw1, fw2, fw3);
			}
		}, 4*20);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				canTake.put(p.getUniqueId(), true);
			}
		}, 11*20);
		
		p.openInventory(random);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		ItemStack item = e.getCurrentItem();
		if (inv.getName().equalsIgnoreCase("�b�lVoting Chest")) {
			if (item != null) {
				e.setCancelled(true);
				if (item.getType() == Material.DIAMOND_SWORD) {
					unlockCombat(p);
				}
				else if (item.getType() == Material.DIAMOND_PICKAXE) {
					unlockTools(p);
				}
				else if (item.getType() == Material.OBSIDIAN) {
					unlockTrack(p);
				}
				else if (item.getType() == Material.PORTAL) {
					unlockRandom(p);
				}
			}
		}
		if (inv.getName().equalsIgnoreCase("�c�lCombat Voting Chest") || inv.getName().equalsIgnoreCase("�c�lTools Voting Chest") || inv.getName().equalsIgnoreCase("�c�lTracking Voting Chest") || inv.getName().equalsIgnoreCase("�c�lRandom Voting Chest")) {
				if (item != null) {
					if (canTake.get(p.getUniqueId()) == false) {
						e.setCancelled(true);
					}
				}
			}
		}
	
	@EventHandler
	public void onInventoryOpen(PlayerInteractEvent e) {
		Location loc = new Location(Bukkit.getWorld("world"), -13, 68, 2);
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.hasBlock()) {
				if (e.getClickedBlock().getType() == Material.CHEST) {
					if (e.getClickedBlock().getLocation().equals(loc)) {
						e.setCancelled(true);
						if (Main.getDatabaseManager().getPlayerKeys(e.getPlayer()) > 0) {
							openMainMenu(e.getPlayer());
						} else {
							e.getPlayer().sendMessage("�cYou do not have any Voting Keys. �a/vote now!");
						}
					}
				}
			}
		}
	}
}
