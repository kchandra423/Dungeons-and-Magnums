package kchandra423.entities;
import Sprite.Sprite;
import processing.core.PApplet;

public class Entity {
	private Sprite sprite;
	public Entity(Sprite sprite) {
		this.sprite=sprite;
	}
	public void draw(PApplet p) {
		sprite.draw(p);
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
