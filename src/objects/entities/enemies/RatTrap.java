package objects.entities.enemies;

import objects.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.RatTrapValues;
import world.Cell;

public class RatTrap extends Entity {

    private Image image;

    public RatTrap(float x, float y) {
        super(x, y, 0, 0, RatTrapValues.HEALTH, RatTrapValues.ATTACK, Images.ratTrap, RatTrapValues.IFRAMES);
        image = Images.ratTrap;
    }

    public void render(Graphics g) {
        super.render(g);
        image.draw(x,y);
        g.setColor(Color.blue);
        g.draw(getBounds());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
//        super.update(gc, sbg, delta);
    }

    public Rectangle getBounds() {
        return new Rectangle(x + Cell.getWidth() / 3, y + Cell.getHeight() / 3, w - Cell.getWidth() / 3 * 2, h - Cell.getHeight() / 3);
    }
}
