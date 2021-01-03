package kchandra423.entities.obstacles;

import Sprite.SpriteRectangle;
import Textures.Texture;
import kchandra423.entities.Entity;
import kchandra423.entities.actors.Actor;
import kchandra423.shapes.Rectangle;
import kchandra423.shapes.Shape;
import kchandra423.utility.Constants.collisionDirection;
import kchandra423.utility.Constants.collisionTypes;

public class Obstacle extends Entity{
	public Obstacle(Texture t, Rectangle r) {
		super(new SpriteRectangle(t,r,true));
	}
	public collisionTypes getObstaclesType() {
		return null;
		
	}
//	public collisionDirection intersects(Actor other) {
//		collisionDirection answer=collisionDirection.NONE;
//		Shape otherShape=other.getSprite().getShape();
//		Rectangle myShape= (Rectangle)getSprite().getShape();
//		try {
//			if(otherShape.intersects(myShape.getLeft())||otherShape.intersects(myShape.getRight())) {
//				answer=collisionDirection.X;
//			}
//			if(otherShape.intersects(myShape.getTop())||otherShape.intersects(myShape.getBottom())) {
//				double height=otherShape.getBoundingRectangle().getRight().getLength()/2-10;
////				if(otherShape.getY()>myShape.getTop().getY()||otherShape.getY()<myShape.getBottom().getY()) {
//////					
////
//////					answer=collisionDirection.Y;
////				}
////				else {
////					answer=collisionDirection.Y;
//					if(answer==collisionDirection.X) {
//						
//					answer=collisionDirection.BOTH;
//					}
//					else {
//						answer=collisionDirection.Y;
////					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return answer;
//	}
	
}
