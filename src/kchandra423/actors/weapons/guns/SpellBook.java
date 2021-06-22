package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.PiercingProjectile;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.utility.AssetLoader;

public class SpellBook extends Gun{
    /**
     * Creates a new shotgun with the given stats
     */
    public SpellBook() {
        super(AssetLoader.getImage(AssetLoader.Sprite.SPELLBOOK), 0.25f,
                (float) Math.PI / 10,
                new Projectile(AssetLoader.getImage(AssetLoader.Sprite.MAGIC_BALL),
                        20f, 0, true, new float[0],
                        DamageTypes.MAGIC, 10),
                1, 0, 1);
    }
}
