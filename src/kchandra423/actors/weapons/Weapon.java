package kchandra423.actors.weapons;

import kchandra423.actors.Actor;
import kchandra423.graphics.textures.KImage;

import java.util.TimerTask;

/**
 * Weapons used by the player to defeat actors
 * @author Kumar Chandra
 */
public abstract class Weapon extends Actor {
    /**
     * Creates an actor with the specified image
     *
     * @param image The specified image
     */
    public Weapon(KImage image) {
        super(image);
    }

    /**
     * Fires this weapon if it can
     */
    public abstract void fire(float[] stats);

    public abstract void reload();

    public abstract int getMagazine();

    public abstract int getMagazineSize();

    public abstract float getTimeSinceReloaded();

    public abstract float getReloadTime();
}
