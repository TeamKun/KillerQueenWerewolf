package net.kunmc.lab.killerqueen;

import net.kunmc.lab.killerqueen.listener.CommandListener;
import net.kunmc.lab.killerqueen.listener.StandListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class KillerQueenWerewolf extends JavaPlugin {

    public static KillerQueenWerewolf plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginCommand("killerqueenw").setExecutor(new CommandListener());
        getServer().getPluginCommand("killerqueenw").setTabCompleter(new CommandListener());
        getServer().getPluginManager().registerEvents(new StandListener(), this);
        getLogger().info("キラークイーンが有効になりました");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("キラークイーンが有効になりました");
    }
}
