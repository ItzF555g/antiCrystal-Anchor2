package me.georg.endcampfirerespawn;

import me.georg.endcampfirerespawn.listeners.CampfireListener;
import me.georg.endcampfirerespawn.listeners.ExplosionListener;
import me.georg.endcampfirerespawn.managers.SpawnManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EndCampfireRespawn extends JavaPlugin {

    private static EndCampfireRespawn instance;

    private SpawnManager spawnManager;

    public static EndCampfireRespawn getInstance() {
        return instance;
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        spawnManager = new SpawnManager(this);

        getServer().getPluginManager().registerEvents(
                new CampfireListener(), this);

        getServer().getPluginManager().registerEvents(
                new ExplosionListener(), this);

        getServer().getPluginManager().registerEvents(
                new PlayerRespawnListener(), this);

        getLogger().info("EndCampfireRespawn Enabled");

    }

    @Override
    public void onDisable() {

        getLogger().info("Plugin Disabled");

    }

}
