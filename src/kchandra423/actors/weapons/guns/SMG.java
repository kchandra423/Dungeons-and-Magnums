package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.utility.AssetLoader;

/**
 * Represents a gun that shoots projectiles
 */
public class SMG extends Gun {
    /**
     * Creates a new SMG with the given stats
     */
    public SMG() {
        super(AssetLoader.getImage(AssetLoader.Sprite.SMG), 0.1f,
                (float) Math.PI / 8,
                new Projectile(AssetLoader.getImage(AssetLoader.Sprite.BULLET),
                        20f, 0, true, new float[0],
                        DamageTypes.RANGED, 5), 1,
                1f, 30);
    }
}
