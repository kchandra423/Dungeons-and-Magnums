package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GSlider;
import main.DungeonsAndMagnums;
import processing.core.PConstants;

public class MusicScreen implements Screen {
    private GButton save;
    private GSlider volume;

    @Override
    public void draw(DrawingSurface d) {
        d.background(100);
        d.pushStyle();

        d.fill(255);
        d.textSize(60);
        d.textAlign(PConstants.CENTER, PConstants.TOP);
        d.text("Volume Settings", d.width / 2f, 0);
        d.textSize(40);
        d.text("Volume:\n"+volume.getValueI(),d.width/2f,3 * d.height / 4f - 200);
        d.popStyle();
    }

    @Override
    public void setup(DrawingSurface d) {
        volume = new GSlider(d, d.width / 2f - d.width / 4f, 3 * d.height / 4f - 30, d.width / 2f, 60, 50);
        volume.setLimits(0, -20, 20);
        volume.setVisible(false);
        volume.setShowTicks(true);
        volume.addEventHandler(this, "setVolume");
        save = new GButton(d, 20, d.height - 100, 200, 80, "Save");
        save.addEventHandler(this, "save");
        save.setVisible(false);
    }

    @Override
    public GAbstractControl[] getUI() {
        return new GAbstractControl[]{volume, save};
    }

    public void save(GButton b, GEvent event) {
        DrawingSurface.setScreen(Window.HOME);
    }

    public void setVolume(GSlider gs, GEvent event) {
        if (event == GEvent.RELEASED) {
            DungeonsAndMagnums.music.stopSong();
            DungeonsAndMagnums.music.setVolume(gs.getValueF());
            DungeonsAndMagnums.music.nextSong();
        }
    }
}
