package ui;

import engine.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import world.Cell;

public class Images
{
    public static Image titleScreen;
    public static Image sewerBackground;
    public static Image streetBackground;
    public static Image schoolBackground;

    // Level Platforms
    public static Image sewerPlatform;
    public static Image sewerFloor;
    public static Image streetFloor;

    // Objects
    public static Image ratIdle;
    public static Image ratWalking;
    public static Image cockroachIdle;
    public static Image cockroachWalking;
    public static Image birdIdle;
    public static Image birdFlying;
    public static Image car1;
    public static Image car2;

    // Interactables
    public static Image key;
    public static Image door;
    public static Image knife;

    public static SpriteSheet rat;
    public static SpriteSheet knifeAttack;

    public static void loadImages()
    {
        try {
            titleScreen = new Image("res/misc/TitleScreen.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            sewerBackground = new Image("res/levels/sewer/Sewer_Background.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            streetBackground = new Image("res/levels/street/Street_Background.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            schoolBackground = new Image("res/levels/school/School_Background.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());

            sewerFloor = new Image("res/levels/sewer/Sewer_Floor.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());
            sewerPlatform = new Image("res/levels/sewer/Sewer_Platform.png").getScaledCopy((int) Cell.getWidth(), (int) (Cell.getHeight() / 8));
            streetFloor = new Image("res/levels/street/Street_Floor.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());

            ratIdle = new Image("res/misc/Rat_Idle1.png").getScaledCopy((int) (Cell.getWidth() * 2.5), (int) Cell.getHeight());
            cockroachIdle = new Image("res/levels/sewer/Cockroach_Idle.png").getScaledCopy((int) Cell.getWidth() * 4, (int) Cell.getHeight());
            birdIdle = new Image("res/levels/street/Eagle.png").getScaledCopy((int) Cell.getWidth() * 6, (int) Cell.getHeight() * 3);
            car1 = new Image("res/levels/street/Car.png").getScaledCopy((int) (Cell.getWidth() * 1.5f), (int) Cell.getHeight());
            car2 = new Image("res/levels/street/Car2.png").getScaledCopy((int) (Cell.getWidth() * 1.5f), (int) Cell.getHeight());

            key = new Image("res/misc/Key.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() / 3);
            door = new Image("res/misc/Door.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() * 2);
            knife = new Image("res/misc/Knife.png").getScaledCopy((int) Cell.getWidth() / 2, (int) (Cell.getHeight()));

//            rat = new SpriteSheet(ratWalking,174,48); Not for now (After Beta)
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
