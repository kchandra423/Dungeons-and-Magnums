Dungeons and Magnums
Authors: Kumar Chandra, Spencer Ye, Ryan Lee
Revision: 5/24/2021

Introduction: 
Our program creates a graphical-based game where the user controls a character around a room and uses weapons to defeat computer-controlled enemies to progress through levels in order to complete the game.
The problem that we solve is the continued problem of boredom. A lot of people are still stuck at home, with little entertainment, or they have gotten bored of the entertainment they previously had, and they can use this program to get entertainment. 
The story of our game is that, when we were sitting in a call trying to decide what our AP Computer Science capstone project, one of our team members was playing Soul Knight, so we decided to create our ideal version of the game, which is how our game came to be.
The rules of our game is that you must stay in the confines of the room, there are game mechanics that prevent you from leaving. Similarly, most other rules of the game are enforced through code, such as: players cannot move onto obstacles. The goal of our game is to complete all the levels to finish the program.
While this game is targeted more at a younger audience, anyone who wants a new form of entertainment, especially in a game form, will want to use our program.
The primary features of our game includes a menu that allows users to change program settings such as volume, and a game page, where the actual game will be started, played, and ended.

Instructions:
Throughout the actual game, the player will use the typical movement keys, “W”, “A”, “S’, and “D” to respectively move up, right, down, and left. 
During the game, the player may click anywhere on the screen to fire the weapon in their hand. Players will use their mouse cursor to aim their weapon, simply by moving it to the direction that they want to aim at. 
There will be a single start menu that will contain 3 different classes, a “mage” class, “knight” class, and a “rogue” class. The player may choose a class to play as, with each class having a different weapon to play with. There will also be a volume slider on the menu, to control the volume of the music and weapon sound effects, as well as an fps slider to set the max fps in game once you choose a class.
Once in the game, players can use any controls in any order they wish to control their character.

Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
GUI to start the game, and change settings. Users should be able to choose their character and other aspects of the game, such as the music or volume.(DONE)
Menu has a basic instruction manual, which just explains how to play the game. Should explain how to fight against enemies, how to progress through levels, Until they eventually finish or die.(DONE)
Playable game - Users should be able to move, fire weapons, and collide with objects. Players should also be able to move through and navigate each level, and progress onto the next one(DONE)
Multiple levels - Multiple unique levels, each with different obstacle and enemy layouts(DONE)
2 dimensional graphics - The entire game should be shown through images and all graphics should have color. (DONE)


Want-to-have Features:
Consistently hold 60 fps - Throughout the entire runtime, the program should be able to hold at least 60 fps, to ensure that movement is smooth and consistent. Any optimizations to the code to increase performance should be sought after.(DONE)
Enemy AI - Enemy AI will make enemies behave in a somewhat intelligent manner (move towards player, circle player, fire towards player, etc), which will make the game able to be actually fun. (DONE)
Store player information on a database - Players stats, such as level, username, password, xp, etc. Players will be able to log on from any device and log into their account using their username and password, and all relevant stats will be saved.
Players can save game progress - Games that are in progress will also be stored on the database or in a local file. Players will be able to restart a level from a certain point with these saves. 
Multiple characters - Players should be able to choose from multiple characters, all with different stats and appearances(DONE)
Full screen support - Users should be able to toggle in and out of a full screen mode with no changes to gameplay, or significant differences in performance. (DONE)
Music + sounds effects - Some sort of music should play, and sound effects should be played on certain actions. For example, some background music would play the entire game, but when you take damage or are below a certain health, a sound effect would play on top of the music.(DONE)


Stretch Features:
Fancy neural net for enemy ai - This feature is not for gameplay purposes, but rather more for a learning experience. The AI should be able to play against the person, and should be able to do basic activities to attempt to defeat the player, even if it is weird/ too bad/ too good.
Multiple difficulty settings - There should be multiple difficulty levels, (easy, medium, hard) which provide different levels of challenge to the player. These changes could be shifts in the ai’s behavior, and / or changing the stats and multipliers for enemies. 
Online multiplayer - Users would be able to play online with up to 3 different people. They would play co - op against the computer, and player and enemy stats would be modified to ensure there is still a challenge. 
Character abilities - Characters would have unique abilities, such as shooting a projectile, or add a buff multiplier to themselves or their teammates. 
Local multiplayer for up to three players at a time on the same network. Similar to online multiplayer, except it would be faster and everyone would just need to be on the same network. 


Class List:

Actor
	An actor is an entity that can move and has physics that bounce it back when it collides with another object, extends Entity class
MovingActor
	An actor with built in acceleration physics
Enemy
	Extends Actor, the enemy that attempts to damage and kill the user
RangedEnemy
	Represents a enemy that can attack from range
Bat
	Represents an enemy with a bat sprite
Witch
	Represents an enemy with a witch sprite
Goblin
	Represents an enemy with a goblin sprite
Damage
	Represents the damage that can be given to an actor
EnemyAI
	The program that controls the enemies to attack the player
Player
	Extends Actor, the player is the user and can interact with the game environment with abilities, weapons, and different classes
Knight
	Extends Player, knight is more tanky than the other classes 
Mage
	Extends Player, mage is less tanky than the other classes, but can deal more damage
Rogue
	Extends Player, rouge is more tanky than mage, but less tanky that the knight, Rouge has better movement than the other classes
Obstacle
	Extends Entity and implements Collideable, blocks the Player and other Actors from movement and bounces them back when they collide
Room
	The room contains the Player, Obstacles, enemies, and projectiles, it is where the game is played
DrawingSurface
	Extends PApplet, this is used to draw all the entities and take care of graphical processing
KImage
	Has a texture and displays on screen as a sprite, and can be moved around and collided with other KImages.
PGif
	Allows for use of .gif files in processing
Texture
	Represents an image that can be moved around and resized. Supports Gif, jpeg, and png images.
TextureImage
	Takes in a PImage to be used as a texture
Weapon
	The object a player can use in order to attack the enemies in the room, it can come in different forms such as a gun or sword
RangedWeapon
	Has a sprite and a projectile texture, used by Player and Enemy as a weapon
Gun
	Represents a gun with firing and a bullet spread
MagicStaff
	Represents a magic staff that fires magic projectiles
Shotgun
	Represents a shotgun that shoots multiple projectiles
Smg
	Represents a gun that shoots projectiles
MeleeWeapon
	Used by Player and Enemy as a weapon, has a set swing duration and length
BroadSword
	Represents a sword that can be swung
Projectile
	Projectile used by any form of a ranged RangedWeapon, has its own sprite, and determines if it comes into contact with an enemy or player, and whether they are killed or not
Calculator
	Represents a basic calculator to do basic calculations with things like damage, and it also calculates the angle between points
AssetLoader
	Loads all assets needed initially and held statically so that it does not need to be loaded during runtime.
JayLayer
	Modded JayLayer class from the Shelby code demos that allows for the control of volume, allows use of mp3 files through Player
JavaSoundAudioDevice
	Modded JavaSoundAudioDevice that uses java sound api, allows you to change volume, from Shelby coding demos
Player
	Modded Player that uses JavaSoundAudioDevice for playback of sound files, from Shelby coding demos
Level
	Basic construction for a level, containing an array of rooms that you progress through to complete the level
HUD
	Displays the player’s health during gameplay
MainMenu
	Draws the main menu, which lets you choose your character, set volume level, and set the max framerate during the actual game.
DungeonsAndMagnums
	Runs the main menu, which allows you to play the game.

Credits:
	Kumar
		Create all classes related to graphics
		Create all classes related to the physics engine and enemy interactions
		Created some of the art assets for the project (Players and weapons)
	Spencer
		Created the enemies
		Edited the video
		Created levels
		Create the system for transferring levels
		
	Ryan
		Create the GUI
		Create the music and sounds effect system
		Created and did the UML diagram
	Processing
		We used the Java processing library to help us display all of our graphics quickly
	JaysEasierSound
		Taken from Shelby’s coding demos in order to use sounds.
	Processing Widget Libraries
		Taken from Shelby’s coding demos.
	Elthen 
		Patreon at https://www.patreon.com/elthen
		Used his Sprites for the bat and witch enemies
	Thekingphoenix
		Used their sprite for the goblin enemy
	AlbertoV
		Menu background
	G4P controls by Peter Lager
		Used for menu buttons and sliders
	The Pokemon Company
		Used for main menu theme and theme when playing the game
		https://www.youtube.com/watch?v=1u0z2z_O95Q
	Sword sound effect
		https://www.youtube.com/watch?v=AzLPBuVqa58
	Terraria inferno fork use sound effect
		https://www.youtube.com/watch?v=nnVhe8GjKqs
		Gun sound effect
	Parakoopa
		Used for alternate menu background
		
