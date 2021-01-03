package kchandra423.weapons.projectiles;

public class AbilityBullet extends Bullet{
	Object synchronize;
	public AbilityBullet(double x1, double y1, double v, double theta, int boundsX1, int boundsY1, int boundsX2,
			int boundsY2, Object o) {
		super(x1, y1, v, theta, boundsX1, boundsY1, boundsX2, boundsY2);
		synchronize=o;
//		hitEnemy=true;
		// TODO Auto-generated constructor stub
	}
	public void setInactive() {
		synchronized(synchronize){
			synchronize.notify();
			}
		isActive=false;
		
	}

}
