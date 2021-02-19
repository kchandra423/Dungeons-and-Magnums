package kchandra423.graphics.Sprites;

import kchandra423.graphics.textures.Texture;
import kchandra423.shapes.Rectangle;
import processing.core.PApplet;
import processing.core.PConstants;

public class SpriteRectangle extends Sprite{
	private boolean centered;
//	private float extraX;
//	private float extraY;
	public SpriteRectangle(Texture t, Rectangle r, boolean centered) {
		super(t,r);
		this.centered=centered;
		if(centered) {
			float extraX=(float)r.getTop().getLength()/2;
			float extraY=(float) r.getLeft().getLength()/2;
			r.shift(-extraX,-extraY);
		}
	}
	@Override
	public void setAngle(float angle) {
	if(reflected) {
		angle=(float) (Math.PI-angle);
	}
	float difference = angle-this.angle;
	if(!centered) {
		if(reflected) {
			s.rotate(s.getX(),s. getY(), -difference);
		}
		else {
			s.rotate(s.getX(), s.getY(), difference);
		}
	}else {
		if(reflected) {
			s.rotate(((Rectangle)s).getCenterX(),((Rectangle)s). getCenterY(), -difference);
			}
		else {
			s.rotate(((Rectangle)s).getCenterX(), ((Rectangle)s). getCenterY(), difference);
			}
	}
	this.angle=angle;
		
	}
	@Override
	public void draw(PApplet p, float offSetX, float offSetY) {
		if(!centered) {
		if(isReflected()) {
			p.pushMatrix();
			p.scale(-1,1);
			p.translate((float)-(s.getX()+offSetX), (float)s.getY()+offSetY);
			p.rotate(getAngle());
			
			t.draw(p,0,0);
			p.popMatrix();
			}else {
				p.pushMatrix();
				p.translate((float)s.getX()+offSetX, (float)s.getY()+offSetY);
				p.rotate(getAngle());
				t.draw(p, 0,0);
				p.popMatrix();

			}
		}else {
			p.pushStyle();
			p.imageMode(PConstants.CENTER);
			if(isReflected()) {
				p.pushMatrix();
				p.scale(-1,1);
				p.translate((float)-(((Rectangle)s).getCenterX()+offSetX), (float)((Rectangle)s).getCenterY()+offSetY);
				p.rotate((getAngle()));
				t.draw(p,0,0);
				p.popMatrix();
				}else {
					p.pushMatrix();
					p.translate((float)((Rectangle)s).getCenterX()+offSetX, (float)((Rectangle)s).getCenterY()+offSetY);
					p.rotate(getAngle());
					t.draw(p, 0,0);
					p.popMatrix();
				}
			p.popStyle();

		}
//			s.draw(p);
	}
	@Override
	public void setReflected(boolean reflected) {
		if(!centered) {
			if(this.reflected!=reflected) {
				s.reflectOver(s.getX());
			}
		}else {
			if(this.reflected!=reflected) {
				s.reflectOver(((Rectangle)s).getCenterX());
			}
		}
		this.reflected=reflected;
	}
	public boolean isCentered() {
		return centered;
	}
	public void setCentered(boolean centered) {
		this.centered = centered;
	}
}
