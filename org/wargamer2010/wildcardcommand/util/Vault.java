package org.wargamer2010.wildcardcommand.util;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.Server;

public class Vault {
    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;
    public static Boolean vaultFound = null;
    private static Server server = Bukkit.getServer();
    
    private static Boolean findVault() {
        if(vaultFound != null)
            return vaultFound;
        if(server.getPluginManager().isPluginEnabled("Vault"))
            vaultFound = true;
        else
            vaultFound = false;
        return vaultFound;
    }
    
    public static Boolean setupPermissions()
    {
        if(!findVault())
            return false;
        RegisteredServiceProvider<Permission> permissionProvider = server.getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }        
        return (permission != null);
    }

    public static Boolean setupChat()
    {
        if(!findVault())
            return false;
        RegisteredServiceProvider<Chat> chatProvider = server.getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    public static Boolean setupEconomy()
    {
        if(!findVault())
            return false;
        RegisteredServiceProvider<Economy> economyProvider = server.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
}
