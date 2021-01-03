package kchandra423.weapons;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Sprite.Sprite;
import Sprite.SpriteCircle;
import Sprite.SpriteRectangle;
import Textures.Texture;
import kchandra423.graphics.TwodGameThing;
import kchandra423.shapes.Circle;
import kchandra423.shapes.Rectangle;
import kchandra423.shapes.Shape;
import kchandra423.weapons.projectiles.Bullet;
import kchandra423.weapons.projectiles.Projectile;
import processing.core.PApplet;

public class RangedWeapon implements Weapon{
	private Sprite sprite;
	private RangedWeaponMetadata data;
	private Texture projectileTexture;
	public RangedWeapon(int weaponNum, float x, float y) {
		data= new RangedWeaponMetadata(weaponNum);
		Texture t;
//		if(data.isCenter()) {
		t= Texture.TextureBuilder.getTexture("res/Images/Weapons/"+data.getName()+".png");
//		}else {
//		t= Texture.TextureBuilder.getTexture("res/Images/Weapons/"+data.getName()+".png", Texture.drawMode.CORNER);
//		}
		Rectangle r= new Rectangle(x,y+30,t.getWidth(),t.getHeight());
		sprite= new SpriteRectangle(t,r,data.isCenter());
		projectileTexture=	 Texture.TextureBuilder.getTexture("res/Images/Projectiles/Bullet.png");

	}
	@Override
	public void draw(PApplet p) {
		ArrayList<Projectile> removes= new ArrayList<Projectile>();
		for(int i=0;i< data.getProjectiles().size();i++) {
			Projectile b= data.getProjectiles().get(i);
			if(b.isActive()) {
				b.act();
				b.draw(p);
			}else {
				removes.add(b);

			}
			
		}
			
		data.getProjectiles().removeAll(removes);
//		System.out.println(data.getProjectiles().size());
				sprite.draw(p);

	}
	public void act() {
//		if(data.getMagazine()<=0) {
//			if(!reloading) {
//				
//				reload();
//				System.out.println("auto reloading");
//				firing=false;
//			}
//			
//		}if(reloading&&magazine>0) {
//			System.out.println("reloading cancelled");
//					timeSinceReloaded=0;
//					reloadTimer.cancel();
//					reloadTimer= new Timer();
//					reloading=false;
//					firing=false;
//		}
//		if(((System.currentTimeMillis() - lastTimeShot) > fireRate*1000)&&!reloading) { // hold to fire
//            lastTimeShot = System.currentTimeMillis();
//            firing=true;
//    		double initialx=body.getTop().getx2();
//    		double initialy=body.getTop().gety2(); 
//
//    		double tempAngle=angle;
//    		if(Math.random()>=0.5) {
//    			tempAngle+=Math.random()*spread/2;
//    		}else {
//    			tempAngle-=Math.random()*spread/2;
//    		}
//    		//makes the bullets
//    		Bullet p=new Bullet((double)initialx,(double)initialy,
//    				projectileVelocity, (double)tempAngle, 0, 
//    				0, TwodGameThing.BOUNDSX, TwodGameThing.BOUNDSY);
//    		addProjectile(p);
//    		magazine--;
//    		
//        }
		if(data.canFire()&&data.isTriggerPressed()) {
			fire();
		}else if(data.getMagazine()>0&&data.isReloading()&&data.isTriggerPressed()) {
			data.getReloadTask().cancel();
			fire();
		}else if(data.getMagazine()<=0) {
			reload();
		}
	}
	private void fire() {
		for(int i=0;i<data.getPellets();i++) {
		float tempAngle=(float) data.getAngle();
		if(Math.random()>=0.5) {
			tempAngle+=Math.random()*data.getSpread()/2;
		}else {
			tempAngle-=Math.random()*data.getSpread()/2;
		}
		Projectile p=new Projectile((float)data.getProjectileVelocity(),tempAngle, new SpriteCircle(projectileTexture, new Circle(sprite.getX(),sprite.getY(),projectileTexture.getWidth())));
		
		data.getProjectiles().add(p);
		
		}
		data.setLastTimeShot(System.currentTimeMillis());
		data.setMagazine(data.getMagazine()-1);
		System.out.println(data.getMagazine());
	}
	@Override
	public void shift(float xAmount,float yAmount) {

		sprite.shift(xAmount,yAmount);
	}

	
	@Override
	public void reload() {
		if(data.getMagazine()<data.getMagazineSize()&&!data.isReloading()) {
		data.setTimeSinceReloaded(System.currentTimeMillis());
		data.setReloading(true);
		TimerTask t= new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int dif= data.getMagazineSize()-data.getMagazine();
				data.setMagazine(data.getMagazine()+dif);
				if(dif>data.getAmmo()) {
					dif=data.getAmmo();
				}
				data.setAmmo(data.getAmmo()-dif);
				data.setReloading(false);
				System.out.println("successfully reladed: ");
			}
			
		};
		data.setReloadTask(t);
		data.getReloadTimer().schedule(t, (long)data.getReloadTime()*1000);
		}
	}

	@Override
	public int getReloadPercentagee() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMagazine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMagazineSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHitStreak() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getKillStreak() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Sprite getSprite() {
		return sprite;
	}

	@Override
	public void pressTrigger() {
		data.setTriggerPressed(true);
		
	}

	@Override
	public void releaseTrigger() {
		data.setTriggerPressed(false);
		
	}

	@Override
	public void setAngle(double theta) {
//		double change= theta-data.getAngle();
//		sprite.getTexture().setAngle((float)theta);
//		Shape s= sprite.getShape();
//		s.rotate(s.getX(), s.getY(), change);
		data.setAngle((float) theta);
		sprite.setAngle((float)theta);
	}

//	@Override
//	public void moveTo(float x, float y) {
//		sprite.setX(x);
//		sprite.setY(y);
//		
//	}

	@Override
	public ArrayList<Projectile> getProjectiles() {
		// TODO Auto-generated method stub
		return data.getProjectiles();
	}
	
	
}
