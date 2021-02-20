package kchandra423.weapons;

import java.util.ArrayList;
import java.util.TimerTask;

import kchandra423.graphics.Sprites.Sprite;
import kchandra423.graphics.Sprites.SpriteCircle;
import kchandra423.graphics.Sprites.SpriteRectangle;
import kchandra423.graphics.textures.Texture;
import kchandra423.graphics.shapes.*;
import kchandra423.weapons.projectiles.Bullet;
import kchandra423.weapons.projectiles.Projectile;
import processing.core.PApplet;

public class RangedWeapon implements Weapon {
    private final Sprite sprite;
    private final RangedWeaponMetadata data;
    private final Texture projectileTexture;

    public RangedWeapon(int weaponNum, float x, float y) {
        data = new RangedWeaponMetadata(weaponNum);
        Texture t;
        t = Texture.TextureBuilder.getTexture("res/Images/Weapons/" + data.getName() + ".png");
        Rectangle r = new Rectangle(x, y + 30, t.getWidth(), t.getHeight());
        sprite = new SpriteRectangle(t, r, data.isCenter());
        projectileTexture = Texture.TextureBuilder.getTexture("res/Images/Projectiles/Bullet.png");

    }

    @Override
    public void draw(PApplet p, float offSetx, float offSetY) {
        ArrayList<Projectile> removes = new ArrayList<Projectile>();
        for (int i = 0; i < data.getProjectiles().size(); i++) {
            Projectile b = data.getProjectiles().get(i);
            if (b.isActive()) {
                b.act();
                b.draw(p, offSetx, offSetY);
            } else {
                removes.add(b);
            }

        }

        data.getProjectiles().removeAll(removes);
        sprite.draw(p, offSetx, offSetY);

    }

    public void act() {

        if (data.canFire() && data.isTriggerPressed()) {
            fire();
        } else if (data.getMagazine() > 0 && data.isReloading() && data.isTriggerPressed()) {
            data.getReloadTask().cancel();
            fire();
        } else if (data.getMagazine() <= 0) {
            reload();
        }
    }

    private void fire() {
        for (int i = 0; i < data.getPellets(); i++) {
            float tempAngle = (float) data.getAngle();
            if (Math.random() >= 0.5) {
                tempAngle += Math.random() * data.getSpread() / 2;
            } else {
                tempAngle -= Math.random() * data.getSpread() / 2;
            }
            Bullet p = new Bullet((float) data.getProjectileVelocity(), tempAngle, new SpriteCircle(projectileTexture, new Circle(sprite.getX(), sprite.getY(), projectileTexture.getWidth())));

            data.getProjectiles().add(p);

        }
        data.setLastTimeShot(System.currentTimeMillis());
        data.setMagazine(data.getMagazine() - 1);
        System.out.println(data.getMagazine());
    }

    @Override
    public void shift(float xAmount, float yAmount) {
        sprite.shift(xAmount, yAmount);
    }


    @Override
    public void reload() {
        if (data.getMagazine() < data.getMagazineSize() && !data.isReloading()) {
            data.setTimeSinceReloaded(System.currentTimeMillis());
            data.setReloading(true);
            TimerTask t = new TimerTask() {

                @Override
                public void run() {
                    int dif = data.getMagazineSize() - data.getMagazine();
                    data.setMagazine(data.getMagazine() + dif);
                    if (dif > data.getAmmo()) {
                        dif = data.getAmmo();
                    }
                    data.setAmmo(data.getAmmo() - dif);
                    data.setReloading(false);
                }

            };
            data.setReloadTask(t);
            data.getReloadTimer().schedule(t, (long) data.getReloadTime() * 1000);
        }
    }

    @Override
    public int getReloadPercentagee() {
        int percentage = (int) ((data.getTimeSinceReloaded() / data.getReloadTime()) * 100);
        return Math.min(percentage, 100);
    }

    @Override
    public int getMagazine() {
        return data.getMagazine();
    }

    @Override
    public int getMagazineSize() {
        return data.getMagazineSize();
    }

    @Override
    public int getHitStreak() {
        return data.getHitStreak();
    }

    @Override
    public int getKillStreak() {
        return data.getKillStreak();
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void pressTrigger() {
        data.setTriggerPressed(true);

    }

    @Override
    public void releaseTrigger() {
        data.setTriggerPressed(false);

    }

    @Override
    public void setAngle(double theta) {
        data.setAngle((float) theta);
        sprite.setAngle((float) theta);
    }

    @Override
    public ArrayList<Projectile> getProjectiles() {
        return data.getProjectiles();
    }


}
