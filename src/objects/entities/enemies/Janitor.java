package objects.entities.enemies;

import engine.Main;
import engine.states.Game;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.BirdValues;
import values.JanitorValues;
import values.PlayerValues;
import world.Cell;
import world.World;

import java.util.ArrayList;


public class Janitor extends Entity {

    private Image image;
    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 4;

    private boolean moveLeft;
    private boolean moveRight;
    private int jumpCount;

    private ArrayList<JanitorProjectile> janitorProjectile = new ArrayList<>();

    public Janitor(float x, float y)
    {
        super(x, y, JanitorValues.X_SPEED, JanitorValues.Y_SPEED, JanitorValues.HEALTH, JanitorValues.ATTACK, Images.janitorIdle, JanitorValues.IFRAMES);
        facingRight = false;
        image = Images.janitorIdle;
        mySheet = Images.janitor;
        currentFrame = mySheet.getSprite(0,0);
        moveLeft = true;
        moveRight = false;
        jumpCount = 1;
    }

    public void render(Graphics g)
    {
        if (isHit){
            if (invincibilityFrames % 7 == 0){

            }
            else{
                if (xVelocity != 0) {
                    float renderOffsetY = h - currentFrame.getHeight();
                    if (facingRight) {
                        currentFrame.draw(x, y + renderOffsetY);
                    } else {
                        currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                    }
                }
                else{
                    image.draw(x,y,w,h);
                }
            }
        }
        else{
            if (xVelocity != 0) {
                float renderOffsetY = h - currentFrame.getHeight();
                if (facingRight) {
                    currentFrame.draw(x, y + renderOffsetY);
                } else {
                    currentFrame.getFlippedCopy(true, false).draw(x, y + renderOffsetY);
                }
            }
            else{
                image.draw(x,y,w,h);
            }
        }

        for (JanitorProjectile j : janitorProjectile){
            j.render(g);
        }

        g.drawString("corner", x, y);
        g.drawString("moveLeft: " + moveLeft, 500, 500);
        g.drawString("moveRight: " + moveRight, 500, 550);
        g.drawString("jumpCount: " + jumpCount, 500, 600);

        g.drawString(""+janitorProjectile.size(), 500, 650);

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if (!janitorProjectile.isEmpty()) {
            if (!Game.levelObjects.contains(janitorProjectile.getFirst())) {
                Game.levelObjects.addAll(janitorProjectile);
            }
        }

        if (World.level.equals(JanitorValues.LEVEL_SPAWN_LOCATION) && PlayerValues.isPlayerHurtingEnemy) {
            isHit = true;
            PlayerValues.isPlayerHurtingEnemy = false;
        }

        if (isHit && !hasTakenDamage){
            if (PlayerValues.isPlayerUsingKnife){
                takeDamage(PlayerValues.ATTACK * 3);
            }
            else takeDamage(PlayerValues.ATTACK);
            hasTakenDamage = true;
        }

//        if (movementTimer >= 0) moveRight();
//        else if (movementTimer > -JanitorValues.MOVEMENT_TIMER_VALUE) moveLeft();
//
//        movementTimer--;
//        if (movementTimer < -JanitorValues.MOVEMENT_TIMER_VALUE) movementTimer = JanitorValues.MOVEMENT_TIMER_VALUE;

        if (moveLeft){
            moveLeft();
            facingRight = false;
        }

        if (moveRight){
            moveRight();
            facingRight = true;
        }

        if (x < (Cell.getWidth() * 13) && moveLeft && jumpCount < 4){
            moveLeft = false;
            moveRight = true;
            jumpCount++;
        }

        if (x + getW() > Main.getScreenWidth() - (Cell.getWidth() * 13) && moveRight && jumpCount < 3){
            moveLeft = true;
            moveRight = false;
            jumpCount++;
        }

        if (!janitorProjectile.isEmpty()) {
            for (JanitorProjectile projectile : janitorProjectile) {
                projectile.update(gc, sbg, delta);
            }
        }

        if (jumpCount == 3){
            if (moveRight){
                if (x + getW() < Main.getScreenWidth() - (Cell.getWidth() * 20)) {
                    image = rightFacingImage;
                    xVelocity = xSpeed * 3;
                    yVelocity = (float) -ySpeed / 3 * 2;
                    onGround = false;
                }
                else{
                    jumpCount = 0;
                    janitorProjectile.clear();
                    janitorProjectile.add(new JanitorProjectile(Cell.getWidth() * 3, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - Cell.getWidth() * 3, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Cell.getWidth() * 4, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - Cell.getWidth() * 4, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 5)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 5)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 6)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 6)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));
                }
            }
            else if (moveLeft){
                if (x > (Cell.getWidth() * 20)) {
                    image = rightFacingImage.getFlippedCopy(true, false);
                    xVelocity = -xSpeed * 3;
                    yVelocity = (float) -ySpeed / 3 * 2;
                    onGround = false;
                }
                else{
                    jumpCount = 0;
                    janitorProjectile.clear();
                    janitorProjectile.add(new JanitorProjectile(Cell.getWidth() * 3, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - Cell.getWidth() * 3, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Cell.getWidth() * 4, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - Cell.getWidth() * 4, Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 5)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 5)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 6)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 6)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile((float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));
                    janitorProjectile.add(new JanitorProjectile(Main.getScreenWidth() - (float) (Cell.getWidth() * ((Math.random() * 10) + 12)), Cell.getHeight()));  }
            }
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

        currentFrame = mySheet.getSprite(step, 0).getScaledCopy((int) (Cell.getWidth() * 6), (int) (Cell.getHeight() * 5));

        super.update(gc, sbg, delta);

        xAccel = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y + (Cell.getHeight() / 2),w,h + (Cell.getHeight() / 2));
    }
}
