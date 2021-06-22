package kchandra423.actors.weapons.projectiles;

import kchandra423.actors.Actor;
import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.constants.ActorState;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

import java.util.ArrayList;

/**
 * Represents any sort of projectile that moves in a specified direction every frame
 *
 * @author Kumar Chandra
 * @see kchandra423.actors.Actor
 */
public class Projectile extends Actor {
    protected final boolean ally;
    protected final float v;
    protected final float[] stats;
    protected final DamageTypes type;
    protected final int damage;


    /**
     * Creates a new Projectile with the specified image, and initial velocity and angle
     *
     * @param image The specified image
     * @param v     The initial velocity of this projectile
     * @param angle The initial angle of this projectile
     * @param ally  Whether or not this projectile belongs to an ally
     */
    public Projectile(KImage image, float v, float angle, boolean ally, float[] stats, DamageTypes type, int damage) {
        super(image);
        this.ally = ally;
        image.setAngle(angle);

        this.v = v * (60f / DrawingSurface.getGoalFrameRate());
        this.stats = stats;
        this.damage = damage;
        this.type = type;
    }

    /**
     * Moves this projectile and does collision detection and handling
     *
     * @param d    The drawing surface to be acted upon
     * @param room The room the actor is currently in
     */
    public void act(DrawingSurface d, Room room) {
        if (active) {
            image.translate((float) (v * Math.cos(image.getAngle())), (float) (v * Math.sin(image.getAngle())));
            if (!room.inBounds(image)) {
                active = false;
                return;
            }
            ArrayList<Obstacle> obstacles = room.getObstacles();
            for (Obstacle o : obstacles) {
                if (intersects(o)) {
                    active = false;
                    return;
                }
            }
            if (ally) {
                ArrayList<Enemy> enemies = room.getEnemies();
                for (Enemy e : enemies) {
                    if (intersects(e) && e.getState() != ActorState.DEAD) {
                        active = false;
                        e.interceptHitBox(new Damage(damage, stats, type));
                        return;
                    }
                }

            } else {

                if (intersects(room.getPlayer())) {
                    active = false;
                    room.getPlayer().interceptHitBox(new Damage(damage, stats, type));

                }
            }
        }
    }

    /**
     * Clones this projectile with all the correct attributes in the speciifed direction
     *
     * @param angle The specified direction in radians
     * @return A projectile with all the same attributes except angle.
     */
    public Object clone(float angle, float[] stats) {
        return new Projectile((KImage) image.clone(), v, angle, ally, stats, type, damage);
    }


}
