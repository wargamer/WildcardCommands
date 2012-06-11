package org.wargamer2010.wildcardcommand;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.Bukkit;
import java.util.logging.*;
import org.wargamer2010.wildcardcommand.util.Vault;

public class WildcardCommand extends JavaPlugin {
    private static final Logger logger = Logger.getLogger("Minecraft");
        
    public void log(String message, Level lvl) {
        PluginDescriptionFile pdfFile = this.getDescription();
        logger.log(lvl, ("["+pdfFile.getName()+" v"+pdfFile.getVersion()+"] " + message));
    }
        
    @Override
    public void onEnable() {
        log("Enabled", Level.INFO);
        Vault.setupPermissions();
        Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(), this);
    }
    
    @Override
    public void onDisable() {
        log("Disabled", Level.INFO);
    }
    
}
