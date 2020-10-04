package kchandra423.weapons;

import processing.core.PApplet;

public class Sword implements Weapon{
	private double swingLength;//radians
	private double swingDurations;//seconds
	private double length;
	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shift(float xAmount, float yAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(int mouseX, int mouseY) {
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

}
