package kchandra423.entities.actors.players;


public class PlayerMetadata {
	public PlayerMetadata(float[] multiplierStats, int maxHealth, int maxShield,float baseV) {
		statMultipliers=multiplierStats;
		this.maxHealth=maxHealth;
		setHealth(maxHealth);
		this.maxShield=maxShield;
		setShield(maxShield);
		angle=0;
		this.baseV=baseV;
	}
	public static final int RANGED=0, MAGIC=1, MELEE=2,SHORT=3,LONG=4,PHYSDEF=5,MAGDEF=6;
	private final float baseV;
	private float angle;
	private float[] statMultipliers;
	private int health;
	private int maxHealth;
	private int shield;
	private int maxShield;
	public void incrementAllAttackStats(float buff) {
		for(int i=0;i< 5;i++) {
			statMultipliers[i]+=buff;
		}
	}
	public void incrementAllDefenseStats(float buff) {
		for(int i=5;i< statMultipliers.length;i++) {
			statMultipliers[i]+=buff;
		}
	}
	public void incrementAllStats(float buff) {
		for(int i=0;i< statMultipliers.length;i++) {
			statMultipliers[i]+=buff;
		}
	}
	public void incrementStat(int index, float buff) {
		statMultipliers[index]+=buff;
	}
	public void setStat(int index, float stat) {
		statMultipliers[index]=stat;
	}
	public float getStat(int index) {
		return statMultipliers[index];
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public int getMaxShield() {
		return maxShield;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getBaseV() {
		return baseV;
	}
}
