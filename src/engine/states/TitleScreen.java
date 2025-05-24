package engine.states;

import engine.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;

public class TitleScreen extends BasicGameState {
    private int id;

    public TitleScreen(int id) {
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

    public static GameContainer gc;
    StateBasedGame sbg;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        this.gc = gc;
        this.sbg = sbg;
        Images.loadImages();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.drawImage(Images.titleScreen, 0, 0);
        g.setColor(Color.black);
        g.drawString("WASD TO MOVE", (float) Main.getScreenWidth() / 2 + 200, (float) Main.getScreenHeight() / 2);
        g.drawString("JUMP ON ENEMIES TO KILL THEM", (float) Main.getScreenWidth() / 2 + 200, (float) Main.getScreenHeight() / 2 + 20);
        g.drawString("WHEN YOU GET YOUR WEAPON PRESS SPACE TO USE IT", (float) Main.getScreenWidth() / 2 + 200, (float) Main.getScreenHeight() / 2 + 40);
        g.drawString("COLLECT THE KEY TO OPEN THE DOOR", (float) Main.getScreenWidth() / 2 + 200, (float) Main.getScreenHeight() / 2 + 60);
        g.drawString("KILLING ENEMIES CAN ALSO OPEN THE DOOR", (float) Main.getScreenWidth() / 2 + 200, (float) Main.getScreenHeight() / 2 + 80);
        g.drawString("WALK INTO THE DOOR WITH THE KEY TO OPEN IT", (float) Main.getScreenWidth() / 2 + 200, (float) Main.getScreenHeight() / 2 + 100);
        g.drawString("CLICK TO START GAME", (float) Main.getScreenWidth() / 2 + 200, (float) Main.getScreenHeight() / 2 + 120);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        // This code happens when you enter a gameState.
    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c)
    {
        // This code happens every time the user presses a key
    }

    public void mousePressed(int button, int x, int y)
    {
        this.sbg.enterState(Main.GAME_ID);
    }




}
