package io.github.mertout.listeners.api;

import io.github.mertout.Claim;
import io.github.mertout.api.events.ClaimBlockMoveEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockMoveEvent implements Listener {

    @EventHandler
    public void on(ClaimBlockMoveEvent e) {
        if (e.isCancelled() || !Claim.getInstance().getConfig().getBoolean("settings.sounds.MOVE-BLOCK.enabled")) {
            return;
        }
        Player p = e.getPlayer();
        String sound = Claim.getInstance().getConfig().getString("settings.sounds.MOVE-BLOCK.type").toUpperCase();
        float volume = (float) Claim.getInstance().getConfig().getDouble("settings.sounds.MOVE-BLOCK.volume");
        float pitch = (float) Claim.getInstance().getConfig().getDouble("settings.sounds.MOVE-BLOCK.pitch");
        p.playSound(p.getLocation(), Sound.valueOf(sound), volume, pitch);
    }
}
