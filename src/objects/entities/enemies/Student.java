package objects.entities.enemies;

import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.StudentValues;

public class Student extends Entity {
    public Student(float x, float y) {
        super(x, y, StudentValues.X_SPEED, StudentValues.Y_SPEED, StudentValues.HEALTH, StudentValues.ATTACK, Images.studentOneIdle, StudentValues.IFRAMES);
    }

    public void render(Graphics g)
    {

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        super.update(gc, sbg, delta);
    }
}
