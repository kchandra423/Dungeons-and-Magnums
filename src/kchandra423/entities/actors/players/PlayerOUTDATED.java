package kchandra423.entities.actors.players;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Timer;
import kchandra423.entities.obstacles.Obstacle;
import kchandra423.graphics.TwodGameThing;
import kchandra423.shapes.Circle;
import kchandra423.utility.Calculator;
import kchandra423.utility.Constants.subclasses;
import kchandra423.weapons.GunOutDated;
import kchandra423.weapons.SemiAutoGun;
import kchandra423.weapons.Weapon;
import kchandra423.weapons.projectiles.Projectile;
import processing.core.PApplet;
import processing.core.PConstants;
import kchandra423.utility.Constants.multipliers;
public abstract class PlayerOUTDATED {
	private Circle body;
	private EnumMap<multipliers, Float> statMultipliers;
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
	private boolean isInvincible;
	private boolean isVisible;
	private boolean up;
	private boolean left;
	private boolean down;
	private boolean right;
	private int abilityOverRidingWeapon=0;
	private AbilityMetadata ability1;
	private AbilityMetadata ability2;
	private AbilityMetadata ability3;
	private long timeSinceUsedSuper;
	private Timer superCooldownTimer;
	private int superCooldown;
	private ArrayList<Projectile> abilityProjectiles;
	public PlayerOUTDATED(subclasses subClass) throws Exception {
		body=new Circle(10,10,10);
		weapons= new ArrayList<Weapon>();
		abilityProjectiles=new ArrayList<Projectile>();
		currentWeapon=new SemiAutoGun();
		statMultipliers = new EnumMap<>(multipliers.class);
		setDead(false);
		setVelocityX(0);
		setVelocityY(0);
	    up=false;
	    down=false;
	    left=false;
	    right=false;
	    isInvincible=false;
	    isVisible=true;
	    switch (subClass){
	    case ROGUE: {
	    	statMultipliers.put(multipliers.RANGED, 1.5f);
	    	statMultipliers.put(multipliers.MELEE, 1f);
	    	statMultipliers.put(multipliers.MAGIC, 1f);
	    	statMultipliers.put(multipliers.SHORT, 1f);
	    	statMultipliers.put(multipliers.LONG, 1.25f);
	    	statMultipliers.put(multipliers.PHYSDEF, 1f);
	    	statMultipliers.put(multipliers.MAGDEF, 1f);
	    	health=100;
	    	maxHealth=100;
	    	shield=120;
	    	maxShield=120;
	    	setAcceleration(0.75f);
	    	ability1=new AbilityMetadata(2);
	    	ability2=new AbilityMetadata(5);
	    	ability3=new AbilityMetadata(15);
	    	setSuperCooldown(120);
	    	break;
	    	}
	    case KNIGHT: {
	    	statMultipliers.put(multipliers.RANGED, 1f);
	    	statMultipliers.put(multipliers.MELEE, 1.5f);
	    	statMultipliers.put(multipliers.MAGIC, 0.5f);
	    	statMultipliers.put(multipliers.SHORT, 1.5f);
	    	statMultipliers.put(multipliers.LONG, 0.5f);
	    	statMultipliers.put(multipliers.PHYSDEF, 1.5f);
	    	statMultipliers.put(multipliers.MAGDEF, 0.75f);
	    	health=125;
	    	maxHealth=125;
	    	shield=150;
	    	maxShield=150;
	    	setAcceleration(0.5f);
	    	ability1=new AbilityMetadata(3);
	    	ability2=new AbilityMetadata(5);
	    	ability3=new AbilityMetadata(15);
	    	setSuperCooldown(240);
	    	break;
	    }
		case MAGE:
			statMultipliers.put(multipliers.RANGED, 0.75f);
	    	statMultipliers.put(multipliers.MELEE, 0.5f);
	    	statMultipliers.put(multipliers.MAGIC, 3f);
	    	statMultipliers.put(multipliers.SHORT, 0.75f);
	    	statMultipliers.put(multipliers.LONG, 1f);
	    	statMultipliers.put(multipliers.PHYSDEF, 0.75f);
	    	statMultipliers.put(multipliers.MAGDEF, 2f);
	    	health=75;
	    	maxHealth=75;
	    	shield=100;
	    	maxShield=100;
	    	setAcceleration(0.625f);
	    	ability1= new AbilityMetadata(2);
	    	ability2=new AbilityMetadata(5);
	    	ability3=new AbilityMetadata(15);
	    	setSuperCooldown(100);
			break;
		default:
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
		int ability1Percentage=ability1.getAbilityCooldownPercentageRemaining();
		int ability2Percentage=ability2.getAbilityCooldownPercentageRemaining();
		int ability3Percentage=ability3.getAbilityCooldownPercentageRemaining();
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
	public EnumMap<multipliers, Float> getMultipliers() {
		return statMultipliers;
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
	public AbilityMetadata getAbility3Info() {
		return ability3;
	}
	public AbilityMetadata getAbility2Info() {
		return ability2;
		
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
	public AbilityMetadata getAbility1Info() {
		return ability1;
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
		statMultipliers.put(multipliers.RANGED, statMultipliers.get(multipliers.RANGED)+buff);
		statMultipliers.put(multipliers.MELEE, statMultipliers.get(multipliers.MELEE)+buff);
		statMultipliers.put(multipliers.MAGIC, statMultipliers.get(multipliers.MAGIC)+buff);
		statMultipliers.put(multipliers.SHORT, statMultipliers.get(multipliers.SHORT)+buff);
		statMultipliers.put(multipliers.LONG, statMultipliers.get(multipliers.LONG)+buff);
	}
	public void incrementAllDefenseStats(float buff) {
		statMultipliers.put(multipliers.PHYSDEF, statMultipliers.get(multipliers.PHYSDEF)+buff);
		statMultipliers.put(multipliers.MAGDEF, statMultipliers.get(multipliers.MAGDEF)+buff);
	}
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}
	public void setInvincible(boolean invincible) {
		isInvincible = invincible;
	}
	public void setVisile(boolean visible) {
		isVisible=visible;
	}

	
}
