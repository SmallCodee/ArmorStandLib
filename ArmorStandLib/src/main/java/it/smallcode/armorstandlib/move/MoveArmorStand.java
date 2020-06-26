package it.smallcode.armorstandlib.move;
/*

Class created by SmallCode
26.06.2020 15:27

*/

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;

public class MoveArmorStand {

    private int schedulerID;

    private int millis = 0;
    private int durationCounter;

    public MoveArmorStand(final ArmorStand armorStand, final double speedX, final double speedY, final double speedZ, final int delay, final int duration, Plugin plugin){

        this.durationCounter = duration;

        if(this.durationCounter == 0)
            this.durationCounter = -1;

        schedulerID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
            public void run() {

                millis++;

                Location newLoc = armorStand.getLocation();

                newLoc.setX(newLoc.getX() + speedX);
                newLoc.setY(newLoc.getY() + speedY);
                newLoc.setZ(newLoc.getZ() + speedZ);

                armorStand.teleport(newLoc);

                if(millis == 20){

                    if(durationCounter > 0)
                        durationCounter--;

                    if(durationCounter == 0){

                        cancel();

                    }

                    millis = 0;

                }

            }
        }, delay, 0);

    }

    public MoveArmorStand(final ArmorStand armorStand, final double speed, int delay, int duration, Plugin plugin) {

        this(armorStand, speed, speed, speed, delay, duration, plugin);

    }

    public MoveArmorStand(final ArmorStand armorStand, final double speedX, final double speedY, final double speedZ, int delay, Plugin plugin){

        this(armorStand, speedX, speedY, speedZ, delay, 0, plugin);

    }

    public MoveArmorStand(final ArmorStand armorStand, final double speed, int delay, Plugin plugin){

        this(armorStand, speed, speed, speed, delay, plugin);

    }

    public MoveArmorStand(final ArmorStand armorStand, final double speedX, final double speedY, final double speedZ, Plugin plugin, int duration){

        this(armorStand, speedX, speedY, speedZ, 0, duration, plugin);

    }

    public MoveArmorStand(final ArmorStand armorStand, final double speed, Plugin plugin, int duration){

        this(armorStand, speed, speed, speed, plugin, duration);

    }

    public MoveArmorStand(final ArmorStand armorStand, final double speedX, final double speedY, final double speedZ, Plugin plugin) {

        this(armorStand, speedX, speedY, speedZ, 0, 0, plugin);

    }


    public MoveArmorStand(final ArmorStand armorStand, final double speed, Plugin plugin) {

        this(armorStand, speed, speed, speed, plugin);

    }

    public void cancel(){

        Bukkit.getScheduler().cancelTask(schedulerID);

    }

}
