package code.Young_Explicit.voting.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import code.Young_Explicit.voting.Main;

import com.vexsoftware.votifier.model.VotifierEvent;

public class VoteHandler implements Listener {
	
	@EventHandler
	public void onVote(VotifierEvent e) {
		if (Bukkit.getPlayer(e.getVote().getUsername()).isOnline()) {
			Player p = Bukkit.getPlayer(e.getVote().getUsername());
			Bukkit.broadcastMessage("§b" + p.getName() + " has just voted! /vote for great rewards!");
			Main.getDatabaseManager().setPlayerVote(p, Main.getDatabaseManager().getPlayerVotes(p) + 1);
			if (Main.getDatabaseManager().getPlayerVotes(p) == 1) {
				p.sendMessage("§b§l§m§n---------------------------------------------");
				p.sendMessage("");
				p.sendMessage("§a§lThanks for voting! /votekit to use your kit!");
				p.sendMessage("§a§l(2) more votes to receive a voting key!");
				p.sendMessage("");
				p.sendMessage("§b§l§m§n---------------------------------------------");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 50");
			}
			else if (Main.getDatabaseManager().getPlayerVotes(p) == 2) {
				p.sendMessage("§b§l§m§n---------------------------------------------");
				p.sendMessage("");
				p.sendMessage("§a§lThanks for voting!");
				p.sendMessage("§a§l(1) more vote to receive a voting key!");
				p.sendMessage("");
				p.sendMessage("§b§l§m§n---------------------------------------------");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 50");
			}
			else if (Main.getDatabaseManager().getPlayerVotes(p) == 3) {
				p.sendMessage("§b§l§m§n---------------------------------------------");
				p.sendMessage("");
				p.sendMessage("§a§lThanks for voting!");
				p.sendMessage("§a§lYou've received a voting key! Use it under spawn!");
				p.sendMessage("");
				p.sendMessage("§b§l§m§n---------------------------------------------");
				Main.getDatabaseManager().setPlayerKeys(p, Main.getDatabaseManager().getPlayerKeys(p) + 1);
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 50");
			}
		}
	}
}