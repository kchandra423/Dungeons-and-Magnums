package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.PlayerData;

/**
 * A Enemy which represents a minotaur
 *
 * @author Kumar Chandra
 */
public class Minotaur extends Enemy{
    /**
     * Creates a new minotaur at the specific location
     * @param x The x coord
     * @param y The y coord
     */
    public Minotaur(float x, float y) {
        super(new KImage[]{AssetLoader.getImage(AssetLoader.Sprite.MINOTAUR_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.MINOTAUR_MOVING),
                AssetLoader.getImage(AssetLoader.Sprite.MINOTAUR_ATTACK),
                AssetLoader.getImage(AssetLoader.Sprite.MINOTAUR_DEATH)}, 6, 0.5f, MovingActor.createStates(1,1,1.5f,0.75f, 0.4f, 1f),200, 5*100L, 10);

        image.moveTo(x, y);
    }

    @Override
    public void onDeath() {
        PlayerData.setCoins(PlayerData.getCoins()+1);
    }
}
