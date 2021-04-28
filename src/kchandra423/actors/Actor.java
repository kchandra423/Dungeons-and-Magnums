package kchandra423.actors;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

public abstract class Actor {
    protected KImage image;
    protected float vx, vy;
    protected float maxV;
    protected float accel;
    public final KImage weapon;

    protected Actor(KImage image, float maxV, float accel) {
        this.image = image;
        weapon = new KImage(image.getWidth()/2, image.getHeight()/2, false, true, Texture.TextureBuilder.getTexture("res/Images/Weapons/SMG.png"));
        this.maxV = maxV;
        this.accel = accel;
    }

    public KImage getImage() {
        return image;
    }

    public void bounceBackX() {
        image.translate(-vx, 0);
        vx *= -0.3f;
    }

    public void bounceBackY() {
        image.translate(0, -vy);
        vy *= -0.3f;
    }

    public void draw(DrawingSurface d) {
        image.draw(d);
        weapon.draw(d);
    }

    public abstract void act(DrawingSurface d);

    protected void moveX(boolean[] directions) {
        int left = directions[0] ? -1 : 0;
        int right = directions[1] ? 1 : 0;
        int netX = left + right;

        float newVx = vx + netX * accel;

        if (Math.abs(newVx) < maxV) {
            vx = newVx;
        } else if (newVx < 0) {
            vx = -maxV;
        } else {
            vx = maxV;
        }

        vx *= 0.9f;
        image.translate(vx, 0);
        weapon.translate(vx, 0);
    }

    protected void moveY(boolean[] directions) {

        int up = directions[0] ? -1 : 0;
        int down = directions[1] ? 1 : 0;
        int netY = up + down;
        float newVy = vy + netY * accel;
        if (Math.abs(newVy) < maxV) {
            vy = newVy;
        } else if (newVy < 0) {
            vy = -maxV;
        } else {
            vy = maxV;
        }
        vy *= 0.9f;

        image.translate(0, vy);
        weapon.translate(0, vy);
    }
}
