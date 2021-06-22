package kchandra423.actors.obstacles;

import kchandra423.actors.Actor;
import kchandra423.levels.Room;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;

/**
 * Represents a generic obstacle that cannot be intersected by normal actors
 *
 * @see kchandra423.actors.Actor
 * @author Kumar Chandra
 */
public class Obstacle extends Actor {
    /**
     * Constructs an obstacle with the given image
     * @param image The image of this actor
     */
    public Obstacle(KImage image) {
        super(image);
    }

    @Override
    public void act(DrawingSurface d, Room room) {

    }
}
