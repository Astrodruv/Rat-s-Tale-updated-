package objects.world;


import engine.Main;
import objects.GameObject;
import objects.entities.Entity;
import objects.platforms.Platform;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Cell {
    private int x;
    private int y;
    private GameObject object;

    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getXPixel(){return (int) (x * getWidth());}

    public int getYPixel(){
        return (int) (y * getHeight());
    }


    public void setObject(GameObject o){
        object = o;
        if (o != null) object.setCell(this);

//        x = getXPixel();
//        y = getYPixel();

//        w = getWidth();
//        h = getHeight() * 1.5f;
    }

    public void render(Graphics g){
        if (object != null){
            object.render(g);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        if (object != null){
            object.update(gc, sbg, delta);
        }
    }

    public void keyPressed(int key, char c){
        if (object != null){
            object.keyPressed(key, c);
        }
    }

    public ArrayList<Cell> addCellToList(ArrayList<Cell> list, int x, int y){
        if (World.getCell(x,y) != null){
            list.add(World.getCell(x,y));
        }
        return list;
    }

    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public GameObject getObject(){return object;}
    public static float getWidth(){return (float) (Main.getScreenWidth()) / World.WIDTH;}
    public static float getHeight(){
        return (float) (Main.getScreenHeight()) / World.HEIGHT;
    }
}
