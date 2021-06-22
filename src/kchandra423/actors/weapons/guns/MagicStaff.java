package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.PiercingProjectile;
import kchandra423.utility.AssetLoader;

/**
 * Represents a magic staff that fires projectiles
 */
public class MagicStaff extends Gun {
    /**
     * Creates a new magic staff with the given stats
     */
    public MagicStaff() {
        super(AssetLoader.getImage(AssetLoader.Sprite.MAGIC_STAFF),
                0.3f, (float) Math.PI / 5,
                new PiercingProjectile(AssetLoader.getImage(AssetLoader.Sprite.FIREBALL),
                        7f, 0, true, new float[0],
                        DamageTypes.MAGIC, 30,5),
                1, 2.5f, 5);
    }
}
