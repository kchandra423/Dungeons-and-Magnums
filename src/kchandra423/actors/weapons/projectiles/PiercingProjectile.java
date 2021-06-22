package kchandra423.actors.weapons.projectiles;

import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.movingActors.constants.ActorState;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

import java.util.ArrayList;

public class PiercingProjectile extends Projectile {
    private final ArrayList<MovingActor> hits;
    private final int pierce;

    /**
     * Creates a new Projectile with the specified image, and initial velocity and angle
     *
     * @param image  The specified image
     * @param v      The initial velocity of this projectile
     * @param angle  The initial angle of this projectile
     * @param ally   Whether or not this projectile belongs to an ally
     * @param stats
     * @param type
     * @param damage
     */
    public PiercingProjectile(KImage image, float v, float angle, boolean ally, float[] stats, DamageTypes type, int damage, int pierce) {
        super(image, v, angle, ally, stats, type, damage);
        hits = new ArrayList<>();
        this.pierce = pierce;
    }

    @Override
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
                    if (intersects(e) && e.getState() != ActorState.DEAD && !hits.contains(e)) {
                        e.interceptHitBox(new Damage(damage, stats, type));
                        hits.add(e);
                        if (hits.size() > pierce) {
                            active = false;
                            return;
                        }
                    }
                }

            } else {

                if (intersects(room.getPlayer()) && !hits.contains(room.getPlayer())) {
                    room.getPlayer().interceptHitBox(new Damage(damage, stats, type));
                    hits.add(room.getPlayer());
                }
            }
        }
    }
    @Override
    public Object clone(float angle, float[] stats) {
        return new PiercingProjectile((KImage) image.clone(), v, angle, ally, stats, type, damage, pierce);
    }
}
