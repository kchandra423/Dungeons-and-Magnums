package kchandra423.testers;

import kchandra423.shapes.*;
public class Test {
	public static void main (String[] args) {
//		Line l=new Line(10,10,30,20);
//		System.out.println(l);
//		l.rotateAboutPoint(0,5,Math.PI*1/3);
//		
//		System.out.println(l);
		Rectangle r=new Rectangle(10,10,30,20);
		System.out.println(r);
		r.rotateAboutTLCorner(Math.PI*1/3);
		
		System.out.println(r);
		
		
	}
}
