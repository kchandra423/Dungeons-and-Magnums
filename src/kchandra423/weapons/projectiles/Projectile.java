package kchandra423.weapons.projectiles;

import kchandra423.entities.Entity;
import kchandra423.graphics.Sprites.Sprite;
import processing.core.PApplet;

public interface Projectile {
    public void draw(PApplet p, float offSetX, float offSetY);
    public void act();
    public boolean isActive();
    public float getX();
    public float getY();
    public boolean hasHitEnemy();
    public boolean hasKilledEnemy();
    public boolean intersects(Projectile other);
    public boolean intersects(Entity other);
    public void setActive(boolean active);
    public Sprite getSprite();
}
