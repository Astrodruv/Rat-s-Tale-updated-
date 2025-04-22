package engine.states.start;

import engine.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.images.ImageRenderer;

public class TitleScreen extends BasicGameState {
	private int id;

	public TitleScreen(int id)
	{
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
		ImageRenderer.loadImages();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setBackground(Color.gray);
		g.setColor(Color.black);
		g.drawString("A RAT'S TALE", (float) Main.getScreenWidth() / 2, (float) Main.getScreenHeight() / 2);
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
		this.sbg.enterState(1);
	}
	
	


}
