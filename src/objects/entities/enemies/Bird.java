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

public class Bird extends Entity{

    private float flightTime;
    private float flightHeight;

    public Bird(float x, float y) {
        super(x, y, BirdValues.X_SPEED, BirdValues.Y_SPEED, BirdValues.HEALTH, BirdValues.ATTACK, Images.birdIdle, BirdValues.IFRAMES);
        facingRight = true;
        flightTime = 3 * 60;
        flightHeight = (float) (Math.random() * 500) + 500;
    }

    public void render(Graphics g) {
        if(!isDead) {
            super.render(g);
        }

//        g.draw(getBounds());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (PlayerValues.isPlayerHurtingEnemy){
            isHit = true;
            takeDamage(PlayerValues.ATTACK);
            PlayerValues.isPlayerHurtingEnemy = false;
        }

//        if (isHit && !hasTakenDamage){
//            takeDamage(PlayerValues.ATTACK);
//            hasTakenDamage = true;
//        }

        y = (((float) -1 / (900)) * (x) * (x - (Main.getScreenWidth() - image.getWidth())));


        if(y < -200)
        {
            xSpeed *= -1;
            image = image.getFlippedCopy(true, false);
        }

        x += xSpeed;

        if (isHit){
            invincibilityFrames--;
        }

        if (invincibilityFrames <= 0){
            invincibilityFrames = invincibilityFrameValue;
            isHit = false;
        }

        percentHealth = (float) curHealth / maxHealth;

        if(curHealth <= 0)
        {
            isDead = true;
            cell.removeObject();
        }

//        super.update(gc, sbg, delta);
    }

}