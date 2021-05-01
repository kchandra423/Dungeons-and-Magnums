package kchandra423.actors.players;

import kchandra423.actors.Actor;
import kchandra423.actors.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;

public class Enemy extends Actor
{


    protected Enemy(KImage image, float maxV, float accel) {
        super(image, maxV, accel);
    }

    @Override
    public void act(DrawingSurface d, Room r) {

    }
}
