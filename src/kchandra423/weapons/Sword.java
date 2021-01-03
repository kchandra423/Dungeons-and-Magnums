package kchandra423.weapons;

import java.util.ArrayList;

import kchandra423.weapons.projectiles.Projectile;
import processing.core.PApplet;

public class Sword implements Weapon{
	private double swingLength;//radians
	private double swingDurations;//seconds
	private double length;
	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub
	}


	public void shift(float xAmount, float yAmount) {
		// TODO Auto-generated method stub
		
	}

	
	private void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTimeToFinishReload() {
		// TODO Auto-generated method stub
		return 101;
	}

	@Override
	public int getMagazine() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public int getMagazineSize() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public int getHitStreak() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pressTrigger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseTrigger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAngle(double theta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveTo(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftX(float xAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shiftY(float yAmount) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ArrayList<Projectile> getProjectiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
