package kchandra423.actors.players;

import kchandra423.actors.Actor;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.Calculator;

import java.awt.event.KeyEvent;

public class Player extends Actor {
    private final KImage idle;
    private final KImage active;

    public Player(KImage idle, KImage active) {
        super(idle, 5, 0.5f);
        this.idle = idle;
        this.active = active;
    }

    @Override
    public void act(DrawingSurface d) {
        float angle = (float) Calculator.calculateAngle(d.width / 2, d.height / 2,
                d.mouseX, d.mouseY);
        if (!Float.isNaN(angle)) {
            weapon.rotate(angle - weapon.getAngle());
        }

        if (angle > Math.PI / 2 && angle < 3 * Math.PI / 2) {
            image.setReflected(true);
            weapon.setReflected(true);
        } else {
            image.setReflected(false);
            weapon.setReflected(false);
        }
        boolean up = DrawingSurface.getKey(KeyEvent.VK_W);
        boolean left = DrawingSurface.getKey(KeyEvent.VK_A);
        boolean down = DrawingSurface.getKey(KeyEvent.VK_S);
        boolean right = DrawingSurface.getKey(KeyEvent.VK_D);
        super.moveX(new boolean[]{left, right});
        super.moveY(new boolean[]{up, down});
    }


}
