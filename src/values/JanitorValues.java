package values;

import world.Cell;

public class JanitorValues {

    public static final int X_SPEED = (int) (Cell.getWidth() / 6);
    public static final int Y_SPEED = (int) (Cell.getHeight() / 5);
    public static final int HEALTH = 35;
    public static final int ATTACK = 2;
    public static final int IFRAMES = 45;

    public static final String LEVEL_SPAWN_LOCATION = "levels/closet5.txt";

//    public static final int PROJ_X_SPEED = (int) (Cell.getWidth() / 2); // 0
    public static final int PROJ_Y_SPEED = (int) (Cell.getWidth() / 2);
    public static final int PROJ_ATTACK = 10;
    public static final int PROJ_FRAMES = 60;
}
