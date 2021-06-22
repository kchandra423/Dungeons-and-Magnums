package kchandra423.utility;

import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

import java.awt.*;
import java.awt.geom.Area;

/**
 * Loads all assets needed initially and held statically.
 * This is to prevent the need to do this throughout runtime, which is extremely time consuming.
 * Returns clones of these so as to not have to recalculate, but also be safe to use as separate objects.
 *
 * @author Kumar Chandra
 */
public class AssetLoader {
//    static {
//        long start = System.currentTimeMillis();
//        sprites = new KImage[]{
//                new KImage("res/Images/Enemies/Attack/Bat.gif"),
//                new KImage("res/Images/Enemies/Attack/Goblin.gif"),
//                new KImage("res/Images/Enemies/Attack/Witch.gif"),
//                new KImage("res/Images/Enemies/Death/Bat.gif"),
//                new KImage("res/Images/Enemies/Death/Goblin.gif"),
//                new KImage("res/Images/Enemies/Death/Witch.gif"),
//                new KImage("res/Images/Enemies/Idle/Bat.gif"),
//                new KImage("res/Images/Enemies/Idle/Goblin.gif"),
//                new KImage("res/Images/Enemies/Idle/Witch.gif"),
//                new KImage("res/Images/Enemies/Moving/Bat.gif"),
//                new KImage("res/Images/Enemies/Moving/Goblin.gif"),
//                new KImage("res/Images/Enemies/Moving/Witch.gif"),
//                new KImage(0, 0, Texture.TextureBuilder.getTexture("res/Images/Obstacles/Box.png"), new Area(new Rectangle(200, 200))),
//                new KImage("res/Images/Players/KnightIdle.gif"),
//                new KImage("res/Images/Players/MageActive.gif"),
//                new KImage("res/Images/Players/MageIdle.gif"),
//                new KImage("res/Images/Players/RogueIdle.gif"),
//                new KImage("res/Images/Projectiles/Bullet.png"),
//                new KImage("res/Images/Projectiles/Hex.gif"),
//                new KImage("res/Images/Weapons/MagicStaff.png", true),
//                new KImage("res/Images/Weapons/Pistol.png", true),
//                new KImage("res/Images/Weapons/RustySword.png", true),
//                new KImage("res/Images/Weapons/Shotgun.png", true),
//                new KImage("res/Images/Weapons/SMG.png", true),
//                new KImage("res/Images/Weapons/SpellBook.png", true),
//                new KImage("res/Images/Weapons/Sword.png", true),
//                new KImage("res/Images/Obstacles/Teleporter.gif"),
//                new KImage("res/Images/Projectiles/MagicBall.gif"),
//                new KImage("res/Images/Enemies/Attack/Minotaur.gif"),
//                new KImage("res/Images/Enemies/Death/Minotaur.gif"),
//                new KImage("res/Images/Enemies/Idle/Minotaur.gif"),
//                new KImage("res/Images/Enemies/Moving/Minotaur.gif"),
//                new KImage("res/Images/Enemies/Attack/Cyclops.gif"),
//                new KImage("res/Images/Enemies/Death/Cyclops.gif"),
//                new KImage("res/Images/Enemies/Idle/Cyclops.gif"),
//                new KImage("res/Images/Enemies/Moving/Cyclops.gif"),
//                new KImage("res/Images/Projectiles/Rock.gif")
//
//        };
//        long end = System.currentTimeMillis();
//        System.out.println("Initializing assets took " + ((end - start) / 1000.0) + " seconds");
//    }

    private static KImage[] sprites;

    public static float loadKImages() {
        long start = System.currentTimeMillis();
        sprites = new KImage[]{
                new KImage("res/Images/Enemies/Attack/Bat.gif"),
                new KImage("res/Images/Enemies/Attack/Goblin.gif"),
                new KImage("res/Images/Enemies/Attack/Witch.gif"),
                new KImage("res/Images/Enemies/Death/Bat.gif"),
                new KImage("res/Images/Enemies/Death/Goblin.gif"),
                new KImage("res/Images/Enemies/Death/Witch.gif"),
                new KImage("res/Images/Enemies/Idle/Bat.gif"),
                new KImage("res/Images/Enemies/Idle/Goblin.gif"),
                new KImage("res/Images/Enemies/Idle/Witch.gif"),
                new KImage("res/Images/Enemies/Moving/Bat.gif"),
                new KImage("res/Images/Enemies/Moving/Goblin.gif"),
                new KImage("res/Images/Enemies/Moving/Witch.gif"),
                new KImage(0, 0, Texture.TextureBuilder.getTexture("res/Images/Obstacles/Box.png"), new Area(new Rectangle(200, 200))),
                new KImage("res/Images/Players/KnightIdle.gif"),
                new KImage("res/Images/Players/MageActive.gif"),
                new KImage("res/Images/Players/MageIdle.gif"),
                new KImage("res/Images/Players/RogueIdle.gif"),
                new KImage("res/Images/Projectiles/Bullet.png"),
                new KImage("res/Images/Projectiles/Hex.gif"),
                new KImage("res/Images/Weapons/MagicStaff.png", true),
                new KImage("res/Images/Weapons/Pistol.png", true),
                new KImage("res/Images/Weapons/RustySword.png", true),
                new KImage("res/Images/Weapons/Shotgun.png", true),
                new KImage("res/Images/Weapons/SMG.png", true),
                new KImage("res/Images/Weapons/SpellBook.png", true),
                new KImage("res/Images/Weapons/Sword.png", true),
                new KImage("res/Images/Obstacles/Teleporter.gif"),
                new KImage("res/Images/Projectiles/MagicBall.png"),
                new KImage("res/Images/Enemies/Attack/Minotaur.gif"),
                new KImage("res/Images/Enemies/Death/Minotaur.gif"),
                new KImage("res/Images/Enemies/Idle/Minotaur.gif"),
                new KImage("res/Images/Enemies/Moving/Minotaur.gif"),
                new KImage("res/Images/Enemies/Attack/Cyclops.gif"),
                new KImage("res/Images/Enemies/Death/Cyclops.gif"),
                new KImage("res/Images/Enemies/Idle/Cyclops.gif"),
                new KImage("res/Images/Enemies/Moving/Cyclops.gif"),
                new KImage("res/Images/Projectiles/Rock.gif"),
                new KImage("res/Images/Projectiles/Fireball.gif"),
                new KImage("res/Images/Weapons/MagicGloves.png", true),
                new KImage("res/Images/Projectiles/Lightning.gif"),
                new KImage("res/Images/Weapons/Axe.png", true)
        };
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000f);
        return (end - start) / 1000f;
    }

    /**
     * Represents which sprite you want to get from this class.
     *
     * @author Kumar Chandra
     */
    public enum Sprite {
        BAT_ATTACK(0), GOBLIN_ATTACK(1), WITCH_ATTACK(2), BAT_DEATH(3), GOBLIN_DEATH(4),
        WITCH_DEATH(5), BAT_IDLE(6), GOBLIN_IDLE(7), WITCH_IDLE(8), BAT_MOVING(9),
        GOBLIN_MOVING(10), WITCH_MOVING(11), BOX(12), KNIGHT_IDLE(13), MAGE_ACTIVE(14),
        MAGE_IDLE(15), ROGUE_IDLE(16), BULLET(17), HEX(18), MAGIC_STAFF(19), PISTOL(20),
        RUSTY_SWORD(21), SHOTGUN(22), SMG(23), SPELLBOOK(24), SWORD(25), TELEPORTER(26),
        MAGIC_BALL(27), MINOTAUR_ATTACK(28), MINOTAUR_DEATH(29), MINOTAUR_IDLE(30), MINOTAUR_MOVING(31),
        CYCLOPS_ATTACK(32), CYCLOPS_DEATH(33), CYCLOPS_IDLE(34), CYCLOPS_MOVING(35), ROCK(36),
        FIREBALL(37), MAGIC_GLOVES(38), LIGHTNING(39), AXE(40);

        private final int val;

        Sprite(int value) {
            val = value;
        }

        private int getVal() {
            return val;
        }
    }


    /**
     * Gets a clone of the KImage at the given index. All indexes are specified as a constant. For example, to get a Bat attack sprite,
     * you would call getImage(BAT_ATTACK);
     *
     * @param sprite The sprite you want to retrieve
     * @return A clone of the original image.
     */
    public static KImage getImage(Sprite sprite) {
        return (KImage) AssetLoader.sprites[sprite.getVal()].clone();
    }


}
