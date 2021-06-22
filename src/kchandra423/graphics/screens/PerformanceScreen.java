package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GSlider;
import kchandra423.graphics.textures.KImage;
import processing.core.PConstants;

public class PerformanceScreen implements Screen{
    private GSlider frameRate;
    private GSlider pixelDensity;
    private GButton save;
    @Override
    public void draw(DrawingSurface d) {

        d.background(100);
        d.pushStyle();

        d.fill(255);
        d.textSize(60);
        d.textAlign(PConstants.CENTER, PConstants.TOP);
        d.text("Performance Information",d.width/2f,0);
        d.textSize(20);
        d.text("Framerate indicates how fast the program's screen attempts to refresh. \n" +
                "A higher framerate may make the program run faster, but is more taxing on your computer" +
                "\nPixel density indicates how precise the collision detection between actors is.\n" +
                "A pixel density of 1 will have pixel perfect collision detection.\n" +
                "A higher pixel density will increase imprecision at a squared rate, but will also improve load times at a squared rate.",d.width/2f,100);

        d.textAlign(PConstants.CENTER, PConstants.BOTTOM);
        d.text("Framerate\n" +frameRate.getValueI(), d.width / 2f, d.height / 2f-50);

        d.text("PixelDensity\n" +pixelDensity.getValueI(), d.width / 2f, 3*d.height/4f -50);
        d.popStyle();
    }

    @Override
    public void setup(DrawingSurface d) {
        frameRate = new GSlider(d, d.width/2f - d.width/4f,d.height/2f-30,d.width/2f, 60, 50);
        frameRate.setLimits(60,30,90);
        frameRate.setVisible(false);
        frameRate.setShowTicks(true);
        pixelDensity = new GSlider(d, d.width/2f - d.width/4f,3*d.height/4f-30,d.width/2f, 60, 50);
        pixelDensity.setLimits(2,1,16);
        pixelDensity.setVisible(false);
        pixelDensity.setShowTicks(true);
        save = new GButton(d, 20, d.height -100, 200, 80, "Save");
        save.addEventHandler(this, "save");
        save.setVisible(false);
    }

    @Override
    public GAbstractControl[] getUI() {
        return new GAbstractControl[]{frameRate,save, pixelDensity};
    }

    public void save(GButton b, GEvent event){
        ((DrawingSurface)b.getPApplet()).changeFrameRate(frameRate.getValueI());
        KImage.setAreaDensity(pixelDensity.getValueI());
        DrawingSurface.setScreen(Window.HOME);
    }


}
