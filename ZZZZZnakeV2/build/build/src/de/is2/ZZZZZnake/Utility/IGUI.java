package de.is2.ZZZZZnake.Utility;

import de.is2.ZZZZZnake.ZZZZZnake;
import de.is2.ZZZZZnake.Gui.GUI;

public interface IGUI {
	void produce(ZZZZZnake znake);

	void produceText(ZZZZZnake znake);

	void startMessages(ZZZZZnake znake);

	void victory(ZZZZZnake znake);

	void death(ZZZZZnake znake);

	void goldGet(ZZZZZnake znake);

	int teleporterNumberInput();

	void getTeleporterBehaviourInput(GUI gui);

	String playerMovement();
}
