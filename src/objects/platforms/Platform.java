package objects.platforms;

import objects.GameObject;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import world.Cell;
import world.World;

public class Platform extends GameObject {
    public Platform(float x, float y, Image image) {
        super(x, y, image);
    }

    public void collisions(StateBasedGame sbg) {

    }

    public boolean isBottomPlatform() {
        if (cell == null) return true;

        int belowY = cell.getY() + 1;
        int aboveY = cell.getY() - 1;
        int x = cell.getX();

        if (belowY >= World.HEIGHT || aboveY < 0) return true;

        Cell belowCell = World.getCell(x, belowY);
        Cell aboveCell = World.getCell(x, aboveY);

        boolean spaceBelow = belowCell == null || belowCell.getObject() == null;
        boolean spaceAbove = aboveCell == null || aboveCell.getObject() == null;

        return spaceBelow || spaceAbove;
    }

    public boolean isSidePlatform() {
        if (cell == null) return true;

        int x = cell.getX();
        int y = cell.getY();

        if (x <= 0 || x + 1 >= World.WIDTH) return true;

        Cell rightCell = World.getCell(x + 1, y);
        Cell leftCell = World.getCell(x - 1, y);

        boolean spaceRight = rightCell == null || rightCell.getObject() == null;
        boolean spaceLeft = leftCell == null || leftCell.getObject() == null;

        return spaceLeft || spaceRight;
    }
}