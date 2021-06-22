package rlee348.sounds;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import javazoom.jl.player.Player;
import jay.jaysound.JayLayerListener;

/**
 * JSoundPlayer
 * 
 * JSoundPlayer is a library based off the JLayer library, to enable Java beginners to use it.
 * 
 * This library is only tested with standard mp3 files!
 * 
 * This player is based off of playlists and sound effects. Playlists are designed for background music, which constantly plays, while sound effects are one-time-only sounds, that can be called when needed.
 * 
 * @author Jay Kamat, highly modified by Shelby without regard to Jay's "licenses", also modified by Ryan Lee
 * Manages a Playlists for MP3 files
 * 
 * A very tiny bit of this was made by shaines...thank you!
 * 
 * Released under the Lesser GNU Lesser General Public License V3.0
 * 
 * This program uses an unmodified version of the JLayer library (1.0.1), it is just bundled inside for convenience.
 * Find the unmodified JLayer library at http://www.javazoom.net/javalayer/javalayer.html
 * 
 * 
 * Copyright Jay Kamat, 2013
 */
public class JayLayer {
	private MP3Player sound;
	private String currentSongName;
	//two "types" of sound... titlesounds and gamesounds (there is a big difference)
	private ArrayList<ArrayList<String>> playLists;
	private ArrayList<MP3Player> soundEffects;
	private int currentPlayList=-1;
	private int currentSong=-1;
	private Random ran;
	private boolean randomize;
	private String prefixBackgroundEffects; //no prefix is inside the src folder (default package)\
	private String prefixSoundEffects;
	private float volume;

	private ArrayList<JayLayerListener> listeners;
	
	/**
	 * 
	 * @param prefixBackgroundSounds a prefix (or path) to sound files for background sounds. This is the root directory if null. Example: "audio/"
	 * @param randomize if true, songs in a playlist will be played in random order. If false, songs will be played in the order they are added.
	 * @param prefixSoundEffects a prefix (or path) to sound files for effects. This is the root directory if null. Example: "audio/"
	 * 
	 */
	public JayLayer(String prefixBackgroundSounds,String prefixSoundEffects, boolean randomize){
		super();
		listeners = new ArrayList<JayLayerListener>();
		this.randomize=randomize;
		playLists=new ArrayList<ArrayList<String>>();
		soundEffects=new ArrayList<MP3Player>();
		if (prefixBackgroundSounds != null)
			this.prefixBackgroundEffects=prefixBackgroundSounds;
		else
			this.prefixBackgroundEffects="";
		if (prefixSoundEffects != null)
			this.prefixSoundEffects=prefixSoundEffects;
		else
			this.prefixSoundEffects="";
		this.prefixSoundEffects=prefixSoundEffects;
		ran=new Random();
	}

	/**
	 * This constructor assumes all sound files are in the src directory
	 * @param doubleSoundCheck if true, this will do a check so a song wont play twice in a row. Leave false if only 1 song in any playlist, otherwise, leave true
	 */
	public JayLayer(boolean doubleSoundCheck){
		this(null,null,doubleSoundCheck);
	}
	
	/**
	 * Add a new listener. This listener will be notified when sound related events occur.
	 * @param jll The listener to be added.
	 */
	public void addJayLayerListener(JayLayerListener jll) {
		listeners.add(jll);
	}
	
	/**
	 * Remove a listener. This listener will no longer be notified when sound related events occur.
	 * @param jll The listener to be removed.
	 */
	public void removeJayLayerListener(JayLayerListener jll) {
		listeners.remove(jll);
	}

	/**
	 * Adds a playlist
	 * @return the index of the playlist you added
	 */
	public int addPlayList(){
		playLists.add(new ArrayList<String>());
		return playLists.size()-1;
	}

	/**
	 * Adds a song to a playlist
	 * @param theList the playlist to add the song to
	 * @param song the filename of the song (the prefix will be included)
	 * @return the index of the song you just added
	 */
	public int addSong(int theList, String song){
		if(theList>=playLists.size())
			throw new IllegalArgumentException("That playlist dosent exist!");
		playLists.get(theList).add(prefixBackgroundEffects+""+song);
		return playLists.get(theList).size()-1;
	}

	/**
	 * Adds a sound to the soundEffects playlist
	 * @param song the filename of the soundeffect
	 * @return the index of the song you just added
	 */
	public int addSoundEffect(String song){
		this.soundEffects.add(new MP3Player(prefixSoundEffects+""+song,this,false));
		return soundEffects.size()-1;
	}

	/**
	 * Plays a sound effect
	 * @param i the index of the sound effect to play
	 */
	public void playSoundEffect(int i){
		soundEffects.get(i).play();
	}
	
	/**
	 * @return The amount of sound effects stored
	 */
	public int getNumberOfSoundEffect(){
		return soundEffects.size();
	}
	
	/**
	 * 
	 * @return The number of playlists stored
	 */
	public int getNumberOfPlayLists(){
		return playLists.size();
	}
	
	/**
	 * 
	 * @return MP3Player from the Player
	 */
	public Player getPlayerFromMP3() {
		return sound.getPlayer();
	}
	
	/** Sets the volume gain with the inputteted float value.
	 * 
	 * @param f volume increase or decrease with positive or negative numbers
	 */
	public void setVolume(float f) {
		volume = f;
		
	}
	
	/**
	 * Method that returns the amount of songs in a playlist
	 * @param theList The playlist to look at
	 * @return The amount of songs in the respective playlist
	 */
	public int getNumberOfSongs(int theList){
		return playLists.get(theList).size();
	}

	/**
	 * Adds songs to a playlist from an array
	 * @param theList the playlist to add songs to
	 * @param songs the array of songs
	 */
	public void addSongs(int theList, String[] songs){
		for(String in:songs){
			addSong(theList,in);
		}
	}

	/**
	 * Adds sound effects to the sound effect list.
	 * @param effects the sound effects to add
	 */
	public void addSoundEffects(List<String> effects){
		for(String in:effects){
			addSoundEffect(in);
		}
	}
	
	
	/**
	 * Adds sound effects to the sound effect list.
	 * @param effects the sound effects to add
	 */
	public void addSoundEffects(String[] effects){
		for(String in:effects){
			addSoundEffect(in);
		}
	}

	/**
	 * Adds songs to a playlist from a list (you can use an arraylist here)
	 * @param theList the playlist to add to
	 * @param songs the songs to add
	 */
	public void addSongs(int theList, List<String> songs){
		for(String in:songs){
			addSong(theList,in);
		}
	}
	
	

	/**
	 * Sets the randomizer
	 * @param b if true, songs in a playlist will be played in random order. If false, songs will be played in the order they are added.
	 */
	public void setRandomizePlayOrder(boolean b){
		this.randomize=b;
	}

	/**
	 * Gets the randomizer (see constructor)
	 * @return if true, songs in a playlist will be played in random order. If false, songs will be played in the order they are added.
	 */
	public boolean getDoubleSoundCheck(){
		return this.randomize;
	}

	/**
	 * Changes the playlist to your liking
	 * @param i the playlist to change to
	 */
	public void changePlayList(int i){
		if(i>=playLists.size())
			throw new IllegalArgumentException("That playlist dosent exist!");
		currentPlayList=i;
	}

	private void changeSound(int playList){
		//cycles throught the playlist, randomly except if title is the same as the last title
		String old=currentSongName;
		currentPlayList=playList; //letting the rest of the class know
		int size = playLists.get(currentPlayList).size();
		if (!randomize) {
			currentSong=(currentSong+1);
			if (currentSong >= size) {
				for (JayLayerListener jll : listeners) {
					SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			            	jll.playlistEnded();
			            }
					});
				}
				currentSong = 0;
			}
			
		} else {
			if (size > 1) {
				int newSong=ran.nextInt(size-1);
				if (newSong == currentSong)
					newSong++;
				currentSong = newSong;
			}
		}
		currentSongName=playLists.get(currentPlayList).get(currentSong);
	}


	private void startSong(){
		changeSound(currentPlayList);
		sound=new MP3Player(currentSongName,this,true);
		sound.setVolume(volume);
		sound.play();
	}
	
	private void stopCurrentMusic(){
		if(sound!=null&&sound.player!=null)
			sound.player.close();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				for (JayLayerListener jll : listeners) {
					jll.musicStopped();
				}
            }
		});
	}

	public void stopSong(){
		stopCurrentMusic();
		currentSong = -1;
	}
	
	/**
	 * Determine whether music is currently playing.
	 * @return True if music is currently playing, false otherwise.
	 */
	public boolean isPlaying() {
		return !(sound != null && sound.player != null && sound.player.isComplete());
	}

	/**
	 * changes the sound (stays in the same playlist). Please call the setPlaylist method first
	 * this also starts the sound if there is nothing playing
	 */
	public void nextSong(){
		if(playLists.isEmpty())
			throw new IllegalArgumentException("You havent added any playlists yet!");
		if(currentPlayList==-1)
			throw new IllegalArgumentException("You havent set the playlist to play!");
		if(playLists.get(currentPlayList).isEmpty()){
			throw new IllegalArgumentException("This playlist dosent have any songs!");
		}
		changeSound(currentPlayList);
		stopCurrentMusic();
		startSong();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				for (JayLayerListener jll : listeners) {
					jll.musicStarted();
				}
            }
		});
	}

	/**
	 *
	 * @author Jay Kamat
	 * @version 2.1
	 * 
	 * Original Class was made by Shaines
	 */
	private class MP3Player {

		private Player player;
		private InputStream is;
		private JayLayer top;
		private boolean repeat;
		private PlayerThread pt;
		private float volume;

		public MP3Player(String filename,JayLayer t, boolean repeat) {
			top=t;
			this.repeat=repeat;
			//is = getClass().getResourceAsStream(filename);
			try {
				is = new FileInputStream( filename );
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//for a more "outer" root directory use this
			// is = new FileInputStream( filename );

		}
		
		public Player getPlayer() {
			return player;
		}
		
		public void setVolume(float volume) {
			this.volume = volume;
		}
		
		public void play() {
			try {
				player = new Player(is);
				player.setGain(volume);
				pt = new PlayerThread();
				pt.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		class PlayerThread extends Thread {

			public void run() {
				try {
					player.play();
					if(repeat&&player.isComplete()) {
						
						SwingUtilities.invokeLater(new Runnable() {
				            public void run() {
								for (JayLayerListener jll : listeners) {
									jll.songEnded();
								}
				            }
						});
						top.nextSong();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}


