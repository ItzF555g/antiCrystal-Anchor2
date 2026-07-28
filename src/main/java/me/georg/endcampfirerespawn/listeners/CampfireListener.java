package me.georg.endcampfirerespawn.listeners;

import me.georg.endcampfirerespawn.EndCampfireRespawn;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CampfireListener implements Listener {

    @EventHandler
    public void onCampfireClick(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        Block block = event.getClickedBlock();

        if (block == null)
            return;

        Material type = block.getType();

        if (type != Material.CAMPFIRE &&
                type != Material.SOUL_CAMPFIRE)
            return;

        Player player = event.getPlayer();

        if (player.getWorld().getEnvironment() != World.Environment.THE_END)
            return;

        EndCampfireRespawn.getInstance()
                .getSpawnManager()
                .setSpawn(player.getUniqueId(), block.getLocation());

        player.sendMessage("§5[EndSpawn] §aRespawn point set.");

        if (EndCampfireRespawn.getInstance().getConfig()
                .getBoolean("campfire.sounds")) {

            player.playSound(
                    player.getLocation(),
                    Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN,
                    1F,
                    1F);

        }

        if (EndCampfireRespawn.getInstance().getConfig()
                .getBoolean("campfire.particles")) {

            player.getWorld().spawnParticle(

                    Particle.PORTAL,

                    block.getLocation().add(.5,1,.5),

                    80,

                    .4,

                    .4,

                    .4,

                    0

            );

        }

    }

}
