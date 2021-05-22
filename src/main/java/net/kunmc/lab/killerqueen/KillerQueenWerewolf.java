package net.kunmc.lab.killerqueen;

import net.kunmc.lab.killerqueen.listener.CommandListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class KillerQueenWerewolf extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new CommandListener(), this);
        getLogger().info("キラークイーンが有効になりました");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("キラークイーンが有効になりました");
    }
}
