package kchandra423.actors;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class Gun {
    private final Texture projectile;
    private final Area projectileArea;
    private final float velocity;
    private final KImage weapon;
    private final ArrayList<Projectile> projectiles;

    public Gun(float x, float y) {
        velocity = 20;
        weapon = new KImage(x, y, false, true, Texture.TextureBuilder.getTexture("res/Images/Weapons/SMG.png"));
        projectile = Texture.TextureBuilder.getTexture("res/Images/Projectiles/Bullet.png");
        projectileArea = KImage.loadArea(projectile);
        projectiles = new ArrayList<>();
    }
    public void act(DrawingSurface d, Room r){
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.act(d,r);
            if (!p.isActive()) {
                projectiles.remove(i);
                i--;
            }

        }
    }

    public void draw(DrawingSurface d) {
        d.fill(255);
        for (Projectile p :
                projectiles) {
//            Rectangle bounds = p.getBounds();
//            d.rect(bounds.x,bounds.y, bounds.width, bounds.height);
            p.draw(d);
        }

        weapon.draw(d);
    }

    public void fire() {
        projectiles.add(new Projectile(
                new KImage((float) (weapon.getX() + 60 * Math.cos(weapon.getAngle())), (float) (weapon.getY() + 60 * Math.sin(weapon.getAngle()))
                        , false, false, projectile, projectileArea)
                , velocity, weapon.getAngle()));
    }

    public KImage getImage() {
        return weapon;
    }
}
