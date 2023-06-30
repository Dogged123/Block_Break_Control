package me.isaacfediw.blockbreakcontroltutorial;

import org.bukkit.plugin.java.JavaPlugin;

public final class BlockBreakControlTutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new blockBreakPrevention(), this);
    }
}
