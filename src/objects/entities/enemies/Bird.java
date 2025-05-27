
package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.BirdValues;
import values.CockroachValues;
import values.PlayerValues;
import world.Cell;
import world.World;

public class Bird extends Entity {

    private int counter;
    private boolean diveTime;

    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;

    public Bird(float x, float y) {
        super(x, y, BirdValues.X_SPEED, BirdValues.Y_SPEED, BirdValues.HEALTH, BirdValues.ATTACK, Images.birdIdle, BirdValues.IFRAMES);
        facingRight = true;
        diveTime = false;
        counter = 0;

        mySheet = Images.bird;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g) {
        if (isHit){
            if (invincibilityFrames % 7 == 0){

            }
            else{
                float renderOffsetY = h - currentFrame.getHeight();
                if (facingRight) {
                    currentFrame.draw(x, y + renderOffsetY);
                } else {
                    currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                }
            }
        }
        else{
            float renderOffsetY = h - currentFrame.getHeight();
            if (facingRight) {
                currentFrame.draw(x, y + renderOffsetY);
            } else {
                currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
            }
        }

        g.drawString("corner", x,y);
        g.drawString(""+counter, 700,700);
        g.draw(getBounds());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (World.level.equals(BirdValues.LEVEL_SPAWN_LOCATION) && PlayerValues.isPlayerHurtingEnemy) {
            isHit = true;
            PlayerValues.isPlayerHurtingEnemy = false;
        }

        if (isHit && !hasTakenDamage){
            if (PlayerValues.isPlayerUsingKnife){
                takeDamage(PlayerValues.ATTACK * 3);
//                PlayerValues.isPlayerUsingKnife = false;
            }
            else takeDamage(PlayerValues.ATTACK);
            hasTakenDamage = true;
        }

        if (x <= -Images.birdIdle.getWidth() || x + w >= Main.getScreenWidth() || 0 >= x) {
            xSpeed *= -1;
            counter++;
            if (diveTime){
                counter = 0;
                diveTime = false;
            }
            facingRight = !facingRight;
        }

        x += xSpeed;

        if (counter < 4){
            float screenWidth = Main.getScreenWidth() - w;
            float screenHeight = Main.getScreenHeight() - Cell.getHeight() - h;
            float scale = -4 * screenHeight / (screenWidth * screenWidth);

            y = scale * (x - screenWidth / 2) * (x - screenWidth / 2) + screenHeight;
        }
        else{
            float amplitude = (Main.getScreenHeight() - Cell.getHeight() - h) / 2;
            float period = (float)((Math.PI * 7) / Main.getScreenWidth());

            y = (float)((amplitude) * -Math.cos(period * x)) + (amplitude);

            diveTime = true;
        }


        if (isHit) {
            invincibilityFrames--;
        }

        if (invincibilityFrames <= 0) {
            invincibilityFrames = invincibilityFrameValue;
            isHit = false;
        }

        percentHealth = (float) curHealth / maxHealth;

        if (curHealth <= 0) {
            isDead = true;
            cell.removeObject();
        }

        if (!isHit) hasTakenDamage = false;

        frames++;
        if(frames % framesPerStep == 0)
        {
            step++;
        }
        if(step >= mySheet.getHorizontalCount())
        {
            step = 0;
        }
        currentFrame = mySheet.getSprite(step, 0).getScaledCopy((int) Cell.getWidth() * 6, (int) Cell.getHeight() * 3);

//        super.update(gc, sbg, delta);
        }

    public Rectangle getBounds() {
        return new Rectangle(x + Cell.getWidth() / 4, y + Cell.getHeight() / 2 * 3, w - Cell.getWidth() / 2, h - Cell.getHeight() / 2 * 3);
    }
}

