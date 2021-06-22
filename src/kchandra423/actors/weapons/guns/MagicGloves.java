package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.PiercingProjectile;
import kchandra423.utility.AssetLoader;

public class MagicGloves extends Gun {
    public MagicGloves() {
        super(AssetLoader.getImage(AssetLoader.Sprite.MAGIC_GLOVES),
                0.5f, (float) Math.PI / 5,
                new PiercingProjectile(AssetLoader.getImage(AssetLoader.Sprite.LIGHTNING),
                        10f, 0, true, new float[0],
                        DamageTypes.MAGIC, 7, 1),
                4, 2f, 10);
    }
}
