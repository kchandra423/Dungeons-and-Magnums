package kchandra423.actors.players;

import kchandra423.actors.Actor;
import kchandra423.actors.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

public class Player extends Actor {
    private final KImage idle;
    private final KImage active;

    public Player(KImage idle, KImage active) {
        super(idle, 7, 0.7f);
        this.idle = idle;
        this.active = active;
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

        weapon.act(d,r);
        boolean up = DrawingSurface.getKey(KeyEvent.VK_W);
        boolean left = DrawingSurface.getKey(KeyEvent.VK_A);
        boolean down = DrawingSurface.getKey(KeyEvent.VK_S);
        boolean right = DrawingSurface.getKey(KeyEvent.VK_D);
        super.moveX(new boolean[]{left, right});
        if (!r.inBounds(image)){
            bounceBackX();
        }
        super.moveY(new boolean[]{up, down});
        if (!r.inBounds(image)){
            bounceBackY();
        }
    }


}
