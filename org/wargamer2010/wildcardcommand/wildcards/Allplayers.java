package org.wargamer2010.wildcardcommand.wildcards;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.Bukkit;

public class Allplayers extends Wildcard {
    @Override
    public String getWildcardName() {
        return "All Players";
    }
    
    @Override
    public List<Wildcardplayer> getPlayers() {
        List<Wildcardplayer> players = new ArrayList<Wildcardplayer>();        
        for(OfflinePlayer player : Bukkit.getServer().getOfflinePlayers())
            if(!player.isOnline())
                players.add(new Wildcardplayer(player.getName()));        
        for(Player player : Bukkit.getServer().getOnlinePlayers())
                players.add(new Wildcardplayer(player.getName()));        
        return players;
    }
}
