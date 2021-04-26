package kchandra423.graphics.textures;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class KImage {
    private Texture image;
    private Area area;
    private int x;
    private int y;
    private float angle;

    public KImage(Texture t) {
        this(0, 0, t);
    }

    public KImage(int x, int y, Texture t) {
        this.x = x;
        this.y = y;
        angle = 0;
        image = t;
        area = new Area();
        loadArea();
    }

    private void loadArea() {
        PImage img = image.getImage();
        for (int x = 0; x < img.width; x++) {
            for (int y = 0; y < img.height; y++) {
                if (img.pixels[y * img.width + x] != 0) {
                    area.add(new Area(new Rectangle(x, y, img.pixelDensity,img.pixelDensity)));
                }
            }

        }

    }

    public void draw(PApplet p) {
        image.draw(p, x, y, angle);
    }

    public void translate(int delx, int dely) {
        x += delx;
        y += dely;
        area = area.createTransformedArea(AffineTransform.getTranslateInstance(delx, dely));
    }

    public void rotate(float angle) {
        this.angle += angle;
        area.createTransformedArea(AffineTransform.getRotateInstance(angle, x, y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
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
