package kchandra423.graphics;

import java.util.ArrayList;

import kchandra423.entities.*;
import kchandra423.entities.actors.enemies.Enemy;
import kchandra423.entities.actors.players.PlayerOUTDATED;
import kchandra423.entities.obstacles.Crate;
import kchandra423.entities.obstacles.Obstacle;
import kchandra423.weapons.projectiles.Projectile;
import processing.core.PApplet;

public class Room {
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Obstacle> bounds;
	private  ArrayList<Enemy> enemies;
	private  PlayerOUTDATED player;
	private ArrayList<Projectile> playerProjectiles;
	private ArrayList<Projectile> enemyProjectiles;
	private boolean roomCleared;
	public Room (int roomID, PlayerOUTDATED p) {
		
	}
	public Room(PlayerOUTDATED p) {
		player=p;
		bounds= new ArrayList<Obstacle>();
		roomCleared=false;
		obstacles = new ArrayList<Obstacle>();
		enemies = new ArrayList<Enemy>();
		playerProjectiles = new ArrayList<Projectile>();
		enemyProjectiles = new ArrayList<Projectile>();
		Obstacle leftWall=new Crate(-10,-10,10,TwodGameThing.BOUNDSY+20 );
		Obstacle topWall=new Crate(0,-10,TwodGameThing.BOUNDSX,10 );

		Obstacle rightWall=new Crate(TwodGameThing.BOUNDSX,-10,10,TwodGameThing.BOUNDSY+20 );
		Obstacle bottomWall=new Crate(0,TwodGameThing.BOUNDSY-30,TwodGameThing.BOUNDSX,10 );
		bounds.add(leftWall);
		bounds.add(rightWall);
		bounds.add(topWall);
		
		bounds.add(bottomWall);
		
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
		playerProjectiles=player.getProjectiles();
		for(int i=0; i< obstacles.size();i++) {
			obstacles.get(i).draw(p);
		}
		for(int i=0; i< enemies.size();i++) {
			enemies.get(i).draw(p);
		}
		for(int i=0; i< playerProjectiles.size();i++) {
			if(playerProjectiles.get(i).isActive()) {
					playerProjectiles.get(i).draw(p);
			}
		}
		for(int i=0; i< enemyProjectiles.size();i++) {
			enemyProjectiles.get(i).draw(p);
		}
		player.moveX();
		//i feel like this might be error prone but idk
		//check here later if i get a collision bug
		for(int i=0; i< obstacles.size();i++) {
			if(player.getBody().overLaps(obstacles.get(i).getRect())) {
				player.moveBackX(obstacles.get(i));
				break;
			}
			
		}
		for(int i=0; i< bounds.size();i++) {
			if(player.getBody().overLaps(bounds.get(i).getRect())) {
				player.moveBackX(bounds.get(i));
				break;
			}
			
		}
		//OPTIMIZE THIS LATER FOR THE LOVE OF GOD
		for(int i=0;i<playerProjectiles.size();i++) {
			for(int j=0;j<obstacles.size();j++) {
				if(obstacles.get(j).intersects(playerProjectiles.get(i).getX(), 
						playerProjectiles.get(i).getY())){
					playerProjectiles.get(i).setInactive();
						}
			}
		}
		player.moveY();
		for(int i=0; i< obstacles.size();i++) {
			if(player.getBody().overLaps(obstacles.get(i).getRect())) {
				player.moveBackY(obstacles.get(i));
				break;
			}
				
		}
		for(int i=0; i< bounds.size();i++) {
			if(player.getBody().overLaps(bounds.get(i).getRect())) {
				player.moveBackY(bounds.get(i));
				break;
			}
			
		}
		player.draw(p);
	}
	public boolean isRoomCleared() {
		return roomCleared;
	}
}
