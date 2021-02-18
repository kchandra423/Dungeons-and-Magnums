package kchandra423.entities.actors.enemies;


import Sprite.Sprite;
import kchandra423.entities.actors.Actor;
import kchandra423.graphics.DrawingSurface;
import processing.core.PApplet;

public class Enemy extends Actor {
	private EnemyAi brain;
	public Enemy(Sprite sprite, float maxV, float accel) {
		super(sprite,maxV,  accel);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void act(DrawingSurface d) {
		int [] inputs= brain.makeDecision(d);
		
	}
}