package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.weapons.Weapon;
import kchandra423.actors.weapons.guns.MagicGloves;
import kchandra423.actors.weapons.guns.MagicStaff;
import kchandra423.actors.weapons.guns.SpellBook;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.PlayerData;

/**
 * Represents a mage
 * @author Kumar Chandra
 */
public class Mage extends Player {
    private static final float[] stats = MovingActor.createStates(1.25f, 3f, 0.5f, 1f, 3f, 0.3f);

    /**
     * Creates a new mage at the given location
     * @param x the x coord
     * @param y the y coord
     */
    public Mage(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.MAGE_IDLE), AssetLoader.getImage(AssetLoader.Sprite.MAGE_ACTIVE),
                9, 1f,stats,
                150,
                getInitialWeapon());
        image.moveTo(x, y);
    }
    private static Weapon getInitialWeapon(){
        if(PlayerData.getInitialMageWeapon().equals("SpellBook")){
            return new SpellBook();
        }else if(PlayerData.getInitialMageWeapon().equals("MagicGloves")){
            return new MagicGloves();
        }
        else{
            return new MagicStaff();
        }
    }
}
