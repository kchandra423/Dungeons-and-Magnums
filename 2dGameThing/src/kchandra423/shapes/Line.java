package kchandra423.shapes;
import kchandra423.utility.Calculator;
import processing.core.PApplet;
/**
 * Represents a line using processing
 * @author Kumar Chandra
 *
 */
public class Line {
	//your line class should be identical so I don't feel the need to comment this. I didn't add anything special here,
	//except for a few self explanatory method method
	private double x1, y1, x2, y2;
	/**
	 * creates a new line
	 * @param x1 x coord of first point
	 * @param y1 y coord of first point
	 * @param x2 x coord of second point
	 * @param y2 y coord of second point
	 */
	public Line(double x1, double y1,double x2, double y2) {
			this.x1=x1;
			this.x2=x2;
			this.y1=y1;
			this.y2=y2;
		}
	/**
	 * draws the line
	 * @param pad PApplet used to draw stuff
	 */
		public void draw(PApplet pad) {
			pad.beginShape();
			pad.vertex((float)getx1(), (float)gety1());
			pad.vertex((float)getx2(), (float)gety2());
			pad.endShape();
		}
		/**
		 * returns the intersection point between this line and a given line
		 * @param other the given line
		 * @return xcoord of intersection point
		 */
		public double getIntersectionX(Line other) {
			double answer=0;
			double x3=other.getx1(), x4=other.getx2(), y3=other.gety1(), y4=other.gety2();
			answer= ((x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
			return answer;
		} 
		/**
		 * returns the intersection point between this line and a given line
		 * @param other the given line
		 * @return ycoord of intersection point
		 */
		public double getIntersectionY(Line other) {
			double answer=0;
			double x3=other.getx1(), x4=other.getx2(), y3=other.gety1(), y4=other.gety2();
			answer= ((x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
			return answer;
		}
		/**
		 * tells whether or not this line and a given line intersect along its domain
		 * @param other the given line
		 * @return true if they intersect along the domain, false if they dont 
		 */
		public boolean intersects(Line other) {
			boolean answer =false;
			double x=getIntersectionX(other);
			double y=getIntersectionY(other);
			double otherlesserX, othergreaterX,otherlesserY, othergreaterY, greaterX, lesserX, lesserY, greaterY;
			
			if(x1>x2) {
				greaterX=x1;
				lesserX=x2;
			}
			else {
				greaterX=x2;
				lesserX=x1;
			}
			
			if(y1>y2) {
				greaterY=y1;
				lesserY=y2;
			}
			else {
				greaterY=y2;
				lesserY=y1;
			}
			
			
			if(other.getx1()>other.getx2()) {
				othergreaterX=other.getx1();
				otherlesserX=other.getx2();
			}
			else {
				othergreaterX=other.getx2();
				otherlesserX=other.getx1();
			}
			if(other.gety1()>other.gety2()) {
				othergreaterY=other.gety1();
				otherlesserY=other.gety2();
			}
			else {
				othergreaterY=other.gety2();
				otherlesserY=other.gety1();
			}
			if(
					(
					(x>=lesserX&&x<=greaterX)
					&&
					(x>=otherlesserX&&x<=othergreaterX)
					)
				&&(
						(y>=lesserY&&y<=greaterY)
						&&
						(y>=otherlesserY&&y<=othergreaterY)
				)
				
					) {
				
				answer=true;
			}
			if(((x1==other.getx1()||x1==other.getx2())||(x2==other.getx1()||x2==other.getx2()))
					&&
					((y1==other.gety1()||y1==other.gety2())||(y2==other.gety1()||y2==other.gety2()))
					) {
				answer=true;
			}
			if((x1==x2)&&(y1==y2)){
				if((x1==other.getx1()||x==other.getx2())||(y1==other.gety1()||y==other.gety2())) {
				answer=true;
				}
			}
			if((other.getx2()==other.getx1())&&(other.gety2()==other.gety1())) {
				if((x1==other.getx1()||x==other.getx2())||(y1==other.gety1()||y==other.gety2())) {
					answer=true;
					}
			}
			return answer;
			
		}
		/**
		 * checks if a point intersects this line along the lines domain
		 * @param x xcoord of point
		 * @param y ycoord of point
		 * @return true if they intersect along the lines domain, false otherwise
		 */
		public boolean intersects(double x, double y) {
			boolean lineIntersects=false;
			boolean inXRange=false;
			boolean inYRange=false;
			boolean answer=false;
			if(getSlope()*x+getYInterecept()-0.0001<=y&&y<=getSlope()*x+getYInterecept()+0.0001) {
				lineIntersects=true;
			}
			if(getMinX()-0.00001<=x&&x<=getMaxX()+0.00001) {
				inXRange=true;
			}
			if(getMinY()-0.00001<=y&&y<=getMaxY()+0.00001) {
				inYRange=true;
			}
			if(lineIntersects&&inXRange&&inYRange) {
				answer=true;
			}
			return answer;
		}
		/**
		 * returns maximum x coord (x coord of right most point)
		 * @return xcoord of right most point
		 */
		public double getMaxX() {
			return Math.max(x1, x2);
		}
		/**
		 * returns min x coord (x coord of left most point)
		 * @return x coord of left most point
		 */
		public double getMinX() {
			return Math.min(x1, x2);
		}
		/**
		 * returns maximum ycoord (y coord of bottom most point)
		 * @return ycoord of bottom most point
		 */
		public double getMaxY() {
			return Math.max(y1, y2);
		}
		/**
		 * returns  minimum y coord (y coord of top most point)
		 * @return ycoord of top most point
		 */
		public double getMinY() {
			return Math.min(y1, y2);
		}
		/**
		 * returns slope of the line (the "m" y=mx+b)
		 * @return slope
		 */
		public double getSlope() {
			double deltaY=y1-y2;
			double deltaX=x1-x2;
			return deltaY/deltaX;
		}
		/**
		 * returns y intercept of the line (the b in y= mx+b)
		 * @return y intercept
		 */
		public double getYInterecept() {
			return y1-getSlope()*x1;
		}
		/**
		 * set the coordinate of the second point
		 * @param x2 new xcoord of second point
		 * @param y2 new ycoord of second point
		 */
		public void setPoint2(double x2,double y2) {
			this.x2=x2;
			this.y2=y2;
			
		}
		public void rotate(double theta) {
			double midX=getMidPointX();
			double midY= getMidPointY();
//		    x1 = ((x1 - midX) * Math.cos(theta) + (y1 - midY) * Math.sin(theta) ) + midX;
//		    y1 = ( -(x1 - midX) * Math.sin(theta) + (y1 - midY) * Math.cos(theta) ) + midY;
//		    x2 = ((x2 - midX) * Math.cos(theta) + (y2 - midY) * Math.sin(theta) ) + midX;
//		    y2 = ( -(x2 - midX) * Math.sin(theta) + (y2 - midY) * Math.cos(theta) ) + midY;
			
			
			 double s = Math.sin(theta);
			 double c = Math.cos(theta);

			  // translate point back to origin:
			  x1 -= midX;
			  y1 -= midY;

			  // rotate point
			  double xnew = x1 * c - y1 * s;
			  double ynew = x1 * s + y1 * c;

			  // translate point back:
			  x1 = xnew + midX;
			  y1 = ynew + midY;

			  x2 -= midX;
			  y2 -= midY;

			  // rotate point
			   xnew = x2 * c - y2 * s;
			  ynew = x2 * s + y2 * c;

			  // translate point back:
			  x2 = xnew + midX;
			  y2 = ynew + midY;
			
//		    double length=getLength();
//			double theta=Calculator.calculateAngle(midY, midY, x1, y1);
//			x1=midX+Math.cos(theta+d)*length/2;
//			y1=midY+Math.sin(theta+d)*length/2;
//			x2=midX+Math.cos(theta+d+Math.PI)*length/2;
//			y2=midY+Math.sin(theta+d+Math.PI)*length/2;
		}
		public void rotateAboutPoint(double x, double y, double theta) {
			double pX=x;
			double midY= y;
//		    x1 = ((x1 - midX) * Math.cos(theta) + (y1 - midY) * Math.sin(theta) ) + midX;
//		    y1 = ( -(x1 - midX) * Math.sin(theta) + (y1 - midY) * Math.cos(theta) ) + midY;
//		    x2 = ((x2 - midX) * Math.cos(theta) + (y2 - midY) * Math.sin(theta) ) + midX;
//		    y2 = ( -(x2 - midX) * Math.sin(theta) + (y2 - midY) * Math.cos(theta) ) + midY;
			
			
			 double s = Math.sin(theta);
			 double c = Math.cos(theta);

			  // translate point back to origin:
			  x1 -= pX;
			  y1 -= midY;

			  // rotate point
			  double xnew = x1 * c - y1 * s;
			  double ynew = x1 * s + y1 * c;

			  // translate point back:
			  x1 = xnew + pX;
			  y1 = ynew + midY;

			  x2 -= pX;
			  y2 -= midY;

			  // rotate point
			   xnew = x2 * c - y2 * s;
			  ynew = x2 * s + y2 * c;

			  // translate point back:
			  x2 = xnew + pX;
			  y2 = ynew + midY;
		}
		//this is the method that I added cuz I didn't wanna make a new lane every single time 
		/**
		 * sets both coordinates to specified values
		 * @param x1 new xcoord of first point
		 * @param y1 new ycoord of first point
		 * @param x2 new xcoord of second point
		 * @param y2 new ycoord of second point
		 */
		public void setPoints(double x1, double y1, double x2, double y2 ) {
			this.x1=x1;
			this.x2=x2;
			this.y1=y1;
			this.y2=y2;
		}
		/**
		 * moves both points by specified values
		 * @param xamount x translation
		 * @param yamount y translation
		 */
		public void shift(double xamount, double yamount) {
			x1+=xamount;
			x2+=xamount;
			y1+=yamount;
			y2+=yamount;
		}
		/**
		 * returns xcoord of first point
		 * @return first xcoord
		 */
		public double getx1() {
			//why did i put this.
			return this.x1;
		}
		/**
		 * returns xcoord of second point
		 * @return second xcoord
		 */
		public double getx2() {
			return this.x2;
		}
		/**
		 * returns y coord of first point
		 * @return ycoord of first point
		 */
		public double gety1() {
			return this.y1;
		}
		/**
		 * returns y coord of second point
		 * @return y coord of second point
		 */
		public double gety2() {
			return this.y2;
		}
		/**
		 * returns the length of the line
		 * @return length
		 */
		public double getLength() {
			return PApplet.dist((float)x1, (float)y1, (float)x2, (float)y2);
		}
		public double getMidPointX() {
			return (x1+x2)/2;
		}
		public double getMidPointY() {
			return (y1+y2)/2;
		}
		public String toString() {
			String answer;
			answer=""+x1+" , "+y1+" , "+x2+" , "+y2;
			return answer;
			
		}
}
