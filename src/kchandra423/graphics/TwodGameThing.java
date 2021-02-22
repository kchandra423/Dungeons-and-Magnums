package kchandra423.graphics;
import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
/**
 * Has the main method
 * @author Kumar Chandra
 *
 */
public class TwodGameThing{

//	public static final int BOUNDSX=1500,BOUNDSY=1000;
	
	
/**
 * runs the program
 * @param args
 */
	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
//		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
//		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
//		JFrame window = (JFrame)canvas.getFrame();
//
////		window.setSize(BOUNDSX, BOUNDSY);
//		window.setSize(1500,100);
//		window.setMinimumSize(new Dimension(1500,1000));
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setResizable(true);
//
//
//		window.setVisible(true);
//		canvas.requestFocus();
	}
	
	
	
	
	
	
	
}
