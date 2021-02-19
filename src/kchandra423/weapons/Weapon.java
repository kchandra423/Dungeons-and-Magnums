package kchandra423.weapons;
import java.util.ArrayList;


import kchandra423.graphics.Sprites.Sprite;
import kchandra423.weapons.projectiles.Bullet;
import processing.core.PApplet;

public interface Weapon {
	public void draw(PApplet p,float offsetX, float offsetY);
	public void shift(float xAmount,float yAmount);
	public void reload();
	public int getReloadPercentagee();
	public int getMagazine();
	public int getMagazineSize();
	public int getHitStreak();
	public int getKillStreak();
	public Sprite getSprite();
	public void pressTrigger();
	public void releaseTrigger();
	public void setAngle(double theta);
//	public void moveTo(float x, float y);
	public ArrayList<Bullet> getProjectiles();
	public void act();
}