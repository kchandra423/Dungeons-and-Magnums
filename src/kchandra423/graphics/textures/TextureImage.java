package kchandra423.graphics.textures;

import processing.core.PApplet;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class TextureImage implements Texture {
    private final PImage image;

    TextureImage(String pathName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new PImage(img);
    }

    @Override
    public void draw(PApplet p, int x, int y) {
        p.image(image, x, y);
    }

    @Override
    public PImage getImage() {
        return image;
    }


    @Override
    public void resize(int w, int h) {
        image.resize(w, h);

    }

    @Override
    public int getWidth() {
        return image.width;
    }

    @Override
    public int getHeight() {
        return image.height;
    }


}
