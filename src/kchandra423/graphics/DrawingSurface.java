package kchandra423.graphics;

import Textures.Texture;
import kchandra423.entities.*;
import kchandra423.entities.actors.players.Player;
import kchandra423.entities.actors.players.PlayerOUTDATED;
import kchandra423.entities.actors.players.Rogue;
import kchandra423.entities.obstacles.Crate;
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

//	private Rectangle r;
//	private Circle c;
//	private Line l1, l2;
	private static boolean []keys;
	private static int mouseX;
	private static int mouseY;
	private Player p;
	private Obstacle[] obstacles;
//	private Room r;
//	Obstacle o=new Crate(100,100,30,40);
	/**
	 * Creates a new Drawing surface
	 */
	public DrawingSurface() {
//		r= new Rectangle(10,10,50,30);
//		 c= new Circle(100,100,50);
		keys= new boolean[128];
		Texture t= Texture.TextureBuilder.getTexture("res/Images/Obstacles/Box.png");
		Obstacle o= new Obstacle(t, new Rectangle( 500,500,t.getWidth(),t.getHeight()));
		Obstacle o2= new Obstacle(t, new Rectangle( 1000,500,t.getWidth(),t.getHeight()));

		obstacles= new Obstacle[] {o,o2};
			p= new Rogue(50,50);
		p.addWeapon(new RangedWeapon(1,p.getSprite().getX(),p.getSprite().getY()));
//		r=new Room(p);
//		
//		r.addObstacle(o);
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
//p.draw(this);
//		r.draw(this);
//		System.out.println(frameRate);
//		DrawingSurface.mouseX=super.mouseX;
//		DrawingSurface.mouseY=super.mouseY;
		for(Obstacle o:obstacles)
		o.draw(this);
		
		p.act(this);
		p.draw(this);
//		collisionDirection direction= o.intersects(p);
//		
//		if(direction==collisionDirection.X) {
//			p.bounceBackX();
//			
//		}else if(direction==collisionDirection.Y) {
//			p.bounceBackY();
//		}else if(direction==collisionDirection.BOTH) {
//			p.bounceBackX();
//			p.bounceBackY();
//		}
//		Rectangle rect=new Rectangle(10,10,30,20);
////		System.out.println(r);
//		rect.draw(this);
//		rect.rotateAboutTLCorner(Math.PI*1/3);
//		rect.draw(this);
////		System.out.println(rect);
	}
	/**
	 * moves creates lines
	 */
	public void mousePressed() {
//		if (mouseButton == LEFT) {
//			p.pressTrigger();;
//		} 
//		else if (mouseButton == RIGHT) {
//			p.useAbility1();
//		}
		
	}
	public void mouseReleased() {
//		if (mouseButton == LEFT) {
//			p.releaseTrigger();
//		
//		} 
	}
	
	/**
	 * moves lines
	 */
	public void mouseDragged() {
//		if (mouseButton == LEFT) {
//			l1.setPoint2(mouseX,mouseY);
//		} 
//		else if (mouseButton == RIGHT)
//			l2.setPoint2(mouseX,mouseY);
	}
	/**
	 * moves rectangle
	 */
	public void keyPressed() {
//		System.out.println(keyCode);
		if(keyCode<128) {
			keys[keyCode]=true;
		}
//		 if(key=='w') {
//		p.setUp(true);
//		}
//		else if(key=='a') {
//			p.setLeft(true);
//		}
//		else if(key=='s') {
//			p.setDown(true);
//		}
//		else if(key=='d') {
//			p.setRight(true);
//		}else if (key=='r') {
//			p.reload();
//		}
//		else if (key=='c') {
//			p.useAbility2();
//		}
//		else if (key=='v') {
//			p.useAbility3();
//		}
//		else if (key=='q') {
//			p.useSuper();
//		}
		
		
		
	}
	public void keyReleased() {

		if(keyCode<128) {
			keys[keyCode]=false;
		}
//		 if(key=='w') {
//		p.setUp(false);
//		}
//		else if(key=='a') {
//			p.setLeft(false);
//		}
//		else if(key=='s') {
//			p.setDown(false);
//		}
//		else if(key=='d') {
//			p.setRight(false);
//		}
	}
	public static boolean[] getKeys() {
		return keys;
	}
	//Precondition: keyCode must be less than 128 and greater than 0
	public static boolean getKey(int keyCode) {
		return keys[keyCode];
		
	}

	public static int getMouseX() {
		return mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}
	public Obstacle[] getObstacles() {
		return obstacles;
	}
	
}










