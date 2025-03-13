package io.github.mertout.listeners;

import de.tr7zw.changeme.nbtapi.NBT;
import io.github.mertout.Claim;
import io.github.mertout.filemanager.files.MenusFile;
import io.github.mertout.gui.GuiType;
import io.github.mertout.gui.backend.MenuClick;
import io.github.mertout.utils.ClaimUtils;
import io.github.mertout.utils.HexColor;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import io.github.mertout.core.ClaimManager;


public class ClickEvent extends ClaimManager implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (p.getOpenInventory().getTitle().equals(HexColor.hex(MenusFile.get(GuiType.CLAIMMANAGEMENT).getString("gui.title")))) {
            e.setCancelled(true);
            p.getOpenInventory().close();
            new MenuClick(p, e.getSlot(), GuiType.CLAIMMANAGEMENT);

        }
            if (p.getOpenInventory().getTitle().contains(HexColor.hex(MenusFile.get(GuiType.MEMBERS).getString("gui.title").replace("{page}", "")))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                final String[] page = new String[1];
                final String[] owner = new String[1];
                NBT.get(e.getCurrentItem(), nbt -> {
                    page[0] = nbt.getString("page");
                    owner[0] = nbt.getString("owner");
                });
                if (page[0].equals("next")) {
                    Claim.getInstance().getMemberManager().openMemberPage(p, (Claim.getInstance().getMemberManager().findPage(e.getInventory()) + 1), Claim.getInstance().getMemberManager().calculateSkull(e.getInventory()));
                }
                else if (page[0].equals("previous")) {
                    if (Claim.getInstance().getMemberManager().findPage(e.getInventory()) > 1) {
                        Claim.getInstance().getMemberManager().openMemberPage(p, (Claim.getInstance().getMemberManager().findPage(e.getInventory()) - 1), 0);
                    }
                }
                else if (owner[0] != null) {
                    ClaimUtils.memberKick(p, owner[0], Claim.getInstance().getClaimManager().getChunkClaim(p.getLocation()));
                }
            }
        }
    }
}
