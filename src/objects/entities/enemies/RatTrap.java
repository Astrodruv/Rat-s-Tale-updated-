package objects.entities.enemies;

import objects.entities.Entity;
import objects.entities.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import values.RatTrapValues;
import world.Cell;

public class RatTrap extends Entity {

    private Image image;
    private SpriteSheet mySheet;
    private Image currentFrame;
    private int step = 0;
    private int frames = 0;
    private int framesPerStep = 6;

    public RatTrap(float x, float y) {
        super(x, y, 0, 0, RatTrapValues.HEALTH, RatTrapValues.ATTACK, Images.ratTrap, RatTrapValues.IFRAMES);
        image = Images.ratTrap;
        mySheet = Images.trapping;
        currentFrame = mySheet.getSprite(0,0);
    }

    public void render(Graphics g) {
//        super.render(g);
        float renderOffsetY = h - currentFrame.getHeight();
        currentFrame.draw(x, y + renderOffsetY);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
//        super.update(gc, sbg, delta);
        if(Player.trapped) {
            frames++;
            if (frames % framesPerStep == 0) {
                step++;
            }
            if (step >= mySheet.getHorizontalCount()) {
                step = 0;
            }
            currentFrame = mySheet.getSprite(step, 0);
        }
        else currentFrame = mySheet.getSprite(0,0);
    }

    public Rectangle getBounds() {
        return new Rectangle(x + Cell.getWidth() / 3, y + Cell.getHeight() / 3, w - Cell.getWidth() / 3 * 2, h - Cell.getHeight() / 3);
    }
}
