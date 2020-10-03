package kchandra423.weapons;
import processing.core.PApplet;

public interface Weapon {
	public void draw(PApplet p);
	public void shift(float xAmount, float yAmount);
	public void use(int mouseX, int mouseY);
	public void reload();
	public int getTimeToFinishReload();
}
