package org.wargamer2010.wildcardcommand.wildcards;

import org.bukkit.OfflinePlayer;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.Bukkit;

public class Offlineplayers extends Wildcard {
    @Override
    public String getWildcardName() {
        return "All Offline Players";
    }
    
    @Override
    public List<Wildcardplayer> getPlayers() {
        List<Wildcardplayer> players = new ArrayList<Wildcardplayer>();        
        for(OfflinePlayer player : Bukkit.getServer().getOfflinePlayers())
            if(!player.isOnline())
                players.add(new Wildcardplayer(player.getName()));        
        return players;
    }
}
