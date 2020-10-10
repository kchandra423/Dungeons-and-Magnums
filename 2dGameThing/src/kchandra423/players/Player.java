package kchandra423.players;

import java.util.ArrayList;
import java.util.Timer;
import kchandra423.graphics.TwodGameThing;
import kchandra423.obstacles.Obstacle;
import kchandra423.projectiles.Projectile;
import kchandra423.shapes.Circle;
import kchandra423.utility.Calculator;
import kchandra423.weapons.SemiAutoGun;
import kchandra423.weapons.Weapon;
import processing.core.PApplet;
import processing.core.PConstants;

public abstract class Player {
	private Circle body;
	private float gunMultiplier;
	private float magicWeaponMultiplier;
	private float meleeWeaponMultiplier;
	private float shortRangeMultiplier;
	private float longRangeMultiplier;
	private float physicalDefenseMultiplier;
	private float magicDefenseMultiplier;
	private Weapon currentWeapon;
	private ArrayList<Weapon> weapons;
	private int health;
	private int maxHealth;
	private int shield;
	private int maxShield;
	private boolean isDead;
	private double angle;
	private float velocityX;
	private float velocityY;
	private float acceleration;
	private boolean up;
	private boolean left;
	private boolean down;
	private boolean right;
	private int abilityOverRidingWeapon=0;
	private long timeSinceUsedAbility1;
	private boolean ability1OnCooldown;
	private float ability1Cooldown;
	private Timer ability1CooldownTimer;
	private long timeSinceUsedAbility2;
	private boolean ability2OnCooldown;
	private float ability2Cooldown;
	private Timer ability2CooldownTimer;
	private long timeSinceUsedAbility3;
	private boolean ability3OnCooldown;
	private float ability3Cooldown;
	private Timer ability3CooldownTimer;
	private long timeSinceUsedSuper;
	private Timer superCooldownTimer;
	private int superCooldown;
	private ArrayList<Projectile> abilityProjectiles;
	public Player(Player other) throws Exception {
		body=new Circle(10,10,10);
		weapons= new ArrayList<Weapon>();
		abilityProjectiles=new ArrayList<Projectile>();
		currentWeapon=new SemiAutoGun();
		setDead(false);
		setVelocityX(0);
		setVelocityY(0);
		setAcceleration(0.5f);
	    up=false;
	    down=false;
	    left=false;
	    right=false;
	    ability1CooldownTimer=new Timer();
	    ability2CooldownTimer=new Timer();
	    ability3CooldownTimer=new Timer();
	    ability1OnCooldown=false;
	    ability2OnCooldown=false;
	    ability3OnCooldown=false;
	    if(other instanceof Rogue) {
	    	setGunMultiplier(1.5f);
	    	setMeleeWeaponMultiplier(1);
	    	setMagicWeaponMultiplier(1);
	    	setShortRangeMultiplier(1);
	    	setLongRangeMultiplier(1.25f);
	    	setPhysicalDefenseMultiplier(1);
	    	setMagicDefenseMultiplier(1);
	    	health=100;
	    	maxHealth=100;
	    	shield=120;
	    	maxShield=120;
	    	setAcceleration(0.75f);
	    	ability1Cooldown=2;
	    	ability2Cooldown=5;
	    	ability3Cooldown=15;
	    	setSuperCooldown(120);
	    }
	    else if(other instanceof Knight) {
	    	setGunMultiplier(1);
	    	setMeleeWeaponMultiplier(1.5f);
	    	setMagicWeaponMultiplier(0.5f);
	    	setShortRangeMultiplier(1.5f);
	    	setLongRangeMultiplier(0.5f);
	    	setPhysicalDefenseMultiplier(1.5f);
	    	setMagicDefenseMultiplier(0.75f);
	    	health=125;
	    	maxHealth=125;
	    	shield=150;
	    	maxShield=150;
	    	setAcceleration(0.5f);
	    	ability1Cooldown=3;
	    	ability2Cooldown=5;
	    	ability3Cooldown=15;
	    	setSuperCooldown(240);
	    }
	    else if (other instanceof Mage) {
	    	setGunMultiplier(0.75f);
	    	setMeleeWeaponMultiplier(0.5f);
	    	setMagicWeaponMultiplier(3f);
	    	setShortRangeMultiplier(0.75f);
	    	setLongRangeMultiplier(1f);
	    	setPhysicalDefenseMultiplier(0.75f);
	    	setMagicDefenseMultiplier(2f);
	    	health=75;
	    	maxHealth=75;
	    	shield=100;
	    	maxShield=100;
	    	setAcceleration(0.625f);
	    	ability1Cooldown=2;
	    	ability2Cooldown=5;
	    	ability3Cooldown=15;
	    	setSuperCooldown(100);
	    }
	    else {
	    	throw new Exception ("A player of unspecified type was initialized");
	    }
	}
	public void draw(PApplet p) {
		angle=(Calculator.calculateAngle(body.getX(), body.getY(), p.mouseX, p.mouseY));
		currentWeapon.setAngle(getAngle());
		usePassive();
		body.draw(p);
		currentWeapon.draw(p);
		int reloadPercentage= currentWeapon.getTimeToFinishReload();
		int ability1Percentage=getAbility1Cooldown();
		int ability2Percentage=getAbility2Cooldown();
		int ability3Percentage=getAbility3Cooldown();
		p.pushStyle();
		if(reloadPercentage<=100) {
		p.noFill();
		p.ellipseMode(PConstants.CENTER);
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
			//haha funny number
		}
		p.noFill();
		p.stroke(0);
		p.rect(20, TwodGameThing.BOUNDSY-40, 30, 10,7);
		if(ability3Percentage<=100) {
			p.fill(255,0,0,ability3Percentage*255/100f);
			p.stroke(255, 0, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-39, ability3Percentage*29/100f, 9,7);
			}
		else {
			p.fill(0,255,0,255);
			p.stroke(0, 255, 0, 100);
			p.rect(21, TwodGameThing.BOUNDSY-39, 29, 9,7);
		}
		p.fill(0);
		p.textSize(20);
		p.textAlign(PConstants.CENTER);
		p.text(""+currentWeapon.getMagazine()+" | "+currentWeapon.getMagazineSize(), TwodGameThing.BOUNDSX/2, TwodGameThing.BOUNDSY-50);
		p.popStyle();

	}
	
	public void moveX() {
		float xamount=0;
		if((!right&&!left)||(left&&right)) {
			if(getVelocityX()!=0) {
				setVelocityX((float) (getVelocityX() * 0.9));
			}	
		}
		else if(right) {
			setVelocityX(getVelocityX() + getAcceleration());
		}
		else if(left) {
			setVelocityX(getVelocityX() - getAcceleration());
		}
		if(getVelocityX()>5) {
			setVelocityX(getVelocityX() - getAcceleration());
		 }
		 else if(getVelocityX()<-5) {
			setVelocityX(getVelocityX() + getAcceleration());
		 }
		xamount=getVelocityX();
		body.shiftX(xamount);
		currentWeapon.shiftX(xamount);
	}
	public void moveY() {
		float yamount=0;
		if((!up&&!down)||(up&&down)) {
			if(getVelocityY()!=0) {
				setVelocityY((float) (getVelocityY() * 0.9));
			}
		}
		else if(down) {
			setVelocityY(getVelocityY() + getAcceleration());
		}
		else  if(up) {
			setVelocityY(getVelocityY() - getAcceleration());
		}
		if(getVelocityY()>5) {
			setVelocityY(getVelocityY() - getAcceleration());
		}
		else if(getVelocityY()<-5) {
			setVelocityY(getVelocityY() + getAcceleration());
		 }
		 yamount=getVelocityY();
		 body.shiftY(yamount);
		 currentWeapon.shiftY(yamount);
	}

	public void moveBackX(Obstacle o) {
		if(getVelocityX()<0) {
			body.shiftX(-(getVelocityX()-body.getRadius()));
			currentWeapon.shiftX((float)(-(getVelocityX()-body.getRadius())));
			while(!(body.intersectsEdge(o.getRect()))) {
				body.shiftX(-1);
				currentWeapon.shiftX(-1);
			}
			body.shiftX(1);
			currentWeapon.shiftX(1);
		}
		else {
			body.shiftX(-(getVelocityX()+body.getRadius()));
			currentWeapon.shiftX((float)(-(getVelocityX()+body.getRadius())));
			
			while(!(body.intersectsEdge(o.getRect()))) {
				body.shiftX(1);
				currentWeapon.shiftX(1);
			}
			body.shiftX(-1);
			currentWeapon.shiftX(-1);
		}
			
		
		setVelocityX(-getVelocityX()/3);
	}
	public void moveBackY(Obstacle o) {
		if(getVelocityY()<0) {
			body.shiftY(-(getVelocityY()-body.getRadius()));
			currentWeapon.shiftY((float)(-(getVelocityY()-body.getRadius())));
			while(!(body.intersectsEdge(o.getRect()))) {
				body.shiftY(-1);
				currentWeapon.shiftY(-1);
			}
			body.shiftY(1);
			currentWeapon.shiftY(1);
		}
		else {
			body.shiftY(-(getVelocityY()+body.getRadius()));
			currentWeapon.shiftY((float)(-(getVelocityY()+body.getRadius())));
			while(!(body.intersectsEdge(o.getRect()))) {
				body.shiftY(1);
				currentWeapon.shiftY(1);
			}
			body.shiftY(-1);
			currentWeapon.shiftY(-1);
		}
		setVelocityY(-getVelocityY()/3);
	}
	public void reload() {
		currentWeapon.reload();
		System.out.println("manually reloaded");
	}
	public void equipWeapon() {
		
	}
	public void pressTrigger() {
		switch(getAbilityOverRidingWeapon()) {
		case 0:
			currentWeapon.pressTrigger();
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
		currentWeapon.releaseTrigger();
	}

	public abstract void usePassive();
	public int getAbility1Cooldown() {
		int percentage = (int) ((System.currentTimeMillis() - getTimeSinceUsedAbility1()) / (ability1Cooldown * 10));
		return percentage;
	}
	public int getAbility2Cooldown() {
		int percentage = (int) ((System.currentTimeMillis() - getTimeSinceUsedAbility2()) / (ability2Cooldown * 10));
		return percentage;
	}
	public int getAbility3Cooldown() {
		int percentage = (int) ((System.currentTimeMillis() - getTimeSinceUsedAbility3()) / (ability3Cooldown * 10));
		return percentage;
	}
	public ArrayList<Projectile> getProjectiles(){
		ArrayList<Projectile> answer= new ArrayList<Projectile>();
		answer.addAll(getAbilityProjectiles());
		answer.addAll(currentWeapon.getProjectiles());
		return answer;
		
	}
	public abstract void useAbility1();
	public abstract void useAbility2();
	public abstract void useAbility3();
	public abstract void useSuper();
	
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
	public Circle getBody() {
		return body;
	}
	public float getGunMultiplier() {
		return gunMultiplier;
	}
	public void setGunMultiplier(float gunMultiplier) {
		this.gunMultiplier = gunMultiplier;
	}
	public float getMagicWeaponMultiplier() {
		return magicWeaponMultiplier;
	}
	public void setMagicWeaponMultiplier(float magicWeaponMultiplier) {
		this.magicWeaponMultiplier = magicWeaponMultiplier;
	}
	public float getMeleeWeaponMultiplier() {
		return meleeWeaponMultiplier;
	}
	public void setMeleeWeaponMultiplier(float meleeWeaponMultiplier) {
		this.meleeWeaponMultiplier = meleeWeaponMultiplier;
	}
	public float getShortRangeMultiplier() {
		return shortRangeMultiplier;
	}
	public void setShortRangeMultiplier(float shortRangeMultiplier) {
		this.shortRangeMultiplier = shortRangeMultiplier;
	}
	public float getLongRangeMultiplier() {
		return longRangeMultiplier;
	}
	public void setLongRangeMultiplier(float longRangeMultiplier) {
		this.longRangeMultiplier = longRangeMultiplier;
	}
	public float getPhysicalDefenseMultiplier() {
		return physicalDefenseMultiplier;
	}
	public void setPhysicalDefenseMultiplier(float physicalDefenseMultiplier) {
		this.physicalDefenseMultiplier = physicalDefenseMultiplier;
	}
	public float getMagicDefenseMultiplier() {
		return magicDefenseMultiplier;
	}
	public void setMagicDefenseMultiplier(float magicDefenseMultiplier) {
		this.magicDefenseMultiplier = magicDefenseMultiplier;
	}
	public int getHealth() {
		return health;
	}
	public void incrementHealth(int health) {
		this.health+=health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getShield() {
		return shield;
	}
	public void incrementShield(int shield) {
		this.shield+=shield;
	}
	public int getMaxShield() {
		return maxShield;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public int getAbilityOverRidingWeapon() {
		return abilityOverRidingWeapon;
	}
	public void setAbilityOverRidingWeapon(int abilityOverRidingWeapon) {
		this.abilityOverRidingWeapon = abilityOverRidingWeapon;
	}
	public ArrayList<Projectile> getAbilityProjectiles() {
		return abilityProjectiles;
	}
	public double getAngle() {
		return angle;
	}
	public boolean isAbility3OnCooldown() {
		return ability3OnCooldown;
	}
	public void setAbility3OnCooldown(boolean ability3OnCooldown) {
		this.ability3OnCooldown = ability3OnCooldown;
	}
	public Timer getAbility3CooldownTimer() {
		return ability3CooldownTimer;
	}
	public long getTimeSinceUsedAbility3() {
		return timeSinceUsedAbility3;
	}
	public void setTimeSinceUsedAbility3(long timeSinceUsedAbility3) {
		this.timeSinceUsedAbility3 = timeSinceUsedAbility3;
	}
	public Timer getAbility2CooldownTimer() {
		return ability2CooldownTimer;
	}
	public boolean isAbility2OnCooldown() {
		return ability2OnCooldown;
	}
	public void setAbility2OnCooldown(boolean ability2OnCooldown) {
		this.ability2OnCooldown = ability2OnCooldown;
	}
	public long getTimeSinceUsedAbility2() {
		return timeSinceUsedAbility2;
	}
	public void setTimeSinceUsedAbility2(long timeSinceUsedAbility2) {
		this.timeSinceUsedAbility2 = timeSinceUsedAbility2;
	}
	public Timer getSuperCooldownTimer() {
		return superCooldownTimer;
	}
	public int getSuperCooldown() {
		return superCooldown;
	}
	public void setSuperCooldown(int superCooldown) {
		this.superCooldown = superCooldown;
	}
	public long getTimeSinceUsedSuper() {
		return timeSinceUsedSuper;
	}
	public void setTimeSinceUsedSuper(long timeSinceUsedSuper) {
		this.timeSinceUsedSuper = timeSinceUsedSuper;
	}
	public boolean isAbility1OnCooldown() {
		return ability1OnCooldown;
	}
	public void setAbility1OnCooldown(boolean ability1OnCooldown) {
		this.ability1OnCooldown = ability1OnCooldown;
	}
	public Timer getAbility1CooldownTimer() {
		return ability1CooldownTimer;
	}
	public long getTimeSinceUsedAbility1() {
		return timeSinceUsedAbility1;
	}
	public void setTimeSinceUsedAbility1(long timeSinceUsedAbility1) {
		this.timeSinceUsedAbility1 = timeSinceUsedAbility1;
	}
	public float getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}
	public float getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}
	public float getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}
	public void incrementAllAttackStats(float buff) {
		
	}
	public void incrementAllDefenseStats(float buff) {
		
	}
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	
}
