package kchandra423.entities.actors;
import java.util.Arrays;

import Sprite.Sprite;
import kchandra423.entities.Entity;
import kchandra423.graphics.DrawingSurface;

public abstract class Actor extends Entity{
	private float vx,vy;
	private float maxV;
	private float accel;
	
	public Actor(Sprite sprite,float maxV, float accel) {
		super(sprite);
		this.maxV=maxV;
		
		this.accel=accel;
	}
	public void bounceBackX() {
		getSprite().shift(-getVx(),0);
		setVx(getVx()*-0.3f);
		
	}
	public void bounceBackY() {
		getSprite().shift(0,-getVy());
		setVy(getVy()*-0.3f);
	}
	public abstract void act(DrawingSurface d);
	//array format is  left, right 
	protected void moveX(boolean[] directions) {
//		int up = directions[0]?-1:0;
//		int down = directions[2]?1:0;
		int left = directions[0]?-1:0;
		int right = directions[1]?1:0;
		int netX=left+right;
		
		float newVx=getVx()+netX*getAccel();
		
		if(Math.abs(newVx)<maxV) {
			setVx(newVx);
		}else if(newVx<0){
			setVx(-maxV);
		}else {
			setVx(maxV);
		}
		
		
		
		
		

		setVx(getVx()*0.9f);
		
		getSprite().shift(getVx(),0);
//		if(getVx()<0) {
//
//			
//			getSprite().setReversed(true);
//		}else {
//			getSprite().setReversed(false);
//		}

	}
	//up, down
	protected void moveY(boolean[] directions) {

		int up = directions[0]?-1:0;
		int down = directions[1]?1:0;
		int netY=up+down;
		float newVy=getVy()+netY*getAccel();
		if(Math.abs(newVy)<maxV) {
			setVy(newVy);
		}else if(newVy<0){
			setVy(-maxV);
		}else {
			setVy(maxV);
		}
		setVy(getVy()*0.9f);
		getSprite().shift(0,getVy());

	}
	
	public float getVy() {
		return vy;
	}
	protected void setVy(float velocityY) {
		this.vy = velocityY;
	}
	public float getVx() {
		return vx;
	}
	protected void setVx(float velocityX) {
		this.vx = velocityX;
	}
	public float getAccel() {
		return accel;
	}
	protected void setAccel(float accel) {
		this.accel=accel;
	}
	public float getMaxV() {
		return maxV;
	}
	
}
