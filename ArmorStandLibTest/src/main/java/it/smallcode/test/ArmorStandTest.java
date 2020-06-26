package it.smallcode.test;
/*

Class created by SmallCode
26.06.2020 15:25

*/

import it.smallcode.test.cmds.SpawnArmorStandCMD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorStandTest extends JavaPlugin {

    private static ArmorStandTest armorStands;

    @Override
    public void onEnable() {

        armorStands = this;

        Bukkit.getPluginCommand("spawnArmorStand").setExecutor(new SpawnArmorStandCMD());

    }

    @Override
    public void onDisable() {

    }

    public static ArmorStandTest getArmorStands() {
        return armorStands;
    }

}
