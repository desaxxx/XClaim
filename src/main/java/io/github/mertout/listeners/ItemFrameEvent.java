package io.github.mertout.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.Listener;
import io.github.mertout.core.ClaimManager;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ItemFrameEvent extends ClaimManager implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onItemFrame(final HangingBreakByEntityEvent e) {
        if (super.getChunkClaim(e.getEntity().getLocation()) != null && super.hasClaimAtLocation(e.getEntity().getLocation(), (Player)e.getRemover())) { e.setCancelled(true); }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onItemFrame2(final HangingPlaceEvent e) {
        if (super.getChunkClaim(e.getEntity().getLocation()) != null && super.hasClaimAtLocation(e.getEntity().getLocation(), e.getPlayer())) { e.setCancelled(true); }
    }
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onItemFrame3(final PlayerInteractEntityEvent e) {
        if (super.getChunkClaim(e.getRightClicked().getLocation()) != null && super.hasClaimAtLocation(e.getRightClicked().getLocation(), e.getPlayer())) { e.setCancelled(true); }
    }
}
