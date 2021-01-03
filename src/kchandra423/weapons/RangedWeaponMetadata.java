package kchandra423.weapons;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kchandra423.files.FileManager;
import kchandra423.weapons.projectiles.Projectile;

public class RangedWeaponMetadata {
	private final String name;
	private final int id;
	private final float projectileVelocity;
	private final float fireRate;
	private final int reloadTime;
	private double angle;
	private int magazine;
	private final int magazineSize;
	private TimerTask reloadTask;
	private Timer reloadTimer;
	private boolean reloading;
	private int ammo;
	private int pellets;
	private final int maxAmmo;
	private long lastTimeShot;
	private final float spread;
	private long timeSinceReloaded;
	private int hitStreak;
	private int killStreak;
	private boolean triggerPressed;
	private final boolean center;
	private ArrayList<Projectile> projectiles= new ArrayList<Projectile>();
	public RangedWeaponMetadata(int weaponId) {
		this.id=weaponId;
		reloading=false;
		setAngle(0);
		setLastTimeShot(System.currentTimeMillis());
		setTriggerPressed(false);
		reloadTimer=new Timer();
//		reloadTask= new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				int dif= magazineSize-magazine;
//				magazine+=dif;
//				if(dif>ammo) {
//					dif=ammo;
//				}
////				magazine=magazineSize;
//				ammo-=dif;
//				reloading=false;
//				System.out.println("successfully reladed: "+magazine);
//			}
//			
//		};
//		firing=false;
		FileManager file = new FileManager("res/GameConstants/WeaponStats",true);
		String s=null;
		try {
			s=file.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			file.close();
		}
		String[] split=s.split("\n")[weaponId-1].split(",");
		name=split[0];
		this.projectileVelocity=Float.parseFloat(split[1]);
		this.fireRate= Float.parseFloat(split[2]);
		reloadTime= Integer.parseInt(split[3]);
		magazineSize= Integer.parseInt(split[4]);
		maxAmmo= Integer.parseInt(split[5]);
		spread= Float.parseFloat(split[6]);
		pellets= Integer.parseInt(split[7]);
		center= Boolean.parseBoolean(split[8]);
		ammo=maxAmmo;
		magazine= magazineSize;
		hitStreak=0;
		setKillStreak(0);
		
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public float getProjectileVelocity() {
		return projectileVelocity;
	}
	public float getFireRate() {
		return fireRate;
	}
	public int getReloadTime() {
		return reloadTime;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void setMagazine(int magazine) {
		this.magazine=magazine;
	}
	public int getMagazine() {
		return magazine;
	}
	public int getMagazineSize() {
		return magazineSize;
	}
	public void setAmmo(int ammo) {
		this.ammo=ammo;
	}
	public int getAmmo() {
		return ammo;
	}
	public int getMaxAmmo() {
		return maxAmmo;
	}
	public float getSpread() {
		return spread;
	}
	public int getHitStreak() {
		return hitStreak;
	}
	public void setHitStreak(int hitStreak) {
		this.hitStreak = hitStreak;
	}
	public long getTimeSinceReloaded() {
		return timeSinceReloaded;
	}
	public void setTimeSinceReloaded(long timeSinceReloaded) {
		this.timeSinceReloaded = timeSinceReloaded;
	}
	public boolean isTriggerPressed() {
		return triggerPressed;
	}
	public void setTriggerPressed(boolean triggerPressed) {
		this.triggerPressed = triggerPressed;
	}
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	public int getKillStreak() {
		return killStreak;
	}
	public void setKillStreak(int killStreak) {
		this.killStreak = killStreak;
	}
	public boolean canFire() {
		if(magazine>0&&ammo>0) {
			if(System.currentTimeMillis()-lastTimeShot>=fireRate*1000) {
				if(!isReloading()) {
				return true;
				}
			}
		}
		return false;
	}
	public void setReloading(boolean reloading) {
		this.reloading=reloading;
	}
	public boolean isReloading() {
		return reloading;
	}
	public long getLastTimeShot() {
		return lastTimeShot;
	}
	public void setLastTimeShot(long lastTimeShot) {
		this.lastTimeShot = lastTimeShot;
	}
	public void setReloadTask(TimerTask t) {
		reloadTask=t;
	}
	public TimerTask getReloadTask() {
		return reloadTask;
	}
	public Timer getReloadTimer() {
		return reloadTimer;
	}
	public boolean isCenter() {
		return center;
	}
	public int getPellets() {
		return pellets;
	}
}
