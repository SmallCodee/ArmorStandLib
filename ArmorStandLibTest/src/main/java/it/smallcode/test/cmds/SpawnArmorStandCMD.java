package it.smallcode.test.cmds;
/*

Class created by SmallCode
26.06.2020 13:28

*/

import it.smallcode.armorstandlib.move.MoveArmorStand;
import it.smallcode.armorstandlib.rotate.RotateArmorStand;
import it.smallcode.test.ArmorStandTest;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpawnArmorStandCMD implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {

        if(s instanceof Player) {

            Player p = (Player) s;

            ArmorStand armorStand = (ArmorStand) p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);

            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setInvulnerable(true);
            armorStand.setCollidable(false);

            armorStand.setHelmet(new ItemStack(Material.CREEPER_HEAD));

            new RotateArmorStand(armorStand, 10f, ArmorStandTest.getArmorStands());

        }

        return false;

    }
}
