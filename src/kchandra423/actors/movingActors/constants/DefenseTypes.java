package kchandra423.actors.movingActors.constants;

/**
 * Constants for the type of defense something has. Can be ranged, magic or melee.
 * @author Kumar Chandra
 * @see kchandra423.actors.movingActors.constants.Stat
 */
public enum DefenseTypes implements Stat {
    RANGEDEF(3), MAGDEF(4), PHYSDEF(5);

    private final int val;

    DefenseTypes(int val) {
        this.val = val;
    }
    /**
     * Returns the value of this DefenseType. Currently just used for indexing.
     * @return 3 if ranged defense, 4 if magic defense, and 5 if melee/physical defense
     */
    public int getValue() {
        return val;
    }
}
