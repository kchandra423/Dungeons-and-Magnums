package kchandra423.entities;

import kchandra423.graphics.Sprites.Sprite;
import processing.core.PApplet;

public class Entity {
	private Sprite sprite;
	public Entity(Sprite sprite) {
		this.sprite=sprite;
	}
	public void draw(PApplet p, float offSetX, float offSetY) {
		sprite.draw(p,offSetX,offSetY);
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite s) {
		sprite=s;
	}
	public boolean intersects(Entity other) {
		try {
			return sprite.getShape().intersects(other.getSprite().getShape());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
