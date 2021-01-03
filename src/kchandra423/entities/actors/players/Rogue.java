package kchandra423.entities.actors.players;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Sprite.Sprite;
import Sprite.SpriteCircle;
import Textures.Texture;
import Textures.Texture.TextureBuilder;
import kchandra423.graphics.TwodGameThing;
import kchandra423.shapes.Circle;
import kchandra423.utility.Constants.multipliers;
import kchandra423.utility.Constants.subclasses;
import kchandra423.weapons.projectiles.AbilityBullet;
import kchandra423.weapons.projectiles.ThrowingKnife;

public class Rogue extends Player {
	// there has seriously got to be a better way to do this than like this, but
	// whatever
	public Rogue(float x, float y) {
		super(loadSprites(x, y), 20, 2.5f, loadPlayerMetadata(), loadAbilities());
	}

	private ArrayList<AbilityBullet> shots = new ArrayList<AbilityBullet>();
	private boolean firstShot;
	private int shotsFired;
	private Object synchronize;
	private float totalBuffs;

	@Override
	public void usePassive() {
//		if(getCurrentWeapon().getHitStreak()>0) {
//			incrementAllAttackStats(0.2f);
//			totalBuffs+=0.2;
//		}else {
//			incrementAllAttackStats(-totalBuffs);
//			totalBuffs=0;
//		}

	}

	@Override
	public void useAbility1() {
		// TODO Auto-generated method stub
		// roll, theres literally no other options (maybe with attack at the end)
//		AbilityMetadata ability= getAbility1Info();
//		if (!ability.isAbilityOnCooldown()) {
//			ability.setAbilityOnCooldown(true);
//			ability.setTimeSinceUsedAbility(System.currentTimeMillis());
//			double angOfRoll=getAngle();
//			setVelocityX((float) (15*Math.cos(angOfRoll)));
//			setVelocityY((float) (15*Math.sin(angOfRoll)));
//			setAcceleration(0);
//			Timer resetSpeed= new Timer();
//			resetSpeed.schedule(new TimerTask() {
//				public void run() {
//					if(getVelocityX()>5) {
//						setVelocityX(5);
//					}
//					else if(getVelocityX()<-5) {
//						setVelocityX(-5);
//					}
//					if(getVelocityY()>5) {
//						setVelocityY(5);
//					}
//					else if(getVelocityY()<-5) {
//						setVelocityY(-5);
//					}
//					setAcceleration(0.75f);
//				}
//			}, (long) (0.15*1000));
//
//			ability.getAbilityCooldownTimer().schedule(new TimerTask() {
//				@Override
//				public void run() {
//					ability.setAbilityOnCooldown(false);
//				}
//			}, (long) (ability.getAbilityCooldown() * 1000));
//		}

	}

	@Override
	public void useAbility2() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
//		AbilityMetadata ability= getAbility2Info();
//		if (!ability.isAbilityOnCooldown()) {
//			ability.setAbilityOnCooldown(true);
//			ability.setTimeSinceUsedAbility(System.currentTimeMillis());
//			double initialx = getBody().getX();
//			double initialy = getBody().getY();
//
//			double tempangle = getAngle();
//
//			for (int i = 0; i < 5; i++) {
//				ThrowingKnife knife = new ThrowingKnife(initialx, initialy, 7, tempangle + ((i - 3) * Math.PI * 3 / 180), 0,
//						0, TwodGameThing.BOUNDSX, TwodGameThing.BOUNDSY);
//				getAbilityProjectiles().add(knife);
//			}
//			ability.getAbilityCooldownTimer().schedule(new TimerTask() {
//				public void run() {
//					ability.setAbilityOnCooldown(false);
//
//				}
//			}, (long) (ability.getAbilityCooldown() * 1000));
//		}
	}

	@Override
	public void useAbility3() {
		// TODO Auto-generated method stub
//		AbilityMetadata ability= getAbility3Info();
//		if (!ability.isAbilityOnCooldown()) {
//			if (firstShot) {
//				firstShot = false;
//				setAbilityOverRidingWeapon(3);
//				new Thread(new Runnable() {
//
//					@Override
//					public void run() {
//
//						int shotsHit = 0;
//						try {
//
//							synchronized (synchronize) {
//
//								synchronize.wait();
//
//							}
//							synchronized (synchronize) {
//
//								synchronize.wait();
//
//							}
//							synchronized (synchronize) {
//
//								synchronize.wait();
//
//							}
//							synchronized (synchronize) {
//
//								synchronize.wait();
//
//							}
//
//							synchronized (synchronize) {
//
//								synchronize.wait();
//
//							}
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						for (int i = 0; i < shots.size(); i++) {
//							if (shots.get(i).hasHitEnemy()) {
//								shotsHit++;
//							}
//					        
//						}
//						
//						if (shotsHit < 5) {
//							ability.setAbilityOnCooldown(true);
//							ability.setTimeSinceUsedAbility(System.currentTimeMillis());
//							ability.getAbilityCooldownTimer().schedule(new TimerTask() {
//								public void run() {
//									ability.setAbilityOnCooldown(false);
//
//								}
//							}, (long) (ability.getAbilityCooldown() * 1000));
//						}
//						shotsFired = 0;
//						firstShot = true;
//					}
//				}).start();
//			}else {
//
//			if (shotsFired < 5) {
//				double initialx = getBody().getX();
//				double initialy = getBody().getY();
//
//				double tempangle = getAngle();
//
//				AbilityBullet p = new AbilityBullet(initialx, initialy, 10, tempangle, 0, 0, TwodGameThing.BOUNDSX,
//						TwodGameThing.BOUNDSY, synchronize);
//				getAbilityProjectiles().add(p);
//				shots.add(p);
//				shotsFired++;
//			}
//			if(shotsFired==5) {
//			setAbilityOverRidingWeapon(0);	
//			}
//			}
//
//		}
	}

	@Override
	public void useSuper() {

	}

//	statMultipliers.put(multipliers.RANGED, 1.5f);
//	statMultipliers.put(multipliers.MAGIC, 1f);
//	statMultipliers.put(multipliers.MELEE, 1f);
//	statMultipliers.put(multipliers.SHORT, 1f);
//	statMultipliers.put(multipliers.LONG, 1.25f);
//	statMultipliers.put(multipliers.PHYSDEF, 1f);
//	statMultipliers.put(multipliers.MAGDEF, 1f);
//	health=100;
//	maxHealth=100;
//	shield=120;
//	maxShield=120;
//	setAcceleration(0.75f);
//	ability1=new AbilityMetadata(2);
//	ability2=new AbilityMetadata(5);
//	ability3=new AbilityMetadata(15);
//	setSuperCooldown(120);
	private static Sprite[] loadSprites(float x, float y) {
		Sprite idle;
		Texture tIdle = TextureBuilder.getTexture("res/Images/Players/MageIdle.gif");
//		tIdle.resize(80, 80);
		Circle cIdle = new Circle(x, y, tIdle.getWidth()-20);
		idle = new SpriteCircle(tIdle, cIdle);
		Sprite active;
		Texture tActive = TextureBuilder.getTexture("res/Images/Players/MageActive.gif");
//		tActive.resize(80, 80);
		// Circle cActive= new Circle(x,y,tActive.getWidth());
		active = new SpriteCircle(tActive, cIdle);
		return new Sprite[] { idle, active };
	}

	private static PlayerMetadata loadPlayerMetadata() {
		float[] multipliers = new float[] { 1.5f, 1, 1, 1, 1.25f, 1, 1 };
		return new PlayerMetadata(multipliers, 100, 120);
	}

	private static AbilityMetadata[] loadAbilities() {
		AbilityMetadata[] abilities = new AbilityMetadata[4];
		abilities[0] = new AbilityMetadata(2);
		abilities[1] = new AbilityMetadata(5);
		abilities[2] = new AbilityMetadata(15);
		abilities[3] = new AbilityMetadata(120);
		return abilities;
	}

}
