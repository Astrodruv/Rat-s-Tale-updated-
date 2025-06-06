package values;

import world.Cell;

public class ChefValues {

    public static final int X_SPEED = (int) (Cell.getWidth() / 5);
    public static final int Y_SPEED = (int) (Cell.getHeight() / 4);
    public static final int HEALTH = 30;
    public static final int ATTACK = 3;
    public static final int IFRAMES = 60;

    public static boolean chefWithFood = false;

    public static final String LEVEL_SPAWN_LOCATION = "levels/cafeteria.txt";
}
