package objects.healthbars;

import objects.GameObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;

public abstract class HealthBar {
    protected float x;
    protected float y;
    protected float w;
    protected float h;
    protected Color color1;
    protected Color color2;

    public HealthBar(float x, float y, float w, float h, Color color1, Color color2){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color1 = color1;
        this.color2 = color2;
    }

}
