package ui.text;

import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class Fonts {
    public static TrueTypeFont big;
    public static TrueTypeFont medium;
    public static void loadFonts(){
        medium = new TrueTypeFont(new Font("Verdana",Font.ITALIC,42),false);
        big = new TrueTypeFont(new Font("Verdana", Font.BOLD, 42), false);
    }

}
