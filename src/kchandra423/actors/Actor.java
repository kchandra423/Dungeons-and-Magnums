package kchandra423.actors;

import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

/**
 * An actor represents any thing that can be drawn and repeatedly acts each frame. Uses a Kimage to draw and check collisions
 * Also has a field that determines whether or not the actor is active.
 *
 * @see KImage
 * @author Kumar Chandra
 */
public abstract class Actor {
    protected boolean active;
    protected KImage image;

    /**
     * Creates an actor with the specified image
     * @param image The specified image
     */
    public Actor(KImage image){
        this.image = image;
        active = true;
    }

    /**
     * Indicates what this actor will do every frame besides drawing. Usually is just movement and checking collisions
     * @param d The drawing surface to be acted upon
     * @param room The room the actor is currently in
     */
    public abstract void act(DrawingSurface d, Room room);

    /**
     * Returns whether this actor is currently active
     * @return whether or not this actor is currently active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the Kimage representing this actor
     * @return This actors Kimage
     */
    public KImage getImage() {
        return image;
    }

    /**
     * Draws the actor's Kimage
     * @param d The drawing surface to be drawed onto
     */
    public void draw(DrawingSurface d){
        if(active) {
            image.draw(d);
        }
    }

    /**
     * Returns whether or not this actor intersects another actor by comparing their Kimage's
     * Relatively expensive call
     * @param other The other actor
     * @return True if the two actors intersect, false otherwise
     */
    public boolean intersects(Actor other) {
        return image.intersects(other.image);
    }


    /**
     * Sets this actor as active or inactive
     * @param active The new activity of this actor
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public float getCenterX(){
        return image.getX()+image.getTexture().getWidth()/2f;
    }
    public float getCenterY(){
        return image.getY()+image.getTexture().getHeight()/2f;
    }

}
