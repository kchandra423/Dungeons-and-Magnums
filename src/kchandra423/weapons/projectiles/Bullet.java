package kchandra423.weapons.projectiles;

import Sprite.Sprite;
import kchandra423.graphics.TwodGameThing;
import processing.core.PApplet;

public class Bullet implements Projectile{
    private boolean active;
    private Sprite s;
    private boolean hitEnemy;
    private boolean killedEnemy;
    private ProjectileMetadata data;

    public Bullet(float initialV, float initialAngle, Sprite sprite) {
        s = sprite;
        s.setAngle(initialAngle);
        active = true;
        hitEnemy = false;
        killedEnemy = false;
        data = new ProjectileMetadata(initialV, initialAngle);
    }

    public void draw(PApplet p) {
        if (isActive())
            s.draw(p);
    }

    public void act() {
        if (isActive()) {
            move();
            if (s.getX() < 0 || s.getX() > TwodGameThing.BOUNDSX) {
                setActive(false);
            }
            if (s.getY() < 0 || s.getY() > TwodGameThing.BOUNDSY) {
                setActive(false);
            }
        }
    }

    private void move() {
        s.shift(data.getVelocity() * (float) Math.cos(data.getAngle()), data.getVelocity() * (float) Math.sin(data.getAngle()));
    }

    public boolean isActive() {
        return active;
    }

    private void setActive(boolean active) {
        this.active = active;
    }

    public float getX() {
        return s.getX();
    }

    public float getY() {
        return s.getY();
    }

    public boolean hasHitEnemy() {
        return hitEnemy;
    }

    public boolean hasKilledEnemy() {
        return killedEnemy;
    }

}
