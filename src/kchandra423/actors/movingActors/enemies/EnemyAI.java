package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.movingActors.players.Player;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;

/**
 * Indicates how an enemy will make decisions
 *
 * @author Kumar Chandra
 */
class EnemyAI {
    private Enemy e;

    /**
     * Creates a new Enemy ai for this enemy
     *
     * @param enemy The enemy that this Enemy Ai will make decisions for
     */
    EnemyAI(Enemy enemy) {
        e = enemy;
    }

    float makeMovementDecision(Room r) {
        return (float) Calculator.calculateAngle(e.getCenterX(), e.getCenterY(), r.getPlayer().getCenterX(), r.getPlayer().getCenterY());
    }

    private boolean[] getDirectionTowardsPlayer(Room room) {
        Player p = room.getPlayer();
        boolean[] answer = new boolean[4];
        if (e.getImage().getX() < p.getImage().getX()) {
            answer[0] = false;
            answer[1] = true;
        } else {
            answer[1] = false;
            answer[0] = true;
        }
        if (e.getImage().getY() < p.getImage().getY()) {
            answer[2] = false;
            answer[3] = true;
        } else {
            answer[3] = false;
            answer[2] = true;
        }
        return answer;

    }
}
