package de.is2.ZZZZZnake.Gui;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BetterGridPane extends GridPane {

	private TwoDimensionalArrayList<Node> nodeList = new TwoDimensionalArrayList<Node>();

	public BetterGridPane() {
		super();
	}

	public void addXColums(int x) {

		for (int count = 0; count < x; count++) {
			this.addColumn(count);
		}
	}

	public void addYRows(int y) {

		for (int count = 0; count < y; count++) {

			this.addRow(count);
		}
	}

	public void increaseGridSize(int x, int y) {
		this.addXColums(x);
		this.addYRows(y);
	}

	public ColumnConstraints setColumnConstraints(double percentConstraint) {
		ColumnConstraints columnConstraints = new ColumnConstraints();
		columnConstraints.setPercentWidth(percentConstraint);
		return columnConstraints;

	}

	public RowConstraints setRowConstraints(double percentConstraint) {
		RowConstraints rowConstraints = new RowConstraints();
		rowConstraints.setPercentHeight(percentConstraint);
		return rowConstraints;
	}

	public void applyRowConstraints(int rows) {
		RowConstraints rowConstraints = this.setRowConstraints((1D / (double) rows) * 100D);
		rowConstraints.setVgrow(Priority.ALWAYS);
		for (int y = 0; y < rows; y++) {
			this.getRowConstraints().add(rowConstraints);
		}
	}

	public void applyColumnConstraints(int columns) {
		ColumnConstraints columnConstraints = this.setColumnConstraints((1D / (double) columns) * 100D);
		columnConstraints.setHgrow(Priority.ALWAYS);
		for (int y = 0; y < columns; y++) {
			this.getColumnConstraints().add(columnConstraints);

		}
	}

	public void placeNode(int column, int row, Node node) {
		if (nodeList.getFromInnerArray(column, row) == null) {
			this.setNode(column, row, node);
		} else {
			this.replaceNode(column, row, node);
		}
	}

	public void fillGrid(int columns, int rows) {
		for (int countX = 0; countX < columns; countX++) {
			for (int countY = 0; countY < rows; countY++) {
				Rectangle rect1 = new Rectangle(0, 0, this.getPercentWidth(columns), this.getPercentHeight(rows));
				rect1.setFill(Color.BLUE);
				this.placeNode(countX, countY, rect1);
			}
		}
	}

	public void replaceNode(int column, int row, Node node) {
		// internal use, use this.placeNode instead
		this.removeNode(column, row);
		this.setNode(column, row, node);
	}

	/*
	 * public void removeNode(int column, int row) {
	 * 
	 * this.getChildren().remove(nodeList.getFromInnerArray(column, row));
	 * nodeList.removeFromInnerArray(column, row); }
	 */

	public void removeNode(int column, int row) {

		this.getChildren().remove(nodeList.getFromInnerArray(column, row));
		// nodeList.removeFromInnerArray(column, row);
	}

	public void setNode(int column, int row, Node node) {
		// internal use, use this.placeNode instead

		this.add(node, column, row);
		nodeList.addToInnerArray(column, row, node);
		;
	}

	public TwoDimensionalArrayList<Node> getNodeList() {
		return nodeList;
	}

	public double getPercentHeight(int rows) {
		return (((double) this.getHeight() / (double) rows) - 1D);
	}

	public double getPercentWidth(int columns) {
		return (((double) this.getWidth() / (double) columns) - 1D);
	}

	public void moveRight(Node node) {
		this.setNode(1, 0, node);
	}
}
