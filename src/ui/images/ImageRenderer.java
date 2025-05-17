package ui.images;

import engine.Main;
import objects.world.Cell;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageRenderer
{
    public static final float screenRatio = 1 / ((float) (Main.getScreenWidth() * Main.getScreenHeight()) / 1000000);

    public static Image sewerBackgroundUnscaled;
    public static Image streeBackgroundUnscaled;
    public static Image schoolBackgroundUnscaled;
    public static Image movingPlatformUnscaled;
    public static Image sewerFloorUnscaled;
    public static Image streetFloorUnscaled;
    public static Image ratIdleUnscaled;
    public static Image cockroachIdleUnscaled;
    public static Image birdIdleUnscaled;
    public static Image keyUnscaled;
    public static Image lockUnscaled;
    public static Image knifeUnscaled;
    public static Image knifeInvUnscaled;

    public static Image sewerBackground;
    public static Image streetBackground;
    public static Image schoolBackground;
    public static Image movingPlatform;
    public static Image ratIdle;
    public static Image sewerFloor;
    public static Image streetFloor;
    public static Image key;
    public static Image lock;
    public static Image knife;
    public static Image knifeInv;

    public static Image cockroachIdle;
    public static Image cockroachWalking;
    public static Image ratWalking;
    public static Image ratWalkingScaled;
    public static Image knifeAttacking;
    public static Image knifeAttackingUnscaled;

    public static Image birdIdle;
    public static Image birdFlying;
    public static Image ratKnifeUnscaled;
    public static Image ratKnife;

    public static SpriteSheet rat;
    public static SpriteSheet knifeAttack;

    public static void loadImages()
    {
        try {
            sewerBackgroundUnscaled = new Image("res/Sewer_Background.png");
            streeBackgroundUnscaled = new Image("res/Street_Background.png");
            schoolBackgroundUnscaled= new Image("res/School_Background.png");
            sewerFloorUnscaled = new Image("res/Sewer_Floor.png");
            streetFloorUnscaled = new Image("res/Street_Floor2.png");
            movingPlatformUnscaled = new Image("res/Moving_Platform.png");
            ratIdleUnscaled = new Image("res/Rat_Idle1.png");
            cockroachIdleUnscaled = new Image("res/Cockroach_Idle.png");
            birdIdleUnscaled = new Image("res/Eagle.png");
            sewerFloor = new Image("res/Sewer_Floor.png");
            keyUnscaled = new Image("res/Key.png");
            lockUnscaled = new Image("res/Door.png");
            knifeUnscaled = new Image("res/Knife.png");
            ratWalking = new Image("res/Rat_Walking.png");
            ratKnifeUnscaled = new Image("res/RatKnife.png");
            knifeAttackingUnscaled = new Image("res/Knife_Attack.png");
            knifeInvUnscaled = new Image("res/Knife_Inventory.png");

            ratKnife = ratKnifeUnscaled.getScaledCopy(((int) Cell.getWidth()) * 6 / 2, (int) Cell.getHeight() * 3/2);
            sewerBackground = sewerBackgroundUnscaled.getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            streetBackground = streeBackgroundUnscaled.getScaledCopy(Main.getScreenWidth(),Main.getScreenHeight());
            schoolBackground = schoolBackgroundUnscaled.getScaledCopy(Main.getScreenWidth(),Main.getScreenHeight());
            ratIdle = ratIdleUnscaled.getScaledCopy(((int) Cell.getWidth()) * 5 / 2, ((int) Cell.getHeight()) - 5);
            movingPlatform = movingPlatformUnscaled.getScaledCopy(screenRatio);
            cockroachIdle = cockroachIdleUnscaled.getScaledCopy(((int) Cell.getWidth()) * 5 / 2, (int) Cell.getHeight());
            birdIdle = birdIdleUnscaled.getScaledCopy((int) Cell.getWidth() * 8, (int) Cell.getHeight() * 4);
            sewerFloor = sewerFloorUnscaled.getScaledCopy(screenRatio);
            streetFloor = streetFloorUnscaled.getScaledCopy(screenRatio);
            key = keyUnscaled.getScaledCopy(1.5f);
            lock = lockUnscaled.getScaledCopy(((int) Cell.getWidth()) * 2 +2, ((int) Cell.getHeight() * 2 ));
            knife = knifeUnscaled.getScaledCopy(0.5f);
            knifeAttacking = knifeAttackingUnscaled.getScaledCopy(screenRatio);
            knifeInv = knifeInvUnscaled.getScaledCopy(1.2f);
          //  ratWalkingScaled = ratWalking.getScaledCopy(screenRatio);
            rat = new SpriteSheet(ratWalking,174,48);
            knifeAttack = new SpriteSheet(knifeAttacking,156,208);


            System.out.println("Screen ratio scaling: " + screenRatio);
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}