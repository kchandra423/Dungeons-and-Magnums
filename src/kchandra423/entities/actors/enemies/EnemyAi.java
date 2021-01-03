package kchandra423.entities.actors.enemies;

import kchandra423.graphics.DrawingSurface;

public interface EnemyAi {
	//[movement directionX, movementDirectoinY, firingAt player]
	public int[] makeDecision(DrawingSurface d);
}
