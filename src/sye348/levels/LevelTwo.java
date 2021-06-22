package sye348.levels;

import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.obstacles.Crate;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Level 2
 * @author Spencer Ye modified by Kumar Chandra
 * @version 1.2.0
 * Last Revised: 5/20/2021
 */
public class LevelTwo extends Level {


    public LevelTwo(Player player) {
        super(getRooms(player));
    }

    private static ArrayList<Room> getRooms(Player player) {
        ArrayList<Room> arr = new ArrayList<>();

        Texture t = Texture.TextureBuilder.getTexture("res/Images/Backgrounds/Stones.png");
        arr.add(Level.getRoom(10, 0, 0, 0, 0, new ArrayList<Obstacle>(), player, t));
        arr.add(Level.getRoom(4, 4, 4, 1, 0, new ArrayList<Obstacle>(Arrays.asList(new Crate(0, 500), new Crate(900, 900))), player, t));
        arr.add(Level.getRoom(2, 2, 3, 2, 0, new ArrayList<Obstacle>(Arrays.asList(new Crate(500, 1000), new Crate(1000, 1000))), player, t));
        arr.add(Level.getRoom(5, 0, 3, 5, 0, new ArrayList<Obstacle>(Arrays.asList(new Crate(200, 500), new Crate(400, 1000), new Crate(1000, 300))), player, t));
        return arr;
    }


    public Level getNextLevel() {
        getCurrentRoom().getPlayer().setHealth(getCurrentRoom().getPlayer().getMaxHealth());
        return new LevelThree(getCurrentRoom().getPlayer());
    }

    @Override
    public String toString() {
        return "2 - " + getRoomNumber();
    }
}
