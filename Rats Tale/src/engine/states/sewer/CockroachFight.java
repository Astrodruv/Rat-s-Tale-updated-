//package engine.states.sewer;
//
//import engine.Main;
//import objects.GameObject;
//import objects.entities.enemy.boss.attacking.Cockroach;
//import objects.entities.player.Player;
//import objects.healthbars.CockroachHealthBar;
//import objects.healthbars.PlayerHealthBar;
//import objects.platforms.gamePlatforms.SewerPlatform;
//import org.newdawn.slick.*;
//import org.newdawn.slick.state.BasicGameState;
//import org.newdawn.slick.state.StateBasedGame;
//import ui.images.ImageRenderer;
//
//import java.util.ArrayList;
//
//public class CockroachFight extends BasicGameState
//{
//	public static ArrayList<GameObject> levelObjects;
//
//	private int id;
//	private static Player player;
//	private static PlayerHealthBar playerHealthBar;
//	private Image background;
//	private static SewerPlatform platform1;
//	private static SewerPlatform platform2;
//	private Cockroach cockroach;
//	private static CockroachHealthBar cockroachHealthBar;
//
//	public static GameContainer gc;
//	StateBasedGame sbg;
//
//	public CockroachFight(int id) {
//		this.id = id;
//		ArrayList<Object> levelObjects = new ArrayList<>();
//	}
//
//	public int getID()
//	{
//		return id;
//	}
//
//	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
//	{
////		player = Sewer.myPlayer();
////		playerHealthBar = Sewer.myPlayerHealthBar();
//////		background = ImageRenderer.sewerBackground;
////		cockroach = new Cockroach();
////		cockroachHealthBar = new CockroachHealthBar();
////		platform1 = new SewerPlatform(0,Main.getScreenHeight() / 2);
////		platform2 = new SewerPlatform(Main.getScreenWidth() - 100,Main.getScreenHeight() / 2);
////
////		levelObjects.add(player);
////		levelObjects.add(cockroach);
//	}
//
//	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
//	{
//		player.update(gc, sbg, delta);
//		cockroach.update(gc);
//	}
//
//	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
//	{
////		g.setBackground(Color.white);
//////		background.draw();
////		player.render(g);
////		cockroach.render(g);
////
////		playerHealthBar.render(g);
////		cockroachHealthBar.render(g);
////
////		platform1.render(g);
////		platform2.render(g);
//	}
//
//	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
//	{
//		// This code happens when you enter a gameState.
//	}
//
//	public void leave(GameContainer gc, StateBasedGame sbg)
//	{
//		// This code happens when you leave a gameState.
//	}
//
//	public void keyPressed(int key, char c)
//	{
//		// This code happens every time the user presses a key
//	}
//
//	public void mousePressed(int button, int x, int y)
//	{
//		// This code happens every time the user presses the mouse
//	}
//
//
//
//
//}
