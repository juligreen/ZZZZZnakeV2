package de.is2.ZZZZZnake.Gui;

import java.awt.Point;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	private BetterGridPane gridPane;
	private GUI gui;
	private Scene scene;
	int columns = 41;
	int rows = 11;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("ZZZZZnakeV2");
		gridPane = new BetterGridPane();
		scene = new Scene(gridPane, 1435, 385);
		gridPane.increaseGridSize(columns, rows);

		gridPane.applyColumnConstraints(columns);
		gridPane.applyRowConstraints(rows);

		gridPane.setStyle("-fx-background-color:red");
		gridPane.setVgap(2);
		gridPane.setHgap(2);
		gridPane.fillGrid(columns, rows);

		gui = new GUI();
		this.refreshGrid();

		primaryStage.setScene(scene);

		primaryStage.show();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {

				case DOWN:
					gui.getSnake().runMovement(gui.getSnake(), "up");

					break;
				case UP:
					gui.getSnake().runMovement(gui.getSnake(), "down");

					break;
				case LEFT:
					gui.getSnake().runMovement(gui.getSnake(), "left");

					break;
				case RIGHT:
					gui.getSnake().runMovement(gui.getSnake(), "right");

					break;
				}
				Main.this.refreshGrid();
			}
		});
		scene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				for (int count1 = 0; count1 < columns; count1++) {
					for (int count2 = 0; count2 < rows; count2++) {
						Rectangle rectangle = (Rectangle) gridPane.getNodeList().getFromInnerArray(count1, count2);
						rectangle.setWidth(((double) newSceneWidth / columns) - 2D);
					}
				}
			}
		});

		gridPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				for (int count1 = 0; count1 < columns; count1++) {
					for (int count2 = 0; count2 < rows; count2++) {
						Rectangle rectangle = (Rectangle) gridPane.getNodeList().getFromInnerArray(count1, count2);
						rectangle.setHeight(((double) newSceneHeight / rows) - 2D);
					}
				}
			}
		});
	}

	public void refreshGrid() {
		for (int countX = 0; countX < columns; countX++) {
			for (int countY = 0; countY < rows; countY++) {
				Point dummyPoint = new Point(countX, countY);
				if (gui.getSnake().getPlayer().getPosition().equals(dummyPoint)) {
					Rectangle rect1 = new Rectangle(0, 0, gridPane.getPercentWidth(columns),
							gridPane.getPercentHeight(rows));
					rect1.setFill(Color.BLACK);
					gridPane.placeNode(countX, countY, rect1);
				} else if (gui.getSnake().getDoor().getPosition().equals(dummyPoint)) {
					Rectangle rect1 = new Rectangle(0, 0, gridPane.getPercentWidth(columns),
							gridPane.getPercentHeight(rows));
					rect1.setFill(Color.BROWN);
					gridPane.placeNode(countX, countY, rect1);
				} else if (gui.getSnake().getGold().getPosition().equals(dummyPoint)) {
					Rectangle rect1 = new Rectangle(0, 0, gridPane.getPercentWidth(columns),
							gridPane.getPercentHeight(rows));
					rect1.setFill(Color.GOLD);
					gridPane.placeNode(countX, countY, rect1);

				} else if (Arrays.asList(gui.getSnake().getSnake().getSnakePositions()).contains(dummyPoint)) {
					Rectangle rect1 = new Rectangle(0, 0, gridPane.getPercentWidth(columns),
							gridPane.getPercentHeight(rows));
					rect1.setFill(Color.GREEN);
					gridPane.placeNode(countX, countY, rect1);
				} else if (Arrays.asList(gui.getSnake().getTeleporterPositions()).contains(dummyPoint)) {
					Rectangle rect1 = new Rectangle(0, 0, gridPane.getPercentWidth(columns),
							gridPane.getPercentHeight(rows));
					rect1.setFill(Color.VIOLET);
					gridPane.placeNode(countX, countY, rect1);
				} else {
					Rectangle rect1 = new Rectangle(0, 0, gridPane.getPercentWidth(columns),
							gridPane.getPercentHeight(rows));
					rect1.setFill(Color.BLUE);
					gridPane.placeNode(countX, countY, rect1);

				}

			}
		}

	}

	public Main getMain() {
		return Main.this;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
