package kchandra423.graphics;

import processing.core.PConstants;
import kchandra423.entities.actors.players.Player;
import kchandra423.entities.actors.players.Rogue;

import kchandra423.weapons.RangedWeapon;
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
    private Room r;

    /**
     * Creates a new Drawing surface
     */
    public DrawingSurface() {
        keys = new boolean[128];


        Player p = new Rogue(50, 50);
        p.addWeapon(new RangedWeapon(1, p.getSprite().getX(), p.getSprite().getY()));
        r = new Room(p);

    }

    // The statements in the setup() function
    // execute once when the program begins

    /**
     * doesn't do anything as of right now
     */
    public void settings() {
//        boolean versionCompatibleWP2D=false;
        System.out.println(javaVersionName);
        System.out.println(javaPlatform);
//        if(System.getProperty("os.name").equals("Windows")){
//            versionCompatibleWP2D=true;
//        }else{
//            if(javaVersionName.equals("1.8.242")){
//                versionCompatibleWP2D=true;
//            }
//        }
//        if(versionCompatibleWP2D)
            size(1500, 1000, JAVA2D);
//        else
//            size(1500, 1000, JAVA2D);

//        fullScreen();
    }

    public void setup() {
        frameRate(60);
//        background= loadImage("res/Images/Backgrounds/froggy.jpg");

    }

    // The statements in draw() are executed until the
    // program is stopped. Each statement is executed in
    // sequence and after the last line is read, the first
    // line is executed again.

    /**
     * draws everything in the drawing surface
     */
    public void draw() {
//        background.resize(width,height);
        background(255);   // Clear the screen with a white background
//		System.out.println(frameRate);
//        p.act(this);
//        pushMatrix();
        r.draw(this);
//        int halfx=width/2,halfy=height/2;
//        float difx=(p.getSprite().getX()-halfx),dify=(p.getSprite().getY()-halfy);
////        translate(-difx,-dify);
//        for (Obstacle o : obstacles) {
//            o.draw(this,-difx,-dify);
//            for (Projectile p : p.getCurrentWeapon().getProjectiles()){
//                if(p.intersects(o)){
//                    p.setActive(false);
//                }
//            }
//        }
//
//        p.draw(this,-difx,-dify);

//        popMatrix();

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

//    public Obstacle[] getObstacles() {
//        return obstacles;
//    }

}










