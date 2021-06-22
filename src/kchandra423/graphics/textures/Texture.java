package kchandra423.graphics.textures;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an image that can be moved around and resized. Supports Gif, jpeg, and png images.
 * Gifs will be properly animated. Uses processing to draw.
 *
 * @see PImage
 * @author Kumar Chandra
 */
public interface Texture  {
    /**
     * Resizes the textures image to the given width and height
     *
     * @param w new width of texture
     * @param h new height of texture
     */
    void resize(int w, int h);

    /**
     * Returns the current width of the image
     *
     * @return Current width of the image
     */
    int getWidth();

    /**
     * Returns the current height of the image
     *
     * @return Current height of the image
     */
    int getHeight();

    /**
     * Draws the texture onto the given PApplet
     *
     * @param p The given PApplet to be drawn to
     * @param x The x coordinate to be drawn from
     * @param y The y coordinate to be drawn from
     */
    void draw(PApplet p, int x, int y);

    /**
     * Returns the current image being displayed
     *
     * @return The current image being displayed
     */
    PImage getImage();


    /**
     * Used to create a Texture given its pathname
     * Supports gifs, jpegs, and pngs
     *
     * @author Kumar Chandra
     */
    class TextureBuilder {
        /**
         * Creates a Texture from the image at the given path name
         *
         * @param pathName The path to the Image
         * @return A texture at 0,0 with the image at the pathname
         */
        public static Texture getTexture(String pathName) {
            if (pathName.startsWith(".gif", pathName.length() - 4)) {
                return new PGif(pathName);
            } else {
                return new TextureImage(pathName);
            }
        }
    }

}
