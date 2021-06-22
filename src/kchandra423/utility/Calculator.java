package kchandra423.utility;

import kchandra423.actors.Damage;

/**
 * A basic utility to do basic calculations. Currently only calculates angles between points
 *
 * @author Kumar Chandra
 */
public class Calculator {
    /**
     * Calculates the angle from one point to another. All values are given in the format of the math class' atan2 method
     *
     * @param x1 The x coordinate of the starting point
     * @param y1 The y coordinate of the starting point
     * @param x2 The x coordinate of the ending point
     * @param y2 The y coordinate of the ending point
     * @return An angle in radians between -pi and pi
     * @see Math
     */
    public static double calculateAngle(double x1, double y1, double x2, double y2) {
        return Math.atan2(y2 - y1, x2 - x1);
    }

    /**
     * Calculates the damage that a damage would do on some actor, with the specified stats. Damage is calculated by using the baseDamage
     *  and multiplying by the attack value for the given attack type, divided the defense value for the given attack type. For example, if you attack with a base damage of 40, a
     *  damage type of MAGIC, and the damage has an magic attack stat of 60, and the defender has a magic defense stat of 40,  the defender would take 60 points of damage.
     *
     * @param damage The specified damage
     * @param defenseStats All the stats off the defending actor
     * @return Returns the calculated damage to be dealt to the defender.
     */
    public static float calculateDamage(Damage damage, float[] defenseStats) {
        float base = damage.getAmount();
        // Formula for calculation
        // damage  = base * (attack/defense)
        float[] stats = damage.getStats();
        return base * (stats[damage.getType().getValue()] / defenseStats[damage.getType().getValue()+3]);


    }
}
