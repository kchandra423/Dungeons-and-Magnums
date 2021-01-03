package kchandra423.weapons;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kchandra423.graphics.TwodGameThing;
import kchandra423.shapes.Rectangle;
import kchandra423.weapons.projectiles.Bullet;
import kchandra423.weapons.projectiles.Projectile;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PShape;

public  class GunOutDated implements Weapon {
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
	private boolean firing;
	private PImage texture;
	private ArrayList<Bullet> bullets= new ArrayList<Bullet>();
	public GunOutDated() {
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
		if(firing) {
			texture=p.loadImage("res/firing.png");
		}else {
			texture=p.loadImage("res/notfiring.png");
		}
		PShape yes=  p.createShape(PConstants.RECT,20,20,50,50);
		yes.setTexture(texture);
		p.shape(yes);
		if(changeInAngle!=0) {
		body.rotateAboutTLCorner(changeInAngle);
		changeInAngle=0;
		}
		body.draw(p);
		if(triggerPressed) {
			use();
		}else {
			firing=false;
		}
		if(magazine<=0) {
			reload();
		}

		
	}

	
	public void shift(float xAmount, float yAmount) {
		// TODO Auto-generated method stub
		body.shift(xAmount, yAmount);
	}
	public void shiftX(float xAmount) {
		// TODO Auto-generated method stub
		body.shiftX(xAmount);
	}
	public void shiftY(float yAmount) {
		// TODO Auto-generated method stub
		body.shiftY(yAmount);
	}
	public void moveTo(float x, float y) {
		
	}
	public void setAngle(double theta) {
		
		if(angle!=theta) {
			changeInAngle=theta-angle;
			angle=theta;
			
		}
	}
	protected void use() {
		// TODO Auto-generated method stub
		if(magazine<=0) {
			if(!reloading) {
				
				reload();
				System.out.println("auto reloading");
				firing=false;
			}
		}if(reloading&&magazine>0) {
			System.out.println("reloading cancelled");
					timeSinceReloaded=0;
					reloadTimer.cancel();
					reloadTimer= new Timer();
					reloading=false;
					firing=false;
		}
		if(((System.currentTimeMillis() - lastTimeShot) > fireRate*1000)&&!reloading) { // hold to fire
            lastTimeShot = System.currentTimeMillis();
            firing=true;
    		double initialx=body.getTop().getx2();
    		double initialy=body.getTop().gety2(); 

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
//		System.out.println(magazine);
		if(magazine<magazineSize&&!reloading) {
			reloading=true;
			firing=false;
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
	@Override
	public ArrayList<Projectile> getProjectiles() {
		// TODO Auto-generated method stub
		ArrayList<Projectile> answer= new ArrayList<Projectile>();
		answer.addAll(bullets);
		return answer;
	}
}
