package kchandra423.shapes;
import processing.core.PApplet;
/**
 * Class that represents a circle using processing
 * @author Kumar Chandra
 *
 */
public class Circle {
 private double x, y;//x and y of the center
 private double diameter;
/**
 * Creates a circle
 * @param centerX x coord of center of the circle
 * @param centerY y coord of center of the circle
 * @param diameter diameter of circle (2*radius)
 */
 public Circle(double centerX, double centerY, double diameter) {
	 x=centerX;
	 y=centerY;
	 this.diameter=diameter;
 }
 /**
  * Just draws the shape
  * @param pad a Papplet
  */
 public void draw(PApplet pad) {
	 pad.circle((float)x,(float)y,(float)diameter);
 }
 /**
  * checks whether or not this circle intersects a rectangle by 
  * checking if it intersects any of the rectangle's 4 lines
  * @param other rectangle that you check
  * @return true if intersects edge, false if doesn't or inside
  */
 public boolean intersectsEdge(Rectangle other) {
	 boolean answer=false;
	 Line[] lines = other.getLines();
	 for(int i=0; i<lines.length;i++) {
		 if(intersectsEdge(lines[i])) {
			 answer=true;
		 }
	 }
	 
	 return answer;
 }
 /**
  * Checks if this circle intersects a line 
  * @param other the line youre checking intersection with
  * @return returns true if the line touches the edge of the circle. 
  * returns false otherwise, even if the line is inside the circle
  */
 public boolean intersectsEdge(Line other) {
	 boolean point1Inside=false;
	 boolean point2Inside=false;
	 if(isPointInside(other.getx1(),other.gety1())){
		point1Inside=true;
	 }
	 if(isPointInside(other.getx2(),other.gety2())){
		 point2Inside=true;
	 }
	 // if one point is in the circle, by default they must intersect
	 if((point2Inside&&!point1Inside)||(point1Inside&&!point2Inside)) {
		 return true;
	 }
	 else if(point2Inside&&point1Inside) {
		 return false;
	 }
	
	 
	 
	 boolean answer=false;
	 // find slope and y intercept of normal (perpindicular)
	 // line of the other line that goes through the center
	 double slopeNorm = -(1/other.getSlope());
	 double interceptNorm = y-slopeNorm*x;
	 
	 //get where the two lines intersects
	 double xValueOfIntersection=(interceptNorm-other.getYInterecept())/(other.getSlope()-slopeNorm);
	 double yValueOfIntersection= slopeNorm*xValueOfIntersection+interceptNorm;
	 
//	 if(yValueOfIntersection>(other.getSlope()*xValueOfIntersection+other.getYInterecept())+0.0001){
//		 System.out.println("things were calculated incorrectly");
//	 }
//	 if(yValueOfIntersection<(other.getSlope()*xValueOfIntersection+other.getYInterecept())-0.00001){
//		 System.out.println("things were calculated incorrectly");
//	 }
//	 double ahhhh=(other.getSlope()*xValueOfIntersection+other.getYInterecept());
//	 if(yValueOfIntersection!=ahhhh){
//		 System.out.println(ahhhh+" : norm line");
//		 System.out.println(yValueOfIntersection+" : line");
//	 }
//	 if(!other.intersects(xValueOfIntersection, yValueOfIntersection)) {
//		 System.out.println("something is mongey");ue
//	 }
	
	 //intersection point is part of the other line
	 if(!other.intersects(xValueOfIntersection, yValueOfIntersection)) {
		
		 return false;
	 }
	 
	 // if the place they intersect is less than the radius
	 double dist=PApplet.dist((float)x, (float)y, (float)xValueOfIntersection,(float)yValueOfIntersection);
	 if(dist+0.001<=getRadius()) {

		 answer=true;
	 }
	 
	 
	 return answer;
 }
 // **********THIS METHOD DOESN'T WORK FOR VERTICAL AND HORIZANTAL LINES******************8
 // the calculations become bad because the slope of the line or of the tangent line 
 // will be 1/0, and it messes up the calculations
 /**
  * checks if a line is tangent to the circle. has a relatively high margin of error, so as to be useful
  * Does not work for horizontal and vertical lines!
  * @param other line that you check tangency with
  * <pre> other  line can't be vertical or horizontal
  * @return  true if tangent to circle. false if not tangent or wouldn't be tangent if line extended forever
  * 
  */
 public boolean isTangent(Line other) {
//	 boolean point1Inside=false;
//	 boolean point2Inside=false;
//	 if(isPointInside(other.getx1(),other.gety1())){
//		point1Inside=true;
//	 }
//	 if(isPointInside(other.getx2(),other.gety2())){
//		 point2Inside=true;
//	 }
//	 if((point2Inside&&!point1Inside)||(point1Inside&&!point2Inside)) {
//		 return true;
//	 }
	 boolean answer=false;
	 double slopeNorm = -(1/other.getSlope());
	 double interceptNorm = y-slopeNorm*x;
	 
	 double xValueOfIntersection=(interceptNorm-other.getYInterecept())/(other.getSlope()-slopeNorm);
	 double yValueOfIntersection= slopeNorm*xValueOfIntersection+interceptNorm;

	 if(!other.intersects(xValueOfIntersection, yValueOfIntersection)) {
		
		 return false;
	 }
	 double dist=PApplet.dist((float)x, (float)y, (float)xValueOfIntersection,(float)yValueOfIntersection);
	 //really high margin of error on the tangent line, just to make it so its a practical method
	 // if it calculated things exactly, you would basically never get the tangent line
	 if(getRadius()-1<=dist&&dist<=getRadius()+1) {

		 answer=true;
	 }
	 
	 
	 return answer;
 }
 /**
  * checks if points is inside circle
  * @param x  x coord of point
  * @param y  y coord of point
  * @return  true if in or on circle 
  */
 public boolean isPointInside(double x, double y) {
	 boolean answer=false;
	 if(PApplet.dist((float)this.x,(float)this.y, (float)x, (float)y)<=getRadius()) {
		 answer=true;
	 }
	 
	 return answer;
 }
 /**
  * gives perimeter (2*pi*radius)
  * @return  perimeter 
  */
 public double getPerimeter() {
	 return Math.PI*getDiameter();
 }
 /**
  * shifts the circle by the specified amount
  * @param xAmount x translation
  * @param yAmount y translation
  */
 public void shift(double xAmount, double yAmount) {
	 
	 x+=xAmount;
	 y+=yAmount;
 }
 public double getX() {
	 return x;
 }
 public double getY() {
	 return y;
 }
 /**
  * gives area (pi*r^2)
  * @return  area
  */
 public double getArea() {
	 //pi*r^2
	 return Math.PI*Math.pow(getRadius(), 2);
 }
 /**
  * gives diameter of circle
  * @return  diameter
  */
 public double getDiameter() {
	 return diameter;
 }
 /**
  * gives radius (half of diameter)
  * @return radius
  */
 public double getRadius() {
	 return diameter/2;
 }
}
