package kchandra423.entities.actors.players;

public class AbilityMetadata {
    private long timeSinceUsedAbility;
    //	private boolean abilityOnCooldown;
    private float abilityCooldown;

    public AbilityMetadata(float abilityCooldown) {
        timeSinceUsedAbility = System.currentTimeMillis();
        this.abilityCooldown = abilityCooldown;
//		abilityCooldownTimer=new Timer();
    }

    public float getAbilityCooldown() {
        return abilityCooldown;
    }

    public int getAbilityCooldownPercentageRemaining() {
        return (int) ((System.currentTimeMillis() - timeSinceUsedAbility) / (abilityCooldown * 10));

    }

    public long getTimeSinceUsedAbility() {
        return timeSinceUsedAbility;
    }

    public void setTimeSinceUsedAbility(long time) {
        timeSinceUsedAbility = time;
    }

    public boolean isAbilityOnCooldown() {
        return !((System.currentTimeMillis() - timeSinceUsedAbility) >= abilityCooldown * 1000);
    }
//	public void setAbilityOnCooldown(boolean abilityOnCooldown) {
//		this.abilityOnCooldown = abilityOnCooldown;
//	}
//	public Timer getAbilityCooldownTimer() {
//		return abilityCooldownTimer;
//	}
}
