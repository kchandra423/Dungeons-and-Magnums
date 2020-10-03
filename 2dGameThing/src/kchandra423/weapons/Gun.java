package kchandra423.weapons;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kchandra423.graphics.TwodGameThing;
import kchandra423.projectiles.Bullet;
import kchandra423.shapes.Rectangle;
import processing.core.PApplet;

public class Gun implements Weapon {
	private float projectileVelocity;
	private float fireRate;
	private int reloadTime;
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
	private ArrayList<Bullet> bullets= new ArrayList<Bullet>();
	public Gun() {
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
		body.draw(p);
		for (int i=0; i<bullets.size();i++) {
			Bullet cur= bullets.get(i);
			if(cur.isActive()==false) {
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

	@Override
	public void use(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		if(magazine<=0) {
			if(!reloading) {
				
				reload();
				System.out.println("auto reloading");
			}
			
		}if(reloading&&magazine>0) {
			System.out.println("reloading cancelled");
			
					reloadTimer.cancel();
					reloadTimer= new Timer();
					reloading=false;
		}
		if(((System.nanoTime() - lastTimeShot) > fireRate*1000000000)&&!reloading) { // hold to fire
            lastTimeShot = System.nanoTime();
            
            double initialx, initialy, displacementx, displacementy,angle;
    		initialx=body.getTop().getx2();
    		initialy=body.getTop().gety2(); 
    		//literally just physics
    		displacementx=mouseX-initialx;
    		displacementy=mouseY-initialy;
    		angle=Math.atan(displacementy/displacementx);
    	
    		if(displacementx<0) {
    			//remeber that the domain of arctan is the first and 4th quadrants, so if cos is negative,
    			// you must add 180 degs
    			angle+=Math.PI;
    		}
    		if(Math.random()>=0.5) {
    			angle+=Math.random()*spread/2;
    		}else {
    			angle-=Math.random()*spread/2;
    		}
    		//makes the bullets
    		Bullet p=new Bullet((double)initialx,(double)initialy,
    				projectileVelocity, (double)angle, 0, 
    				0, TwodGameThing.BOUNDSX, TwodGameThing.BOUNDSY);
    		addProjectile(p);
    		magazine--;
    		
        }
	
		
		
	}
	public void reload() {
		System.out.println(magazine);
		if(magazine<magazineSize&&!reloading) {
			reloading=true;
			timeSinceReloaded=System.nanoTime();
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
		int percentage=(int)((System.nanoTime()-timeSinceReloaded)/(reloadTime*10000000));
		
		return percentage;
	}
	
}
