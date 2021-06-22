package kchandra423.actors.obstacles;

import kchandra423.utility.AssetLoader;
/**
 * Represents a crate obstacle
 * @author Kumar Chandra
 */
public class Crate extends Obstacle{
    /**
     * Creates a new Crate at the specific locatioin
     * @param x The x coord
     * @param y The y coord
     */
    public Crate(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.BOX));
        image.moveTo(x, y);
    }
}
