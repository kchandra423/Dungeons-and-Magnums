package kchandra423.graphics;

import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import processing.core.PImage;

import javax.imageio.ImageIO;
//import java.awt.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test {
    public static void main (String[] args){

        PImage img = Texture.TextureBuilder.getTexture("res/Images/Players/MageIdle.gif").getImage();
        for (int x = 0; x < img.width; x++) {
            for (int y = 0; y < img.height; y++) {
                System.out.print(img.pixels[y * img.width + x] + " ");
            }
            System.out.println();
            System.out.println();

        }
    }
}
