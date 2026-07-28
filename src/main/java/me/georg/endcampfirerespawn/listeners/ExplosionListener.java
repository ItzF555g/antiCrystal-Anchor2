package me.georg.endcampfirerespawn.listeners;

import me.georg.endcampfirerespawn.EndCampfireRespawn;
import org.bukkit.Material;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    @EventHandler
    public void onCrystalExplosion(EntityExplodeEvent event) {

        if (event.getEntity() instanceof EnderCrystal &&
                EndCampfireRespawn.getInstance()
                        .getConfig()
                        .getBoolean("explosion-protection.end-crystals")) {

            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onAnchorExplosion(BlockExplodeEvent event) {

        if (!EndCampfireRespawn.getInstance()
                .getConfig()
                .getBoolean("explosion-protection.respawn-anchors"))
            return;

        if (event.getBlock().getType() == Material.RESPAWN_ANCHOR) {

            event.setCancelled(true);

        }

    }

}
