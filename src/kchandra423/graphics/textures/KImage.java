package kchandra423.graphics.textures;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class KImage {
    private Texture image;
    private Area area;
    private float x;
    private float y;
    private float angle;
    private boolean reflected;

    public KImage(Texture t) {
        this(0, 0, false, t);
    }

    public KImage(int x, int y, boolean reflected, Texture t) {
        this.x = x;
        this.reflected = reflected;

        this.y = y;
        angle = 0;
        image = t;
        area = new Area();
        loadArea();
        area = area.createTransformedArea(AffineTransform.getTranslateInstance(x,y));
        if (reflected) {
            area = area.createTransformedArea(AffineTransform.getScaleInstance(-1, 0));
        }
    }

    private void loadArea() {
        PImage img = image.getImage();
        for (int x = 0; x < img.width; x++) {
            for (int y = 0; y < img.height; y++) {
                if (img.pixels[y * img.width + x] != 0) {
                    area.add(new Area(new Rectangle(x, y, img.pixelDensity, img.pixelDensity)));
                }
            }

        }

    }

    public void draw(PApplet p) {
        if (!reflected) {
            image.draw(p, (int) x, (int) y, angle);
        } else {
            p.pushMatrix();
            p.scale(-1, 0);
            p.translate(-x, y);
            image.draw(p, (int) x, (int) y, (float) (Math.PI - angle));
            p.popMatrix();
        }
    }

    public void translate(float delx, float dely) {
        x += delx;
        y += dely;
        area = area.createTransformedArea(AffineTransform.getTranslateInstance(delx, dely));
    }

    public void rotate(float angle) {
        this.angle += angle;
        area = area.createTransformedArea(AffineTransform.getRotateInstance(angle, x, y));
    }

    public void setReflected(boolean reflected) {
//        if (reflected != this.reflected) {
//            AffineTransform a = AffineTransform.getScaleInstance(-1, 0);
//            a.translate(2 * x, 0);
//            area = area.createTransformedArea(a);
//        }
        this.reflected = reflected;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getAngle() {
        return angle;
    }


    public boolean intersects(KImage other) {
        Area copy = (Area) area.clone();
        copy.intersect(other.area);
        return !copy.isEmpty();
    }

}
