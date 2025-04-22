package engine;

import engine.states.start.TitleScreen;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame 
{
	public final static int FRAMES_PER_SECOND = 60;
	private static AppGameContainer appgc;
	
    public static final int TITLE_ID  = 0;
	public static final int GAME_ID  = 1;
//	public static final int SEWER_ID  = 1;
//	public static final int COCKROACH_FIGHT_ID  = 2;
//	public static final int PARKING_LOT_ID  = 3;
//	public static final int BIRD_FIGHT_ID  = 4;
//	public static final int CROSSYROAD_ID  = 5;
//	public static final int JANITORS_CLOSET_ID  = 6;
//	public static final int SNEAK_ID  = 7;
//	public static final int CLASSROOM_ID  = 8;
//	public static final int TEACHER_FIGHT  = 9;
//	public static final int GYM_ID  = 10;
//	public static final int DODGEBALL_FIGHT_ID  = 11;
//	public static final int MAZE_ID  = 12;
//	public static final int CAFETERIA_ID  = 13;
//	public static final int CAFETERIA_FIGHT_ID  = 14;
//	public static final int END_ID  = 15;

	private BasicGameState title;
	private BasicGameState game;
//	private BasicGameState sewer1.txt;
//	private BasicGameState cockroachFight;
//	private BasicGameState parkingLot;
//	private BasicGameState birdFight;
//	private BasicGameState crossyRoad;
//	private BasicGameState janitorsCloset;
//	private BasicGameState sneak;
//	private BasicGameState classroom;
//	private BasicGameState teacherFight;
//	private BasicGameState gym;
//	private BasicGameState dodgeballFight;
//	private BasicGameState maze;
//	private BasicGameState cafeteria;
//	private BasicGameState cafeteriaFight;
//	private BasicGameState end;

	public Main(String name) 
	{
		super(name);
		title = new TitleScreen(TITLE_ID);
		game = new Game(GAME_ID);
//		sewer1.txt = new Sewer(SEWER_ID);
//		cockroachFight = new CockroachFight(COCKROACH_FIGHT_ID);
	}

	public static int getScreenWidth()
	{
		return appgc.getScreenWidth();
	}
	
	public static int getScreenHeight()
	{
		return appgc.getScreenHeight();
	}
	

	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(title);
		addState(game);
	}

	public static void main(String[] args) 
	{
		try 
		{
			appgc = new AppGameContainer(new Main("A Rat's Rale"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
			appgc.setTargetFrameRate(FRAMES_PER_SECOND);
			appgc.setVSync(true);
			appgc.start();

		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}

	}
}