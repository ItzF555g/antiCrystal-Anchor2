package me.georg.endcampfirerespawn.managers;

import me.georg.endcampfirerespawn.EndCampfireRespawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SpawnManager {

    private final EndCampfireRespawn plugin;

    private File file;
    private YamlConfiguration data;

    public SpawnManager(EndCampfireRespawn plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {

        file = new File(plugin.getDataFolder(), "spawns.yml");

        if (!file.exists()) {

            try {

                file.getParentFile().mkdirs();
                file.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        data = YamlConfiguration.loadConfiguration(file);

    }

    public void save() {

        try {

            data.save(file);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void setSpawn(UUID uuid, Location location) {

        String path = uuid.toString();

        data.set(path + ".world", location.getWorld().getName());
        data.set(path + ".x", location.getBlockX());
        data.set(path + ".y", location.getBlockY());
        data.set(path + ".z", location.getBlockZ());

        save();

    }

    public boolean hasSpawn(UUID uuid) {

        return data.contains(uuid.toString());

    }

    public void removeSpawn(UUID uuid) {

        data.set(uuid.toString(), null);

        save();

    }

    public Location getSpawn(UUID uuid) {

        if (!hasSpawn(uuid))
            return null;

        String path = uuid.toString();

        World world = Bukkit.getWorld(
                data.getString(path + ".world"));

        if (world == null)
            return null;

        return new Location(

                world,

                data.getInt(path + ".x") + 0.5,

                data.getInt(path + ".y"),

                data.getInt(path + ".z") + 0.5

        );

    }

}
