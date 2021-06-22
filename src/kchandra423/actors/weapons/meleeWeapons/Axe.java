package kchandra423.actors.weapons.meleeWeapons;

import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.enemies.RangedEnemy;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.graphics.screens.DrawingSurface;
import kchandra423.levels.Room;
import kchandra423.utility.AssetLoader;

public class Axe extends MeleeWeapon{
    public Axe() {
        super(AssetLoader.getImage(AssetLoader.Sprite.AXE), 45,
                (float) (Math.PI*2),0.75f);
    }
    @Override
    public void act(DrawingSurface d, Room room) {
        //if you cannot fire, it means that you are firing
        float differential = (float) (((System.currentTimeMillis() - swingStartTime) /  (swingTime * 1000))*Math.PI/2);

        if (!canFire()) {
            if(image.isReflected()){
                image.rotate(-(float) (range * Math.sin(differential)));
            }else {
                image.rotate((float) (range * Math.sin(differential)));
            }
            for (Enemy e :
                    room.getEnemies()) {
                if (intersects(e) && !hits.contains(e)) {
                    hits.add(e);
                    e.interceptHitBox(new Damage(damage, stats, DamageTypes.MELEE));
                }
                if(e instanceof RangedEnemy) {
                    for (Projectile p :
                            ((RangedEnemy) e).getProjectiles()) {
                        if(intersects(p)){
                            p.setActive(false);
                        }
                    }
                }
            }
        }
    }
}
