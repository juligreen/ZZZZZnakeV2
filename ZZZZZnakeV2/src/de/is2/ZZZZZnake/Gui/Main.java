package de.is2.ZZZZZnake.Gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ZZZZZnakeV2");
		Group root = new Group();
		Canvas canvas = new Canvas(1435, 385);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));

		GUI gui = new GUI();

		gui.getTeleporterBehaviourInput(gui);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
