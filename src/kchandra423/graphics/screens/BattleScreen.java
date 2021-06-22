package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import kchandra423.actors.movingActors.players.Knight;
import kchandra423.actors.movingActors.players.Mage;
import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.movingActors.players.Rogue;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.PlayerData;
import main.DungeonsAndMagnums;
import sye348.levels.Level;
import sye348.levels.LevelOne;

import java.io.FileWriter;
import java.io.IOException;

public class BattleScreen implements Screen {

    private GButton home;
    private static Level level;
    private final HUD hud;
    private static boolean ready = false;
    enum PlayerType{
        MAGE,KNIGHT,ROGUE;
    }
    public BattleScreen() {
        hud = new HUD();
    }

    static void init(PlayerType p) {
        DrawingSurface.setScreen(Window.LOADING);
        AssetLoader.loadKImages();
        Player player;
        if(p==PlayerType.MAGE){
            player = new Mage(700,700);
        }else if(p == PlayerType.KNIGHT){
            player = new Knight(700,700);
        }else{
            player = new Rogue(700,700);
        }
        BattleScreen.level = new LevelOne(player);
        ready = true;

        DrawingSurface.setScreen(Window.BATTLE);
    }

    /**
     * Draws the current room and everything in it
     */
    public void draw(DrawingSurface p) {
        if (ready) {
            p.background(100);
            p.pushMatrix();
            int halfx = p.width / 2;
            int halfy = p.height / 2;
            if (level.isCompleted()) {
                level = level.getNextLevel();
            }
            if (level == null) {
                DrawingSurface.setScreen(Window.HOME);
                return;
            }


            p.translate(-level.getCurrentRoom().getPlayer().getImage().getX() + halfx - level.getCurrentRoom().getPlayer().getImage().getTexture().getWidth() / 2.0f, -level.getCurrentRoom().getPlayer().getImage().getY() + halfy - level.getCurrentRoom().getPlayer().getImage().getTexture().getHeight() / 2.0f);
            level.draw(p);
            p.popMatrix();
            hud.draw(p, level);

            if (!level.getCurrentRoom().getPlayer().isActive()) {
                DrawingSurface.setScreen(Window.HOME);
                return;
            }
        }

    }

    @Override
    public void setup(DrawingSurface d) {
        home = new GButton(d, 20, d.height - 100, 200, 80, "Return to home screen");
        home.addEventHandler(this, "goHome");
        home.setVisible(false);
    }

    @Override
    public GAbstractControl[] getUI() {
        return new GButton[]{home};
    }

    public static Level getCurrentlevel() {
        return level;
    }
    public void goHome(GButton b, GEvent event) {

        DrawingSurface.setScreen(Window.HOME);

    }
}

