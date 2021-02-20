package kchandra423.weapons.projectiles;

import kchandra423.entities.Entity;
import kchandra423.graphics.Sprites.Sprite;
import processing.core.PApplet;

public class Bullet implements Projectile {
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

    public void draw(PApplet p, float offSetX, float offSetY) {
        if (isActive())
            s.draw(p, offSetX, offSetY);
        if (s.getX() + offSetX < 0 || s.getX() + offSetX > p.width) {
            setActive(false);
        }
        if (s.getY() + offSetY < 0 || s.getY() + offSetY > p.height) {
            setActive(false);
        }

    }

    public void act() {
        if (isActive()) {
            move();

        }
    }

    private void move() {
        s.shift(data.getVelocity() * (float) Math.cos(data.getAngle()), data.getVelocity() * (float) Math.sin(data.getAngle()));
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        if (!active)
            System.out.println("ok!");
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

    @Override
    public boolean intersects(Projectile other) {
        boolean b = false;
        try {
            b = other.getSprite().getShape().intersects(s.getShape());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean intersects(Entity other) {
        boolean b = false;
        try {
            b = s.getShape().intersects(other.getSprite().getShape());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;

    }

    @Override
    public Sprite getSprite() {
        return s;
    }

}
