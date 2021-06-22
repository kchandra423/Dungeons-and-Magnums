package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.PlayerData;

/**
 * A Enemy which represents a cyclops
 *
 * @author Kumar Chandra
 */
public class Cyclops extends RangedEnemy{
    /**
     * Creates a new Cyclops at the specific location
     * @param x The x coord
     * @param y The y coord
     */
    public Cyclops(float x, float y)
    {
        super(new KImage[] {AssetLoader.getImage(AssetLoader.Sprite.CYCLOPS_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.CYCLOPS_MOVING),
                AssetLoader.getImage(AssetLoader.Sprite.CYCLOPS_ATTACK),
                AssetLoader.getImage(AssetLoader.Sprite.CYCLOPS_DEATH)}, 5f, 0.3f, MovingActor.createStates(1.25f,1,1.5f,0.75f, 0.5f, 1.5f), 300,
                DamageTypes.RANGED, AssetLoader.Sprite.ROCK,8 * 100L, 30
        ,13/5f,10, 600);
        image.moveTo(x, y);
    }
    @Override
    public void onDeath() {
        PlayerData.setCoins(PlayerData.getCoins()+2);
    }
}
