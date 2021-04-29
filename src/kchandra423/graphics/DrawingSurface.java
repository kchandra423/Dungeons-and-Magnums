package kchandra423.graphics;

import kchandra423.actors.players.Player;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import processing.core.PApplet;

/**
 * Represents a drawing surface, which is a type of PApplet
 *
 * @author Kumar Chandra
 */
public class DrawingSurface extends PApplet {

    //    private PImage background;
    private static boolean[] keys;
    private Player p1;
    private Player p2;
    private int sum = 0;

    /**
     * Creates a new Drawing surface
     */
    public DrawingSurface() {
        keys = new boolean[128];
        p1 = new Player(new KImage(0, 0, false, false, Texture.TextureBuilder.getTexture("res/Images/Players/MageIdle.gif")), null);
        p2 = new Player(new KImage(100, 100, false, false, Texture.TextureBuilder.getTexture("res/Images/Players/RogueIdle.gif")), null);
    }

    // The statements in the setup() function
    // execute once when the program begins

    /**
     * doesn't do anything as of right now
     */
    public void settings() {
        size(1500, 1000, P2D);
//        fullScreen();
    }

    public void setup() {
        frameRate(60);
        surface.setTitle("Hello World!");
        surface.setResizable(true);
    }

    // The statements in draw() are executed until the
    // program is stopped. Each statement is executed in
    // sequence and after the last line is read, the first
    // line is executed again.

    /**
     * draws everything in the drawing surface
     */
    public void draw() {
        background(255);
        pushMatrix();
        int halfx = width / 2;
        int halfy = height / 2;
        translate(-p1.getImage().getX() + halfx - p1.getImage().getWidth() / 2, -p1.getImage().getY() + halfy - p1.getImage().getHeight() / 2);
        p1.act(this);
        p1.draw(this);
        p2.draw(this);
        popMatrix();
        line(width / 2, 0, width / 2, height);
        line(0, height / 2, width, height / 2);
        if (p1.getImage().intersects(p2.getImage())) {
            fill(255, 0, 0);
            text("colliding or something", 500, 500);
        }
        if (p1.weapon.intersects(p2.getImage())) {
            fill(0, 255, 0);
            text("weaponds colliding or something", 500, 600);
        }
        fill(0);
        text(((((int) frameRate + 5) / 10) * 10) + " : fps", width - 100, height - 100);

    }

    public void keyPressed() {
        if (keyCode < 128) {
            keys[keyCode] = true;
        }


    }

    public void keyReleased() {

        if (keyCode < 128) {
            keys[keyCode] = false;
        }

    }

    public static boolean[] getKeys() {
        return keys;
    }

    //Precondition: keyCode must be less than 128 and greater than 0
    public static boolean getKey(int keyCode) {
        return keys[keyCode];

    }

}










