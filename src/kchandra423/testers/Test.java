package kchandra423.testers;

import kchandra423.files.FileManager;
public class Test {
	//while in development, ill just write stuff here, and just use the keys by myself
	// and not store them all in one place, just to make it slighlty more a pain in the
	// ass to find that info if anyone cares
	public static void main (String[] args) {
		//player constatns keys: "3H_~arV-St83LRyN"
//		File format is the name of the class, ranged, magic, 
//		melee, short, long, physdef, magdef, health, shield, 
//		accel, ab1 cooldown, ab2 cooldown, ab3 cooldown, super cooldown
//		FileManager file = new FileManager("res/GameConstants/PlayerConstants","3H_~arV-St83LRyN",false);
//		file.writeLine("ROGUE,1.5,1,1,1,1.25,1,1,100,120,0.75,2,5,15,120");
//		file.writeLine("KNIGHT,1,0.5,1.5,1.5,0.5,1.5,0.75,125,150,0.5,3,5,15,240");
//		file.writeLine("MAGE,1,3,0.5,1,1,0.75,2,75,100,0.625,2,5,15,100"); 
//		FileMafdager file1 = new FileManager("res/GameConstants/PlayerConstants","3H_~arV-St83LRyN",true);
//		try {
//			System.out.println(file1.readAll());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		FileManager file = new FileManager("res/GameConstants/WeaponStats",false);
		//weapon name, projectile velocity, fire rate (time between bullets in seconds),
		// reload time(seconds), magazine size,  ammo count, spread(as an angle in radians),numberOf bullets if the drawmode is center
		file.writeLine("Shotgun,40,0.7,3,5,40,0.5,6,false");
		file.close();
		
	}
}
