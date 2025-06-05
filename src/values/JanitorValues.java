package values;

import world.Cell;

public class JanitorValues {

    public static final int X_SPEED = (int) (Cell.getWidth() / 6);
    public static final int Y_SPEED = (int) (Cell.getHeight() / 5);
    public static final int HEALTH = 100;
    public static final int ATTACK = 3;
    public static final int IFRAMES = 60;

    public static final int MOVEMENT_TIMER_VALUE = 100;

    public static final String LEVEL_SPAWN_LOCATION = "levels/closet5.txt";
}
