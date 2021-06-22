package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import processing.core.PConstants;

public class HomeScreen implements Screen {

    private GButton mage;
    private GButton knight;
    private GButton rogue;

    private GButton music;
    private GButton loadout;
    private GButton performance;

    @Override
    public void draw(DrawingSurface d) {

        d.background(100);
        d.pushStyle();

        d.fill(255);
        d.textSize(60);
        d.textAlign(PConstants.CENTER, PConstants.TOP);
        d.text("Dungeons and Magnums", d.width / 2f, 0);
        d.textSize(20);

        d.text("By Kumar Chandra, Spencer Ye, and Ryan Lee\n"+"Instructions: Use wasd to move your character. \n "
                + "Use mouse button 2 to fire your weapon. \n "
                + "The goal of the game is to clear each level by \n "
                + "killing all the enemies in each room. \n"
                + "Try to avoid dying, as you will lose the game if you do. \n"
                + "You can choose your character from 3 classes:\n"
                + "knight, mage, or rogue.", d.width / 2f, d.height / 5f);
        d.popStyle();

    }

    @Override
    public void setup(DrawingSurface d) {
        mage = new GButton(d, d.width / 4f - 100, d.height - d.height / 3f - 40, 200, 80, "Mage");
        knight = new GButton(d, d.width / 2f - 100, d.height - d.height / 3f - 40, 200, 80, "Knight");
        rogue = new GButton(d, 3 * d.width / 4f - 100, d.height - d.height / 3f - 40, 200, 80, "Rogue");
        music = new GButton(d, d.width / 4f - 200, d.height - d.height / 6f - 40, 400, 100, "Music");
        loadout = new GButton(d, d.width / 2f - 200, d.height - d.height / 6f - 40, 400, 100, "Loadout");
        performance = new GButton(d, 3 * d.width / 4f - 200, d.height - d.height / 6f - 40, 400, 100, "Performance");
        mage.addEventHandler(this, "startGame");
        mage.setVisible(false);
        knight.addEventHandler(this, "startGame");
        knight.setVisible(false);
        rogue.addEventHandler(this, "startGame");
        rogue.setVisible(false);
        performance.addEventHandler(this, "moveToPerformance");
        performance.setVisible(false);
        loadout.addEventHandler(this,"moveToLoadOut");
        loadout.setVisible(false);
        music.setVisible(false);
        music.addEventHandler(this,"moveToMusic");
    }

    @Override
    public GAbstractControl[] getUI() {
        return new GButton[]{mage, knight, rogue, music, loadout, performance};
    }

    public void startGame(GButton b, GEvent event) {
//        if (event == GEvent.PRESSED) {
        DrawingSurface.setScreen(Window.LOADING);
        Thread t = new Thread(){
            @Override
            public void run() {
                if (b == mage) {
                    BattleScreen.init(BattleScreen.PlayerType.MAGE);
                } else if (b == knight) {
                    BattleScreen.init(BattleScreen.PlayerType.KNIGHT);
                } else if (b == rogue) {
                    BattleScreen.init(BattleScreen.PlayerType.ROGUE);
                }
            }
        };
        t.start();

//        }
    }

    public void moveToPerformance(GButton b, GEvent event) {

            DrawingSurface.setScreen(Window.PERFORMANCE);

    }
    public void moveToLoadOut(GButton b, GEvent event) {

        DrawingSurface.setScreen(Window.LOADOUT);

    }
    public void moveToMusic(GButton b, GEvent event) {

        DrawingSurface.setScreen(Window.MUSIC);

    }
}
