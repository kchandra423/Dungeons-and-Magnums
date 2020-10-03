package kchandra423.graphics;
import kchandra423.shapes.Circle;
import kchandra423.shapes.Line;
import kchandra423.shapes.Rectangle;

/**
 * basic tester class
 * @author Kumar Chandra
 *
 */
public class Tester {
	/**
	 * runs the tester
	 * @param args
	 */
	public static void main(String args[]) {	
		Rectangle r= new Rectangle(10,10,50,30);
		Circle c= new Circle(10,10,50);
		
		
		System.out.println("*******Testing for rectangle******");
		System.out.println(r.isPointInside(20, 15)+" (should be true)");
		System.out.println(r.getArea());
		System.out.println(r.getPerimeter());
		System.out.println(r.intersectsEdge(new Line(15,5,15,10)));
		System.out.println(r.intersectsEdge(new Rectangle(5,5,50,30)));
		System.out.println(r.intersectsEdge(r));
		System.out.println(r.intersectsEdge(r.getBottom()));
		System.out.println(r.intersectsEdge(new Line(0,0,10,0)));
		System.out.println(r.intersectsEdge(new Rectangle(15,15,2,2)));
		System.out.println(r.intersectsEdge(new Rectangle (70,40,5,5)));
		
		
		System.out.println("*******Testing for circle******");
		System.out.println(c.intersectsEdge(new Line(5,5,15,15)));
		System.out.println(c.intersectsEdge(new Line(0,0,15,10)));
		System.out.println(c.isTangent(new Line (60,0,60,20)));
		System.out.println(c.isTangent(new Line (20,30,21,62.6)));
	}
}
