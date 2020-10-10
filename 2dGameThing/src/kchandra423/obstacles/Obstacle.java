package kchandra423.obstacles;

import kchandra423.shapes.Rectangle;
import processing.core.PApplet;

public interface Obstacle {
	public boolean intersects(double x, double y);
	public int getObstaclesType();
	public void draw(PApplet p);
	public Rectangle getRect();
	
}
