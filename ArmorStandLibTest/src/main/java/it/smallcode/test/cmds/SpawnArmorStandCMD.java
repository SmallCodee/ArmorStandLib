package it.smallcode.test.cmds;
/*

Class created by SmallCode
26.06.2020 13:28

*/

import it.smallcode.armorstandlib.move.HoverArmorStand;
import it.smallcode.armorstandlib.rotate.RotateArmorStand;
import it.smallcode.test.ArmorStandTest;
import it.smallcode.test.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SpawnArmorStandCMD implements CommandExecutor {

    private int schedulerID;

    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {

        if(s instanceof Player) {

            Player p = (Player) s;

            final ArmorStand armorStand = (ArmorStand) p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);

            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setInvulnerable(true);
            armorStand.setCollidable(false);

            armorStand.setHelmet(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0="));

            /*

            /give @p minecraft:player_head{display:{Name:"{\"text\":\"Neutron Orb\"}"},SkullOwner:{Id:[I;279067190,1329022967,-1567622093,233045850],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0="}]}}} 1

             */

            new RotateArmorStand(armorStand, 10, ArmorStandTest.getArmorStands());

            new HoverArmorStand(armorStand, 0.05D, 0, 0, 1.5D, 1, ArmorStandTest.getArmorStands());

            schedulerID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(ArmorStandTest.getArmorStands(), new Runnable() {
                public void run() {

                    if(armorStand == null || armorStand.isDead())
                        Bukkit.getScheduler().cancelTask(schedulerID);

                    Location loc = armorStand.getLocation().clone();

                    loc.setY(loc.getY() + 1.5D);

                    armorStand.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 5);

                    for(int i = 0; i < 3; i++){

                        Location newLoc = loc.clone();

                        double x = (Math.random() * 1) -0.5;
                        double y = (Math.random() * 1) -0.5;
                        double z = (Math.random() * 1) -0.5;

                        newLoc.setX(newLoc.getX() + x);
                        newLoc.setY(newLoc.getY() + y);
                        newLoc.setZ(newLoc.getZ() + z);

                        armorStand.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, newLoc, 1);

                    }

                }
            },0, 0);

        }

        return false;

    }

}
