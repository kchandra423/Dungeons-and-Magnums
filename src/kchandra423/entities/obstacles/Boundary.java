package kchandra423.entities.obstacles;


import kchandra423.graphics.shapes.Rectangle;
import kchandra423.graphics.shapes.Shape;

public class Boundary extends Rectangle implements Collideable {
    public Boundary(float x, float y, float width, float height){
        super(x,y,width,height);
    }

}
