package kchandra423.players;


import java.util.ArrayList;
import java.util.Timer;

import kchandra423.graphics.TwodGameThing;
import kchandra423.projectiles.Bullet;
import kchandra423.projectiles.Projectile;
import kchandra423.projectiles.ThrowingKnife;
import kchandra423.shapes.Circle;
import kchandra423.utility.Calculator;
import kchandra423.weapons.FullAutoGun;
import kchandra423.weapons.Weapon;
import processing.core.PApplet;

public abstract class Player {
	protected Circle body;
	protected float damageMultiplier;
	protected float defenseMultiplier;
	protected Weapon w;
	private int health;
	private boolean isDead;
	protected double angle;
	protected  float velocityX;
	protected float velocityY;
	protected float acceleration;
	protected boolean up;
	protected boolean left;
	protected boolean down;
	protected boolean right;
//	private boolean leftMouse;
	protected int abilityOverRidingWeapon=0;

	protected long timeSinceUsedAbility1;
	protected boolean ability1OnCooldown=false;
	protected float ability1Cooldown=3;
	protected Timer ability1CooldownTimer=new Timer();
	protected long timeSinceUsedAbility2;
	protected boolean ability2OnCooldown=false;
	protected float ability2Cooldown=1;
	protected Timer ability2CooldownTimer=new Timer();
	protected long timeSinceUsedAbility3;
	protected boolean ability3OnCooldown=false;
	protected float ability3Cooldown=6;
	protected Timer ability3CooldownTimer=new Timer();
	protected int superCooldown;
	protected ArrayList<Projectile> abilityProjectiles= new ArrayList<Projectile>();
	public Player() {
		body=new Circle(10,10,10);
		w=new FullAutoGun();
		health=100;
		isDead=false;
		velocityX=0;
		velocityY=0;
		acceleration=0.5f;
	    up=false;
	    down=false;
	    left=false;
	    right=false;
//	    abilityCooldown=1*1000;
	    superCooldown=5*1000;
	    damageMultiplier=1.0f;
	    defenseMultiplier=1.0f;
	}
	public void setup(PApplet p) {
		
	}
	public void draw(PApplet p) {
		angle=Calculator.calculateAngle(body.getX(), body.getY(), p.mouseX, p.mouseY);
		w.setAngle(angle);
		move();
		usePassive();
//		if(leftMouse) {
//			useWeapon();
//		}
		body.draw(p);
		w.draw(p);
		for (int i=0; i<abilityProjectiles.size();i++) {
			Projectile cur= abilityProjectiles.get(i);
			if(cur.isActive()==false) {
				abilityProjectiles.remove(cur);
			}
			else {
			cur.draw(p);
			}
//			projectiles.get(i).move();
		}
		int reloadPercentage= w.getTimeToFinishReload();
		int ability1Percentage=getAbility1Cooldown();
		int ability2Percentage=getAbility2Cooldown();
		int ability3Percentage=getAbility3Cooldown();
		p.pushStyle();
		if(reloadPercentage<=100) {

		
		p.noFill();
		p.ellipseMode(p.CENTER);
		p.stroke(255, 0, 0, 100);
		p.strokeWeight(3);
		p.arc((float)body.getX(), (float)body.getY(),25 , 25, 0,(float)(Math.PI*2*reloadPercentage/100));
		p.strokeWeight(1);
		}
		
		p.noFill();
		p.stroke(0);
		p.rect(20, TwodGameThing.BOUNDSY-100, 30, 10,7);
		
		if(ability1Percentage<=100) {

			
			p.fill(255,0,0,ability1Percentage*255/100f);
			
			p.stroke(255, 0, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-99, ability1Percentage*29/100f, 9,7);
				
			}
		else {
			
			p.fill(0,255,0,255);
			
			p.stroke(0, 255, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-99, 29, 9,7);
				
		}
		
		p.noFill();
		p.stroke(0);
		p.rect(20, TwodGameThing.BOUNDSY-70, 30, 10,7);
		
		if(ability2Percentage<=100) {

			
			p.fill(255,0,0,ability2Percentage*255/100f);
			
			p.stroke(255, 0, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-69, ability2Percentage*29/100f, 9,7);
				//haha funny number
			}
		else {
			
			p.fill(0,255,0,255);
			
			p.stroke(0, 255, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-69, 29, 9,7);
				
		}
		
		p.noFill();
		p.stroke(0);
		p.rect(20, TwodGameThing.BOUNDSY-40, 30, 10,7);
		
		if(ability3Percentage<=100) {

			
			p.fill(255,0,0,ability3Percentage*255/100f);
			
			p.stroke(255, 0, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-39, ability3Percentage*29/100f, 9,7);
				//haha funny number
			}
		else {
			
			p.fill(0,255,0,255);
			
			p.stroke(0, 255, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-39, 29, 9,7);
				
		}
		
		
		p.fill(0);
		p.textSize(20);
		p.textAlign(p.CENTER);
		p.text(""+w.getMagazine()+" | "+w.getMagazineSize(), TwodGameThing.BOUNDSX/2, TwodGameThing.BOUNDSY-50);
		p.popStyle();

	}
	public void reload() {
		w.reload();
		System.out.println("manually reloaded");
	}
	public void equipWeapon() {
		
	}
	public void pressTrigger() {
		switch(abilityOverRidingWeapon) {
		case 0:
			w.pressTrigger();
			break;
		case 1:
			useAbility1();
			break;
		case 2:
			useAbility2();
			break;
		case 3:
			useAbility3();
			break;
			
		}
		
	}
	public void releaseTrigger() {
		w.releaseTrigger();
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
	
//	public void useWeapon() {
//		w.use();
//	}
	
	
	
	public abstract void usePassive();
	
	public abstract int getAbility1Cooldown();
	
	public abstract int getAbility2Cooldown();
	
	public abstract int getAbility3Cooldown();
	
	public abstract void useAbility1();
	
	public abstract void useAbility2();
	
	public abstract void useAbility3();
	
	
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
//	public void setLeftMouse(boolean param) {
//		leftMouse=param;
//	}
	public void resetDamageMultiplier() {
		damageMultiplier=1;
		//will change to whatever your default damage multiplier is later
	}
}
