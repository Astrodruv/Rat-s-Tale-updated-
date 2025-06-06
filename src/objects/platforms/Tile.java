package objects.platforms;

import org.newdawn.slick.Graphics;
import ui.Images;
import values.PlayerValues;

public class Tile extends Platform
{
    public Tile(float x, float y) {
        super(x,y, Images.sewerPlatform);
        if (PlayerValues.section == 1) image = Images.sewerPlatform;
//        if (PlayerValues.section == 2) image = Images.closetPlatform;
    }

    public void render(Graphics g) {
       super.render(g);
    }

}

//float cellW = Cell.getWidth();
//float cellH = Cell.getHeight();
//        image.draw(cell.getX() * cellW, cell.getY() * cellH, cellW, h);
//        if (isBottomPlatform()) {
//        g.setColor(Color.yellow);
////            g.drawString("Bottom",x + 5,y + 5);
////            g.draw(getBounds());
//        }
//                if (isSidePlatform()) {
//        g.setColor(Color.yellow);
////            g.drawString("Side",x,y - 5);
////            g.draw(getBounds());
//        }

