package objects.entities.enemies;

import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.RatTrapValues;

public class RatTrap extends Entity {

    private Image image;

    public RatTrap(float x, float y) {
        super(x, y, 0, 0, RatTrapValues.HEALTH, RatTrapValues.ATTACK, Images.cockroachIdle, RatTrapValues.IFRAMES);
        image = Images.car1;
    }

    public void render(Graphics g) {
        super.render(g);
        image.draw(x,y );
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
//        super.update(gc, sbg, delta);
    }
}
