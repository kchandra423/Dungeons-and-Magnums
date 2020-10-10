package kchandra423.weapons;
import java.util.ArrayList;

import kchandra423.projectiles.Projectile;
import processing.core.PApplet;

public interface Weapon {
	public void draw(PApplet p);
	public void shiftX(float xAmount);
	public void shiftY(float yAmount);
//	public void use();
	public void reload();
	public int getTimeToFinishReload();
	public int getMagazine();
	public int getMagazineSize();
	public int getHitStreak();
	public void pressTrigger();
	public void releaseTrigger();
	public void setAngle(double theta);
	public void moveTo(float x, float y);
	public ArrayList<Projectile> getProjectiles();
}
