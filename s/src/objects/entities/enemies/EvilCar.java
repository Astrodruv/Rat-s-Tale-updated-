package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.CarValues;
import world.Cell;

public class EvilCar extends Entity {
private Image image;
    public EvilCar(float x, float y) {
        super(x, y, CarValues.X_SPEED, CarValues.Y_SPEED, CarValues.HEALTH, CarValues.ATTACK, Images.car1, CarValues.IFRAMES);
        image = Images.car1;
        w = image.getWidth();
        h = image.getHeight();
    }

    public void render(Graphics g) {
        super.render(g);
        image.draw(x,y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {

        xVelocity -= (float) xSpeed / 5;

        if (x <= Cell.getWidth() * 2) {
            x = Main.getScreenWidth() - (Cell.getWidth() * 2) - w;
            xVelocity = 0;
        }

        super.update(gc, sbg, delta);
    }
}
