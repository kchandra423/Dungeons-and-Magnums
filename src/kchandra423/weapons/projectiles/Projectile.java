package kchandra423.weapons.projectiles;

public interface Projectile {
    public void act();
    public boolean isActive();
    public float getX();
    public float getY();
    public boolean hasHitEnemy();
    public boolean hasKilledEnemy();
}
