package ui;

import engine.Main;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import world.Cell;

public class Images {
    public static Image titleScreen;
    public static Image winScreen;
    public static Image deathScreen;
    public static Image loreInstructionsBackground;
    public static Image sewerBackground;
    public static Image streetBackground;
    public static Image schoolBackground;
    public static Image closetBackground;
    public static Image classroomBackground;

    // Level Platforms
    public static Image sewerPlatform;
    public static Image sewerWall;
    public static Image sewerFloor;
    public static Image streetFloor;
    public static Image streetBoundary;
    public static Image closetPlatform;

    // Objects
    public static Image ratIdle;
    public static Image ratWalking;
    public static Image cockroachIdle;
    public static Image cockroachWalking;
    public static Image birdIdle;
    public static Image birdFlying;
    public static Image car1;
    public static Image carDriving;
    public static Image tire;
    public static Image ratTrap;
    public static Image ratTrapped;
    public static Image janitorIdle;
    public static Image janitorWalking;
    public static Image studentOneIdle;
    public static Image studentOneWalking;

    // Interactables
    public static Image key;
    public static Image door;
    public static Image knife;
    public static Image knifeInv;
    public static Image coin;
    public static Image streetDoor;
    public static Image screwDriver;
    public static Image vent;

    // Game Entities Animations
    public static SpriteSheet rat;
    public static SpriteSheet cockRoach;
    public static SpriteSheet bird;
    public static SpriteSheet knifeAttack;
    public static SpriteSheet janitor;
    public static SpriteSheet trapping;
    public static SpriteSheet student1;
    public static SpriteSheet movingCar;

    public static void loadImages()
    {
        try {
            titleScreen = new Image("res/misc/TitleScreen.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            winScreen = new Image("res/misc/WinScreen.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            deathScreen = new Image("res/misc/DeathScreen.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            loreInstructionsBackground = new Image("res/misc/LoreAndInstructionsScreen.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            sewerBackground = new Image("res/levels/sewer/Sewer_Background.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            streetBackground = new Image("res/levels/street/Street_Background.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            schoolBackground = new Image("res/levels/school/School_Background.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            closetBackground = new Image("res/levels/closet/Closet_Background.png").getScaledCopy((int) (Main.getScreenWidth() - (Cell.getWidth())), (int) (Main.getScreenHeight() - (Cell.getHeight())));
            classroomBackground = new Image("res/levels/classroom/Classroom_Background.png").getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());

            sewerWall = new Image("res/levels/sewer/Sewer_Wall.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());
            sewerFloor = new Image("res/levels/sewer/Sewer_Floor.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());
            sewerPlatform = new Image("res/levels/sewer/Sewer_Platform.png").getScaledCopy((int) Cell.getWidth(), (int) (Cell.getHeight() / 8));
            streetFloor = new Image("res/levels/street/Street_Floor.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());
            streetBoundary = new Image("res/levels/street/Street_Floor2.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());
            closetPlatform = new Image("res/levels/closet/Closet_Platform.png").getScaledCopy((int) Cell.getWidth(), (int) (Cell.getHeight() / 8));

            ratIdle = new Image("res/misc/Rat_Idle1.png").getScaledCopy((int) (Cell.getWidth() * 2.5), (int) Cell.getHeight() + 10);
            ratWalking = new Image("res/misc/Rat_Walking.png");
            rat = new SpriteSheet(ratWalking,174,48);

            cockroachIdle = new Image("res/levels/sewer/Cockroach_Idle.png").getScaledCopy((int) Cell.getWidth() * 4, (int) Cell.getHeight());
            cockroachWalking = new Image("res/levels/sewer/CockRoach Walking.png");
            cockRoach = new SpriteSheet(cockroachWalking,150,90);

            knife = new Image("res/misc/Knife.png").getScaledCopy((int) Cell.getWidth() / 2, (int) (Cell.getHeight()));
            knifeAttack = new SpriteSheet("res/misc/Knife3.png", 512, 512);
            knifeInv = new Image("res/misc/Knife_Inventory.png").getScaledCopy((int) (Cell.getWidth() * 2), (int) (Cell.getHeight()));

            car1 = new Image("res/levels/street/Car.png").getScaledCopy((int) (Cell.getWidth() * 8), (int) (Cell.getHeight() * 2));
            carDriving = new Image("res/levels/street/Car_Driving2.png");
            tire = new Image("res/levels/street/Tire.png").getScaledCopy((int) (Cell.getWidth() * 8), (int) (Cell.getHeight() * 2));

            birdIdle = new Image("res/levels/street/Eagle.png").getScaledCopy((int) Cell.getWidth() * 6, (int) Cell.getHeight() * 3);
            birdFlying = new Image("res/levels/street/Bird_Flapping3.png");
            bird = new SpriteSheet(birdFlying,329,210);

            ratTrap = new Image("res/levels/closet/Rat_Trap.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight() * 2 / 3);
            ratTrapped = new Image("res/levels/closet/Trap_Closing.png");
            trapping = new SpriteSheet(ratTrapped, (308), (252));

            janitorIdle = new Image("res/levels/closet/Janitor_Idle.png").getScaledCopy((int) Cell.getWidth() * 3, (int) Cell.getHeight() * 4);
            janitorWalking = new Image("res/levels/closet/Janitor_Walking.png");
            janitor = new SpriteSheet(janitorWalking,247, 364);

            studentOneIdle = new Image("res/levels/classroom/Student_Idle.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight());
            studentOneWalking = new Image("res/levels/classroom/Student_Walking.png");
            student1 = new SpriteSheet(studentOneWalking, 258, 275);
            movingCar = new SpriteSheet(carDriving, 333, 144);

            key = new Image("res/misc/Key.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() / 3);
            door = new Image("res/misc/Door.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() * 2);

            coin = new Image("res/levels/street/Coin.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight());
            streetDoor = new Image("res/levels/street/Street_Door.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() * 2);

            screwDriver = new Image("res/levels/closet/Screwdriver.png").getScaledCopy((int) Cell.getWidth(), (int) Cell.getHeight() * 2);
            vent = new Image ("res/levels/closet/Closet_Door.png").getScaledCopy((int) Cell.getWidth() * 2, (int) Cell.getHeight() * 2);
            
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
