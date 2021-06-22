package kchandra423.actors.weapons.meleeWeapons;

import kchandra423.utility.AssetLoader;

public class RustySword extends MeleeWeapon{
    /**
     * Creates an actor with the specified image
     */
    public RustySword() {
        super(AssetLoader.getImage(AssetLoader.Sprite.RUSTY_SWORD), 20, (float) (Math.PI/3),0.7f);
    }
}
