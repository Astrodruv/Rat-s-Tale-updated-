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
    public static Image streetFloor2;
    public static Image streetPlatform;

    // Objects
    public static Image ratIdle;
    public static Image ratWalking;
    public static Image ratWalkingScaled;
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
    public static Image knifeInvUnscaled;
    public static Image knifeInv;

    public static SpriteSheet rat;
    public static SpriteSheet cockRoach;
    public static SpriteSheet bird;
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
            streetFloor2 = new Image("res/levels/street/Street_Floor2.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());
            streetPlatform = new Image("res/misc/Street_Platform.png").getScaledCopy((int) Cell.getWidth(), (int) (Cell.getHeight() / 8));

            ratIdle = new Image("res/misc/Rat_Idle1.png").getScaledCopy((int) (Cell.getWidth() * 2.5), (int) Cell.getHeight() + 10);
            cockroachIdle = new Image("res/levels/sewer/Cockroach_Idle.png").getScaledCopy((int) Cell.getWidth() * 4, (int) Cell.getHeight());
            birdIdle = new Image("res/levels/street/Eagle.png").getScaledCopy((int) Cell.getWidth() * 6, (int) Cell.getHeight() * 3);
            birdFlying = new Image("res/levels/street/Bird_Flapping3.png");
            bird = new SpriteSheet(birdFlying,329,210);
            car1 = new Image("res/levels/street/Car.png").getScaledCopy((int) (Cell.getWidth() * 4), (int) (Cell.getHeight()));
            car2 = new Image("res/levels/street/Car2.png").getScaledCopy((int) (Cell.getWidth() * 4), (int) (Cell.getHeight()));
            ratWalking = new Image("res/misc/Rat_Walking.png");
            rat = new SpriteSheet(ratWalking,174,48);
            cockroachWalking = new Image("res/levels/sewer/CockRoach Walking.png");
            cockRoach = new SpriteSheet(cockroachWalking,150,90);
            key = new Image("res/misc/Key.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() / 3);
            door = new Image("res/misc/Door.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() * 2);
            knife = new Image("res/misc/Knife.png").getScaledCopy((int) Cell.getWidth() / 2, (int) (Cell.getHeight()));
            knifeInvUnscaled = new Image("res/misc/Knife_Inventory.png");
            knifeInv = knifeInvUnscaled.getScaledCopy(1.2f);


//            rat = new SpriteSheet(ratWalking,174,48); Not for now (After Beta)
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}