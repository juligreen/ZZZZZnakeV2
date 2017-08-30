package de.is2.ZZZZZnake.Gui;

import java.util.Optional;

import de.is2.ZZZZZnake.ZZZZZnake;
import de.is2.ZZZZZnake.Utility.IGUI;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;

public class GUI implements IGUI {
	private ZZZZZnake snake;

	public GUI() {
		this.getTeleporterBehaviourInput(this);
	}

	@Override
	public void produce(ZZZZZnake znake) {
	}

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

	public void startMessages(ZZZZZnake znake) {
		String startmessage = znake.getStartmessage1() + System.lineSeparator() + znake.getStartmessage2()
				+ System.lineSeparator() + znake.getStartmessage3() + System.lineSeparator() + znake.getStartmessage4();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Information");
		alert.setContentText(startmessage);

		alert.showAndWait();
	}

	public void victory(ZZZZZnake znake) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Victory Dialog");
		alert.setContentText(znake.getVictoryMessage());

		alert.showAndWait();
		Platform.exit();
	}

	public void death(ZZZZZnake znake) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Defeat Dialog");
		alert.setContentText(znake.getDeathMessage());

		alert.showAndWait();
		Platform.exit();
	}

	public void goldGet(ZZZZZnake znake) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Victory Dialog");
		alert.setContentText(znake.getGoldGetMessage());

		alert.showAndWait();
	}

	public int teleporterNumberInput() {
		final String[] teleporterNumbers = { "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		ChoiceDialog<String> dialog = new ChoiceDialog<>("2", teleporterNumbers);
		dialog.setTitle("Teleporternumber");
		dialog.setHeaderText("Please select the Number of teleporters");
		dialog.setContentText("");

		Optional<String> result = dialog.showAndWait();
		if (false == result.isPresent()) {
			Platform.exit();
			return 0;
		} else {
			return Integer.parseInt(result.get());
		}
	}

	@Override
	public void getTeleporterBehaviourInput(GUI gui) {
		final String[] behaviours = { "Circle", "Chaos", "Pairs" };

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Circle", behaviours);
		dialog.setTitle("Teleporterbehaviour");
		dialog.setHeaderText("Please select a teleporter behaviour");
		dialog.setContentText("");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (false == result.isPresent()) {
			Platform.exit();
		} else {
			snake = new ZZZZZnake(gui, result.get());
			snake.run(snake);
		}

	}

	@Override
	public String playerMovement() {
		// TODO Auto-generated method stub
		return null;
	}

	public ZZZZZnake getSnake() {
		return snake;
	}

}
