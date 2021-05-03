package kchandra423.actors;

import kchandra423.actors.players.Enemy;
import kchandra423.actors.players.Player;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

import java.awt.*;
import java.util.ArrayList;

public class Room {
    private ArrayList<Enemy> enemies;
    private ArrayList obstacles;
    private final Player player;
    private final Rectangle bounds;
    private final Texture background;

    public Room() {
        player = new Player(new KImage(0, 0, false, false, Texture.TextureBuilder.getTexture("res/Images/Players/MageIdle.gif")), null);
        background = Texture.TextureBuilder.getTexture("res/Images/Backgrounds/tiles.jpg");
        enemies = new ArrayList<>();
        enemies.add(new Enemy());
        bounds = new Rectangle(0, 0, 1000, 1000);
        background.resize(1000,1000);
    }


    public void draw(DrawingSurface d) {
        d.stroke(0);
        background.draw(d, bounds.x, bounds.y);
        for (Enemy e :
                enemies) {
            e.draw(d);
        }
        player.act(d, this);
        player.draw(d);
    }

    public boolean inBounds(KImage shape) {
        return bounds.contains(shape.getBounds());
    }
    public boolean inBounds(float x, float y) {
        return bounds.contains(x,y);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
