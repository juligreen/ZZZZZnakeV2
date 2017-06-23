package de.is2.ZZZZZnake.Gui;

import java.awt.FlowLayout;
import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.is2.ZZZZZnake.ZZZZZnake;
import de.is2.ZZZZZnake.Utility.IGUI;

public class GUI implements IGUI {
	Scanner scanner;

	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.getTeleporterBehaviourInput(gui);

	}

	public Scanner lazyMakeScanner() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}

		return scanner;
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
		if (znake.isRich() == true && znake.getLoopCountAfterGoldGet() <= 1) {
			goldGet(znake);
		}
	}

	@Override
	public void produce(ZZZZZnake znake) {

		for (int y = 10; y >= 0; y--) {
			for (int x = 0; x <= 40; x++) {
				Point p = new Point(x, y);
				if (znake.getPlayer().getPosition().equals(p)) {
					System.out.print("&");
				} else if (znake.getDoor().getPosition().equals(p)) {
					System.out.print("#");
				} else if (znake.getGold().getPosition().equals(p) && !znake.isRich()) {
					System.out.print("$");
				} else if (Arrays.asList(znake.getSnake().getSnakePositions()).contains(p)) {
					System.out.print("S");
				} else if (Arrays.asList(znake.getTeleporterPositions()).contains(p)) {
					for (int counter = 0; counter < znake.getNumberOfTeleporters(); counter++) {
						if (znake.getTeleporter(counter).getPosition().equals(p)) {
							System.out.print("T");
						}
					}
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}

	public String playerMovement() {
		char directionChar = lazyMakeScanner().next().charAt(0);
		String string;
		switch (directionChar) {
		case 'u':
			string = "up";
			break;
		case 'd':
			string = "down";
			break;
		case 'l':
			string = "left";
			break;
		case 'r':
			string = "right";
			break;
		default:
			string = "";

		}
		return string;
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
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
			zzzzznakeinstance.run(zzzzznakeinstance);

		});
		frame.setVisible(true);

	}

	@Override
	public void startMessages(ZZZZZnake znake) {
		System.out.println(znake.getStartmessage1() + znake.getNewLine() + znake.getStartmessage2() + znake.getNewLine()
				+ znake.getStartmessage3() + znake.getNewLine() + znake.getStartmessage4());
	}

	@Override
	public void victory(ZZZZZnake znake) {
		System.out.println(znake.getVictoryMessage());
		System.exit(0);
	}

	@Override
	public void death(ZZZZZnake znake) {
		System.out.println(znake.getDeathMessage());
		System.exit(0);
	}

	@Override
	public void goldGet(ZZZZZnake znake) {
		System.out.println(znake.getGoldGetMessage());
	}

}
