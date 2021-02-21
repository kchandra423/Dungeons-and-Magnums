package kchandra423.graphics;

import java.util.ArrayList;

import kchandra423.entities.actors.enemies.Enemy;
import kchandra423.entities.actors.players.Player;
import kchandra423.entities.obstacles.Boundary;
import kchandra423.entities.obstacles.Collideable;
import kchandra423.entities.obstacles.Obstacle;
import kchandra423.graphics.shapes.Rectangle;
import kchandra423.graphics.textures.Texture;

import kchandra423.weapons.projectiles.Projectile;
import processing.core.PApplet;
import processing.core.PImage;

import javax.imageio.ImageIO;

public class Room {
    private boolean firstDraw = true;
    private PImage backGround;
    private Obstacle[] obstacles;
    private ArrayList<Enemy> enemies;
    private Boundary bounds;
    private Player player;
    private ArrayList<Projectile> playerProjectiles;
    private ArrayList<Projectile> enemyProjectiles;

    //	private boolean roomCleared;
    public Room(int roomID, Player p) {

    }

    public Room(Player p) {
        player = p;
        bounds = new Boundary(-50, -50, 1500, 1000);
        Texture t = Texture.TextureBuilder.getTexture("res/Images/Obstacles/Box.png");
        Obstacle o = new Obstacle(t, new Rectangle(-50 + 500, 500, t.getWidth(), t.getHeight()));
        Obstacle o2 = new Obstacle(t, new Rectangle(-50 + 1000, 500, t.getWidth(), t.getHeight()));

        obstacles = new Obstacle[]{o, o2};
//		roomCleared=false;
        enemies = new ArrayList<Enemy>();
        playerProjectiles = new ArrayList<Projectile>();
        enemyProjectiles = new ArrayList<Projectile>();


    }

    //	public void addObstacle(Obstacle o) {
//		obstacles.add(o);
//	}
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void addEnemyProjectile(Projectile p) {
        enemyProjectiles.add(p);

    }

    public void addPlayerProjectile(Projectile p) {
        playerProjectiles.add(p);
    }

    public void draw(DrawingSurface d) {
        if (firstDraw) {
            backGround = d.loadImage("res/Images/Backgrounds/tiles(manually resized).jpg");
            backGround.loadPixels();
            firstDraw = false;
        }
        int halfx = d.width / 2, halfy = d.height / 2;
        float difx = (player.getSprite().getX() - halfx), dify = (player.getSprite().getY() - halfy);
        playerProjectiles = player.getCurrentWeapon().getProjectiles();
        int fx= (int) (bounds.getX() - difx), fy= (int) (bounds.getY()-dify);
//        System.out.println(fx);
//        System.out.println(fy);

        d.image(backGround, fx , fy);

//        d.image(backGround,10,10);
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i].draw(d, -difx, -dify);
            for (Projectile p : player.getCurrentWeapon().getProjectiles()) {
                try {
                    if (p.intersects(obstacles[i]) || p.getSprite().getShape().intersects(bounds)) {
                        p.setActive(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(d, -difx, -dify);
        }

        bounds.draw((PApplet) d, -difx, -dify);
//		for(int i=0; i< playerProjectiles.size();i++) {
//			if(playerProjectiles.get(i).isActive()) {
//					playerProjectiles.get(i).draw(d,-difx,-dify);
//			}
//		}
//		for(int i=0; i< enemyProjectiles.size();i++) {
//			enemyProjectiles.get(i).draw(d,-difx,dify);
//		}
        player.act(d, this);
        player.draw(d, -difx, -dify);
        //i feel like this might be error prone but idk
        //check here later if i get a collision bug
//		for(int i=0; i< obstacles.size();i++) {
//			if(player.getBody().overLaps(obstacles.get(i).getRect())) {
//				player.moveBackX(obstacles.get(i));
//				break;
//			}
//
//		}
//		for(int i=0; i< bounds.size();i++) {
//			if(player.getBody().overLaps(bounds.get(i).getRect())) {
//				player.moveBackX(bounds.get(i));
//				break;
//			}
//
//		}
//        for (Obstacle o : obstacles) {
//            o.draw(this,-difx,-dify);
//
//        }
//		//OPTIMIZE THIS LATER FOR THE LOVE OF GOD
//		for(int i=0;i<playerProjectiles.size();i++) {
//			for(int j=0;j<obstacles.size();j++) {
//				if(obstacles.get(j).intersects(playerProjectiles.get(i).getX(),
//						playerProjectiles.get(i).getY())){
//					playerProjectiles.get(i).setInactive();
//						}
//			}
//		}
//		player.moveY();
//		for(int i=0; i< obstacles.size();i++) {
//			if(player.getBody().overLaps(obstacles.get(i).getRect())) {
//				player.moveBackY(obstacles.get(i));
//				break;
//			}
//
//		}
//		for(int i=0; i< bounds.size();i++) {
//			if(player.getBody().overLaps(bounds.get(i).getRect())) {
//				player.moveBackY(bounds.get(i));
//				break;
//			}

//		};
    }


    public Collideable[] getCollideables() {
        Collideable[] answer = new Collideable[obstacles.length + 1];
        for (int i = 0; i < obstacles.length; i++) {
            answer[i] = obstacles[i];
        }
        answer[answer.length - 1] = bounds;
        return answer;
    }


    public boolean isRoomCleared() {
        return false;
//	    for(Enemy e: enemies){
        //if alive return true;
//        }
    }
}
