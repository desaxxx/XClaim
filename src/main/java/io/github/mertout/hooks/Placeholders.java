package io.github.mertout.hooks;

import io.github.mertout.Claim;
import io.github.mertout.core.ClaimManager;
import io.github.mertout.core.data.DataHandler;
import io.github.mertout.filemanager.files.MessagesFile;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholders extends PlaceholderExpansion
{
    @NotNull
    public String getAuthor() {
        return "mert.out";
    }

    @NotNull
    public String getIdentifier() {
        return "xclaim";
    }

    @NotNull
    public String getVersion() {
        return "1.5.2";
    }

    public boolean persist() {
        return true;
    }

    public String onPlaceholderRequest(@NotNull final Player p, @NotNull final String identifier) {
        ClaimManager claimManager = Claim.getInstance().getClaimManager();
        DataHandler chunk = claimManager.getChunkClaim(p.getLocation());
        return switch (identifier) {
            case "owner" -> {
                if (chunk != null) {
                    yield chunk.getOwner();
                }
                yield "";
            }
            case "team_size" -> {
                if (chunk != null) {
                    yield chunk.getMembers().size() + "";
                }
                yield "";
            }
            case "remaining_time" -> {
                if (chunk != null) {
                    yield ClaimManager.calcTime(chunk);
                }
                yield "";
            }
            case "status" -> {
                if (chunk != null) {
                    yield MessagesFile.convertString("messages.ownership.taken");
                }
                yield MessagesFile.convertString("messages.ownership.untaken");
            }
            default -> null;
        };
    }
}
