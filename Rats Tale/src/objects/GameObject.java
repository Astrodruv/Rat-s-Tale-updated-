package objects;

import objects.world.Cell;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

import java.util.ArrayList;

public abstract class GameObject {
    protected Cell cell;
    protected Image image;
    protected float x;
    protected float y;
    protected float w;
    protected float h;

    public GameObject(float x, float y){
        w = Cell.getWidth();
        h = Cell.getHeight();
    }

    public void setCell(Cell c){
        this.cell = c;
    }

    public void render(Graphics g){
        float cellW = Cell.getWidth();
        float cellH = Cell.getHeight();
        if (image != null) {
            image.draw(cell.getX() * cellW, cell.getY() * cellH, cellW, cellH * 1.5f); //- cellH / 2
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){

    }

    public void keyPressed(int key, char c){

    }

    public abstract void collisions(StateBasedGame sbg);

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getW(){
        return w;
    }

    public float getH(){
        return h;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,w,h);
    }

}
