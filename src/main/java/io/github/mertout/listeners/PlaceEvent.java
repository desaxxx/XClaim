package io.github.mertout.listeners;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.event.EventHandler;
import org.bukkit.Bukkit;
import io.github.mertout.api.events.ClaimCreateEvent;
import io.github.mertout.filemanager.files.MessagesFile;
import io.github.mertout.Claim;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import io.github.mertout.core.ClaimManager;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceEvent extends ClaimManager implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) {
        if (super.hasClaimAtLocation(e.getBlock().getLocation(), e.getPlayer())) {
            if (e.getPlayer().hasPermission("xclaim.bypass") || e.getPlayer().isOp()) {
                e.getPlayer().sendMessage(MessagesFile.convertString("messages.admin.player-bypass-mode"));
            }
            e.setCancelled(true);
            return;
        }
        final boolean[] claimblock = new boolean[1];
        NBT.get(e.getItemInHand(), nbt -> {
            claimblock[0] = nbt.getBoolean("claimblock");
        });
        if (claimblock[0]) {
            if (super.getChunkClaim(e.getPlayer().getLocation()) != null) {
                e.setCancelled(true);
                return;
            }
            for (final String str : Claim.getInstance().getConfig().getStringList("settings.disabled-worlds")) {
                if (e.getPlayer().getWorld().getName().equals(str)) {
                    e.getPlayer().sendMessage(MessagesFile.convertString("messages.disabled-worlds"));
                    e.setCancelled(true);
                    return;
                }
            }
            final ClaimCreateEvent event = new ClaimCreateEvent(e.getPlayer());
            Bukkit.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                super.registerClaim(e.getBlockPlaced().getLocation(), e.getPlayer());
                e.getPlayer().sendMessage(MessagesFile.convertString("messages.created-claims"));
            }
            else {
                e.setCancelled(true);
            }
        }
    }
}
