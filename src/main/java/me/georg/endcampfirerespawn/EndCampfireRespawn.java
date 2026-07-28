package me.georg.endcampfirerespawn;

import me.georg.endcampfirerespawn.listeners.CampfireListener;
import me.georg.endcampfirerespawn.listeners.ExplosionListener;
import org.bukkit.plugin.java.JavaPlugin;

public class EndCampfireRespawn extends JavaPlugin {

    private static EndCampfireRespawn instance;

    public static EndCampfireRespawn getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(
                new CampfireListener(), this);

        getServer().getPluginManager().registerEvents(
                new ExplosionListener(), this);

        getLogger().info("EndCampfireRespawn enabled.");

    }

    @Override
    public void onDisable() {

        getLogger().info("EndCampfireRespawn disabled.");

    }

}
