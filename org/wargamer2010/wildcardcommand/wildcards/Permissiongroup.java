package org.wargamer2010.wildcardcommand.wildcards;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.List;
import java.util.ArrayList;
import org.wargamer2010.wildcardcommand.util.Vault;

public class Permissiongroup extends Wildcard {
    @Override
    public String getWildcardName() {
        return "All Players from the Permission groups";
    }

    @Override
    public List<Wildcardplayer> getPlayers() {
        List<Wildcardplayer> players = new ArrayList<Wildcardplayer>();
        if(!Vault.isVaultFound() || Vault.getPermission() == null)
            return players;
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            try {
                for(String group : Vault.getPermission().getPlayerGroups(player))
                    if(getWildcardparts().contains(group))
                        players.add(new Wildcardplayer(player));
            } catch(UnsupportedOperationException UnsupportedEX) {
                return players;
            }
        }
        return players;
    }
}
