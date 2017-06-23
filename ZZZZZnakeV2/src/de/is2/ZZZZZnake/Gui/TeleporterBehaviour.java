package de.is2.ZZZZZnake.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class TeleporterBehaviour extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] behaviours = { "Circle", "Chaos", "Pairs" };
	JComboBox<String> comboBox = new JComboBox<>(behaviours);
	JButton button = new JButton();

	public TeleporterBehaviour() {
		JFrame frame = new JFrame("Teleporterbehaviour");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(comboBox);
		frame.add(button);
		button.addActionListener(this);
	}

	public void actionPerformed(ActionEvent actionEvent) {

	}
}