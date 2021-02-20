package kchandra423.entities.actors.players;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


import kchandra423.entities.actors.Actor;
import kchandra423.entities.obstacles.Obstacle;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.Room;
import kchandra423.graphics.Sprites.Sprite;
import kchandra423.utility.Calculator;
import kchandra423.weapons.Weapon;
import processing.core.PApplet;

public abstract class Player extends Actor {

    private final PlayerMetadata playerInfo;
    private final ArrayList<Weapon> weapons;
    private final AbilityMetadata ability1;
    private final AbilityMetadata ability2;
    private final AbilityMetadata ability3;
    private final AbilityMetadata Super;
    private int lastState;
    //the arrays format goes [idle, walking, maybe something else??]
    private final Sprite[] sprites;

    public Player(Sprite[] sprites,  float accel, PlayerMetadata playerData, AbilityMetadata[] abilityData) {
        super(sprites[0], playerData.getBaseV(), accel);
        this.sprites = sprites;
        this.playerInfo = playerData;
        this.ability1 = abilityData[0];
        this.ability2 = abilityData[1];
        this.ability3 = abilityData[2];
        this.Super = abilityData[3];
        weapons = new ArrayList<>();
        lastState = 0;
    }

    //	protected Player(Player p) {
//		super(p.getSprite(), p.getMaxVx(), p.getMaxVy(),p.getAccel());
//	}
//	protected Player() {
//		super(sprite, maxVx, maxVy, accel);
//		// TODO Auto-generated constructor stub
//	}
//	protected void loadPlayer(subclasses playerType) {
//		FileManager file = new FileManager("res/GameConstants/PlayerConstants","3H_~arV-St83LRyN",true);
//		String[] raw=null;
//		String[] split = null;
//		try {
//			raw= file.readAll().split("\n");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		switch(playerType) {
//			case ROGUE:{
//				split=raw[0].split(",");
//			}
//		case KNIGHT:{
//				split=raw[1].split(",");
//			}
//		case MAGE:{
//				split=raw[2].split(",");
//			}
//		}
//		
//		float[] playerMap= new float[7];
//		for(int i=0;i< playerMap.length;i++) {
//			playerMap[i]=Float.parseFloat(split[i+1]);
//		}
//		PlayerMetadata playerData= new PlayerMetadata(playerMap,
//				Integer.parseInt(split[8]), Integer.parseInt(split[9]));
//		AbilityMetadata[] abilities= new AbilityMetadata[] {
//				new AbilityMetadata(Float.parseFloat(split[11])), 
//				new AbilityMetadata(Float.parseFloat(split[12])),
//				new AbilityMetadata(Float.parseFloat(split[13])),
//				new AbilityMetadata(Float.parseFloat(split[14]))};
//		
//	}
    @Override
    public void bounceBackX() {
        getSprite().shift(-(getVx()), 0);
        weapons.get(0).getSprite().shift(-getVx(), 0);
        setVx(getVx() * -0.5f);
    }

    @Override
    public void bounceBackY() {
        getSprite().shift(0, -(getVy()));
        weapons.get(0).getSprite().shift(0, -getVy());
        setVy(getVy() * -0.5f);
    }

    @Override
    public void draw(PApplet p, float offSetX, float offSetY) {
        super.draw(p,offSetX,offSetY);


        weapons.get(0).draw(p, offSetX, offSetY);
    }

    protected void updateState() {
        int newState;
        if (Math.abs(getVx()) < 0.1 && Math.abs(getVy()) < 0.1) {
            newState = 0;
        } else {
            newState = 1;
        }
        if (newState != lastState) {
            Sprite newSprite = sprites[newState];
            lastState = newState;
            float difX = getSprite().getX() - newSprite.getX();

            float difY = getSprite().getY() - newSprite.getY();
            newSprite.shift(difX, difY);
            setSprite(newSprite);

        }
    }

    @Override
    public void act(DrawingSurface d, Room r) {
        boolean up = DrawingSurface.getKey(KeyEvent.VK_W);
        boolean left = DrawingSurface.getKey(KeyEvent.VK_A);
        boolean down = DrawingSurface.getKey(KeyEvent.VK_S);
        boolean right = DrawingSurface.getKey(KeyEvent.VK_D);
        usePassive();
        super.moveX(new boolean[]{left, right});
        weapons.get(0).shift(getVx(), 0);
        Obstacle[] obstacles = r.getObstacles();
        for (Obstacle o : obstacles) {
            if (o.intersects(this)) {
                bounceBackX();
                break;
            }
        }
        super.moveY(new boolean[]{up, down});
        weapons.get(0).shift(0, getVy());
        for (Obstacle o : obstacles) {
            if (o.intersects(this)) {
                bounceBackY();
                break;
            }
        }
        updateState();
        int halfx=d.width/2,halfy=d.height/2;
//        float difx=(getSprite().getX()-halfx),dify=(getSprite().getY()-halfy);
        float angle = (float) Calculator.calculateAngle(halfx, halfy,
                d.mouseX, d.mouseY);
        playerInfo.setAngle(angle);

        if (angle > Math.PI / 2 && angle < 3 * Math.PI / 2) {

            getSprite().setReflected(true);
            weapons.get(0).getSprite().setReflected(true);
        } else {
            getSprite().setReflected(false);
            weapons.get(0).getSprite().setReflected(false);
        }
        if (d.mousePressed) {
            if (d.mouseButton == PApplet.LEFT) {
                weapons.get(0).pressTrigger();
            }else if(d.mouseButton == PApplet.RIGHT){
                weapons.get(0).releaseTrigger();
                useAbility1();
            }

        } else {
            weapons.get(0).releaseTrigger();
        }
        weapons.get(0).setAngle(angle);
        weapons.get(0).act();

    }

    public PlayerMetadata getPlayerInfo() {
        return playerInfo;
    }

    public AbilityMetadata getAbility1() {
        return ability1;
    }

    public AbilityMetadata getAbility2() {
        return ability2;
    }

    public AbilityMetadata getAbility3() {
        return ability3;
    }

    public AbilityMetadata getSuper() {
        return Super;
    }

    public void addWeapon(Weapon w) {
        weapons.add(w);
    }
    public Weapon getCurrentWeapon(){
        return weapons.get(0);
    }

    public abstract void usePassive();

    public abstract void useAbility1();

    public abstract void useAbility2();

    public abstract void useAbility3();

    public abstract void useSuper();
}
