package objects.platforms.streetPlatforms;

import objects.platforms.Platform;
import org.newdawn.slick.Graphics;
import ui.Images;

public class StreetBoundary extends Platform {

    public StreetBoundary(float x, float y) {
        super(x,y, Images.streetBoundary);
    }

    public void render(Graphics g) {
        super.render(g);
    }
}
