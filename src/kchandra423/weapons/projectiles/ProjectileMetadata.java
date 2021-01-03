package kchandra423.weapons.projectiles;

public class ProjectileMetadata {
	private float velocity;
	private float angle;
	public ProjectileMetadata(float initalV, float initialAngle) {
		velocity=initalV;
		angle=initialAngle;
	}
	public float getVelocity() {
		return velocity;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
}
