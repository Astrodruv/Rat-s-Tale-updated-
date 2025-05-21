package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.PlayerValues;
import values.StudentValues;
import world.Cell;

import javax.xml.crypto.dsig.XMLSignature;

public class Student extends Entity {

    private boolean crazed;

    public Student(float x, float y) {
        super(x, y, StudentValues.X_SPEED, StudentValues.Y_SPEED, StudentValues.HEALTH, StudentValues.ATTACK, Images.knife, StudentValues.IFRAMES);
        crazed = false;
        int randomizer = (int) (Math.random() + 1);
        if (randomizer == 0)
        {
            facingRight = true;
        } else {
            facingRight = false;
        }
    }

    public void render(Graphics g)
    {
        super.render(g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if(PlayerValues.isPlayerHurtingEnemy)
        {
            isHit = true;
            PlayerValues.isPlayerHurtingEnemy = false;
        }
        if(isHit)
        {
            takeDamage(PlayerValues.ATTACK);
            crazed = true;
        }
        if(crazed)
        {
            xSpeed = StudentValues.CRAZED_SPEED;
        }
        if(x <= 0 || x >= Main.getScreenWidth() - Cell.getWidth() - Images.knife.getWidth())
        {
            xSpeed *= -1;
        }

        x += xSpeed;

        super.update(gc, sbg, delta);
    }

    public void moveLeft(){
        image = rightFacingImage.getFlippedCopy(true, false);
        xVelocity = -(xSpeed + xAccel);
        xAccel += 0.075f;
    }

    public void moveRight(){
        image = rightFacingImage;
        xVelocity = (xSpeed - xAccel);
        xAccel -= 0.075f;
    }
}
