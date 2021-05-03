package kchandra423.graphics.textures;

import kchandra423.actors.Collideable;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

public class KImage {
    private final Texture image;
    private final Area area;
    private float x;
    private float y;
    private float angle;
    private boolean reflected;
    private boolean reversed;

    public KImage(Texture t) {
        this(0, 0, false, false, t);
    }

    public KImage(float x, float y, boolean reflected, boolean reversed, Texture t) {
        this.x = x;
        this.reflected = reflected;
        this.reversed = reversed;
        this.y = y;
        angle = 0;
        image = t;
        area = KImage.loadArea(image);
    }

    public KImage(float x, float y, boolean reflected, boolean reversed, Texture t, Area area) {
        this.x = x;
        this.reflected = reflected;
        this.reversed = reversed;
        this.y = y;
        angle = 0;
        image = t;
        this.area = area;
    }

    public static Area loadArea(Texture texture) {
        Area area = new Area();
        PImage img = texture.getImage();
        for (int x = 0; x < img.width; x++) {
            for (int y = 0; y < img.height; y++) {
                if (img.pixels[y * img.width + x] != 0) {
                    area.add(new Area(new Rectangle(x, y, img.pixelDensity, img.pixelDensity)));
                }
            }

        }
        return area;

    }

    public void draw(PApplet p) {
        if (!reflected) {
            p.pushMatrix();
            p.translate(x, y);
            p.rotate(angle);
            image.draw(p, 0, 0);
            p.popMatrix();
        } else {
            p.pushMatrix();
            p.scale(-1, 1);

            if (reversed) {
                p.translate(-x, y);
                p.rotate((float) (Math.PI - angle));
            } else {
                p.translate(-(x + image.getWidth()), y);
                p.rotate(angle);
            }
            image.draw(p, 0, 0);
            p.popMatrix();
        }
    }


    public void translate(float delx, float dely) {
        x += delx;
        y += dely;
    }

    public void rotate(float angle) {
        this.angle += angle;
    }

    public void setReflected(boolean reflected) {
        this.reflected = reflected;
    }


    public boolean isReflected() {
        return reflected;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
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

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public Texture getTexture() {
        return image;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public boolean isReversed() {
        return reversed;
    }

    public boolean intersects(ArrayList<KImage> images) {
        Area orginial = getTransformedArea();
        for (KImage kImage :
                images) {
            Area copy = (Area) orginial.clone();
            copy.intersect(kImage.getTransformedArea());
            if (!copy.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean intersects(KImage other) {
        Area overLap = getTransformedArea();
        overLap.intersect(other.getTransformedArea());
        return !overLap.isEmpty();
    }


    public Rectangle getBounds() {
        return getTransformedArea().getBounds();
    }

    public Area getTransformedArea() {
        if (!reflected) {
            AffineTransform transform = new AffineTransform();
            transform.translate(x, y);
            transform.rotate(angle);
            return area.createTransformedArea(transform);
        } else {
            AffineTransform transform = new AffineTransform();
            transform.scale(-1, 1);
            if (reversed) {
                transform.translate(-x, y);
                transform.rotate(Math.PI - angle);
            } else {
                transform.translate(-(x + image.getWidth()), y);
                transform.rotate(angle);
            }
            return area.createTransformedArea(transform);
        }
    }
}

