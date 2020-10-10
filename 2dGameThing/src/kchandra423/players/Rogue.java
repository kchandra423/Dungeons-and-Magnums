package kchandra423.players;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kchandra423.graphics.TwodGameThing;
import kchandra423.projectiles.AbilityBullet;
import kchandra423.projectiles.ThrowingKnife;
import kchandra423.utility.Calculator;

public class Rogue extends Player {

	public Rogue() throws Exception {
		super(this);
		
		// TODO Auto-generated constructor stub
	}


	private ArrayList<AbilityBullet> shots = new ArrayList<AbilityBullet>();
	private boolean firstShot = true;
	private int shotsFired = 0;
	private Object synchronize = new Object();
	private float totalBuffs=0;
	@Override
	public void usePassive() {
		// TODO Auto-generated method stub
		if(getCurrentWeapon().getHitStreak()>0) {
			incrementAllAttackStats(0.2f);
			totalBuffs+=0.2;
		}else {
			incrementAllAttackStats(-totalBuffs);
			totalBuffs=0;
		}
		

	}

	@Override
	public void useAbility1() {
		// TODO Auto-generated method stub
		// roll, theres literally no other options (maybe with attack at the end)
		if (!isAbility1OnCooldown()) {
			setAbility1OnCooldown(true);
			setTimeSinceUsedAbility1(System.currentTimeMillis());
//			System.out.println("ability activated");
			double angOfRoll=getAngle();
			setVelocityX((float) (15*Math.cos(angOfRoll)));
			setVelocityY((float) (15*Math.sin(angOfRoll)));
			setAcceleration(0);
			Timer resetSpeed= new Timer();
			resetSpeed.schedule(new TimerTask() {
				public void run() {
					if(getVelocityX()>5) {
						setVelocityX(5);
					}
					else if(getVelocityX()<-5) {
						setVelocityX(-5);
					}
					if(getVelocityY()>5) {
						setVelocityY(5);
					}
					else if(getVelocityY()<-5) {
						setVelocityY(-5);
					}
					setAcceleration(0.75f);
				}
			}, (long) (0.15*1000));

			getAbility1CooldownTimer().schedule(new TimerTask() {
				public void run() {
					setAbility1OnCooldown(false);

				}
			}, (long) (getAbility1Cooldown() * 1000));
		}

	}

	@Override
	public void useAbility2() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (!isAbility2OnCooldown()) {
			setAbility2OnCooldown(true);
			setTimeSinceUsedAbility2(System.currentTimeMillis());
			double initialx = getBody().getX();
			double initialy = getBody().getY();

			double tempangle = getAngle();

			for (int i = 0; i < 5; i++) {
				ThrowingKnife knife = new ThrowingKnife(initialx, initialy, 7, tempangle + ((i - 3) * Math.PI * 3 / 180), 0,
						0, TwodGameThing.BOUNDSX, TwodGameThing.BOUNDSY);
				getAbilityProjectiles().add(knife);
			}
			getAbility2CooldownTimer().schedule(new TimerTask() {
				public void run() {
					setAbility2OnCooldown(false);

				}
			}, (long) (getAbility2Cooldown() * 1000));
		}
	}

	@Override
	public void useAbility3() {
		// TODO Auto-generated method stub
		if (!isAbility3OnCooldown()) {
			if (firstShot) {
				firstShot = false;
				setAbilityOverRidingWeapon(3);
				new Thread(new Runnable() {

					@Override
					public void run() {

						int shotsHit = 0;
						try {

							synchronized (synchronize) {

								synchronize.wait();

							}
							synchronized (synchronize) {

								synchronize.wait();

							}
							synchronized (synchronize) {

								synchronize.wait();

							}
							synchronized (synchronize) {

								synchronize.wait();

							}

							synchronized (synchronize) {

								synchronize.wait();

							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (int i = 0; i < shots.size(); i++) {
							if (shots.get(i).hasHitEnemy()) {
								shotsHit++;
							}
					        
						}
						shots=new ArrayList<AbilityBullet>();
						if (shotsHit < 5) {
							setAbility3OnCooldown(true);
							setTimeSinceUsedAbility3(System.currentTimeMillis());
							getAbility3CooldownTimer().schedule(new TimerTask() {
								public void run() {
									setAbility3OnCooldown(false);

								}
							}, (long) (getAbility3Cooldown() * 1000));
						}
						shotsFired = 0;
						firstShot = true;
					}
				}).start();
			}else {

			if (shotsFired < 5) {
				double initialx = getBody().getX();
				double initialy = getBody().getY();

				double tempangle = getAngle();

				AbilityBullet p = new AbilityBullet(initialx, initialy, 10, tempangle, 0, 0, TwodGameThing.BOUNDSX,
						TwodGameThing.BOUNDSY, synchronize);
				getAbilityProjectiles().add(p);
				shots.add(p);
				shotsFired++;
			}
			if(shotsFired==5) {
			setAbilityOverRidingWeapon(0);	
			}
			}

		}
	}


	@Override
	public void useSuper() {

	}

	

}
