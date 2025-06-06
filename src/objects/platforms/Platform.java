package objects.platforms;

import objects.GameObject;
import objects.interactables.Door;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import world.Cell;
import world.World;

public class Platform extends GameObject {
    public Platform(float x, float y, Image image) {
        super(x, y, image);
    }

    public void render(Graphics g){
        super.render(g);
//        g.setColor(Color.white);
//        if (isBottomPlatform()) g.drawString("B", x + (w / 3), y + (h / 2));
//        g.setColor(Color.yellow);
//        if (isSidePlatform()) g.drawString("S", x + (w / 2), y + (h / 2));
//        g.setColor(Color.blue);
//        if (isNextToFloor()) g.drawString("N", x + (w / 4), y + (h / 2));
    }

    public void collisions(StateBasedGame sbg) {

    }

    public boolean isBottomPlatform() {
        if (cell == null) return true;

        int belowY = cell.getY() + 1;
        int aboveY = cell.getY() - 1;
        int above2Y = cell.getY() - 2;
        int x = cell.getX();

        if (belowY >= World.HEIGHT || aboveY < 0) return true;

        Cell belowCell = World.getCell(x, belowY);
        Cell aboveCell = World.getCell(x, aboveY);

        Cell aboveCellDoor = null;
        if (above2Y >= 0) {
            aboveCellDoor = World.getCell(x, above2Y);
        }

        boolean spaceBelow = belowCell == null || belowCell.getObject() == null;
        boolean spaceAbove = aboveCell == null || aboveCell.getObject() == null;

        if (belowCell != null && belowCell.getObject() instanceof Door) return false;
        if (aboveCellDoor != null && aboveCellDoor.getObject() instanceof Door) return false;

        return spaceBelow || spaceAbove;
    }

    public boolean isSidePlatform() {
        if (cell == null) return true;

        int x = cell.getX();
        int y = cell.getY();

        if (x <= 0 || x + 1 >= World.WIDTH) return true;

        Cell rightCell = World.getCell(x + 1, y);
        Cell leftCell = World.getCell(x - 1, y);

        boolean spaceRight = rightCell == null || rightCell.getObject() == null || getH() > rightCell.getObject().getH();
        boolean spaceLeft = leftCell == null || leftCell.getObject() == null || getH() > leftCell.getObject().getH();

        return spaceLeft || spaceRight;
    }

    public boolean isSurroundedPlatform(){ // is not working as of 6/4/2025
        if (cell == null) return true;

        int x = cell.getX();
        int y = cell.getY();

        if (x <= 0 || x + 1 >= World.WIDTH) return true;
        if (cell.getY() + 1 >= World.HEIGHT || cell.getY() - 1 < 0) return true;

        Cell rightCell = World.getCell(x + 1, y);
        Cell leftCell = World.getCell(x - 1, y);
        Cell belowCell = World.getCell(x, cell.getY() + 1);
        Cell aboveCell = World.getCell(x, cell.getY() - 1);

        return rightCell != null && rightCell.getObject() != null && leftCell != null && leftCell.getObject() != null && belowCell != null && belowCell.getObject() != null && aboveCell != null && aboveCell.getObject() != null;
    }

    public boolean isNextToFloor(){
        if (cell == null) return true;

        int x = cell.getX();
        int y = cell.getY();

        if (x <= 0 || x + 1 >= World.WIDTH) return true;
        if (cell.getY() + 1 >= World.HEIGHT || cell.getY() - 1 < 0) return true;

        Cell rightCell = World.getCell(x + 1, y);
        Cell leftCell = World.getCell(x - 1, y);

        return rightCell.getObject() instanceof Floor || leftCell.getObject() instanceof Floor;
    }

    public Rectangle getBounds() { // Change if need be for rendering problem fixes
        return super.getBounds();
    }

}