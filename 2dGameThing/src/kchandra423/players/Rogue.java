package kchandra423.players;

import java.util.Timer;
import java.util.TimerTask;

public class Rogue extends Player{
//	private Timer abilityTimer=new Timer();
//	private float abilityTime=0.1f;
	private boolean abilityOnCooldown=false;
	private float abilityCooldown=3;
	private Timer abilityCooldownTimer=new Timer();
//	private float prevVX;
	
	@Override
	public void useAbility() {
		// TODO Auto-generated method stub
		//roll, theres literally no other options (maybe with attack at the end)
		if(!abilityOnCooldown) {
			abilityOnCooldown=true;
		System.out.println("ability activated");
		velocityX*=2;
		velocityY*=2;
//		abilityTimer.schedule(new TimerTask() {
//			public void run() {
//				acceleration=0.75f;
				
//				System.out.println("ability ended");
				abilityCooldownTimer.schedule(new TimerTask() {
			public void run() {
				abilityOnCooldown=false;
				
			}
		}, (long)(abilityCooldown*1000));
	}
//		}, (long)(abilityTime*1000));
//		}
}

	@Override
	public void useSuper() {
		// TODO Auto-generated method stub
		
	}

}
