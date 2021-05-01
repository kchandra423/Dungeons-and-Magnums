package kchandra423.graphics;

import kchandra423.actors.Room;
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
//    private static int currentWidth, currentHeight;
    private static boolean[] keys;
    private Room room;

    /**
     * Creates a new Drawing surface
     */
    public DrawingSurface() {
        keys = new boolean[128];
        room = new Room();

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
//        DrawingSurface.currentWidth = width;
//        DrawingSurface.currentHeight = height;
        background(255);
        pushMatrix();
        int halfx = width / 2;
        int halfy = height / 2;
        translate(-room.getPlayer().getImage().getX() + halfx - room.getPlayer().getImage().getWidth() / 2.0f, -room.getPlayer().getImage().getY() + halfy - room.getPlayer().getImage().getHeight() / 2.0f);
//        p1.act(this);
//        p1.draw(this);
//        p2.draw(this);
//        if (p1.getImage().intersects(p2.getImage())) {
//            fill(255, 0, 0);
//            text("colliding or something", 500, 500);
//        }
//        if (p1.weapon.getImage().intersects(p2.getImage())) {
//            fill(0, 255, 0);
//            text("weaponds colliding or something", 500, 600);
//        }
        room.draw(this);
        popMatrix();
        line(width / 2.0f, 0, width / 2.0f, height);
        line(0, height / 2.0f, width, height / 2.0f);
        fill(0);
        text((frameRate ) + " : fps", width - 100, height - 100);

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
//    public static int getCurrentWidth(){
//        return currentWidth;
//    }
//
//    public static int getCurrentHeight() {
//        return currentHeight;
//    }
}










