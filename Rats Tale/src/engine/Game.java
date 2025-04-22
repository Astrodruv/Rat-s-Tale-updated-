package engine;

import objects.GameObject;
import objects.entities.player.Player;
import objects.healthbars.CockroachHealthBar;
import objects.healthbars.PlayerHealthBar;
import objects.platforms.gamePlatforms.SewerPlatform;
import objects.world.World;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

import java.util.ArrayList;

public class Game extends BasicGameState
{
    public static ArrayList<GameObject> levelObjects;
    private int id;

    public static boolean changeLevels;
    private World world;
    private static Player player;
    private static PlayerHealthBar playerHealthBar;
    private static CockroachHealthBar cockroachHealthBar;
    public static GameContainer gc;
    StateBasedGame sbg;

    public Game(int id) {
        this.id = id;
        levelObjects = new ArrayList<>();
        changeLevels = true;
    }

    public int getID()
    {
        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        this.gc = gc;
        this.sbg = sbg;
        gc.setShowFPS(true);
        setLevel("levels/sewer1.txt");

        world = new World();
        playerHealthBar = new PlayerHealthBar();
        cockroachHealthBar = new CockroachHealthBar();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        world.update(gc, sbg, delta);
        playerHealthBar.update(gc, sbg, delta);
        cockroachHealthBar.update(gc, sbg, delta);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.drawImage(ImageRenderer.sewerBackground, 0, 0);
        world.render(g);
        playerHealthBar.render(g);
        cockroachHealthBar.render(g);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {

    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c)
    {
        world.keyPressed(key, c);
    }

    public void mousePressed(int button, int x, int y) {

    }

    public static Player myPlayer(){
        return player;
    }

    public static PlayerHealthBar myPlayerHealthBar(){
        return playerHealthBar;
    }

    public static void setLevel(String s){
        levelObjects.clear();
        World.level = s;
        changeLevels = true;
    }

}
