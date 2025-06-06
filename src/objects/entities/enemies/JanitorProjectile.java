package objects.entities.enemies;

import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.CarValues;
import values.JanitorValues;
import world.Cell;

public class JanitorProjectile extends Entity {

    public JanitorProjectile(float x, float y) {
        super(x, y, 0, JanitorValues.PROJ_Y_SPEED, 1, JanitorValues.PROJ_ATTACK, Images.janitorProj, 0);
        w = image.getWidth();
        h = image.getHeight();
    }

    public void render(Graphics g) {
        image.draw(x,y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        y += ySpeed;

        if (y > Main.getScreenHeight() - Cell.getHeight() * 10){
            isDead = true;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y + (Cell.getHeight() / 8),w,h - (Cell.getHeight() / 8));
    }
}
