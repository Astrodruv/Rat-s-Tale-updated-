package world;

import engine.states.Game;
import objects.GameObject;
import objects.entities.Player;
import objects.entities.enemies.Bird;
import objects.entities.enemies.EvilCar;
import objects.entities.enemies.Cockroach;
import objects.entities.enemies.PassiveCar;
import objects.interactables.Door;
import objects.interactables.Key;
import objects.interactables.Weapon;
import objects.platforms.sewerPlatforms.SewerFloor;
import objects.platforms.sewerPlatforms.SewerPlatform;
import objects.platforms.streetPlatforms.StreetFloor;
import objects.platforms.streetPlatforms.StreetPlatform;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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

    private boolean enemy;

    public World() {
        cells = new Cell[WIDTH][HEIGHT];
        objects = new ArrayList<>();

        enemy = false;

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

        for (GameObject obj : objects) {
            if (obj instanceof SewerPlatform || obj instanceof SewerFloor || obj instanceof StreetFloor || obj instanceof StreetPlatform) {
                obj.render(g);
            }
        }

        for (GameObject obj : objects) {
            if (obj instanceof Key || obj instanceof Door || obj instanceof Weapon || obj instanceof PassiveCar) {
                obj.render(g);
            }
        }

        for (GameObject obj : objects) {
            if (obj instanceof Cockroach || obj instanceof Bird || obj instanceof Player || obj instanceof EvilCar) {
                obj.render(g);
            }
        }

//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                cells[i][j].render(g);
//            }
//        }
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
        objects.clear();
        for(int j = 0; j < HEIGHT; j++){
            for(int i = 0; i < WIDTH; i++){
                cells[i][j].setObject(null);
            }
        }
    }

    public void setCell(Cell cell, char code) throws SlickException {
        GameObject obj = null;

        // Characters Used: c, d, e, f, k, m, p, s, C, E, W

        if (code == 'p'){
            obj = new SewerPlatform(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'f'){
            obj = new SewerFloor(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 's'){
            obj = new StreetFloor(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'm'){
            obj = new StreetPlatform(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }

        if (code == 'P'){
            obj = new Player(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'C'){
            obj = new Cockroach(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'E'){
            obj = new Bird(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if(code == 'e'){
            obj = new EvilCar(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }

        if (code == 'k'){
            obj = new Key(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if(code == 'd') {
            obj = new Door(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'W'){
            obj = new Weapon(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }
        if (code == 'c'){
            obj = new PassiveCar(cell.getX() * Cell.getWidth(), cell.getY() * Cell.getHeight());
        }

        if (obj != null){
            addObject(obj, cell.getX(), cell.getY());
            Game.levelObjects.add(obj);
        } else{
            cell.setObject(null);
        }
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

