package de.is2.ZZZZZnake.GameObjects;

import java.awt.Point;

public class Player extends GameObject {

	public Player() {
		randomPosition();
	}

	public void setXPosition(int xPosition) {
		this.position.x = xPosition;
	}

	public void setYPosition(int yPosition) {
		this.position.y = yPosition;
	}

	public void setPositionToOtherObject(GameObject otherObject) {
		this.setPosition(otherObject.getPosition());
	}

	public void setPosition(Point position) {
		Point newPoint = new Point();
		newPoint.x = position.x;
		newPoint.y = position.y;
		this.position = newPoint;
	}

	public void move(String movementDirection, Player player) {
		switch (movementDirection) {
		case "up":
			player.setYPosition(Math.min(player.getPosition().y + 1, 10));
			break;
		case "down":
			player.setYPosition(Math.max(player.getPosition().y - 1, 0));
			break;
		case "left":
			player.setXPosition(Math.max(player.getPosition().x - 1, 0));
			break;
		case "right":
			player.setXPosition(Math.min(player.getPosition().x + 1, 40));
			break;
		}
	}

}