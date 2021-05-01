package kchandra423.actors;

import kchandra423.graphics.textures.KImage;

public class Entity {

    protected KImage image;
    public Entity (KImage image){
        this.image = image;
    }
    public boolean intersects(Entity other){
        return image.intersects(other.image);
    }
}
