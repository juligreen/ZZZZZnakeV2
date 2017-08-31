package de.is2.ZZZZZnake;

import java.awt.Point;
import java.util.Arrays;

import de.is2.ZZZZZnake.GameObjects.Door;
import de.is2.ZZZZZnake.GameObjects.Gold;
import de.is2.ZZZZZnake.GameObjects.Player;
import de.is2.ZZZZZnake.GameObjects.Snake;
import de.is2.ZZZZZnake.GameObjects.Teleporter;
import de.is2.ZZZZZnake.Utility.IGUI;
import de.is2.ZZZZZnake.Utility.TeleporterSelection;

public class ZZZZZnake {

	private Player player = new Player();
	private Door door = new Door();
	private Gold gold = new Gold();
	private Snake snake = new Snake();
	private Teleporter[] teleporters;
	final private Point[] teleporterPositions;
	private int loopCountAfterGoldGet = 0;
	private int loopCount = 0;

	private int numberOfTeleporters;
	private final String victoryMessage = "You win!";
	private final String deathMessage = "You died a pitiful and gruesome death...";
	private final String goldGetMessage = "With tha money you can exit tha door";
	private final String newLine = System.getProperty("line.separator");
	private final String startmessage1 = "You are the black rectangle, the snake is the green rectangles, the geolden rectangle is the gold, the brown rectangle is the exit door and the violet rectangles are teleporters which connect with each other.";
	private final String startmessage2 = "You control by pressing the arrow keys";
	private final String startmessage3 = "Get the gold to exit trough the door before the snake eats you!";
	private final String startmessage4 = "Getting the gold makes the Snake freeze for 3 turns.";
	private boolean dead = false;
	private boolean victory = false;
	private boolean rich = false;
	private final IGUI gui;
	private final String teleporterBehaviour;

	public ZZZZZnake(final IGUI gui, final String teleporterBehaviour) {
		this.gui = gui;
		player.randomPositionUntilDistanceSmallerThan(15, door);
		snake.randomPositionUntilDistanceBiggerThan(10, door);
		this.numberOfTeleporters = gui.teleporterNumberInput();
		this.teleporterBehaviour = teleporterBehaviour;
		Teleporter[] teleporters = new Teleporter[numberOfTeleporters];
		for (int count = 0; count < numberOfTeleporters; count++) {
			teleporters[count] = new Teleporter();
		}
		this.teleporters = teleporters;
		this.teleporterPositions = new Point[numberOfTeleporters];
		for (int count = 0; count < numberOfTeleporters; count++) {
			teleporterPositions[count] = this.teleporters[count].getPosition();
		}
	}

	public void runMovement(ZZZZZnake znake, String movementDirection) {

		if (loopCount > 0) {
			player.move(movementDirection, getPlayer());

		}

		if (!rich) {
			snake.snakeMovementTowards(player);
		} else {
			if (loopCountAfterGoldGet >= 2) {
				snake.snakeMovementTowards(player);
			}
			loopCountAfterGoldGet++;
		}

		if (znake.isRich() && getPlayer().getPosition().equals(znake.getDoor().getPosition())) {
			setVictory(true);
		}
		if (Arrays.asList(znake.getTeleporterPositions()).contains(znake.getPlayer().getPosition())) {
			final TeleporterSelection teleporterSelection = new TeleporterSelection();
			teleporterSelection.selectTeleporter(getTeleporterBehaviour(), teleporters, player);
		}
		if (getPlayer().getPosition().equals(getGold().getPosition())) {
			setRich(true);
		}

		if (Arrays.asList(getSnake().getSnakePositions()).contains(getPlayer().getPosition())) {
			setDead(true);
		}

		loopCount++;

		gui.produceText(znake);
		gui.produce(znake);
	}

	public void run(ZZZZZnake znake) {

		gui.produceText(znake);
		gui.produce(znake);

		if (znake.isRich() && getPlayer().getPosition().equals(znake.getDoor().getPosition())) {
			setVictory(true);
		}

		if (Arrays.asList(getSnake().getSnakePositions()).contains(getPlayer().getPosition())) {
			setDead(true);
		}
		if (getPlayer().getPosition().equals(getGold().getPosition())) {
			setRich(true);
		}
		if (Arrays.asList(znake.getTeleporterPositions()).contains(znake.getPlayer().getPosition())) {
			final TeleporterSelection teleporterSelection = new TeleporterSelection();
			teleporterSelection.selectTeleporter(getTeleporterBehaviour(), teleporters, player);
		}

		if (!rich) {
			snake.snakeMovementTowards(player);
		} else {
			if (loopCountAfterGoldGet >= 2) {
				snake.snakeMovementTowards(player);
			}
			loopCountAfterGoldGet++;
		}
		loopCount++;
	}

	public Player getPlayer() {
		return player;
	}

	public Door getDoor() {
		return door;
	}

	public Gold getGold() {
		return gold;
	}

	public Snake getSnake() {
		return snake;
	}

	public boolean isRich() {
		return rich;
	}

	public String getVictoryMessage() {
		return victoryMessage;
	}

	public String getDeathMessage() {
		return deathMessage;
	}

	public String getGoldGetMessage() {
		return goldGetMessage;
	}

	public String getNewLine() {
		return newLine;
	}

	public String getStartmessage1() {
		return startmessage1;
	}

	public String getStartmessage2() {
		return startmessage2;
	}

	public String getStartmessage3() {
		return startmessage3;
	}

	public String getStartmessage4() {
		return startmessage4;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public void setRich(boolean rich) {
		this.rich = rich;
	}

	public int getLoopCountAfterGoldGet() {
		return loopCountAfterGoldGet;
	}

	public int getLoopCount() {
		return loopCount;
	}

	public Teleporter getTeleporter(int number) {
		return teleporters[number];
	}

	public Teleporter[] getTeleporters() {
		return teleporters;
	}

	public int getNumberOfTeleporters() {
		return numberOfTeleporters;
	}

	public Point[] getTeleporterPositions() {
		return teleporterPositions;
	}

	public String getTeleporterBehaviour() {
		return teleporterBehaviour;
	}

}
