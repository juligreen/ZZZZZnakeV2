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
	JPanel[][] panelArray = new JPanel[columns][rows];

	public GUI() {
		addKeyListener(this);
		this.panelArray = this.fillPanelArray(columns, rows, panelArray);
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
		for (int columnCount = 0; columnCount < columns; columnCount++) {
			for (int rowCount = 0; rowCount < rows; rowCount++) {
				panelArray[columnCount][rowCount] = new JPanel();
				this.add(panelArray[columnCount][rowCount]);
			}
		}
	}

	public static JPanel[][] fillPanelArray(int columns, int rows, JPanel[][] panelArray) {
		for (int columnCount = 0; columnCount < columns; columnCount++) {
			for (int rowCount = 0; rowCount < rows; rowCount++) {
				JPanel panel = new JPanel();
				panel.setBackground(Color.BLUE);
				panelArray[columnCount][rowCount] = panel;
			}
		}
		return panelArray;
	}

	public void prepareFrame(int columns, int rows) {
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
		if (znake.getLoopCount() < 1) {
			startMessages(znake);
		}
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
		String startmessage = znake.getStartmessage1() + System.lineSeparator() + znake.getStartmessage2()
				+ System.lineSeparator() + znake.getStartmessage3() + System.lineSeparator() + znake.getStartmessage4();
		JTextArea textArea = new JTextArea(startmessage);
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		button.addActionListener(e -> dialog.dispose());
	}

	@Override
	public void victory(ZZZZZnake znake) {
		JDialog dialog = new JDialog();

		JTextArea textArea = new JTextArea(snake.getVictoryMessage());
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		button.addActionListener(e -> {

			dialog.dispose();
			this.dispose();
		});
	}

	@Override
	public void death(ZZZZZnake znake) {
		JDialog dialog = new JDialog();

		JTextArea textArea = new JTextArea(snake.getDeathMessage());
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		button.addActionListener(e -> {
			dialog.dispose();
			this.dispose();
		});
	}

	@Override
	public void goldGet(ZZZZZnake znake) {
		JDialog dialog = new JDialog();

		JTextArea textArea = new JTextArea(snake.getGoldGetMessage());
		JButton button = new JButton("finished");
		dialog.add(textArea);
		dialog.add(button);
		button.addActionListener(e -> dialog.dispose());
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

		JComboBox<String> comboBox = new JComboBox<>(behaviours);
		JButton button = new JButton("Fertig");
		frame.add(comboBox);
		frame.add(button);
		button.addActionListener(e -> {

			String teleporterBehaviour = ((String) comboBox.getSelectedItem());
			frame.dispose();
			ZZZZZnake zzzzznakeinstance = new ZZZZZnake(gui, teleporterBehaviour);
			this.snake = zzzzznakeinstance;
			zzzzznakeinstance.run(zzzzznakeinstance);
			this.prepareFrame(columns, rows);
			this.fillGrid();
			this.refreshGrid();
			this.setVisible(true);

		});
		frame.setVisible(true);

	}

	public void refreshGrid() {

		for (int columnCount = 0; columnCount < columns; columnCount++) {
			for (int rowCount = 0; rowCount < rows; rowCount++) {
				Point dummyPoint = new Point(columnCount, rowCount);
				if (this.getSnake().getPlayer().getPosition().equals(dummyPoint)) {
					panelArray[columnCount][rowCount].setBackground(Color.BLACK);
				} else if (this.getSnake().getDoor().getPosition().equals(dummyPoint)) {
					panelArray[columnCount][rowCount].setBackground(Color.gray);
				} else if (this.getSnake().getGold().getPosition().equals(dummyPoint)) {
					panelArray[columnCount][rowCount].setBackground(Color.YELLOW);
				} else if (Arrays.asList(this.getSnake().getSnake().getSnakePositions()).contains(dummyPoint)) {
					panelArray[columnCount][rowCount].setBackground(Color.GREEN);
				} else if (Arrays.asList(this.getSnake().getTeleporterPositions()).contains(dummyPoint)) {
					panelArray[columnCount][rowCount].setBackground(Color.PINK);
				} else {
					panelArray[columnCount][rowCount].setBackground(Color.BLUE);
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
			this.getSnake().runMovement(this.getSnake(), "down");
			break;
		case KeyEvent.VK_UP:
			this.getSnake().runMovement(this.getSnake(), "up");
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
}
