package kchandra423.levels;

import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.players.Rogue;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import kchandra423.utility.AssetLoader;
import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

/**
 * A room contains all the enemies and obstacles and boundaries that a player is currently in
 *
 * @author Kumar Chandra
 * @see kchandra423.actors.Actor
 */
public class Room {
    private final int[][] grid;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Obstacle> obstacles;
    private final Player player;
    private final Rectangle bounds;
    private final Obstacle portal;
    private final Texture background;

    /**
     * Currently just creates a room with some random default enemies and obstacles
     */
    public Room() {
        grid  = new int[8][8];
        background = Texture.TextureBuilder.getTexture("res/Images/Backgrounds/froggy.jpg");
        obstacles = new ArrayList<>();
        Obstacle o = new Obstacle(AssetLoader.getImage(AssetLoader.Sprite.BOX));
        o.getImage().moveTo(800, 800);
        obstacles.add(o);
        enemies = new ArrayList<>();
        bounds = new Rectangle(50, 50, 2000, 2000);
//        enemies.add(Enemy.createEnemy(bounds.x + 10, bounds.y + 10));
//        enemies.add(Enemy.createEnemy(bounds.x + 500, bounds.y + 500));
        portal = new Obstacle(AssetLoader.getImage(AssetLoader.Sprite.TELEPORTER));
        portal.getImage().moveTo((float) bounds.getCenterX(), (float) bounds.getCenterY());

//        enemies.add(Enemy.createEnemy(bounds.x + 10, bounds.y + 500));
//        enemies.add(Enemy.createEnemy(bounds.x + 500, bounds.y + 10));
        player = new Rogue(bounds.x + 50, bounds.y + 50);
//        player.getImage().moveTo(bounds.x + 50, bounds.y + 50);
        background.resize(bounds.width, bounds.height);
    }

    /**
     * Constructs a room with the given values
     *
     * @param background The background of this room
     * @param obstacles  The obstacles in this room
     * @param enemies    The enemies in this room
     * @param p          The Player in this room
     * @param bounds     The boundaries of this room
     */
    public Room(Texture background, ArrayList<Obstacle> obstacles, ArrayList<Enemy> enemies, Player p, Rectangle bounds) {
        grid  = new int[8][8];
        this.background = background;
        this.obstacles = obstacles;
        this.enemies = enemies;
        this.bounds = bounds;
        player = p;

        portal = new Obstacle(AssetLoader.getImage(AssetLoader.Sprite.TELEPORTER));
        portal.getImage().moveTo((float) bounds.getCenterX()-portal.getImage().getTexture().getWidth()/2f, (float) bounds.getCenterY()-portal.getImage().getTexture().getHeight()/2f);
        background.resize(bounds.width, bounds.height);
    }

    /**
     * Draws everything in this room onto the given drawing surface. Also cause the act method of all actors
     *
     * @param d The drawing surface that actors will be drawn onto
     */
    public void draw(DrawingSurface d) {
        background.draw(d, bounds.x, bounds.y);
        for (Enemy e :
                enemies) {
            e.act(d, this);
            e.draw(d);
        }
        for (Obstacle o :
                obstacles) {
            o.act(d, this);
            o.draw(d);
        }
        player.act(d, this);
        player.draw(d);
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isActive()) {
                enemies.remove(i);
                i--;
            }
        }
        if(allEnemiesDead()){
            portal.draw(d);
        }
    }

    /**
     * Returns whether or not a KImage is within the boundaries of this room.
     * Somewhat expensive call, although not as much as intersection
     *
     * @param shape The Kimage which will have its boundaries checked
     * @return Returns false if the KImage is not within the bounds of this room, using the rectangles class' definition of insides. Returns true otherwise
     */
    public boolean inBounds(KImage shape) {
        return bounds.contains(shape.getBounds());
    }

    /**
     * Returns whether or not a point is within the boundaries of this room
     * Inexpensive call
     *
     * @param x The x value of the point
     * @param y The y value of the point
     * @return Returns whether or not the x,y point is within the bounds of this room using the rectangle class' definition of insideness
     */
    public boolean inBounds(float x, float y) {
        return bounds.contains(x, y);
    }

    /**
     * Returns the player for this room
     *
     * @return this room's player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns all the enemies within this room
     *
     * @return All enemies contained by this room
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns all obstacles within this room
     *
     * @return All obstaacles contained in the room
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Returns the closest enemy from a given point
     *
     * @param x The x coordinate of the given point
     * @param y The y coordinate of the given point
     * @return The closest enemy from the given point
     */
    public Enemy getClosestEnemy(float x, float y) {
        Enemy min = null;
        float currentLowest = Float.POSITIVE_INFINITY;
        for (Enemy e :
                enemies) {
            float dist = PApplet.dist(x, y, e.getImage().getX(), e.getImage().getY());
            if (dist < currentLowest) {
                min = e;
                currentLowest = dist;
            }
        }
        return min;
    }

    private boolean allEnemiesDead() {
        return enemies.size() == 0;
    }

    /**
     * Returns whether this room is completed. This occurs when all enemies are dead and the player is standing on the portal
     * @return True if all enemies are dead and the player is standing on the portal. Returns false otherwise.
     */
    public boolean isCompleted() {
        return allEnemiesDead() && player.intersects(portal);

    }
}
