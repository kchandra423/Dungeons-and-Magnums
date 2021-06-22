package kchandra423.actors.weapons.meleeWeapons;

import kchandra423.utility.AssetLoader;

public class BroadSword extends MeleeWeapon{
    /**
     * Creates an actor with the specified image
     */
    public BroadSword() {
        super(AssetLoader.getImage(AssetLoader.Sprite.SWORD), 30,
                (float) (Math.PI/4),0.5f);
    }
}
