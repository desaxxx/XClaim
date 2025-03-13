package io.github.mertout.hooks;

import io.github.mertout.Claim;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault {

    private Economy economy;

    public void setupEconomy() {
        if (Claim.getInstance().getServer().getPluginManager().getPlugin("Vault") == null) {
            Claim.getInstance().getLogger().warning("Vault not found, Please install Vault!");
            return;
        }
        final RegisteredServiceProvider<Economy> rsp = Claim.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        economy = rsp.getProvider();
    }

    public Economy getEconomy() {
        return economy;
    }
}
