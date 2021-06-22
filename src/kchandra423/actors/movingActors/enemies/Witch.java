package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.MovingActor;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.PlayerData;

/**
 * A Enemy with the textures of a Witch
 * @author Spencer Ye
 * Last Revised: 5/12/2021
 */
public class Witch extends RangedEnemy
{
	/**
	 * Creates a new witch at the specific location
	 * @param x The x coord
	 * @param y The y coord
	 */
	public Witch(float x, float y) 
	{
		super(new KImage[] {AssetLoader.getImage(AssetLoader.Sprite.WITCH_IDLE),
				AssetLoader.getImage(AssetLoader.Sprite.WITCH_MOVING),
				AssetLoader.getImage(AssetLoader.Sprite.WITCH_ATTACK),
				AssetLoader.getImage(AssetLoader.Sprite.WITCH_DEATH)}, 4f, 0.5f, MovingActor.createStates(1,1,1.5f,0.75f, 0.5f, 1.5f), 50, DamageTypes.MAGIC, AssetLoader.Sprite.HEX,9 * 100L, 10
		,1,7,500);
		image.moveTo(x, y);
	}

	@Override
	public void onDeath() {
		if(Math.random()<0.5)
			PlayerData.setCoins(PlayerData.getCoins()+1);
	}
}
