package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import g4p_controls.GButton;
import g4p_controls.GDropList;
import g4p_controls.GEvent;
import kchandra423.actors.weapons.guns.MagicGloves;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import kchandra423.utility.PlayerData;
import processing.core.PConstants;

public class LoadOutScreen implements Screen {
    private GDropList mageWeapons;
    private GDropList knightWeapons;
    private GDropList rogueWeapons;
    private GButton save;
    Texture mage;
    Texture knight;
    Texture rogue;
    Texture pistol;
    Texture smg;
    Texture shotgun;
    Texture rustySword;
    Texture broadSword;
    Texture magicStaff;
    Texture spellBook;
    Texture magicGloves;
    Texture axe;

    @Override
    public void draw(DrawingSurface d) {
        d.background(100);
        d.pushStyle();

        d.fill(255);
        d.textSize(60);
        d.textAlign(PConstants.CENTER, PConstants.TOP);
        d.text("Player Loadouts", d.width / 2f, 0);
        mage.draw(d, d.width / 4, d.height / 2 - 200);

        knight.draw(d, d.width / 2, d.height / 2 - 200);
        rogue.draw(d, d.width / 4 * 3, d.height / 2 - 200);
        getWeapon(PlayerData.getInitialMageWeapon()).draw(d, d.width / 4, d.height / 2+100);

        getWeapon(PlayerData.getInitialKnightWeapon()).draw(d, d.width / 2, d.height / 2+100);

        getWeapon(PlayerData.getInitialRogueWeapon()).draw(d, d.width / 4*3, d.height / 2+100);

        d.popStyle();
    }

    @Override
    public void setup(DrawingSurface d) {
        mage = Texture.TextureBuilder.getTexture("res/Images/Players/MageIdle.gif");
        knight = Texture.TextureBuilder.getTexture("res/Images/Players/KnightIdle.gif");
        rogue = Texture.TextureBuilder.getTexture("res/Images/Players/RogueIdle.gif");
        pistol = Texture.TextureBuilder.getTexture("res/Images/Weapons/Pistol.png");
        smg = Texture.TextureBuilder.getTexture("res/Images/Weapons/SMG.png");
        shotgun = Texture.TextureBuilder.getTexture("res/Images/Weapons/Shotgun.png");
        magicGloves = Texture.TextureBuilder.getTexture("res/Images/Weapons/MagicGloves.png");
        rustySword = Texture.TextureBuilder.getTexture("res/Images/Weapons/RustySword.png");
        broadSword = Texture.TextureBuilder.getTexture("res/Images/Weapons/Sword.png");
        magicStaff = Texture.TextureBuilder.getTexture("res/Images/Weapons/MagicStaff.png");
        spellBook = Texture.TextureBuilder.getTexture("res/Images/Weapons/SpellBook.png");
        axe = Texture.TextureBuilder.getTexture("res/Images/Weapons/Axe.png");
        mageWeapons = new GDropList(d, d.width / 4, d.height - d.height / 4, mage.getWidth(), 100, 3);
        mageWeapons.setItems(new String[]{"MagicStaff", "SpellBook", "MagicGloves"}, 0);
        mageWeapons.addEventHandler(this, "changeWeapon");
        mageWeapons.setVisible(false);
        knightWeapons = new GDropList(d, d.width / 2, d.height - d.height / 4, knight.getWidth(), 100, 3);
        knightWeapons.setItems(new String[]{"RustySword", "Broadsword","Axe"}, 0);
        knightWeapons.addEventHandler(this, "changeWeapon");
        knightWeapons.setVisible(false);
        rogueWeapons = new GDropList(d, d.width / 4 * 3, d.height - d.height / 4, rogue.getWidth(), 100, 3);
        rogueWeapons.setItems(new String[]{"Pistol", "SMG", "Shotgun"}, 0);
        rogueWeapons.addEventHandler(this, "changeWeapon");
        rogueWeapons.setVisible(false);
        save = new GButton(d, 20, d.height -100, 200, 80, "Save");
        save.addEventHandler(this, "save");
        save.setVisible(false);
    }

    private Texture getWeapon(String s) {
        switch (s) {
            case "Pistol":
                return pistol;
            case "SMG":
                return smg;
            case "Shotgun":
                return shotgun;
            case "RustySword":
                return rustySword;
            case "Broadsword":
                return broadSword;
            case "MagicStaff":
                return magicStaff;
            case "SpellBook":
                return spellBook;
            case "MagicGloves":
                return magicGloves;
            case "Axe":
                return axe;
        }
        return null;
    }
    public void changeWeapon(GDropList dropList, GEvent event) {
        if(dropList == mageWeapons){
            PlayerData.setInitialMageWeapon(dropList.getSelectedText());
        }else if(dropList == knightWeapons){
            PlayerData.setInitialKnightWeapon(dropList.getSelectedText());
        }else if(dropList == rogueWeapons){
            PlayerData.setInitialRogueWeapon(dropList.getSelectedText());
        }

    }
    public void save(GButton b, GEvent event){
        DrawingSurface.setScreen(Window.HOME);
    }
    @Override
    public GAbstractControl[] getUI() {
        return new GAbstractControl[]{mageWeapons, rogueWeapons, knightWeapons,save};
    }
}
