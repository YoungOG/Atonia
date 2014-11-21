package code.Young_Explicit.voting;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import redis.clients.jedis.Jedis;

public class DatabaseManager {
	
	public Jedis jc = new Jedis("localhost");
	
	public DatabaseManager() {
		jc.auth(":ZLXF!@P#{!@_+DG)IU)(HY!@#&*G&ZFX^@QFDZ{:JO!@PO#N!@#");
		jc.connect();
	}
	
	public void setPlayerVote(Player p, Integer amount) {
		UUID id = p.getUniqueId();
		jc.setex("{votecount}" + id.toString(), 39600, amount.toString());
	}
	
	public int getPlayerVotes(Player p) {
		return jc.exists("{votecount}" + p.getUniqueId().toString()) ? Integer.valueOf(jc.get("{votecount}" + p.getUniqueId().toString())) : 0;
	}
	
	public boolean hasVoted(Player p) {
		UUID id = p.getUniqueId();
		if (jc.exists("{votecount}" + id.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setPlayerKeys(Player p, Integer amount) {
		UUID id = p.getUniqueId();
		jc.set("{votekeys}" + id.toString(), amount.toString());
	}
	
	public int getPlayerKeys(Player p) {
		return jc.exists("{votekeys}" + p.getUniqueId().toString()) ? Integer.valueOf(jc.get("{votekeys}" + p.getUniqueId().toString())) : 0;
	}
	
	public boolean doesHaveKey(Player p) {
		UUID id = p.getUniqueId();
		if (jc.exists("{votekeys}" + id.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clearVotes() {
	    Set<String> keys = jc.keys("{votecount}*");

	    Iterator<String> it = keys.iterator();
	    while (it.hasNext()) {
	        String s = it.next();
	        System.out.println(s);
	        jc.del(s);
	    }
	}
}
