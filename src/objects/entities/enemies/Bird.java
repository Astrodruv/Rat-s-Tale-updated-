package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.BirdValues;
import values.PlayerValues;
import world.Cell;
import world.World;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Bird extends Entity {

    private float flightTime;
    private float flightHeight;
    private float diveTime;
    private float xStore;
    float time = 0;
    int afterFirst;

    public Bird(float x, float y) {
        super(x, y, BirdValues.X_SPEED, BirdValues.Y_SPEED, BirdValues.HEALTH, BirdValues.ATTACK, Images.birdIdle, BirdValues.IFRAMES);
        facingRight = true;
        flightTime = 265 * 4;
        flightHeight = (float) (Math.random() * 500) + 500;
        diveTime = 60;
    }

    public void render(Graphics g) {
        if (!isDead) {
            super.render(g);
        }

        g.drawString(""+flightTime, 700,700);
        g.drawString(""+xSpeed, 900,700);
        g.drawString(""+Cell.getWidth(), 900, 700);
//        g.draw(getBounds());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        flightTime--;
        time++;

        if (PlayerValues.isPlayerHurtingEnemy) {
            isHit = true;
            takeDamage(PlayerValues.ATTACK);
            PlayerValues.isPlayerHurtingEnemy = false;
        }

//        if (isHit && !hasTakenDamage){
//            takeDamage(PlayerValues.ATTACK);
//            hasTakenDamage = true;
//        }

        if (x <= -Images.birdIdle.getWidth() || x >= Main.getScreenWidth()) {
            xSpeed *= -1;
            image = image.getFlippedCopy(true, false);
            System.out.println(time);
        }

        x += xSpeed;

        if(flightTime > 0) {
            y = (((float) -1 / (950)) * (x) * (x - (Main.getScreenWidth() - image.getWidth())));
        }
        else if(flightTime > (-308)){
            y = (float) (300 * sin((PI / 1729.2) * 5 * (x-38.4))) + 450;
        }
        else {
            flightTime = 308 * 4;
        }
        if (isHit) {
            invincibilityFrames--;


            if (invincibilityFrames <= 0) {
                invincibilityFrames = invincibilityFrameValue;
                isHit = false;
            }

            percentHealth = (float) curHealth / maxHealth;

            if (curHealth <= 0) {
                isDead = true;
                cell.removeObject();
            }

//        super.update(gc, sbg, delta);
        }

    }
}