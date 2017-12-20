package de.is2.ZZZZZnake.Gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.is2.ZZZZZnake.ZZZZZnake;
import de.is2.ZZZZZnake.Utility.IGUI;

public class GUI extends JFrame implements IGUI, KeyListener {
	private ZZZZZnake snake;
	private GUI gui;
	int columns = 41;
	int rows = 11;
	String teleporterBehaviour;
	JPanel[][] panelArray = new JPanel[rows][columns];

	private final String victoryMessage = "You win!";
	private final String deathMessage = "You died a pitiful and gruesome death...";
	private final String goldGetMessage = "With tha money you can exit tha door";
	private final String newLine = System.getProperty("line.separator");
	private final String startmessage1 = "You are the black rectangle, the snake is the green rectangles, the geolden rectangle is the gold, the brown rectangle is the exit door and the violet rectangles are teleporters which connect with each other.";
	private final String startmessage2 = "You control by pressing the arrow keys";
	private final String startmessage3 = "Get the gold to exit trough the door before the snake eats you!";
	private final String startmessage4 = "Getting the gold makes the Snake freeze for 3 turns.";

	public GUI() {
		addKeyListener(this);
		this.panelArray = this.fillPanelArray(rows, columns, panelArray);
	}

	public ZZZZZnake getSnake() {
		return snake;
	}

	public static void main(String[] args) {
		GUI gui = new GUI();

		gui.setTitle("ZZZZZnakeV2 Swing");
		gui.getTeleporterBehaviourInput(gui);

	}

	public void fillGrid() {
		for (int rowCount = 0; rowCount < rows; rowCount++) {
			for (int columnCount = 0; columnCount < columns; columnCount++) {
				panelArray[rowCount][columnCount] = new JPanel();
				this.add(panelArray[rowCount][columnCount]);
			}
		}
	}

	public static JPanel[][] fillPanelArray(final int rows, final int columns, final JPanel[][] panelArray) {
		for (int rowCount = 0; rowCount < rows; rowCount++) {
			for (int columnCount = 0; columnCount < columns; columnCount++) {
				final JPanel panel = new JPanel();
				panel.setBackground(Color.BLUE);
				panelArray[rowCount][columnCount] = panel;
			}
		}
		return panelArray;
	}

	public void prepareFrame(int rows, int columns) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1435, 385);
		this.getContentPane().setBackground(Color.RED);
		GridLayout gridLayout = new GridLayout(rows, 0, 2, 2);
		this.setLayout(gridLayout);
	}

	@Override
	public void produce(ZZZZZnake znake) {
		this.refreshGrid();
	}

	@Override
	public void produceText(ZZZZZnake znake) {
		if (znake.isVictory() == true) {
			victory(znake);
		}
		if (znake.isDead() == true) {
			death(znake);
		}
		if (znake.isRich() == true && znake.getLoopCountAfterGoldGet() < 1) {
			goldGet(znake);
		}
	}

	@Override
	public void startMessages(ZZZZZnake znake) {
		JDialog dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		dialog.setSize(1300, 150);
		String startmessage = this.getStartmessage1() + System.lineSeparator() + this.getStartmessage2()
				+ System.lineSeparator() + this.getStartmessage3() + System.lineSeparator() + this.getStartmessage4();
		JTextArea textArea = new JTextArea(startmessage);
		textArea.setEditable(false);
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		dialog.setVisible(true);
		button.addActionListener(e -> {
			dialog.dispose();

			ZZZZZnake zzzzznakeinstance = new ZZZZZnake(gui, teleporterBehaviour);
			this.snake = zzzzznakeinstance;
			zzzzznakeinstance.run(zzzzznakeinstance);
			this.prepareFrame(rows, rows);
			this.fillGrid();
			this.refreshGrid();
			this.setVisible(true);
		});
	}

	@Override
	public void victory(ZZZZZnake znake) {
		JDialog dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		dialog.setSize(250, 100);
		JTextArea textArea = new JTextArea(this.getVictoryMessage());
		textArea.setEditable(false);
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		button.addActionListener(e -> {

			dialog.dispose();
			this.dispose();
		});
		dialog.setVisible(true);
	}

	@Override
	public void death(ZZZZZnake znake) {
		JDialog dialog = new JDialog();
		dialog.setSize(250, 100);
		dialog.setLayout(new FlowLayout());
		JTextArea textArea = new JTextArea(this.getDeathMessage());
		textArea.setEditable(false);
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		button.addActionListener(e -> {
			dialog.dispose();
			this.dispose();
		});
		dialog.setVisible(true);
	}

	@Override
	public void goldGet(ZZZZZnake znake) {
		JDialog dialog = new JDialog();
		dialog.setSize(250, 100);
		dialog.setLayout(new FlowLayout());
		JTextArea textArea = new JTextArea(this.getGoldGetMessage());
		textArea.setEditable(false);
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		button.addActionListener(e -> dialog.dispose());
		dialog.setVisible(true);
	}

	@Override
	public int teleporterNumberInput() {
		String input = JOptionPane.showInputDialog("Please input the number of teleporters:");
		return Integer.parseInt(input);
	}

	@Override
	public void getTeleporterBehaviourInput(GUI gui) {
		final String[] behaviours = { "Circle", "Chaos", "Pairs" };

		JFrame frame = new JFrame("Teleporterbehaviour");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setSize(200, 200);

		this.gui = gui;

		JComboBox<String> comboBox = new JComboBox<>(behaviours);
		JButton button = new JButton("Fertig");
		frame.add(comboBox);
		frame.add(button);
		button.addActionListener(e -> {

			String teleporterBehaviour = ((String) comboBox.getSelectedItem());
			this.teleporterBehaviour = teleporterBehaviour;
			frame.dispose();
			this.startMessages(snake);

		});
		frame.setVisible(true);

	}

	public void refreshGrid() {

		for (int rowCount = 0; rowCount < rows; rowCount++) {
			for (int columnCount = 0; columnCount < columns; columnCount++) {
				Point dummyPoint = new Point(columnCount, rowCount);
				if (this.getSnake().getPlayer().getPosition().equals(dummyPoint)) {
					panelArray[rowCount][columnCount].setBackground(Color.BLACK);
				} else if (this.getSnake().getDoor().getPosition().equals(dummyPoint)) {
					panelArray[rowCount][columnCount].setBackground(Color.gray);
				} else if (this.getSnake().getGold().getPosition().equals(dummyPoint)) {
					panelArray[rowCount][columnCount].setBackground(Color.YELLOW);
				} else if (Arrays.asList(this.getSnake().getSnake().getSnakePositions()).contains(dummyPoint)) {
					panelArray[rowCount][columnCount].setBackground(Color.GREEN);
				} else if (Arrays.asList(this.getSnake().getTeleporterPositions()).contains(dummyPoint)) {
					panelArray[rowCount][columnCount].setBackground(Color.PINK);
				} else {
					panelArray[rowCount][columnCount].setBackground(Color.BLUE);
				}
			}
		}
		this.repaint();
	}

	@Override
	public String playerMovement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			this.getSnake().runMovement(this.getSnake(), "up");
			break;
		case KeyEvent.VK_UP:
			this.getSnake().runMovement(this.getSnake(), "down");
			break;
		case KeyEvent.VK_LEFT:
			this.getSnake().runMovement(this.getSnake(), "left");
			break;
		case KeyEvent.VK_RIGHT:
			this.getSnake().runMovement(this.getSnake(), "right");
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public GUI getGui() {
		return gui;
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public String getTeleporterBehaviour() {
		return teleporterBehaviour;
	}

	public JPanel[][] getPanelArray() {
		return panelArray;
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

	public void setSnake(ZZZZZnake snake) {
		this.snake = snake;
	}

}
