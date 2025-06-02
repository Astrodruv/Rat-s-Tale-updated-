package ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Message {
    protected float x;
    protected float y;
    protected String message;
    protected Color messageColor;
    protected TrueTypeFont messageFont;

    public Message(float x, float y, String message, Color messageColor, TrueTypeFont messageFont){
        this.x = x;
        this.y = y;
        this.message = message;
        this.messageColor = messageColor;
        this.messageFont = messageFont;
    }

    public void render(Graphics g){
        g.setColor(messageColor);
        g.setFont(messageFont);
        g.drawString(message, x, y);
        g.resetFont();
    }

}
