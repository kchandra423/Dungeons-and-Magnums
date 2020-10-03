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
	protected  float velocityX;
	protected float velocityY;
	protected float acceleration;
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
		acceleration=0.5f;
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
		int percentage= w.getTimeToFinishReload();
//		System.out.println(""+time);
		if(percentage<=100) {
//			p.stroke(0)
			p.pushStyle();
//		p.stroke(0);
//		p.textSize(12);	
//		p.textAlign(p.CENTER);
		p.noFill();
//			p.text(""+percentage, (float)body.getX(), (float)body.getY()+15);
		p.ellipseMode(p.CENTER);
		p.stroke(255, 0, 0, 100);
//		System.out.println(percentage);
		p.arc((float)body.getX(), (float)body.getY(),20 , 20, 0,(float)(Math.PI*2*percentage/100));
			p.popStyle();
		}
	}
	public void reload() {
		w.reload();
		System.out.println("manually reloaded");
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
		 
		 if(velocityY>5) {
			 velocityY-=0.75;
		 }
		 else if(velocityY<-5) {
			 velocityY+=0.75;
		 }
		 if(velocityX>5) {
			 velocityX-=0.75;
		 }
		 else if(velocityX<-5) {
			 velocityX+=0.75;
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
