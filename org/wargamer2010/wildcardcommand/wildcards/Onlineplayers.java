package org.wargamer2010.wildcardcommand.wildcards;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.List;
import java.util.ArrayList;

public class Onlineplayers extends Wildcard {    
    @Override
    public String getWildcardName() {
        return "All Online Players";
    }
    
    @Override
    public List<Wildcardplayer> getPlayers() {        
        List<Wildcardplayer> players = new ArrayList<Wildcardplayer>();
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            if(player.isOnline())
                players.add(new Wildcardplayer(player));
        }
        return players;
    }
}
