package io.github.mertout.filemanager.files;

import io.github.mertout.filemanager.FileManager;
import io.github.mertout.utils.HexColor;
import org.jetbrains.annotations.NotNull;
import org.bukkit.configuration.file.YamlConfiguration;
import io.github.mertout.Claim;
import java.io.File;
import java.util.Objects;

import org.bukkit.configuration.file.FileConfiguration;

public class MessagesFile {
    public static FileConfiguration msgfc;
    public static File msgfile;

    public static void loadMessagesFiles() {
        MessagesFile.msgfile = new File(Claim.getInstance().getDataFolder(), "messages/messages-en_US.yml");
        if (!MessagesFile.msgfile.exists()) {
            FileManager.saveResource("messages/messages-en_US.yml");
        }
        MessagesFile.msgfile = new File(Claim.getInstance().getDataFolder(), "messages/messages-tr_TR.yml");
        if (!MessagesFile.msgfile.exists()) {
            FileManager.saveResource("messages/messages-tr_TR.yml");
        }
        MessagesFile.msgfile = new File(Claim.getInstance().getDataFolder(), "messages/messages-de_DE.yml");
        if (!MessagesFile.msgfile.exists()) {
            FileManager.saveResource("messages/messages-de_DE.yml");
        }
        selectLang();
    }

    @NotNull
    public static String convertString(@NotNull final String params1) {
        if (MessagesFile.msgfc.isSet(params1)) {
            return HexColor.hex(MessagesFile.msgfc.getString(params1)).replaceAll("&", "§").replace("{prefix}", HexColor.hex(MessagesFile.msgfc.getString("messages.prefix")).replaceAll("&", "§"));
        }
        Claim.getInstance().getLogger().warning("ERROR! " + params1 + " not found in Messages file!");
        return "";
    }

    public static void reloadMessages() {
        selectLang();
    }
    public static void selectLang() {
        File folder = new File(Claim.getInstance().getDataFolder(), "messages");
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.getName().contains(Claim.getInstance().getConfig().getString("settings.lang") + ".yml")) {
                MessagesFile.msgfile = new File(Claim.getInstance().getDataFolder(), "messages/" + file.getName());
                msgfc = YamlConfiguration.loadConfiguration(msgfile);
            }
        }
        //if (Claim.getInstance().getConfig().getString("settings.lang").equals("en_US"))
            //MessagesFile.msgfile = new File(Claim.getInstance().getDataFolder(), "messages/messages-en_US.yml");
        //else
            //MessagesFile.msgfile = new File(Claim.getInstance().getDataFolder(), "messages/messages-tr_TR.yml");
        //msgfc = YamlConfiguration.loadConfiguration(msgfile);
    }
}
