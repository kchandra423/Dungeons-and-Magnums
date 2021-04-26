package kchandra423.utility;

public class Calculator {
    public static double calculateAngle(double x1, double y1, double x2, double y2) {
        double initialx, initialy, displacementx, displacementy,angle;
        initialx=x1;
        initialy=y1;
        //literally just physics
        displacementx=x2-initialx;
        displacementy=y2-initialy;
        angle=Math.atan(displacementy/displacementx);
        if(displacementx==0) {
            displacementx=Double.MIN_VALUE;
        }
        if(displacementx<0) {
            //remeber that the domain of arctan is the first and 4th quadrants, so if cos is negative,
            // you must add 180 degs
            angle+=Math.PI;
        }
        return angle;
    }
}
