package objects.entities.enemies;

import engine.Main;
import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;

public class Car extends Entity {

    public Car(float x, float y) {
        super(x, y, (int) ((Math.random() * 5) + 10), 0, 0, 20, Images.car1, 0);
    }

    public void render(Graphics g) {
        super.render(g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        xVelocity -= xSpeed;

        if (x < -50) {
            x = Main.getScreenWidth();
            xVelocity = 0;
        }

        super.update(gc, sbg, delta);
    }
}