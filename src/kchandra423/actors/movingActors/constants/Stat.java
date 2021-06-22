package kchandra423.actors.movingActors.constants;

/**
 * A stat represents any constant that can be represented as an integer value
 * @author Kumar Chandra
 * @see DamageTypes
 * @see DefenseTypes
 */
public interface Stat {
    /**
     * Returns the value of this constant as an integer
     * @return The value of this constant as an integer
     */
    public int getValue();
}
