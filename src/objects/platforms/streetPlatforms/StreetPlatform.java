package objects.platforms.streetPlatforms;

import objects.platforms.Platform;
import org.newdawn.slick.Graphics;
import ui.Images;

public class StreetPlatform extends Platform {

    public StreetPlatform(float x, float y) {
        super(x,y, Images.sewerPlatform); // Temporary for now
    }

    public void render(Graphics g) {
        super.render(g);
    }
}