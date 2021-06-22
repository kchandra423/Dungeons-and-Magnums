package kchandra423.graphics.screens;

import kchandra423.actors.movingActors.players.Player;
import kchandra423.graphics.textures.Texture;
import kchandra423.utility.PlayerData;
import processing.core.PApplet;
import processing.core.PConstants;
import sye348.levels.Level;

class HUD {
    private final Texture bar = Texture.TextureBuilder.getTexture("res/Images/misc/HealthBar.png");


    HUD() {
        bar.resize((int) (bar.getWidth() * 1.5), (int) (bar.getHeight() * 1.5));
    }

    void draw(PApplet p, Level level) {
        bar.draw(p, 10, 10);
        Player player = level.getCurrentRoom().getPlayer();
        p.pushStyle();
        float percentage = (player.getHealth() / (float) player.getMaxHealth());
        percentage = percentage < 0 ? 0 : percentage;
//        rgb(132,222,2) green
//        rgb(175, 0, 42) red)
        p.fill(132, percentage * 222, 42);
        p.rect(15, 15, percentage * (bar.getWidth() - 10), bar.getHeight() - 10);
        float reloadPercentage = player.getWeapon().getTimeSinceReloaded() / player.getWeapon().getReloadTime();
        if (!Float.isNaN(reloadPercentage)) {
            p.noFill();
            p.strokeWeight(2);
            p.stroke(255,0,0);
            int halfx = p.width / 2;
            int halfy = p.height / 2;
            p.pushMatrix();

            p.translate(-level.getCurrentRoom().getPlayer().getImage().getX() + halfx - level.getCurrentRoom().getPlayer().getImage().getTexture().getWidth() / 2.0f, -level.getCurrentRoom().getPlayer().getImage().getY() + halfy - level.getCurrentRoom().getPlayer().getImage().getTexture().getHeight() / 2.0f);            float x = player.getCenterX(), y = player.getCenterY(),
                    radius = Math.max(player.getImage().getTexture().getWidth(), player.getImage().getTexture().getHeight()) + 20, angle = (float) Math.PI * 2 * reloadPercentage;
            p.arc(x, y, radius, radius, 0, angle);
            p.popMatrix();
        }
        p.fill(0);
        p.textSize(24);
        p.textAlign(PConstants.LEFT, PConstants.CENTER);
        p.text("" + player.getWeapon().getMagazine() + " | " + player.getWeapon().getMagazineSize(), 10, 80);

        p.text("Coins: "+PlayerData.getCoins(), 10,130);

        p.text((int)p.frameRate + " : fps", p.width - 200, p.height - 100);
        p.textSize(18);
        p.textAlign(PConstants.CENTER, PConstants.BOTTOM);
        p.text(level.toString(), p.width / 2f, p.height - 50);
        p.popStyle();

    }


}
