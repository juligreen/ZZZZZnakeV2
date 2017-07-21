package de.is2.ZZZZZnake.Gui;

import java.util.Optional;

import de.is2.ZZZZZnake.ZZZZZnake;
import de.is2.ZZZZZnake.Utility.IGUI;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;

public class GUI implements IGUI {

	public void produce(ZZZZZnake znake) {
	}

	public void produceText(ZZZZZnake znake) {
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
	}

	public void death(ZZZZZnake znake) {
	}

	public void goldGet(ZZZZZnake znake) {
	}

	public int teleporterNumberInput() {
		final int[] teleporterNumbers = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		ChoiceDialog dialog = new ChoiceDialog(2, teleporterNumbers);
		dialog.setTitle("Teleporternumber");
		dialog.setHeaderText("Please select the Number of teleporters");
		dialog.setContentText("");

		Optional result = dialog.showAndWait();
		if (false == result.isPresent()) {
			Platform.exit();
			return 0;
		} else {
			return (int) result.get();
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
			ZZZZZnake zzzzznakeinstance = new ZZZZZnake(gui, result.get());
			zzzzznakeinstance.run(zzzzznakeinstance);
		}

	}

	public String playerMovement() {
		return null;
	}
}
