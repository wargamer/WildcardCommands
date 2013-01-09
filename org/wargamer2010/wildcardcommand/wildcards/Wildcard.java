package org.wargamer2010.wildcardcommand.wildcards;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public abstract class Wildcard {
    private static volatile HashMap<String, String> supported_wildcardprefixes = null;
    private List<String> wildcardparts = null;

    /**
     * Attempts to create a Wildcard instance based on the given wildcardprefix
     *
     * @param wildcardprefix full wildcardprefix
     * @return wildcard instance or null
     */
    public static Wildcard getWildcardInstance(String wildcardprefix) {
        synchronized(Wildcard.class) {
            if(supported_wildcardprefixes == null) {
                supported_wildcardprefixes = new HashMap<String, String>();
                initWildcardMap();
            }
        }

        String[] wildcardsplit = wildcardprefix.split(":");
        if(wildcardsplit.length < 2) {
            wildcardsplit = new String[2];
            wildcardsplit[0] = wildcardprefix;
            wildcardsplit[1] = wildcardprefix;
        }
        for(int i = 0; i < wildcardsplit.length; i++)
            wildcardsplit[i] = wildcardsplit[i].trim();

        if(!supported_wildcardprefixes.containsKey(wildcardsplit[0]))
            return null;

        try {
            Class<?> fc = (Class<?>)Class.forName("org.wargamer2010.wildcardcommand.wildcards."+supported_wildcardprefixes.get(wildcardsplit[0]));
            Wildcard instance = (Wildcard)fc.newInstance();
            instance.initWildcardparts(wildcardsplit[1]);
            return instance;
        } catch(ClassNotFoundException notfoundex) {
            return null;
        } catch(InstantiationException instex) {
            return null;
        } catch(IllegalAccessException illex) {
            return null;
        }
    }

    private static void initWildcardMap() {
        if(supported_wildcardprefixes == null)
            return;
        supported_wildcardprefixes.put("p", "Playernames");
        supported_wildcardprefixes.put("g", "Permissiongroup");
        supported_wildcardprefixes.put("online", "Onlineplayers");
        supported_wildcardprefixes.put("on", "Onlineplayers");
        supported_wildcardprefixes.put("offline", "Offlineplayers");
        supported_wildcardprefixes.put("off", "Offlineplayers");
        supported_wildcardprefixes.put("all", "Allplayers");
        supported_wildcardprefixes.put("town", "TownyPlayers");
        supported_wildcardprefixes.put("nation", "TownyPlayers");
    }

    private void initWildcardparts(String wildcardprefix) {
        wildcardparts = new ArrayList<String>();
        if(wildcardprefix.contains(",")) {
            String[] temppieces = wildcardprefix.split(",");
            for(int i = 0; i < temppieces.length; i++)
                wildcardparts.add(temppieces[i].trim());
        } else {
            wildcardparts.add(wildcardprefix);
        }
    }

    protected List<String> getWildcardparts() {
        return wildcardparts;
    }

    /**
     * Get all players that match the given wildcard
     *
     * @return all players that match the wildcard
     */
    public abstract List<Wildcardplayer> getPlayers();

    /**
     * The names that is used in the message returned to the user
     *
     * @return Name of the wildcard
     */
    public abstract String getWildcardName();

}
