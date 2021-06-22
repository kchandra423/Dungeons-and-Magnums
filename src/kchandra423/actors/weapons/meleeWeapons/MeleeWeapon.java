package kchandra423.actors.weapons.meleeWeapons;

import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.enemies.RangedEnemy;
import kchandra423.actors.weapons.Weapon;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

import java.util.ArrayList;

class MeleeWeapon extends Weapon {
    protected ArrayList<MovingActor> hits = new ArrayList<>();
    protected final int damage;
    protected float[] stats;
    protected final float range;
    protected final float swingTime;
    protected long swingStartTime = System.currentTimeMillis();


    MeleeWeapon(KImage image, int damage, float range, float swingTime) {
        super(image);
        this.damage = damage;
        this.stats = new float[0];
        this.range = range;
        this.swingTime = swingTime;
    }

    @Override
    public void act(DrawingSurface d, Room room) {
        //if you cannot fire, it means that you are firing
        float differential = (float) (((System.currentTimeMillis() - swingStartTime) / (swingTime * 1000)) * Math.PI / 2 * 3 + Math.PI);

        if (!canFire()) {
            image.rotate((float) (range * Math.cos(differential)));
            for (Enemy e :
                    room.getEnemies()) {
                if (intersects(e) && !hits.contains(e)) {
                    hits.add(e);
                    e.interceptHitBox(new Damage(damage, stats, DamageTypes.MELEE));
                }
                if (e instanceof RangedEnemy) {
                    for (Projectile p :
                            ((RangedEnemy) e).getProjectiles()) {
                        if (intersects(p)) {
                            p.setActive(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void fire(float[] stats) {
        if (canFire()) {
            hits.clear();
            this.stats = stats;
            swingStartTime = System.currentTimeMillis();
        }
    }

    @Override
    public void reload() {

    }

    @Override
    public int getMagazine() {
        return 1;
    }

    @Override
    public int getMagazineSize() {
        return 1;
    }

    @Override
    public float getTimeSinceReloaded() {
        return Float.NaN;
    }

    @Override
    public float getReloadTime() {
        return Float.NaN;
    }

    protected boolean canFire() {
        return (System.currentTimeMillis() - swingStartTime) >= swingTime * 1000;
    }
}
