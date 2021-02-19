package kchandra423.graphics.Sprites;

import kchandra423.graphics.textures.Fader;
import kchandra423.graphics.textures.Texture;
import kchandra423.shapes.Shape;
import processing.core.PApplet;
public abstract class Sprite {
	protected float angle;
	protected Shape s;
	protected Texture t;
	protected boolean reflected;
	protected Fader fader;
	protected Sprite(Texture t, Shape s) {
		this.t=t;
		this.s=s;
		reflected=false;
		fader=null;
	}
	public void shift(float x, float y) {
		s.shift(x, y);
	}
	public float getX() {
		return (float) s.getX();
	}
	public float getY() {
		return (float) s.getY();
	}
	public float getAngle() {
		return angle;
	}
	public abstract void setAngle(float angle);
	/**
	 * Returns the current width of the image
	 * @return
	 */
	public float getWidth() {
		return t.getWidth();
	}
	/**
	 * Returns the current height of the image
	 * @return
	 */
	public  float getHeight() {
		return t.getHeight();
	}
	/**
	 * Draws the texture onto the given PApplet
	 * @param p The given PApplet to be drawn to
	 */
	public abstract void draw(PApplet p,float offSetX, float offSetY);
	/**
	 * Returns the current x coordinate of the texture
	 * @return current x coordinate
	 */
	
	/**
	 * Fades the image until it is transparent
	 */
	public void fadeOut() {
		fader=new Fader(255,0,0.2f);
		fader.start();
	}
	/**
	 * Fades the image from transparent to opaque
	 */
	public  void fadeIn() {
		fader=new Fader(0,255,0.2f);
		fader.start();
	}
	public abstract void setReflected(boolean reflected);
	public boolean isReflected() {
		return reflected;
	}
	public Shape getShape() {
		return s;
	}
	public Texture getTexture() {
		return t;
	}
}
