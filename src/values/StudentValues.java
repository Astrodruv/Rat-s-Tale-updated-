package values;

import world.Cell;

public class StudentValues {
    public static final int X_SPEED = (int) (Cell.getWidth() / 4);
    public static final int CRAZED_SPEED = (int) (Cell.getWidth() / 2);
    public static final int Y_SPEED = (int) (Cell.getHeight() / 5);
    public static final int HEALTH = 5;
    public static final int ATTACK = 1;
    public static final int IFRAMES = 60;

    public static final String LEVEL_SPAWN_LOCATION = "levels/sewer3.txt";
}
