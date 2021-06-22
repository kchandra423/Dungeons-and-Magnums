package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.PiercingProjectile;
import kchandra423.utility.AssetLoader;

public class Pistol extends Gun{
    /**
     *
     */
    public Pistol() {
        super(AssetLoader.getImage(AssetLoader.Sprite.PISTOL), 0.2f,
                (float) Math.PI / 15,
                new PiercingProjectile(AssetLoader.getImage(AssetLoader.Sprite.BULLET),
                        20f, 0, true, new float[0],
                        DamageTypes.RANGED, 30,1),
                1, 3f, 5);
    }
}
