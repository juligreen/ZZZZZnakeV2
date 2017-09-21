package de.is2.ZZZZZnake.Gui;

import java.awt.Color;

import javax.swing.JPanel;

public class Utility {
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
}
