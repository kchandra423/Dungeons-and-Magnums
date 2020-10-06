package kchandra423.shapes;
import processing.core.PApplet;
/**
 * Represents a rectangle composed of 4 lines
 * @author Kumar Chandra
 *
 */
public class Rectangle {
	// things i Plan to do:
	// add basic shifting methods
	// add a method that checks if you overlap with an object (intersects or inside)
	// things i might do : 
	// rotations
	// scaling
	// if i do end up doing these, i probably wont let the user scale from a given point
	// ill scale from whatever point makes things most convenient (top left and top right)
//	private double x1,x2,x3,x4,y1,y2,y3,y4;
	private Line[] lines;
	private Line top, left, bottom, right;
	//line [] format should be 0-top, 1-left, 2-bottom, 3-right
//	i was debating between having a 4 lines or 4 points,
//	and I'm still not sure what choice is best but im going with this for now
	/**
	 * Creates a new rectangle
	 * @param x x value of top left corner
	 * @param y y value of top left corner
	 * @param width width (x length)
	 * @param height height (y length)
	 */
	public Rectangle(double x, double y, double width, double height) {
		lines=new Line[4];
		top=new Line(x,y,x+width,y);
		left= new Line(x,y,x,y+height);
		bottom= new Line(x,y+height, x+width, y+height);
		right= new Line(x+width, y, x+width, y+height);
		
		lines[0]=top;
		lines[1]=left;
		lines[2]=bottom;
		lines[3]=right;
	}
	/**
	 * draws the rectangle
	 * @param pad the Papplet used
	 */
	public void draw(PApplet pad) {
		for(int i=0;i<lines.length;i++) {
			lines[i].draw(pad);
		}
	}
	/**
	 * checks if a point is inside rectangle
	 * @param x x coord of point
	 * @param y y coord of point
	 * @return
	 */
	public boolean isPointInside(double x, double y ) {
		boolean answer=false;
		double smallX=Math.min(top.getx1(), top.getx2());
		double bigX=Math.max(top.getx1(), top.getx2());
		double smallY=Math.min(left.gety1(), left.gety2());
		double bigY=Math.max(left.gety1(), left.gety2());
		if(smallX<=x&&x<=bigX&&smallY<=y&&y<=bigY) {
			answer=true;
		}
		
		return answer;
	}
	/**
	 * returns the area (length*height)
	 * @return area
	 */
	public double getArea() {
		return top.getLength()*left.getLength();
		
	}
	/**
	 * returns perimeter (2*length + 2*height)
	 * @return perimeter
	 */
	public double getPerimeter() {
		return top.getLength()*2+left.getLength()*2;
	}
	/**
	 * moves the rectangle by x units units to the right (or left if negative)
	 *  and the same for the y direction
	 * @param xAmount x units for translating
	 * @param yAmount y units for translating
	 */
	public void shift(double xAmount, double yAmount) {
		for(int i=0; i<lines.length;i++) {
			lines[i].shift(xAmount, yAmount);
		}
	}
	/**
	 * checks if any of the edges intersect with the given live
	 * @param other the given line
	 * @return true if the line touches any of edges, false
	 * if inside but not touching an edge or otherwise
	 */
	public boolean intersectsEdge(Line other) {
		boolean answer=false;
		for (int i=0;i<lines.length;i++) {
			if(lines[i].intersects(other)) {
				answer=true;
			}
		}
		return answer;
		
	}
	public void rotateAboutTLCorner(double theta) {
			double x=getTopLeftX();
			double y=getTopLeftY();
		for(int i=0;i< 4;i++) {
			lines[i].rotateAboutPoint(x, y, theta);
		}
		
	}
	/**
	 * checks if any of this rectangles edges intersect with any of the other
	 * rectangles edges. If this rectangle returns true, the other rectangle should also return true, 
	 * and vice versa. being inside a rectangle does not count as intersecting.
	 * @param other the given rectangle
	 * @return true if any lines intersect with any of the other rectangles lines
	 * and false otherwise.
	 */
	public boolean intersectsEdge(Rectangle other) {
		boolean answer=false;
		for (int i=0;i<lines.length;i++) {
			for(int j=0;j<other.getLines().length;j++) {
				if(lines[i].intersects(other.getLines()[j])) {
				answer=true;
				}
			}
		}
		return answer;
		
	}
	/**
	 * gives the top line (may not always look like the "top" line)
	 * @return top line
	 */
	public Line getTop() {
		return top;
	}
	/**
	 * gives the left line (may not always look like the "left" line)
	 * @return left line
	 */
	public Line getLeft() {
		return left;
	}
	/**
	 * gives the bottom line (may not always look like the "bottom" line)
	 * @return bottom line
	 */
	public Line getBottom() {
		return bottom;
	}
	/**
	 * gives the right line (may not always look like the "right" line)
	 * @return right line
	 */
	public Line getRight() {
		return right;
	}
	/**
	 * gives all lines in a array, in the order of (top,left,bottom,right)
	 * @return all lines as an array
	 */
	public Line[] getLines() {
		return lines;
	}
	public double getTopLeftX() {
		return top.getx1();
	}
	public double getTopLeftY() {
		return top.gety1();
	}
	public String toString() {
		String answer=top.toString()+"\n"+left.toString()+"\n"+bottom.toString()+"\n"+right.toString();
		return answer;
	}
}
