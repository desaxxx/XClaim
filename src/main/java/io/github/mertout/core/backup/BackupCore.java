package io.github.mertout.core.backup;

import io.github.mertout.Claim;
import io.github.mertout.core.data.DataHandler;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BackupCore {

    public BackupCore() {
        File dataFolder = new File(Claim.getInstance().getDataFolder() + "/backups");
        if (!dataFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dataFolder.mkdirs();
        }
    }
    public void backupData() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timestamp = dateFormat.format(new Date());

        File dataFolder = new File(Claim.getInstance().getDataFolder() + "/backups");
        File claimsFile = new File(dataFolder, "data." + timestamp + ".yml");
        if (!claimsFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            claimsFile.createNewFile();
        }
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(claimsFile);
        long start = System.currentTimeMillis();
        for (DataHandler data : Claim.getInstance().getClaims()) {
            cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".block-location", data.getBlockLocation());
            cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".owner", data.getOwner());
            cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".day", data.getDays());
            cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".hour", data.getHours());
            cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".minutes", data.getMinutes());
            cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".seconds", data.getSeconds());
            cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".creation-date", data.getCreationDate());
            if (data.getMembers() != null) {
                List<String> list = cfg.getStringList("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".members");
                list.addAll(data.getMembers());
                cfg.set("claims." + data.getBlockLocation().getWorld().getName() + "." + data.getChunk() + ".members", list);
            }
            cfg.save(claimsFile);
        }
        Claim.getInstance().getLogger().info("Backed All Data! " + (System.currentTimeMillis() - start) + "ms");
    }
}
