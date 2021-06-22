package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.PlayerData;

/**
 * A Enemy with the textures of a Goblin
 * @author Spencer Ye
 * Last Revised: 5/12/2021
 */
public class Goblin extends Enemy
{
	/**
	 * Creates a goblin at the given location
	 * @param x The x coord
	 * @param y The y coord
	 */
	public Goblin(float x, float y) 
	{
		super(new KImage[] {AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_IDLE),
				AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_MOVING),
				AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_ATTACK),
				AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_DEATH)}, 5, 0.75f, MovingActor.createStates(1,1,1.5f,0.75f, 0.5f, 1.5f),75, 18 * 100L,10);
		image.moveTo(x, y);
	}

	@Override
	public void onDeath() {
		if(Math.random()<0.3){
			PlayerData.setCoins(PlayerData.getCoins()+1);
		}
	}
}
