package kchandra423.entities.obstacles;

import kchandra423.graphics.shapes.Shape;

public interface Collideable {
    public boolean intersects(Shape other) throws Exception;
}
