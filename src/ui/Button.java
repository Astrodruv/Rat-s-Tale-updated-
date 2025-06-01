package ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Button {
    protected float x;
    protected float y;
    protected float w;
    protected float h;
    protected String message;
    protected Color boxColor;
    protected Color borderColor;
    protected Color messageColor;
    protected TrueTypeFont messageFont;

    public Button(float x, float y, int w, int h, String message, Color boxColor, Color borderColor, Color messageColor, TrueTypeFont messageFont){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.message = message;
        this.boxColor = boxColor;
        this.borderColor = borderColor;
        this.messageColor = messageColor;
        this.messageFont = messageFont;
    }

    public void render(Graphics g){
        float messageLength = messageFont.getWidth(message);
        float messageHeight = messageFont.getHeight(message);
        g.setColor(borderColor);
        g.fillRect(x - 5, y - 5, w + 10, h + 10);
        g.setColor(boxColor);
        g.fillRect(x,y,w,h);
        g.setColor(messageColor);
        g.setFont(messageFont);
        g.drawString(message, x + (w / 2) - (messageLength / 2), y + (h / 2) - (messageHeight / 2));
        g.resetFont();
    }

    public boolean isMouseOver(int mX, int mY){
        return mX >= x && mX < x + w && mY >= y && mY < y + h;
    }

}
