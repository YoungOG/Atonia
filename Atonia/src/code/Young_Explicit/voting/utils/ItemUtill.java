package code.Young_Explicit.voting.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtill {

	public static ItemStack createItem(Material material, String displayname, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		for (String loreString : lore)
			Lore.add(loreString);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, int amount, String displayname, String... lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		for (String loreString : lore)
			Lore.add(loreString);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createItem(Material material, short data, int amount, String displayname, String... lore) {
		ItemStack item = new ItemStack(material, amount);
		item.setDurability(data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		for (String loreString : lore)
			Lore.add(loreString);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}
}