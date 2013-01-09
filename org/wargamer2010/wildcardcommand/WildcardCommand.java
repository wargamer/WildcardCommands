package org.wargamer2010.wildcardcommand;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.Bukkit;
import java.util.logging.*;
import org.wargamer2010.wildcardcommand.metrics.SetupMetrics;
import org.wargamer2010.wildcardcommand.util.Vault;

public class WildcardCommand extends JavaPlugin {
    private static final Logger logger = Logger.getLogger("Minecraft");
    private static final SetupMetrics metrics = new SetupMetrics();

    public void log(String message, Level lvl) {
        PluginDescriptionFile pdfFile = this.getDescription();
        logger.log(lvl, ("["+pdfFile.getName()+" v"+pdfFile.getVersion()+"] " + message));
    }

    /**
     * Enables WildcardCommand
     */
    @Override
    public void onEnable() {
        log("Enabled", Level.INFO);
        Vault.setupPermissions();
        Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(), this);
        if(metrics.setup(this)) {
            log("Succesfully started Metrics, see http://mcstats.org for more information.", Level.INFO);
        } else {
            log("Could not start Metrics, see http://mcstats.org for more information.", Level.INFO);
        }
    }

    /**
     * Disables WildcardCommand
     */
    @Override
    public void onDisable() {
        log("Disabled", Level.INFO);
    }

}
