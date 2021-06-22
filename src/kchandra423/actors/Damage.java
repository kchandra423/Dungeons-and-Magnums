package kchandra423.actors;

import kchandra423.actors.movingActors.constants.DamageTypes;

/**
 * Represents a damage that can be given to an actor
 * 
 * @author Kumar Chandra
 *
 */
public class Damage {
	private final int amount;
	private final float[] stats;
	private final DamageTypes type;

	/**
	 * Creates a Damage with the specified base damage, type, and stats
	 * 
	 * @param amount The base damage
	 * @param stats  All of the stats of the actor who fired this
	 * @param type   The damage type of this Damage
	 */
	public Damage(int amount, float[] stats, DamageTypes type) {
		this.amount = amount;
		this.stats = stats;
		this.type = type;
	}

	/**
	 * The base damage of this Damage
	 * 
	 * @return The base damage
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Returns all of the stats of this Damage
	 * @return The stats of this damage 
	 */
	public float[] getStats() {
		return stats;
	}

	/**
	 * Returns the type of this damage
	 * @return The type of this damage
	 */
	public DamageTypes getType() {
		return type;
	}
}
