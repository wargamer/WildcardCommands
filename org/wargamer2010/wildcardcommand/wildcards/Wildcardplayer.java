package org.wargamer2010.wildcardcommand.wildcards;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Wildcardplayer {    
    private String wcName = "";
    private Player wcPlayer = null;
    
    public Wildcardplayer(Player player) {
        if(player != null) {
            wcPlayer = player;
            wcName = player.getName();
        }
    }
    
    public Wildcardplayer(String sName) {
        wcName = sName; 
    }
    
    public String getName() {
        return wcName;
    }
    
    public void sendMessage(String message) {
        if(wcPlayer == null)
            return;
        String prefixed = (ChatColor.DARK_GRAY + "[" 
                            + ChatColor.RED + "Wildcard" + ChatColor.DARK_GRAY + "Commands" + "]: "  
                            + ChatColor.WHITE + message);
        wcPlayer.sendMessage(prefixed);
    }
    
    public static void sendMessage(Player player, String message) {
        if(player == null)
            return;
        new Wildcardplayer(player).sendMessage(message);
    }
}
