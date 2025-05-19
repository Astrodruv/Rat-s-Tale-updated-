package engine.states;

import engine.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;

public class EndingScreen extends BasicGameState {
    private int id;

    public EndingScreen(int id) {
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
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.setBackground(Color.black);
        g.setColor(Color.white);
        g.drawString("Yay you won", (float) Main.getScreenWidth() / 2, (float) Main.getScreenHeight() / 2);
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