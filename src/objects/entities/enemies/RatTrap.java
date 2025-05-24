package objects.entities.enemies;

import objects.entities.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.RatTrapValues;

public class RatTrap extends Entity {

    public RatTrap(float x, float y) {
        super(x, y, 0, 0, RatTrapValues.HEALTH, RatTrapValues.ATTACK, Images.car1, RatTrapValues.IFRAMES);
    }

    public void render(Graphics g)
    {
        super.render(g);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        super.update(gc, sbg, delta);
    }
}
