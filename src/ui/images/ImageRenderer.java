package ui.images;

import engine.Main;
import objects.world.Cell;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageRenderer
{
    public static final float screenRatio = 1 / ((float) (Main.getScreenWidth() * Main.getScreenHeight()) / 1000000);

    public static Image sewerBackgroundUnscaled;
    public static Image movingPlatformUnscaled;
    public static Image sewerFloorUnscaled;
    public static Image ratIdleUnscaled;
    public static Image cockroachIdleUnscaled;
    public static Image keyUnscaled;
    public static Image lockUnscaled;

    public static Image sewerBackground;
    public static Image movingPlatform;
    public static Image ratIdle;
    public static Image sewerFloor;
    public static Image key;
    public static Image lock;

    public static Image cockroachIdle;
    public static Image cockroachWalking;

    public static void loadImages()
    {
        try {
            sewerBackgroundUnscaled = new Image("res/Sewer_Background.png");
            sewerFloorUnscaled = new Image("res/Sewer_Floor.png");
            movingPlatformUnscaled = new Image("res/Moving_Platform.png");
            ratIdleUnscaled = new Image("res/Rat_Idle1.png");
            cockroachIdleUnscaled = new Image("res/Cockroach_Idle.png");
            sewerFloor = new Image("res/Sewer_Floor.png");
            keyUnscaled = new Image("res/Key.png");
            lockUnscaled = new Image("res/Lock.png");

            sewerBackground = sewerBackgroundUnscaled.getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
            ratIdle = ratIdleUnscaled.getScaledCopy(((int) Cell.getWidth()) * 5 / 2, ((int) Cell.getHeight()) - 5);
            movingPlatform = movingPlatformUnscaled.getScaledCopy(screenRatio);
            cockroachIdle = cockroachIdleUnscaled.getScaledCopy(((int) Cell.getWidth()) * 5 / 2, ((int) Cell.getHeight()));
            sewerFloor = sewerFloorUnscaled.getScaledCopy(screenRatio);
            key = keyUnscaled.getScaledCopy(1);
            lock = lockUnscaled.getScaledCopy(((int) Cell.getWidth()) * 2, ((int) Cell.getHeight()));


            System.out.println("Screen ratio scaling: " + screenRatio);
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}