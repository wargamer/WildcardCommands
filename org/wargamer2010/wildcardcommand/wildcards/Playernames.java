package org.wargamer2010.wildcardcommand.wildcards;

import java.util.List;
import java.util.ArrayList;

public class Playernames extends Wildcard {
    @Override
    public String getWildcardName() {
        return "the list of Players";
    }

    @Override
    public List<Wildcardplayer> getPlayers() {
        List<Wildcardplayer> players = new ArrayList<Wildcardplayer>();
        for(String playername : getWildcardparts())
            players.add(new Wildcardplayer(playername));
        return players;
    }
}
