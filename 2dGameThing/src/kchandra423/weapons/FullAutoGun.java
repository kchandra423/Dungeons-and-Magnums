package kchandra423.weapons;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kchandra423.graphics.TwodGameThing;
import kchandra423.projectiles.Bullet;
import kchandra423.shapes.Rectangle;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

public class FullAutoGun implements Weapon {
	private float projectileVelocity;
	private float fireRate;
	private int reloadTime;
	private double angle;
	private double changeInAngle;
	private TimerTask reloadTask;
	private Timer reloadTimer;
	private int magazine;
	private final int magazineSize;
	private boolean reloading;
	private int ammo;
	private long lastTimeShot;
	private float spread;
	private long timeSinceReloaded;
	private Rectangle body;
	private int hitStreak;
	private boolean triggerPressed;
	private ArrayList<Bullet> bullets= new ArrayList<Bullet>();
	public FullAutoGun() {
		changeInAngle=0;
		angle=0;
		timeSinceReloaded=0;
		reloading=false;
		fireRate= 0.1f;//wait time between bullets in seconds
		reloadTime=3;//reload time in seconds
		reloadTimer=new Timer();
		magazineSize=20;
		magazine=magazineSize;
		spread=(float) (Math.PI/18);
		projectileVelocity=10;
		body= new Rectangle(10,10,7,3);
	}
	@Override
	public void draw(PApplet p) {
		// TODO Auto-generated method stub
		if(changeInAngle!=0) {
		body.rotateAboutTLCorner(changeInAngle);
		changeInAngle=0;
		}
		body.draw(p);
		if(triggerPressed) {
			use();
		}
		for (int i=0; i<bullets.size();i++) {
			Bullet cur= bullets.get(i);
			if(cur.isActive()==false) {
				if(cur.hasHitEnemy()) {
					hitStreak++;
				}else {
					hitStreak=0;
				}
				bullets.remove(cur);
			}
			else {
			cur.draw(p);
			}
//			projectiles.get(i).move();
		}
	}

	@Override
	public void shift(float xAmount, float yAmount) {
		// TODO Auto-generated method stub
		body.shift(xAmount, yAmount);
	}
	public void moveTo(float x, float y) {
		
	}
	public void setAngle(double theta) {
		
		if(angle!=theta) {
			changeInAngle=theta-angle;
			angle=theta;
			
		}
	}
	private void use() {
		// TODO Auto-generated method stub
		if(magazine<=0) {
			if(!reloading) {
				
				reload();
				System.out.println("auto reloading");
			}
			
		}if(reloading&&magazine>0) {
			System.out.println("reloading cancelled");
					timeSinceReloaded=0;
					reloadTimer.cancel();
					reloadTimer= new Timer();
					reloading=false;
		}
		if(((System.currentTimeMillis() - lastTimeShot) > fireRate*1000)&&!reloading) { // hold to fire
            lastTimeShot = System.currentTimeMillis();
            
//            double initialx, initialy, displacementx, displacementy,angle;
    		double initialx=body.getTop().getx2();
    		double initialy=body.getTop().gety2(); 
//    		//literally just physics
//    		displacementx=mouseX-initialx;
//    		displacementy=mouseY-initialy;
//    		angle=Math.atan(displacementy/displacementx);
//    	
//    		if(displacementx<0) {
//    			//remeber that the domain of arctan is the first and 4th quadrants, so if cos is negative,
//    			// you must add 180 degs
//    			angle+=Math.PI;
//    		}
//            double angle= Calculator.calculateAngle(initialx,
//            		initialy, mouseX, mouseY);
    		double tempAngle=angle;
    		if(Math.random()>=0.5) {
    			tempAngle+=Math.random()*spread/2;
    		}else {
    			tempAngle-=Math.random()*spread/2;
    		}
    		//makes the bullets
    		Bullet p=new Bullet((double)initialx,(double)initialy,
    				projectileVelocity, (double)tempAngle, 0, 
    				0, TwodGameThing.BOUNDSX, TwodGameThing.BOUNDSY);
    		addProjectile(p);
    		magazine--;
    		
        }
	
		
		
	}
	public void reload() {
		System.out.println(magazine);
		if(magazine<magazineSize&&!reloading) {
			reloading=true;
			timeSinceReloaded=System.currentTimeMillis();
			reloadTask=new TimerTask() {
				public void run() {
					int dif= magazineSize-magazine;
					magazine+=dif;
//					magazine=magazineSize;
					ammo-=dif;
					reloading=false;
					System.out.println("successfully reladed: "+magazine);
				}
			};
		reloadTimer.schedule(reloadTask, (long)(reloadTime*1000));
		}
	}
	public void addProjectile(Bullet p) {
		bullets.add(p);
		
	}
	public int getTimeToFinishReload() {
		//1000000000
		int percentage=(int)((System.currentTimeMillis()-timeSinceReloaded)/(reloadTime*10));
		
		return percentage;
	}
	public int getMagazine() {
		return magazine;
		
	}
	public int getMagazineSize() {
		return magazineSize;
		
	}
	@Override
	public int getHitStreak() {
		// TODO Auto-generated method stub
		return hitStreak;
	}
	@Override
	public void pressTrigger() {
		// TODO Auto-generated method stub
		triggerPressed=true;
	}
	@Override
	public void releaseTrigger() {
		// TODO Auto-generated method stub
		triggerPressed=false;
	}

	
}
