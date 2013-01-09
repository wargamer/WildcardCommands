package org.wargamer2010.wildcardcommand.util;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.Server;

public class Vault {
    private static Permission permission = null;
    private static Economy economy = null;
    private static Chat chat = null;
    private static Boolean vaultFound = null;
    private static Server server = Bukkit.getServer();

    private Vault() {

    }

    private synchronized static Boolean findVault() {
        if(vaultFound != null)
            return vaultFound;
        if(server.getPluginManager().isPluginEnabled("Vault"))
            vaultFound = true;
        else
            vaultFound = false;
        return vaultFound;
    }

    /**
     * Fetch permission handle from Vault if Vault is enabled
     * @return whether it succeeded
     */
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

    /**
     * Fetch chat handle from Vault if Vault is enabled
     * @return whether it succeeded
     */
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

    /**
     * Fetch economy handle from Vault if Vault is enabled
     * @return whether it succeeded
     */
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

    /**
     *
     * @return Permission handle
     */
    public static Permission getPermission() {
        return permission;
    }

    /**
     *
     * @return Economy handle
     */
    public static Economy getEconomy() {
        return economy;
    }

    /**
     *
     * @return Chat handle
     */
    public static Chat getChat() {
        return chat;
    }

    /**
     *
     * @return Whether Vault is enabled
     */
    public static Boolean isVaultFound() {
        return vaultFound;
    }
}
