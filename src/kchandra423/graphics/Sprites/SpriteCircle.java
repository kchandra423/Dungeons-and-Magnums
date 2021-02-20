package kchandra423.graphics.Sprites;


import kchandra423.graphics.shapes.Circle;
import kchandra423.graphics.textures.Texture;
import kchandra423.graphics.shapes.Circle;
import processing.core.PApplet;
import processing.core.PConstants;

public class SpriteCircle extends Sprite {

//	public SpriteCircle(String path, float x, float y) {
//		
//	}

    public SpriteCircle(Texture t, Circle c) {
        super(t, c);
        // TODO Auto-generated constructor stub
    }

    /**
     * Draws the texture onto the given PApplet
     *
     * @param p The given PApplet to be drawn to
     */
    public void draw(PApplet p, float offSetX, float offSetY) {
        p.pushStyle();
        p.imageMode(PConstants.CENTER);
        if (isReflected()) {
            p.pushMatrix();
            p.scale(-1, 1);
            p.translate((float) -(s.getX() + offSetX), (float) (s.getY()) + offSetY);
            p.rotate(getAngle());
            t.draw(p, 0, 0);
            p.popMatrix();
        } else {
            p.pushMatrix();
            p.translate((float) (s.getX() + offSetX), (float) (s.getY()) + offSetY);
            p.rotate(getAngle());
            t.draw(p, 0, 0);
            p.popMatrix();

        }
        p.popStyle();
//        s.draw(p);
    }

    /**
     * Returns the current x coordinate of the texture
     *
     * @return current x coordinate
     */


    public void setReflected(boolean reflected) {
        this.reflected = reflected;
    }


    @Override
    public void setAngle(float angle) {
        this.angle = angle;

    }
}
