package engine.states;

import engine.Main;
import healthbars.*;
import objects.GameObject;
import objects.entities.Player;
import objects.entities.enemies.Bird;
import objects.entities.enemies.Chef;
import objects.entities.enemies.Cockroach;
import objects.entities.enemies.Janitor;
import objects.interactables.Food;
import objects.weapons.Knife;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.Button;
import ui.Fonts;
import ui.Message;
import values.*;
import world.Cell;
import world.World;
import ui.Images;

import java.util.ArrayList;

public class Game extends BasicGameState {
	public boolean pause;

	public static GameContainer gc;
	StateBasedGame sbg;
	private int id;

	private Message pauseMessage;
	private Button controlsButton;
	private Button startOverButton;
	private Button settingsButton;
	private Button goBackButton;
	private Button pauseButton;
	private Button unpauseButton;
	private Button deathButton;

	private ArrayList<Message> instructions;

	private int pauseScreenType;

	public static ArrayList<GameObject> levelObjects;
	public static boolean changeLevels;

	private World world;

	private static Player player;
	private static Cockroach cockroach;
	private static Bird bird;
	private static Janitor janitor;
	private static Chef chef;

	private static Knife knife;

	private static PlayerHealthBar playerHealthBar;
	private static CockroachHealthBar cockroachHealthBar;
	private static BirdHealthBar birdHealthBar;
	private static JanitorHealthBar janitorHealthBar;
	private static ChefHealthBar chefHealthBar;

	private static float speedrunTimer;

	public Image knifeDisplay;

	public Game(int id) {
		this.id = id;
		levelObjects = new ArrayList<>();
		changeLevels = true;
		pause = false;
	}

	public int getID() {
		return id;
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
		gc.setShowFPS(false);

		world = new World();

		instructions = new ArrayList<>();

		pauseScreenType = 0;

		pauseMessage = new Message(((float) Main.getScreenWidth() / 2) - ((float) Fonts.instructionAndLorePageFont.getWidth("GAME PAUSED") / 2), Cell.getHeight(), "GAME PAUSED", Color.yellow, Fonts.instructionAndLorePageFont);
		deathButton = new Button(((float) Main.getScreenWidth() / 1.2f) - (Cell.getWidth() * 20), ((float) Main.getScreenHeight() / 1.5f) - Cell.getHeight(), (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Restart Section", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
		controlsButton = new Button(((float) Main.getScreenWidth() / 2) - (Cell.getWidth() * 5), ((float) Main.getScreenHeight() / 2) - Cell.getHeight(), (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Controls", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
		startOverButton = new Button(((float) Main.getScreenWidth() / 2) - (Cell.getWidth() * 20), ((float) Main.getScreenHeight() / 2) - Cell.getHeight(), (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Restart Game", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
		settingsButton = new Button(((float) Main.getScreenWidth() / 2) + (Cell.getWidth() * 10), ((float) Main.getScreenHeight() / 2) - Cell.getHeight(), (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Settings", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
		goBackButton = new Button(Main.getScreenWidth() - (Cell.getWidth() * 13), Cell.getHeight(), (int) Cell.getWidth() * 10, (int) Cell.getHeight() * 2, "Back", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.titleScreenButtonFont);
		pauseButton = new Button(Cell.getWidth() / 2, Main.getScreenHeight() - (Cell.getHeight() / 2) - (Cell.getHeight() / 4), (int) Cell.getWidth() * 6, (int) Cell.getHeight() / 2, "Pause", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.messageFont);
		unpauseButton = new Button(Cell.getWidth() / 2, Main.getScreenHeight() - (Cell.getHeight() / 2) - (Cell.getHeight() / 4), (int) Cell.getWidth() * 6, (int) Cell.getHeight() / 2, "Unpause", new Color(217, 140, 0), Color.black, Color.yellow, Fonts.messageFont);

		setLevel("levels/sewer1.txt"); // debug
		PlayerValues.section = 0; // debug
		PlayerValues.doesPlayerHaveKnife = false; // debug

		knifeDisplay = Images.knifeInv;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (!pause) {
			if (!Player.gameOver && PlayerValues.section != 6) {
				world.update(gc, sbg, delta);
				speedrunTimer++;
			}

			if (PlayerValues.doesPlayerHaveKnife && knife == null) {
				if (player != null) {
					System.out.println("the");
					knife = new Knife(player);
				}
			}

			if (knife != null) knife.update(gc, sbg, delta);

			for (GameObject o : levelObjects) {
				if (o instanceof Food) {
					o.update(gc, sbg, delta);
				}
			}

			updateHealthBars();
			levelObjects.removeIf(GameObject::isRemoved);


//		if (World.level.equals(("levels/school.txt"))) {
//			this.sbg.enterState(Main.END_ID);
//		}
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
			} else if (o instanceof Janitor && World.level.equals(JanitorValues.LEVEL_SPAWN_LOCATION)) {
				janitor = (Janitor) o;
				if (janitorHealthBar == null || janitorHealthBar.getEntity() != o) {
					janitorHealthBar = new JanitorHealthBar(janitor);
				}
			} else if (o instanceof Chef && World.level.equals(ChefValues.LEVEL_SPAWN_LOCATION)) {
				chef = (Chef) o;
				if (chefHealthBar == null || chefHealthBar.getEntity() != o) {
					chefHealthBar = new ChefHealthBar(chef);
				}
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (!Player.gameOver) {
			if (PlayerValues.section == 0) {
				g.drawImage(Images.sewerBackground, 0, 0);
			} else if (PlayerValues.section == 1) {
				g.drawImage(Images.streetBackground, 0, 0);
			} else if (PlayerValues.section == 2) {
				g.drawImage(Images.streetBackground, 0, 0);
			} else if (PlayerValues.section == 3) {
				g.drawImage(Images.cafeteriaBackground, 0, 0);
			} else if (PlayerValues.section == 4) {
				g.drawImage(Images.cafeteriaBackground, 0, 0);
			}else if (PlayerValues.section == 5) {
				g.drawImage(Images.schoolBackground, 0, 0);
			}else if (PlayerValues.section == 6) {
				g.drawImage(Images.winScreen, 0, 0);
			}
//			} else if (PlayerValues.section == 5) {
//				g.drawImage(Images.schoolBackground, 0, 0);
//			} else if (PlayerValues.section == 6) {
//				g.drawImage(Images.winScreen, 0, 0);
//			}
			//each level has end of level screen to unlock new ability/weapon? (ex sewer 4)
			world.render(g);
			if (knife != null) {
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
			if (janitorHealthBar != null) {
				if (World.level.equals(JanitorValues.LEVEL_SPAWN_LOCATION)) {
					janitorHealthBar.render(g);
				}
			}
			if (chefHealthBar != null) {
				if (World.level.equals(ChefValues.LEVEL_SPAWN_LOCATION)) {
					chefHealthBar.render(g);
				}
			}
			if (PlayerValues.doesPlayerHaveKnife && PlayerValues.section != 6) {
				knifeDisplay.draw(Cell.getHeight(), Cell.getHeight() * 1.5f);
			}

			for (GameObject o : levelObjects) {
				if (o instanceof Food) {
					o.render(g);
				}
			}

			pauseButton.render(g);

			g.setColor(Color.yellow);
			g.setFont(Fonts.messageFont);
			g.drawString(String.format("%.3f", speedrunTimer / 60), Main.getScreenWidth() - Cell.getWidth() * 2, Cell.getHeight());
		}

		if (pause) {
			instructions.clear();
			instructions.add(new Message(Cell.getWidth(), Cell.getHeight(), "Movement Controls:", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 2, "W - Jump:", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 3, "A - Move Left", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 4, "D - Move Right", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 5, "S - Dash", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));

			instructions.add(new Message(Cell.getWidth(), Cell.getHeight() * 6, "Attacking Controls: ", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 7, "Jump on Enemies to Damage Them", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			if (!PlayerValues.doesPlayerHaveKnife)
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 8, "Space - Use Knife - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			else
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 8, "Space - Use Knife", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			if (!PlayerValues.doesPlayerHaveGlock)
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 9, "Space - Use Glock - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			else
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 9, "Space - Use Glock", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));

			instructions.add(new Message(Cell.getWidth(), Cell.getHeight() * 10, "Hotkeys:", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			if (!PlayerValues.doesPlayerHaveKnife)
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 11, "1 - Equip Knife - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			else
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 11, "1 - Equip Knife", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			if (!PlayerValues.doesPlayerHaveGlock)
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 12, "2 - Equip Glock - LOCKED", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			else
				instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 12, "2 - Equip Glock", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));
			instructions.add(new Message(Cell.getWidth() * 2, Cell.getHeight() * 13, "P - Pause & Settings", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));

			instructions.add(new Message(Cell.getWidth(), Cell.getHeight() * 14, "Collect the key or beat the boss to unlock the door and progress to the next level!", new Color(245, 245, 0), Fonts.instructionAndLorePageFont));

			g.drawImage(Images.loreInstructionsBackground, 0, 0); // Can change later

			if (pauseScreenType == 0) {
				pauseMessage.render(g);
				controlsButton.render(g);
				startOverButton.render(g);
				settingsButton.render(g);
				unpauseButton.render(g);
			}
			if (pauseScreenType == 1) {
				goBackButton.render(g);
				for (Message m : instructions) {
					m.render(g);
				}
			}
			if (pauseScreenType == 2) {
				goBackButton.render(g);
			}
		}

		if (Player.gameOver) {
			g.drawImage(Images.deathScreen, 0, 0);
			deathButton.render(g);
			g.setFont(Fonts.titleScreenButtonFont);
//		g.drawString("Space to restart", Main.getScreenWidth()/3, Main.getScreenHeight()/2);
		}
	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		speedrunTimer = 0;
	}

	public void leave(GameContainer gc, StateBasedGame sbg) {

	}

	public void keyPressed(int key, char c) {
		if (!Player.gameOver && !pause) {
			world.keyPressed(key, c);
		}

		if (key == Input.KEY_P) {
			pause = !pause;
		}
	}

	public void mousePressed(int button, int x, int y) {
		if (goBackButton.isMouseOver(x, y)) pauseScreenType = 0;
		if (unpauseButton.isMouseOver(x, y) && pause) {
			pause = false;
		} else if (pauseButton.isMouseOver(x, y) && !pause) {
			pauseScreenType = 0;
			pause = true;
		}
		if (controlsButton.isMouseOver(x, y)) pauseScreenType = 1;
		if (settingsButton.isMouseOver(x, y)) pauseScreenType = 2;
		if (startOverButton.isMouseOver(x, y)) {
			PlayerValues.section = 0;
			PlayerValues.isPlayerTouchingKey = false;
			PlayerValues.isPlayerHurtingEnemy = false;
			PlayerValues.doesPlayerHaveKnife = false;
			PlayerValues.isPlayerHoldingKnife = false;
			PlayerValues.isPlayerUsingKnife = false;
			PlayerValues.doesPlayerHaveDash = false;
			PlayerValues.doesPlayerHaveGlock = false;
			pause = false;
			setLevel("levels/sewer1.txt");
			PlayerValues.isPlayerHoldingKnife = false;
			this.sbg.enterState(Main.TITLE_ID);
		}
		if (deathButton.isMouseOver(x, y) && Player.gameOver) {
			if (PlayerValues.section == 0) {
				setLevel("levels/sewer1.txt");
				Player.gameOver = false;
				PlayerValues.isPlayerTouchingKey = false;
			}
			if (PlayerValues.section == 1) {
				setLevel("levels/street1.txt");
				Player.gameOver = false;
				PlayerValues.isPlayerTouchingKey = false;

			}
			if (PlayerValues.section == 2) {
				setLevel("levels/closet1.txt");
				Player.gameOver = false;
				PlayerValues.isPlayerTouchingKey = false;
			}
			if (PlayerValues.section == 3) {
				setLevel("levels/classroom1.txt");
				Player.gameOver = false;
				PlayerValues.isPlayerTouchingKey = false;
			}
			if (PlayerValues.section == 4) {
				setLevel("levels/cafeteria.txt");
				Player.gameOver = false;
				PlayerValues.isPlayerTouchingKey = false;
			}
		}
	}

	public static PlayerHealthBar getPlayerHealthBar() {
		return playerHealthBar;
	}

	public static Player getPlayer() {
		return player;
	}

	public static float getSpeedrunTimer() {
		return speedrunTimer;
	}

	public static void setLevel(String s) {
		levelObjects.clear();
		World.level = s;
		player = null;
		knife = null;
		changeLevels = true;
	}
}