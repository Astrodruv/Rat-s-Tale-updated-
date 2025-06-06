package objects.platforms;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import ui.Images;
import values.PlayerValues;
import world.Cell;

public class Floor extends Platform
{
    public Floor(float x, float y) {
        super(x,y, Images.sewerFloor);
        if (PlayerValues.section == 1) image = Images.streetFloor;
        if (PlayerValues.section == 2) image = Images.closetFloor;
    }

    public void render(Graphics g) {
        super.render(g);
//        g.setColor(Color.white);
//        g.draw(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h - 1); // (Cell.getHeight() / 16)
    }
}