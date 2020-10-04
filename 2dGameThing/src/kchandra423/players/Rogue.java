package kchandra423.players;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kchandra423.graphics.TwodGameThing;
import kchandra423.projectiles.Bullet;
import kchandra423.projectiles.ThrowingKnife;
import kchandra423.utility.Calculator;

public class Rogue extends Player{

	private ArrayList<Bullet> shots = new ArrayList<Bullet>();
	private int shotsFired;
	private int shotsHit;
	@Override
	public void usePassive() {
		// TODO Auto-generated method stub
		resetDamageMultiplier();
		int streak= w.getHitStreak();
		damageMultiplier+=0.2*streak;
		
	}
	@Override
	public void useAbility1(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		//roll, theres literally no other options (maybe with attack at the end)
		if(!ability1OnCooldown) {
			ability1OnCooldown=true;
			timeSinceUsedAbility1=System.currentTimeMillis();
		System.out.println("ability activated");

 		if(up) {
 			velocityY=-10;
 		}
 		if(right) {
 			velocityX=10;
 		}
 		if(left) {
 			velocityX=-10;
 		}
 		if(down) {
 			velocityY=10;
 		}

				ability1CooldownTimer.schedule(new TimerTask() {
			public void run() {
				ability1OnCooldown=false;
				
			}
		}, (long)(ability1Cooldown*1000));
	}

}

	@Override
	public void useAbility2(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(!ability2OnCooldown) {
			ability2OnCooldown=true;
			timeSinceUsedAbility2=System.currentTimeMillis();		
		double initialx=body.getX();
				double initialy=body.getY(); 

		        double angle= Calculator.calculateAngle(initialx,
		        		initialy, mouseX, mouseY);

		        for (int i = 0; i<5;i++) {
				ThrowingKnife knife=new ThrowingKnife(initialx,initialy,
						7, angle+((i-3)*Math.PI*3/180), 0, 
						0, TwodGameThing.BOUNDSX, TwodGameThing.BOUNDSY);
				abilityProjectiles.add(knife);
		        }
		        ability2CooldownTimer.schedule(new TimerTask() {
					public void run() {
						ability2OnCooldown=false;
						
					}
				}, (long)(ability2Cooldown*1000));
		}
	}
	@Override
	public void useAbility3(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		if(!ability3OnCooldown) {
			shotsFired++;
			
				
		double initialx=body.getX();
				double initialy=body.getY(); 

		        double angle= Calculator.calculateAngle(initialx,
		        		initialy, mouseX, mouseY);

		        Bullet p=new Bullet(initialx,initialy,
	    				10, angle, 0, 
	    				0, TwodGameThing.BOUNDSX, TwodGameThing.BOUNDSY);
		        abilityProjectiles.add(p);
		        shots.add(p);
		        if(shotsFired==5) {
//		        	for(int i=0;i<4;i++) {
//		        		if(shots.get(i).hasHitEnemy()) {
//		        			
//		        		}
//		        	}
					ability3OnCooldown=true;
					timeSinceUsedAbility3=System.currentTimeMillis();	
					ability3CooldownTimer.schedule(new TimerTask() {
						public void run() {
							ability3OnCooldown=false;
							
						}
					}, (long)(ability3Cooldown*1000));
		        }
		        
		}
	}
	public int getAbility1Cooldown() {
		
		//1000000000
		int percentage=(int)((System.currentTimeMillis()-timeSinceUsedAbility1)/(ability1Cooldown*10));
		
		return percentage;
	
	}
	@Override
	public int getAbility2Cooldown() {
int percentage=(int)((System.currentTimeMillis()-timeSinceUsedAbility2)/(ability2Cooldown*10));
		
		return percentage;
	}
	@Override
	public int getAbility3Cooldown() {
	// TODO Auto-generated method stub
	return 0;
}
	
	@Override
	public void useSuper(int mouseX, int mouseY) {
		
        }
	
	

}
