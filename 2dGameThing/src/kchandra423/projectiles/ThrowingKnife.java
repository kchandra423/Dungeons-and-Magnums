package kchandra423.projectiles;
import kchandra423.shapes.Line;
import processing.core.PApplet;
public class ThrowingKnife extends Line implements Projectile{

	private final double velocity;
	private  double angle;
	private double vx,vy;	
	private boolean isActive=true;
	private boolean directionChanged=false;
	private int boundsX1, boundsY1, boundsX2, boundsY2;
	public ThrowingKnife(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
		velocity=0;
		angle=0;
		
		
		// TODO Auto-generated constructor stub
	}
		
		public ThrowingKnife(double x1, double y1,
				double v, double theta,int boundsX1, 
				int boundsY1, int boundsX2, int boundsY2) {
			//makes it seem like the bullet is facing the correct direction
			super(x1, y1, x1+10*Math.cos(theta), y1+10*Math.sin(theta));
			velocity=v;
			angle=theta;
			//yay physics, these are basically just the x and y components of the vector
			vx=velocity*Math.cos(angle);
			vy=velocity*Math.sin(angle);
			
			//again, this doesnt get updated, so dont change window size lol
			this.boundsX1=Math.min(boundsX1, boundsX2);
			this.boundsY1=Math.min(boundsY1,boundsY2);
			this.boundsX2=Math.max(boundsX1, boundsX2);
			this.boundsY2=Math.max(boundsY1,boundsY2);
			
			// TODO Auto-generated constructor stub
		}
//		public void setUp(Gun owner) {
//			//adds the bullet to the drawing pad's array of bullets
//			pad.addBullet(this);
//		}
		public void draw(PApplet pad) {
			//you get the idea
			move();
			if(isActive) {
			
			pad.pushStyle();
			pad.stroke(0);
			if(directionChanged) {
				pad.stroke(255,0,0);
			}
			pad.beginShape();
			pad.vertex((float)getx1(), (float)gety1());
			pad.vertex((float)getx2(), (float)gety2());
			pad.endShape();
			pad.popStyle();
				
			if(this.getx1()<boundsX1||this.getx1()>boundsX2||this.gety1()<boundsY1||this.gety1()>boundsY2) {
					setInactive();
				}
			}
			else {
				//this shouldnt happen
				System.out.println("bullet's draw method was called when its not active");
			}
		}
		public void move() {
			rotate(Math.PI/10);
			shift(vx,vy);
			
		}
//public void changeDirections() {
//	if(directionChanged==false) {
//	//just changes direction because it would be a pain in the butt to calculate
//		// the angle of reflection between bullet and saber and to then change the angle accordingly
//		// it would also make it nearly impossible to win as a jedi
//		angle+=Math.PI;
//	vx=velocity*Math.cos(angle);
//	vy=velocity*Math.sin(angle);
//	directionChanged=true;
//	}
//}

		public boolean isActive() {
			return isActive;
		
		}
		//basically just deletes itself, gc should kill it eventually
		public void setInactive() {
			isActive=false;
			
		}
		public boolean getDirectionChanged() {
			return directionChanged;
		}

}
