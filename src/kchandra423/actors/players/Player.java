package kchandra423.actors.players;

import kchandra423.actors.Collideable;
import kchandra423.actors.MovingActor;
import kchandra423.actors.Gun;
import kchandra423.actors.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

public class Player extends MovingActor {
    private final KImage idle;
    private final KImage active;
    public final Gun weapon;

    public Player(KImage idle, KImage active) {
        super(idle, 7, 0.7f);
        this.idle = idle;
        this.active = active;
        weapon = new Gun(image.getWidth() / 2.0f, image.getHeight() / 2.0f);
    }


    @Override
    public void draw(DrawingSurface d) {
        super.draw(d);
        weapon.draw(d);
    }

    @Override
    public void act(DrawingSurface d, Room r) {
        float angle = (float) Calculator.calculateAngle(d.width / 2.0f, d.height / 2.0f,
                d.mouseX, d.mouseY);
        if (!Float.isNaN(angle)) {
            weapon.getImage().setAngle(angle);
        }
        if (d.mousePressed) {
            if (d.mouseButton == PApplet.LEFT) {
                weapon.fire();
            }
        }
        if (angle > Math.PI / 2 && angle < 3 * Math.PI / 2) {
            image.setReflected(true);
            weapon.getImage().setReflected(true);
        } else {
            image.setReflected(false);
            weapon.getImage().setReflected(false);
        }

        weapon.act(d, r);
        boolean up = DrawingSurface.getKey(KeyEvent.VK_W);
        boolean left = DrawingSurface.getKey(KeyEvent.VK_A);
        boolean down = DrawingSurface.getKey(KeyEvent.VK_S);
        boolean right = DrawingSurface.getKey(KeyEvent.VK_D);
        moveX(new boolean[]{left, right});
        if (!r.inBounds(image)) {
            bounceBackX();
        }
        moveY(new boolean[]{up, down});
        if (!r.inBounds(image)) {
            bounceBackY();
        }
        weapon.getImage().moveTo(image.getX() + image.getWidth() / 2.0f, image.getY() + image.getHeight() / 2.0f);
    }


}
