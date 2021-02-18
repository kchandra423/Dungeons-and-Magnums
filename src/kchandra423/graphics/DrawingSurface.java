package kchandra423.graphics;

import Textures.Texture;
import kchandra423.entities.*;
import kchandra423.entities.actors.players.Player;
import kchandra423.entities.actors.players.Rogue;
import kchandra423.entities.obstacles.Obstacle;
import kchandra423.shapes.Rectangle;
import kchandra423.utility.Constants.collisionDirection;
import kchandra423.weapons.RangedWeapon;
import processing.core.PApplet;

/**
 * Represents a drawing surface, which is a type of PApplet
 * @author Kumar Chandra
 *
 */
public class DrawingSurface extends PApplet {


	private static boolean []keys;
	private Player p;
	private Obstacle[] obstacles;
	/**
	 * Creates a new Drawing surface
	 */
	public DrawingSurface() {
		keys= new boolean[128];
		Texture t= Texture.TextureBuilder.getTexture("res/Images/Obstacles/Box.png");
		Obstacle o= new Obstacle(t, new Rectangle( 500,500,t.getWidth(),t.getHeight()));
		Obstacle o2= new Obstacle(t, new Rectangle( 1000,500,t.getWidth(),t.getHeight()));

		obstacles= new Obstacle[] {o,o2};
			p= new Rogue(50,50);
		p.addWeapon(new RangedWeapon(1,p.getSprite().getX(),p.getSprite().getY()));

	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	/**
	 * doesn't do anything as of right now
	 */
	public void settings() {
		size(TwodGameThing.BOUNDSX,TwodGameThing.BOUNDSY);
		
	}
	public void setup() {
		frameRate(30);
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	/**
	 * draws everything in the drawing surface
	 */
	public void draw() { 
		background(170);   // Clear the screen with a white background
//		System.out.println(frameRate);
		for(Obstacle o:obstacles)
		o.draw(this);
		
		p.act(this);
		p.draw(this);

	}

	public void keyPressed() {
		if(keyCode<128) {
			keys[keyCode]=true;
		}




	}
	public void keyReleased() {

		if(keyCode<128) {
			keys[keyCode]=false;
		}

	}
	public static boolean[] getKeys() {
		return keys;
	}
	//Precondition: keyCode must be less than 128 and greater than 0
	public static boolean getKey(int keyCode) {
		return keys[keyCode];
		
	}

	public Obstacle[] getObstacles() {
		return obstacles;
	}
	
}










