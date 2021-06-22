package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.constants.ActorState;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.actors.weapons.Weapon;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

/**
 * A player represents the users Character avatar that moves and fights the enemies
 *
 * @author Kumar Chandra
 * @see kchandra423.actors.Actor
 * @see kchandra423.actors.movingActors.MovingActor
 * @see kchandra423.actors.movingActors.enemies.Enemy
 */
public abstract class Player extends MovingActor {
    private final KImage idleImage;
    private final KImage activeImage;
    private Weapon weapon;

    /**
     * Creates a player with the specified idle and active animations
     *
     * @param idle   The idle sprite of this player
     * @param active The active sprite of this player
     */
    public Player(KImage idle, KImage active, float maxV, float accel, float[] stats, int maxHealth, Weapon weapon) {
        super(idle, maxV, accel, stats, maxHealth,0.2f);
        this.idleImage = idle;
        this.activeImage = active;
        this.weapon = weapon;
    }

    @Override
    public void draw(DrawingSurface d) {
        super.draw(d);
        weapon.draw(d);
    }

    @Override
    public void act(DrawingSurface d, Room r) {
        super.act(d, r);
        float angle = (float) Calculator.calculateAngle(d.width / 2.0f, d.height / 2.0f,
                d.mouseX, d.mouseY);
        if (angle < 0) {
            angle += Math.PI * 2;
        }
        if (!Float.isNaN(angle)) {
            weapon.getImage().setAngle(angle);
        }
        if (d.mousePressed) {
            if (d.mouseButton == PApplet.LEFT) {
                weapon.fire(statMultipliers);
            }
        }
        if (angle > Math.PI / 2 && angle < 3 * Math.PI / 2) {
            image.setReflected(true);
            weapon.getImage().setReflected(true);
        } else {
            image.setReflected(false);
            weapon.getImage().setReflected(false);
        }
        if (Math.abs(vx) < 0.1f && Math.abs(vy) < 0.1f) {
            updateState(ActorState.IDLE);
        } else {
            updateState(ActorState.MOVING);
        }

        weapon.getImage().moveTo(getCenterX(), getCenterY());
        if(DrawingSurface.getKey(KeyEvent.VK_R)){
            weapon.reload();
        }
        weapon.act(d, r);

    }

    @Override
    protected void makeMoveX(DrawingSurface drawingSurface, Room room) {
        boolean left = DrawingSurface.getKey(KeyEvent.VK_A);
        boolean right = DrawingSurface.getKey(KeyEvent.VK_D);
        moveX(new boolean[]{left, right});
    }

    @Override
    protected void makeMoveY(DrawingSurface drawingSurface, Room room) {
        boolean up = DrawingSurface.getKey(KeyEvent.VK_W);
        boolean down = DrawingSurface.getKey(KeyEvent.VK_S);
        moveY(new boolean[]{up, down});
    }

    @Override
    protected MovingActor interactsWOppponent(Room room) {
        for (Enemy e :
                room.getEnemies()) {
            if (intersects(e)) {
                return e;
            }
        }
        return null;
    }

    @Override
    protected void onOpponentInteraction(MovingActor opponent) {
        //pretend something thatll happen, idk it depends how we design it
    }

    @Override
    protected void onObstacleCollision(boolean isX, Obstacle obstacle) {
        if (isX) {
            bounceBackX();
            if (obstacle.getImage().getX() > image.getX()) {
                vx -= 3;
            } else {
                vx += 3;
            }
        } else {
            bounceBackY();
            if (obstacle.getImage().getY() > image.getY()) {
                vy -= 3;
            } else {
                vy += 3;
            }
        }
    }

    @Override
    protected void updateState(ActorState newState) {
        super.updateState(newState);
        switch (newState) {
            case IDLE:
                idleImage.copyInformation(image);
                image = idleImage;
                break;
            case MOVING:
                activeImage.copyInformation(image);
                image = activeImage;
                break;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon w){
        this.weapon = w;
    }

    @Override
    public void onDeath() {

    }
}
