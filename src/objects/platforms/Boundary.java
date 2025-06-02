package objects.platforms;

import org.newdawn.slick.Graphics;
import ui.Images;

public class Boundary extends Platform {

    public Boundary(float x, float y) {
        super(x,y, Images.streetBoundary);
    }

    public void render(Graphics g) {
        super.render(g);
    }
}
