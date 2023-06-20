package io.github.mertout.core.timer;

import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import io.github.mertout.Claim;
import org.bukkit.entity.Player;
import java.util.Map;
import java.util.HashMap;

public class CreateTimer implements Runnable {
    private static final Map<Player, Integer> task = new HashMap<>();
    private final int createCooldown;

    public CreateTimer() {
        Plugin plugin = Claim.getInstance();
        createCooldown = plugin.getConfig().getInt("settings.claim-create-cooldown");
        Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);
    }

    public void addTimer(final Player p) {
        task.put(p, createCooldown);
    }

    @Override
    public void run() {
        for (final Map.Entry<Player, Integer> entry : task.entrySet()) {
            final Player p = entry.getKey();
            final int time = entry.getValue() - 1;
            if (time <= 0) {
                task.remove(p);
            } else {
                task.put(p, time);
            }
        }
    }

    public Map<Player, Integer> getTask() {
        return task;
    }
}
