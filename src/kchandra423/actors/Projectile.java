package kchandra423.actors;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;

import java.awt.*;

public class Projectile extends Actor {
    private boolean active;
    protected float v;
    protected float angle;

    public Projectile(KImage image, float v, float angle) {
        super(image);
        image.setAngle(angle);
        this.v = v;
        this.angle = angle;
        active = true;
    }
    public Rectangle getBounds(){
        return image.getBounds();
    }
    public void act(DrawingSurface d, Room r) {
        image.translate((float) (v * Math.cos(angle)), (float) (v * Math.sin(angle)));
        if (!r.inBounds(image)){
            active = false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void draw(DrawingSurface d) {
        if (active)
            image.draw(d);
        else
            System.out.println("Attempted to draw bullet while inactive");
    }


}
