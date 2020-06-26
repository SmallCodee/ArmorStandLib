package it.smallcode.armorstandlib.move;
/*

Class created by SmallCode
26.06.2020 17:01

*/

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.plugin.Plugin;

public class HoverArmorStand {

    private int schedulerID;

    private int millis = 0;
    private int durationCounter;

    private double vel;

    public HoverArmorStand(final ArmorStand armorStand, final double speed, final int delay, final int duration, double maxHeightCap, double minHeightCap, Plugin plugin){

        this.durationCounter = duration;

        if(this.durationCounter == 0)
            this.durationCounter = -1;

        final double maxHeight = armorStand.getLocation().getY() + maxHeightCap;
        final double minHeight = armorStand.getLocation().getY() + minHeightCap;

        schedulerID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
            public void run() {

                millis++;

                if(armorStand.getLocation().getY() >= maxHeight)
                    vel = -speed;

                if(armorStand.getLocation().getY() <= minHeight)
                    vel = speed;

                Location newLoc = armorStand.getLocation();

                newLoc.setY(newLoc.getY() + vel);

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

    public void cancel(){

        Bukkit.getScheduler().cancelTask(schedulerID);

    }

}
