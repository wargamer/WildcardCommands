package org.wargamer2010.wildcardcommand.metrics;

import org.bukkit.plugin.Plugin;
import java.io.IOException;

public class SetupMetrics {
    private MetricsLite metrics = null;

    public final Boolean setup(Plugin plugin) {
        try {
            metrics = new MetricsLite(plugin);
            return metrics.start();
        } catch (IOException e) {
            return false;
        }
    }

    public Boolean disable() {
        try {
            metrics.disable();
            return true;
        } catch(IOException e) {
            return false;
        }
    }

    public Boolean enable() {
        try {
            metrics.enable();
            return true;
        } catch(IOException e) {
            return false;
        }
    }
}
