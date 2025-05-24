package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import values.BirdValues;
import values.PlayerValues;
import world.Cell;
import world.World;
import ui.Images;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Bird extends Entity{

    private float flightTime;
    private float flightHeight;
    private int flightCounter;
    private Image image;
    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;
    public Bird(float x, float y) {
        super(x, y, BirdValues.X_SPEED, BirdValues.Y_SPEED, BirdValues.HEALTH, BirdValues.ATTACK, Images.birdIdle, BirdValues.IFRAMES);
        facingRight = true;
        flightTime = (Cell.getWidth() * 49) / BirdValues.X_SPEED;
        flightHeight = (float) (Math.random() * 500) + 500;
        flightCounter = 0;
        image = Images.birdIdle;
        mySheet = Images.bird;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g) {
        if(!isDead) {
            super.render(g);
            float renderOffsetY = h - currentFrame.getHeight();
            if (facingRight) {
                currentFrame.draw(x, y + renderOffsetY);
            } else {
                currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
            }
        }

        g.draw(getBounds());
        // g.drawString(""+flightCounter, 700,700);
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



        if(x <= -Images.bird.getWidth() || x >= Main.getScreenWidth())
        {
            xSpeed *= -1;
            image = image.getFlippedCopy(true, false);
            facingRight = false;
            flightCounter++;
        }

        x += xSpeed;

        if(flightCounter % 5 == 0) //mod this
        {
            y = (float) (300 * sin((PI / 1729.2) * 5 * (x - 38.4)) + 400);
        }
        else
        {
            y = (((float) -1 / (950)) * (x) * (x - (Main.getScreenWidth() - image.getWidth())));

        }
        if(xSpeed > 0){
            facingRight = true;

        }

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
        frames++;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0);
//        super.update(gc, sbg, delta);
    }

}