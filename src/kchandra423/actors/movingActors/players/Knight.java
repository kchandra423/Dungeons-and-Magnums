package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.weapons.Weapon;
import kchandra423.actors.weapons.meleeWeapons.Axe;
import kchandra423.actors.weapons.meleeWeapons.BroadSword;
import kchandra423.actors.weapons.meleeWeapons.RustySword;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.PlayerData;

/**
 * Represents a Knight
 * @author Kumar Chandra
 */
public class Knight extends Player {
    private static final float[] stats =
            MovingActor.createStates(0.8f, 0.5f, 2f, 2f, 1f, 3f);

    /**
     * Creates a knight at the specified coordinates
     * @param x The x coord
     * @param y The y coord
     */
    public Knight(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.KNIGHT_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.KNIGHT_IDLE),
                7, 0.7f, stats,
                400,
                getInitialWeapon()
        );
        image.moveTo(x, y);
    }
    private static Weapon getInitialWeapon(){
        if(PlayerData.getInitialKnightWeapon().equals("Broadsword")){
            return new BroadSword();
        }else if(PlayerData.getInitialKnightWeapon().equals("Axe")){
            return new Axe();
        }
        else{
            return new RustySword();
        }
    }

}
