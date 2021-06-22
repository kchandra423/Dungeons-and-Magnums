package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.PiercingProjectile;
import kchandra423.utility.AssetLoader;

/**
 * Represents a shotgun that shoots multiple projectiles
 */
public class Shotgun extends Gun {
    /**
     * Creates a new shotgun with the given stats
     */
    public Shotgun() {
        super(AssetLoader.getImage(AssetLoader.Sprite.SHOTGUN), 0.75f,
                (float) Math.PI / 6,
                new PiercingProjectile(AssetLoader.getImage(AssetLoader.Sprite.BULLET),
                        20f, 0, true, new float[0],
                        DamageTypes.MAGIC, 7,2),
                6, 0.5f, 2);
    }
}
