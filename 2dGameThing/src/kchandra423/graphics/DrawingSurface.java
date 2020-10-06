package kchandra423.graphics;

import kchandra423.obstacles.Crate;
import kchandra423.obstacles.Obstacle;
import kchandra423.players.Player;
import kchandra423.players.Rogue;
import kchandra423.shapes.Rectangle;
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
	
	private Player p= new Rogue();
	private Room r=new Room(p);
	Obstacle o=new Crate(100,100,30,40);
	/**
	 * Creates a new Drawing surface
	 */
	public DrawingSurface() {
//		r= new Rectangle(10,10,50,30);
//		 c= new Circle(100,100,50);
		r.addObstacle(o);
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	/**
	 * doesn't do anything as of right now
	 */
	public void settings() {
		size(1000,500);
	}
	public void setup() {
		
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	/**
	 * draws everything in the drawing surface
	 */
	public void draw() { 
		background(255);   // Clear the screen with a white background
//p.draw(this);
		r.draw(this);
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
		if (mouseButton == LEFT) {
			p.pressTrigger();;
		} 
		else if (mouseButton == RIGHT) {
			p.useAbility1();
		}
		
	}
	public void mouseReleased() {
		if (mouseButton == LEFT) {
			p.releaseTrigger();
		
		} 
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

		
		 if(key=='w') {
		p.setUp(true);
		}
		else if(key=='a') {
			p.setLeft(true);
		}
		else if(key=='s') {
			p.setDown(true);
		}
		else if(key=='d') {
			p.setRight(true);
		}else if (key=='r') {
			p.reload();
		}
		else if (key=='c') {
			p.useAbility2();
		}
		else if (key=='v') {
			p.useAbility3();
		}
		else if (key=='q') {
			p.useSuper();
		}
		
		
		
	}
	public void keyReleased() {

		
		 if(key=='w') {
		p.setUp(false);
		}
		else if(key=='a') {
			p.setLeft(false);
		}
		else if(key=='s') {
			p.setDown(false);
		}
		else if(key=='d') {
			p.setRight(false);
		}
		
		
		
	}
	
}










