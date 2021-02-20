package kchandra423.entities.obstacles;


import kchandra423.entities.Entity;
import kchandra423.graphics.Sprites.SpriteRectangle;
import kchandra423.graphics.shapes.Rectangle;
import kchandra423.graphics.textures.Texture;

public class Obstacle extends Entity{
	public Obstacle(Texture t, Rectangle r) {
		super(new SpriteRectangle(t,r,true));
	}

	
}
