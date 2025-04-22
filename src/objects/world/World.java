package objects.world;

import engine.Game;
import objects.GameObject;
import objects.entities.enemy.boss.attacking.Cockroach;
import objects.entities.player.Player;
import objects.interactables.Key;
import objects.interactables.Lock;
import objects.platforms.Platform;
import objects.platforms.gamePlatforms.SewerFloor;
import objects.platforms.gamePlatforms.SewerPlatform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class World {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 15;

    public static String level;

    public static Cell[][] cells;
    public static ArrayList<GameObject> objects;

    public World() {
        cells = new Cell[WIDTH][HEIGHT];
        objects = new ArrayList<>();

        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                cells[i][j] = new Cell(i,j);
            }
        }
    }

    public static Cell getCell(int x, int y){
        return cells[x][y];
    }

    public void render(Graphics g){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j].render(g);
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j].update(gc, sbg, delta);
            }
        }

        if (Game.changeLevels){
            clearFile();
            readFile(level);
            Game.changeLevels = false;
        }
    }

    public void keyPressed(int key, char c){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                cells[i][j].keyPressed(key, c);
            }
        }
    }

    public static void addObject(GameObject o, int x, int y){
        objects.add(o);
        cells[x][y].setObject(o);
    }

    public void clearFile() {
        Game.levelObjects.clear();
        for(int j = 0; j < HEIGHT; j++){
            for(int i = 0; i < WIDTH; i++){
                cells[i][j].setObject(null);
            }
        }
    }

    public void setCell(Cell cell, char code) throws SlickException {
        GameObject obj = null;

        if (code == 'p'){
            obj = new SewerPlatform(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'f'){
            obj = new SewerFloor(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'P'){
            obj = new Player(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'C'){
            obj = new Cockroach(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'k'){
            obj = new Key(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'l'){
            obj = new Lock(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }

        if (obj != null){
            addObject(obj, cell.getX(), cell.getY());
            Game.levelObjects.add(obj);
        } else{
            cell.setObject(null);
        }

//        if (code == 'p'){
//            SewerPlatform s = new SewerPlatform(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
//            addObject(s, cell.getX(), cell.getY());
//            Game.levelObjects.add(s);
//        }
//        if (code == 'f'){
//            SewerFloor s = new SewerFloor(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
//            addObject(s, cell.getX(), cell.getY());
//            Game.levelObjects.add(s);
//        }
//        if (code == 'P'){
//            Player p = new Player(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
//            addObject(p, cell.getX(), cell.getY());
//            Game.levelObjects.add(p);
//        }

    }

    public void readFile(String s){
        try{
            File mapFile = new File(s);
            Scanner scan = new Scanner(mapFile);

            for(int j = 0; j < HEIGHT; j++){
                String row = scan.nextLine();

                for(int i = 0; i < WIDTH; i++){
                    char input = row.charAt(i);
                    setCell(cells[i][j], input);
                }
            }
            scan.close();
        }
        catch (FileNotFoundException | SlickException e){
            System.out.println("File not found");
        }
    }

}

