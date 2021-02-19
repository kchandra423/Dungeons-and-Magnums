package kchandra423.weapons.projectiles;

import processing.core.PApplet;

public interface Projectile {
    public void draw(PApplet p, float offSetX, float offSetY);
    public void act();
    public boolean isActive();
    public float getX();
    public float getY();
    public boolean hasHitEnemy();
    public boolean hasKilledEnemy();
}
