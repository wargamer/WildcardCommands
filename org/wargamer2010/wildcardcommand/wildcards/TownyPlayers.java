package org.wargamer2010.wildcardcommand.wildcards;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import org.bukkit.Bukkit;
import java.util.List;
import java.util.ArrayList;

public class TownyPlayers extends Wildcard {
    private String wildcardname = "All Players from the Town";

    @Override
    public String getWildcardName() {
        return wildcardname;
    }

    private void getNamesFromResidents(List<Resident> residents, List<String> existingplayers) {
        for(Resident resident : residents) {
            if(!existingplayers.contains(resident.getName()))
                existingplayers.add(resident.getName());
        }
    }

    @Override
    public List<Wildcardplayer> getPlayers() {
        List<Wildcardplayer> players = new ArrayList<Wildcardplayer>();
        if(Bukkit.getServer().getPluginManager().getPlugin("Towny") == null)
            return players;

        List<String> tempPlayers = new ArrayList<String>();

        for(String part : getWildcardparts()) {
            Town town = null;
            Nation nation = null;

            try {
                town = TownyUniverse.getDataSource().getTown(part);
            } catch(NotRegisteredException ex) {

            }
            try {
                nation = TownyUniverse.getDataSource().getNation(part);
            } catch(NotRegisteredException ex) {

            }

            if(town != null)
                getNamesFromResidents(town.getResidents(), tempPlayers);
            if(nation != null)
                getNamesFromResidents(nation.getResidents(), tempPlayers);

        }

        for(String temp : tempPlayers)
            players.add(new Wildcardplayer(temp));

        return players;
    }
}
