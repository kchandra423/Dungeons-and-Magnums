package kchandra423.projectiles;

import processing.core.PApplet;

public interface Projectile {
	public boolean isActive();
	public void setInactive();
	public void draw(PApplet p);
	public double getX();
	public double getY();
//	public void intersects(Rectangle other);
}
