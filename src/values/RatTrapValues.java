package values;

import world.Cell;

public class RatTrapValues {

    public static final int X_SPEED = (int) Cell.getWidth() / 5;
    public static final int Y_SPEED = (int) (Cell.getHeight() / 4.5);
    public static final int HEALTH = 10;
    public static final int ATTACK = 5;
    public static final int IFRAMES = 60;

    public static boolean isPlayerHurtingEnemy = false;

}
