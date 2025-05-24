package values;

import world.Cell;

public class CockroachValues {
    public static final int X_SPEED = (int) (Cell.getWidth() / 6);
    public static final int Y_SPEED = (int) (Cell.getHeight() / 5);
    public static final int HEALTH = 10;
    public static final int ATTACK = 2;
    public static final int IFRAMES = 60;

    public static final String LEVEL_SPAWN_LOCATION = "levels/sewer3.txt";
}
