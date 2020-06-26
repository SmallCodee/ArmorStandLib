package it.smallcode.armorstandlib.rotate;
/*

Class created by SmallCode
26.06.2020 16:10

*/

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;

public class RotateArmorStand {

    private int schedulerID;

    private int millis = 0;
    private int durationCounter;

    public RotateArmorStand(final ArmorStand armorStand, final float speed, final int delay, final int duration, Plugin plugin){

        this.durationCounter = duration;

        if(this.durationCounter == 0)
            this.durationCounter = -1;

        schedulerID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
            public void run() {

                millis++;

                Location newLoc = armorStand.getLocation();

                newLoc.setYaw(newLoc.getYaw() + speed);

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

    public RotateArmorStand(final ArmorStand armorStand, final float speed, int delay, Plugin plugin){

        this(armorStand, speed, delay, 0, plugin);

    }

    public RotateArmorStand(final ArmorStand armorStand, final float speed, Plugin plugin, int duration){

        this(armorStand, speed, 0, duration, plugin);

    }

    public RotateArmorStand(final ArmorStand armorStand, final float speed, Plugin plugin) {

        this(armorStand, speed, 0, 0, plugin);

    }

    public void cancel(){

        Bukkit.getScheduler().cancelTask(schedulerID);

    }

}
