package ui;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

public class Fonts{
    public static TrueTypeFont titleScreenButtonFont;
    public static TrueTypeFont instructionAndLorePageFont;
    public static TrueTypeFont messageFont;

    public static void loadFonts(){
        titleScreenButtonFont = new TrueTypeFont(new Font("Microsoft JhengHei", Font.BOLD, 47), false);
        instructionAndLorePageFont = new TrueTypeFont(new Font("Microsoft JhengHei", Font.PLAIN, 36), false);
        messageFont = new TrueTypeFont(new Font("Arial", Font.BOLD, 20), false);
    }

}