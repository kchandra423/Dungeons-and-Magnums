package kchandra423.graphics.textures;

import destiny.gif.GifDecoder;
import destiny.gif.GifDecoder.GifImage;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class PGif implements Texture {
    private Frame[] frames;
    private int curFrame;
    private long lastTime;

    PGif(String pathname) {
        try {
            frames = getFrames(pathname);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        lastTime = System.currentTimeMillis();

    }

    private Frame[] getFrames(String pathName) throws FileNotFoundException, IOException {
        GifImage gif = GifDecoder.read(new FileInputStream(pathName));
        Frame[] answer = new Frame[gif.getFrameCount()];
        for (int i = 0; i < gif.getFrameCount(); i++) {
            answer[i] = new Frame(gif.getFrame(i), gif.getDelay(i));
        }
        return answer;
    }

    public void draw(PApplet p, int x, int y) {

        PImage curImage = frames[curFrame].getImage();

        p.image(curImage, x, y);


        advanceFrame();

    }

    @Override
    public PImage getImage() {
        return frames[curFrame].getImage();
    }

    private void advanceFrame() {
        long curTime = System.currentTimeMillis();
        //.getDelay return time in 100ths of a second
        if (curTime >= lastTime + frames[curFrame].getDelay() * 10L) {
            lastTime = curTime;
            curFrame++;
            if (curFrame >= frames.length) {
                curFrame = 0;
            }
        }
    }

    @Override
    public void resize(int w, int h) {
        for (Frame frame : frames) {
            frame.getImage().resize(w, h);
        }
    }

    @Override
    public int getWidth() {
        return frames[curFrame].getImage().width;
    }

    @Override
    public int getHeight() {
        return frames[curFrame].getImage().height;
    }

    //    @Override
//    public void fadeOut() {
//        fader = new Fader(255, 0, 0.2f);
//        fader.start();
//    }
//
//    @Override
//    public void fadeIn() {
//        fader = new Fader(0, 255, 0.2f);
//        fader.start();
//    }

    private static class Frame {
        private final PImage image;
        private final int delay;

        public Frame(BufferedImage image, int delay) {
            this.image = new PImage(image);
            this.delay = delay;
        }

        public PImage getImage() {
            return image;
        }

        public int getDelay() {
            return delay;
        }
    }
}
