package kchandra423.graphics;

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
    //    private Player p;
//    private Obstacle[] obstacles;
//    private Room r;
    private KImage p1 = new KImage(Texture.TextureBuilder.getTexture("res/Images/Players/MageIdle.gif"));
    private KImage p2 = new KImage(Texture.TextureBuilder.getTexture("res/Images/Players/RogueIdle.gif"));
    /**
     * Creates a new Drawing surface
     */
    public DrawingSurface() {
        keys = new boolean[128];


    }

    // The statements in the setup() function
    // execute once when the program begins

    /**
     * doesn't do anything as of right now
     */
    public void settings() {
        size(1500, 1000, P2D);

    }

    public void setup() {
        frameRate(1000);

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
        p1.draw(this);
        p2.draw(this);
        if(p1.intersects(p2)){
            System.out.println("urmom");
        }

    }

    public void keyPressed() {
        if (key=='d'){
            p1.translate(6,0);
        }
        else if (key== 'w'){
            p1.translate(0,-6);
        }
        else if (key== 'a'){
            p1.translate(-6,0);
        }
        else if (key== 's'){
            p1.translate(0,6);
        }
        if (key=='l'){
            p2.translate(6,0);
        }
        else if (key== 'i'){
            p2.translate(0,-6);
        }
        else if (key== 'j'){
            p2.translate(-6,0);
        }
        else if (key== 'k'){
            p2.translate(0,6);
        }
//        if (keyCode < 128) {
//            keys[keyCode] = true;
//        }


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










