package main;

import javazoom.jl.decoder.JavaLayerException;
import processing.core.PApplet;

import kchandra423.graphics.screens.DrawingSurface;
import rlee348.sounds.JayLayer;

/**
 * Main method that tests the gui
 * @author Ryan Lee
 *
 */
public class DungeonsAndMagnums {
	public static JayLayer music;
	/**
	 * Runs the program
	 * @param args The arguments to be passed
	 */
	public static void main(String[] args) {
		String[] songs = new String[]{"BattleTheme.mp3"};
		music = new JayLayer("res/Music/", "res/Music/", false);
		music.addPlayList();
		music.addSongs(0, songs);
		music.changePlayList(0);
		music.nextSong();
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);

	}

}
