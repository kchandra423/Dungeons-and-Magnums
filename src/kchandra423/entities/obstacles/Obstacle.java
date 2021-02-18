package kchandra423.entities.obstacles;

import Sprite.SpriteRectangle;
import Textures.Texture;
import kchandra423.entities.Entity;
import kchandra423.entities.actors.Actor;
import kchandra423.shapes.Rectangle;
import kchandra423.shapes.Shape;
import kchandra423.utility.Constants.collisionDirection;
import kchandra423.utility.Constants.collisionTypes;

public class Obstacle extends Entity{
	public Obstacle(Texture t, Rectangle r) {
		super(new SpriteRectangle(t,r,true));
	}
	public collisionTypes getObstaclesType() {
		return null;
		
	}
	
}
