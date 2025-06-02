package objects.platforms;

import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;

public class Floor extends Platform
{
    public Floor(float x, float y) {
        super(x,y, Images.sewerFloor);
        if (PlayerValues.section == 1) image = Images.streetFloor;
        if (PlayerValues.section == 2) image = Images.sewerFloor;
    }

    public void render(Graphics g) {
        super.render(g);
    }

}