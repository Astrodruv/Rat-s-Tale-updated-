package objects.platforms.streetPlatforms;

import objects.platforms.Platform;
import org.newdawn.slick.Graphics;
import ui.Images;

public class StreetFloor extends Platform {

    public StreetFloor(float x, float y) {
        super(x,y, Images.streetFloor2);
    }

    public void render(Graphics g) {
        super.render(g);
    }
}
