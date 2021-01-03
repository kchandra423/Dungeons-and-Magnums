package kchandra423.utility;

public class Constants {
	public enum collisionTypes{
		BLOCKING, BOUNCING, REFLECTING;
	}
	public enum subclasses{
		ROGUE, KNIGHT, MAGE;
	}
	public enum multipliers{
		RANGED, MAGIC, MELEE, SHORT,
		LONG, PHYSDEF, MAGDEF;
	}
	public enum collisionDirection{
		X,Y,NONE,BOTH;
	}
}
