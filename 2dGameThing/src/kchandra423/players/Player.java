package kchandra423.players;


import java.util.Timer;

import kchandra423.graphics.TwodGameThing;
import kchandra423.shapes.Circle;
import kchandra423.weapons.Gun;
import kchandra423.weapons.Weapon;
import processing.core.PApplet;

public abstract class Player {
	private Circle body;
	private Weapon w;
	private int health;
	private boolean isDead;
	private  float velocityX;
	private float velocityY;
	private float acceleration;
	private boolean up,left,down,right,leftMouse;
	private int abilityCooldown;
	private int superCooldown;
	private boolean abilityOnCooldown;
	private boolean superOnCooldown;
	private Timer abilityTimer;
	private Timer superTimer;
	public Player() {
		body=new Circle(10,10,10);
		w=new Gun();
		health=100;
		isDead=false;
		velocityX=0;
		velocityY=0;
		acceleration=1;
	    up=false;
	    down=false;
	    left=false;
	    right=false;
	    abilityCooldown=1*1000;
	    superCooldown=5*1000;
	}
	public void setup(PApplet p) {
		
	}
	public void draw(PApplet p) {
		move();
		if(leftMouse) {
			useWeapon(p.mouseX, p.mouseY);
		}
		body.draw(p);
		w.draw(p);
	}
	
	public void equipWeapon() {
		
	}
	
	public void move() {
		float xamount=0;
		float yamount=0;
		if((!right&&!left)||(left&&right)) {
			if(velocityX!=0) {
				velocityX*=0.9;
			}	
		}
		else if(right) {
			velocityX+=acceleration;
		}
		else if(left) {
				velocityX-=acceleration;
		}
			if((!up&&!down)||(up&&down)) {
				if(velocityY!=0) {
					velocityY*=0.9;
				}
			}
		else if(down) {
			velocityY+=acceleration;
		}
		else  if(up) {
			 velocityY-=acceleration;
		}
		 
		 if(velocityY>7) {
			 velocityY=7;
		 }
		 else if(velocityY<-7) {
			 velocityY=-7;
		 }
		 if(velocityX>7) {
			 velocityX=7;
		 }
		 else if(velocityX<-7) {
			 velocityX=-7;
		 }
		 xamount=velocityX;
		 yamount=velocityY;
		 if((body.getX()-body.getRadius()+xamount<=0||body.getX()+body.getRadius()+xamount>=TwodGameThing.BOUNDSX)
				&&( body.getY()-body.getRadius()+yamount<=0||body.getY()+body.getRadius()+yamount>=TwodGameThing.BOUNDSY-20)) {
			 body.shift(-xamount,-yamount);
			 w.shift(-xamount, -yamount);
			 velocityX*=-1;
			 velocityY*=-1;
		 }
		 else if(body.getX()-body.getRadius()+xamount<=0||body.getX()+body.getRadius()+xamount>=TwodGameThing.BOUNDSX) {
			 body.shift(-xamount,yamount);
			 w.shift(-xamount, yamount);
			 velocityX*=-1;
//			 velocityY*=-1;
		 }
		 else if(body.getY()-body.getRadius()+yamount<=0||body.getY()+body.getRadius()+yamount>=TwodGameThing.BOUNDSY-20) {
			 body.shift(xamount,-yamount);
			 w.shift(xamount, -yamount);
//			 velocityX*=-1;
			 velocityY*=-1;
		 }
		 else {
		 body.shift(xamount,yamount);
		 w.shift(xamount, yamount);
		 }
	}
	
	public void useWeapon(int mouseX, int mouseY) {
		w.use(mouseX, mouseY);
	}
	
	public abstract void useAbility();
	
	public abstract void useSuper();
	
	public void incrementHealth() {
		
	}
	public void setUp(boolean param) {
		up=param;
	}
	public void setDown(boolean param) {
		down=param;
	}
	public void setRight(boolean param) {
		right=param;
	}
	public void setLeft(boolean param) {
		left=param;
	}
	public void setLeftMouse(boolean param) {
		leftMouse=param;
	}
}
