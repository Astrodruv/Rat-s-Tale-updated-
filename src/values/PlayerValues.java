package values;

import world.Cell;

public class PlayerValues {
    public static final int X_SPEED = (int) Cell.getWidth() / 5;
    public static final int Y_SPEED = (int) (Cell.getHeight() / 2); //test 4.5
    public static final int HEALTH = 10;
    public static final int ATTACK = 1;
    public static final int IFRAMES = 120;

    public static boolean isPlayerHurtingEnemy = false;
    public static boolean keyOnPermanentlySetting = false;
    public static boolean isPlayerTouchingKey = keyOnPermanentlySetting;
    public static boolean doesPlayerHaveWeapon = false;

}
