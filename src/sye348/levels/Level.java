package sye348.levels;

import kchandra423.actors.movingActors.enemies.*;
import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;

import java.awt.*;
import java.util.ArrayList;


/**
 * Represents a level with mutliple rooms
 *
 * @author Spencer Ye modified by Kumar Chandra
 * @version 1.2.0
 * Last Revised: 5/20/2021
 */
public abstract class Level {

    private final ArrayList<Room> rooms;

    private int roomNumber;


    /**
     * Creates a Level given a array of rooms
     *
     * @param rooms The rooms defined for a level
     */
    public Level(ArrayList<Room> rooms) {
        this.rooms = rooms;
        roomNumber = 1;
    }

    /**
     * Draws the level
     *
     * @param window The DrawingSurface that is drawn on
     */
    public void draw(DrawingSurface window) {
        getCurrentRoom().draw(window);
        if (getCurrentRoom().isCompleted()&&rooms.size()>1) {
            goForwardRoom();
        }
    }

    /**
     * Advances the room that the player is in
     */
    public void goForwardRoom() {
        rooms.remove(0);
        roomNumber++;
    }

//    /**
//     * Moves the room that the player is in backwards
//     */
//    public void goBackwardRoom() {
//        if (roomNumber == 0) {
//            System.out.println("NO MORE ROOMS TO GO BACK");
//            return;
//        }
//        roomNumber--;
//    }

    /**
     * Gets the room number that the player is currently in
     *
     * @return The room number that the player is currently in
     */
    public int getRoomNumber() {
        return roomNumber;
    }


    /**
     * Checks if the level has been completed
     *
     * @return If the level has been completed
     */
    public boolean isCompleted() {
        return rooms.size() <= 1 && getCurrentRoom().isCompleted();
    }


    /**
     * Gives the current room that the level is on
     *
     * @return The current room the level is on
     */
    public Room getCurrentRoom() {
        return rooms.get(0);
    }

    /**
     * Gives the next level after the current level
     *
     * @return The next level to play
     */
    public abstract Level getNextLevel();

    /**
     * Gets a random x-coordinate of a random location given a boundary and the padding wanted
     *
     * @param bounds The boundaries where the x-coordinate should correlate with
     * @param margin The absolute distance from the edge of the boundaries where the coordinate given
     * @return The x-coordinate of a random location
     */
    protected static float getRandXCoord(Rectangle bounds, int margin) {
        return (float) (Math.random() * (bounds.width - margin) + bounds.x);
    }

    /**
     * Gets a random y-coordinate of a random location given a boundary and the padding wanted
     *
     * @param bounds The boundaries where the y-coordinate should correlate with
     * @param margin The absolute distance from the edge of the boundaries where the coordinate given
     * @return The y-coordinate of a random location
     */
    protected static float getRandYCoord(Rectangle bounds, int margin) {
        return (float) (Math.random() * (bounds.height - margin) + bounds.y);
    }

    protected static Room getRoom(int bats, int goblins, int witches, int minoutaurs, int cyclops, ArrayList<Obstacle> obstacles, Player player, Texture t) {
        Rectangle bounds = new Rectangle(1500, 1500);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        for (int i = 0; i < goblins; i++)
            enemies.add(new Goblin(getRandXCoord(bounds, 200), getRandYCoord(bounds, 200)));
        for (int i = 0; i < bats; i++)
            enemies.add(new Bat(getRandXCoord(bounds, 200), getRandYCoord(bounds, 200)));
        for (int i = 0; i < witches; i++)
            enemies.add(new Witch(getRandXCoord(bounds, 200), getRandYCoord(bounds, 200)));
        for (int i = 0; i < minoutaurs; i++)
            enemies.add(new Minotaur(getRandXCoord(bounds, 250), getRandYCoord(bounds, 250)));
        for (int i = 0; i < cyclops; i++)
            enemies.add(new Cyclops(getRandXCoord(bounds, 250), getRandYCoord(bounds, 250)));


        return new Room(t,
                obstacles,
                enemies,
                player,
                bounds);
    }

    public abstract String toString();

}



