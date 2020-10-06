package kchandra423.graphics;

import java.util.ArrayList;

import kchandra423.enemies.Enemy;
import kchandra423.obstacles.Obstacle;
import kchandra423.players.Player;
import kchandra423.projectiles.Projectile;
import processing.core.PApplet;

public class Room {
	private ArrayList<Obstacle> obstacles;
	private  ArrayList<Enemy> enemies;
	private  Player player;
	private ArrayList<Projectile> playerProjectiles;
	private ArrayList<Projectile> enemyProjectiles;
	private boolean roomCleared;
	public Room (int roomID, Player p) {
		
	}
	public Room(Player p) {
		player=p;
		roomCleared=false;
		obstacles = new ArrayList<Obstacle>();
		enemies = new ArrayList<Enemy>();
		playerProjectiles = new ArrayList<Projectile>();
		enemyProjectiles = new ArrayList<Projectile>();
		
	}
	public void addObstacle(Obstacle o) {
		obstacles.add(o);
	}
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}
	public void addEnemyProjectile(Projectile p) {
		enemyProjectiles.add(p);
		
	}
	public void addPlayerProjectile(Projectile p) {
		playerProjectiles.add(p);
	}
	public void draw(PApplet p) {
		for(int i=0; i< obstacles.size();i++) {
			obstacles.get(i).draw(p);
		}
		for(int i=0; i< enemies.size();i++) {
			enemies.get(i).draw(p);
		}
		for(int i=0; i< playerProjectiles.size();i++) {
			playerProjectiles.get(i).draw(p);
		}
		for(int i=0; i< enemyProjectiles.size();i++) {
			enemyProjectiles.get(i).draw(p);
		}
		
		player.draw(p);
	}
	public boolean isRoomCleared() {
		return roomCleared;
	}
}
