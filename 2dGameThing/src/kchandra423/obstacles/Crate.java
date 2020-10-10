package kchandra423.obstacles;

import kchandra423.shapes.Rectangle;
import kchandra423.utility.Constants;
import processing.core.PApplet;

public class Crate  extends Rectangle implements Obstacle{
	public Crate(double x, double y, double width, double height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean intersects(double x, double y) {
		// TODO Auto-generated method stub
		return this.isPointInside(x, y);
	}

	@Override
	public int getObstaclesType() {
		// TODO Auto-generated method stub
		return Constants.BLOCKING;
	}
//	public void draw(PApplet p) {
//		this.draw(p);
//	}


	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return this;
	}
	
}
