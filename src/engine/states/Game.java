package engine.states;

import engine.Main;
import healthbars.BirdHealthBar;
import healthbars.CockroachHealthBar;
import healthbars.PlayerHealthBar;
import objects.GameObject;
import objects.entities.Entity;
import objects.entities.Player;
import objects.entities.enemies.Bird;
import objects.entities.enemies.Cockroach;
import objects.interactables.Weapon;
import objects.weapons.Knife;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import values.BirdValues;
import values.CockroachValues;
import values.PlayerValues;
import world.Cell;
import world.World;
import ui.Images;

import java.util.ArrayList;

public class Game extends BasicGameState
{
	public static ArrayList<GameObject> levelObjects;
	public static boolean changeLevels;

	private World world;

	private static Player player;
	private static Cockroach cockroach;
	private static Bird bird;

	private static Knife knife;

	private static PlayerHealthBar playerHealthBar;
	private static CockroachHealthBar cockroachHealthBar;
	private static BirdHealthBar birdHealthBar;

	public static Image knifeDisplay;

	public static GameContainer gc;
	StateBasedGame sbg;
	private int id;

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
		gc.setShowFPS(false);

		world = new World();

		setLevel("levels/sewer4.txt"); // debug
//		PlayerValues.section = 1; // debug
//		PlayerValues.doesPlayerHaveWeapon = true; // debug

		knifeDisplay = Images.knifeInv;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		if (!Player.gameOver) {
			world.update(gc, sbg, delta);
		}

		if (PlayerValues.doesPlayerHaveWeapon && knife == null) {
			if (player != null) {
				System.out.println("the");
				knife = new Knife(player);
			}
		}

		if (knife != null) knife.update(gc, sbg, delta);

		updateHealthBars();

		if (World.level.equals(("levels/school.txt"))) {
			this.sbg.enterState(Main.END_ID);
		}
	}

	public void updateHealthBars() {
		for (GameObject o : levelObjects) {
			if (o instanceof Player) {
				player = (Player) o;
				if (playerHealthBar == null || playerHealthBar.getEntity() != o) {
					playerHealthBar = new PlayerHealthBar(player);
				}
			} else if (o instanceof Cockroach && World.level.equals(CockroachValues.LEVEL_SPAWN_LOCATION)) {
				cockroach = (Cockroach) o;
				if (cockroachHealthBar == null || cockroachHealthBar.getEntity() != o) {
					cockroachHealthBar = new CockroachHealthBar(cockroach);
				}
			} else if (o instanceof Bird && World.level.equals(BirdValues.LEVEL_SPAWN_LOCATION)) {
				bird = (Bird) o;
				if (birdHealthBar == null || birdHealthBar.getEntity() != o) {
					birdHealthBar = new BirdHealthBar(bird);
				}
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.setColor(Color.white);
		g.drawString("x: " + player.getX(), 400, 500);
        g.drawString("y: " + player.getY(), 400, 550);

		if (!Player.gameOver) {
			if(PlayerValues.section == 1){
				g.drawImage(Images.streetBackground,0,0);
			} else{
				g.drawImage(Images.sewerBackground, 0, 0);
			}
			//each level has end of level screen to unlock new ability/weapon? (ex sewer 4)
			world.render(g);
			if (knife != null){
				knife.render(g);
			}
			if (playerHealthBar != null) {
				playerHealthBar.render(g);
			}
			if (cockroachHealthBar != null) {
				if (World.level.equals(CockroachValues.LEVEL_SPAWN_LOCATION)) {
					cockroachHealthBar.render(g);
				}
			}
			if (birdHealthBar != null) {
				if (World.level.equals(BirdValues.LEVEL_SPAWN_LOCATION)) {
					birdHealthBar.render(g);
				}
			}
		}

		if (PlayerValues.doesPlayerHaveWeapon) {
			knifeDisplay.draw(Cell.getHeight(), Cell.getHeight() * 1.5f);
		}

		if (Player.gameOver){
			g.setColor(Color.black);
			g.drawRect(0,0, Main.getScreenWidth(), Main.getScreenHeight());
			g.setColor(Color.yellow);
			g.drawString("YOU DIED", (float) Main.getScreenWidth() / 2, (float) Main.getScreenHeight() / 2);
			g.drawString("Press any key to continue", (float) Main.getScreenWidth() / 2, (float) Main.getScreenHeight() / 2 + 25);
		}

	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
	{

	}

	public void leave(GameContainer gc, StateBasedGame sbg)
	{

	}

	public void keyPressed(int key, char c)
	{
		if (!Player.gameOver) {
			world.keyPressed(key, c);
		}
		else{
			if (key >= 0){
				if (PlayerValues.section == 0){
					setLevel("levels/sewer1.txt");
					Player.gameOver = false;
				}
				if (PlayerValues.section == 1){
					setLevel("levels/street1.txt");
					Player.gameOver = false;
				}
			}
		}
	}

	public void mousePressed(int button, int x, int y) {

	}

	public static PlayerHealthBar getPlayerHealthBar(){
		return playerHealthBar;
	}

	public static Player getPlayer(){
		return player;
	}

	public static void setLevel(String s){
		levelObjects.clear();
		World.level = s;
		player = null;
		knife = null;
		changeLevels = true;
	}

}