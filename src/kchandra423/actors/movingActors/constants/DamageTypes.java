package kchandra423.actors.movingActors.constants;

/**
 * Constants for the type of damage something inflicts. Can be ranged, magic or melee.
 * @author Kumar Chandra
 * @see kchandra423.actors.movingActors.constants.Stat
 */
public enum DamageTypes implements Stat {
    RANGED(0), MAGIC(1), MELEE(2);

    private final int val;

    DamageTypes(int val){
        this.val = val;
    }

    /**
     * Returns the value of this DamageType. Currently just used for indexing.
     * @return 0 if ranged, 1 if magic, and 2 if melee
     */
    @Override
    public int getValue() {
        return val;
    }
}
