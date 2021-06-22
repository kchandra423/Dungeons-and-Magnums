package sye348.levels;

import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.obstacles.Crate;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Level 1
 * @author Spencer Ye modified by Kumar Chandra
 * @version 1.2.1
 * Last Revised: 5/22/2021
 */
public class LevelOne extends Level {


    public LevelOne(Player p) {
        super(getRooms(p));
    }

    private static ArrayList<Room> getRooms(Player player) {
        ArrayList<Room> arr = new ArrayList<>();

        Texture t = Texture.TextureBuilder.getTexture("res/Images/Backgrounds/Tiles.jpg");
        arr.add(Level.getRoom(0, 1, 0, 0, 0, new ArrayList<Obstacle>(), player, t));
        arr.add(Level.getRoom(0, 5, 0, 0, 0, new ArrayList<Obstacle>(Arrays.asList(new Crate(500, 1000), new Crate(1000, 400))), player, t));
        arr.add(Level.getRoom(1, 5, 1, 0, 0, new ArrayList<Obstacle>(Arrays.asList(new Crate(600, 300))), player, t));
        arr.add(Level.getRoom(0, 2, 6, 0, 0, new ArrayList<Obstacle>(Arrays.asList(new Crate(500, 1000), new Crate(800, 300))), player, t));
        return arr;
    }


    @Override
    public Level getNextLevel() {
        getCurrentRoom().getPlayer().setHealth(getCurrentRoom().getPlayer().getMaxHealth());
        return new LevelTwo(getCurrentRoom().getPlayer());
    }

    @Override
    public String toString() {
        return "1 - " + getRoomNumber();
    }
}
