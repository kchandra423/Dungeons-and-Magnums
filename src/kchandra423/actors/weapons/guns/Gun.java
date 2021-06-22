package kchandra423.actors.weapons.guns;

import kchandra423.actors.weapons.Weapon;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a basic gun. Currently only fires and has spread.
 *
 * @author Kumar Chandra
 */
class Gun extends Weapon {
    private final Projectile projectile;
    private final ArrayList<Projectile> projectiles;
    private final float fireRate;
    private final float reloadTime;///1000ths
    private int magazine;
    private final int pellets;
    private final int magazineSize;
    private boolean reloading;
    private TimerTask task;
    private static Timer t;
    private long lastTimeShot;
    private final float spread;
    private long lastTimeReloaded;

    Gun(KImage sprite, float fireRate, float spread, Projectile projectile, int pellets, float reloadTime, int magazineSize) {
        super(sprite);
        this.projectile = projectile;
        this.fireRate = fireRate;
        this.pellets = pellets;
        this.reloadTime = reloadTime;
        this.magazineSize = magazineSize;
        magazine = this.magazineSize;
        t = new Timer();
        this.spread = spread;
        lastTimeReloaded = System.currentTimeMillis();
        projectiles = new ArrayList<>();
        reloading = false;
    }

    /**
     * Makes all the projectiles act. Removes inactive projectiles from this guns list of projectiles
     *
     * @param d The drawing surface to be acted upon
     * @param r The room the actor is currently in
     */
    @Override
    public void act(DrawingSurface d, Room r) {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.act(d, r);
            if (!p.isActive()) {
                projectiles.remove(i);
                i--;
            }

        }
    }

    /**
     * Draws this actor, as well as all all of its projectiles
     *
     * @param d The drawing surface to be drawn onto
     */
    @Override
    public void draw(DrawingSurface d) {

        super.draw(d);

        for (Projectile p :
                projectiles) {
            p.draw(d);
        }
    }

    /**
     * Creates a new projectile using this images current angle. Applies all effects such as the spread, and velocity and pellets
     */
    public void fire(float[] stats) {

        if (canFire()) {
            if (reloading) {
                reloading = false;
                task.cancel();
                t.purge();
            }
            if(reloadTime!=0) {
                magazine--;
            }
            for (int i = 0; i < pellets; i++) {
                float tempAngle = image.getAngle();
                tempAngle += Math.random() * spread;
                tempAngle -= spread / 2;
                Projectile p = (Projectile) projectile.clone(tempAngle,stats);

                p.getImage().moveTo((float) (image.getBounds().getCenterX() + image.getTexture().getWidth() / 2 * Math.cos(image.getAngle())), (float) (image.getBounds().getCenterY() + image.getTexture().getHeight() / 2 * Math.sin(image.getAngle())));
                projectiles.add(p);
            }
            lastTimeShot = System.currentTimeMillis();
        } else if (magazine <= 0) {
            reload();
        }
    }

    private boolean canFire() {
        return System.currentTimeMillis() - lastTimeShot >= fireRate * 1000 && magazine > 0;
    }

    public void reload() {
        if (magazine < magazineSize && !reloading) {
            lastTimeReloaded = System.currentTimeMillis();
            reloading = true;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        if (reloading) {
                            magazine = magazineSize;
                            reloading = false;
                        }
                    }

                };
                t.schedule(task, (long) (reloadTime * 1000));

        }
    }

    public int getMagazine() {
        return magazine;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public float getTimeSinceReloaded() {
        if (reloading) {
            return (System.currentTimeMillis() - lastTimeReloaded) / 1000f;
        }
        return Float.NaN;
    }

    public float getReloadTime() {
        return reloadTime;
    }

}
