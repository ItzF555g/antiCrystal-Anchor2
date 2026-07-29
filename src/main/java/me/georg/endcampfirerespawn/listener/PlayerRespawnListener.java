package me.georg.endcampfirerespawn.listeners;

import me.georg.endcampfirerespawn.EndCampfireRespawn;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        Location saved = EndCampfireRespawn.getInstance()
                .getSpawnManager()
                .getSpawn(player.getUniqueId());

        if (saved == null)
            return;

        if (saved.getWorld() == null)
            return;

        if (saved.getWorld().getEnvironment() != World.Environment.THE_END)
            return;

        Block campfire = saved.getBlock();

        if (campfire.getType() != Material.CAMPFIRE &&
                campfire.getType() != Material.SOUL_CAMPFIRE) {

            player.sendMessage("§cYour End campfire no longer exists.");

            EndCampfireRespawn.getInstance()
                    .getSpawnManager()
                    .removeSpawn(player.getUniqueId());

            return;
        }

        Location safe = findSafeLocation(saved);

        if (safe != null) {
            event.setRespawnLocation(safe);
        }

    }

    private Location findSafeLocation(Location center) {

        World world = center.getWorld();

        int[][] offsets = {

                {0,0},

                {1,0},
                {-1,0},
                {0,1},
                {0,-1},

                {1,1},
                {-1,1},
                {1,-1},
                {-1,-1},

                {2,0},
                {-2,0},
                {0,2},
                {0,-2}

        };

        for (int[] offset : offsets) {

            int x = center.getBlockX() + offset[0];
            int z = center.getBlockZ() + offset[1];

            int y = center.getBlockY() + 1;

            Block feet = world.getBlockAt(x,y,z);
            Block head = world.getBlockAt(x,y+1,z);
            Block floor = world.getBlockAt(x,y-1,z);

            if (!floor.getType().isSolid())
                continue;

            if (!feet.isPassable())
                continue;

            if (!head.isPassable())
                continue;

            return new Location(
                    world,
                    x + 0.5,
                    y,
                    z + 0.5
            );

        }

        return center.clone().add(.5,1,.5);

    }

}
