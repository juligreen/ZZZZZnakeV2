package de.is2.ZZZZZnake.GameObjects;

import java.awt.Point;

import de.is2.ZZZZZnake.Utility.TwoPartVector;

public abstract class GameObject {

	public java.awt.Point position;

	public void randomPosition() {
		position = new Point((int) (Math.random() * 40), (int) (Math.random() * 10));
	}

	public void randomPositionUntilDistanceSmallerThan(int distance, GameObject gameObject) {
		while (this.distanceBetween(gameObject) < distance) {
			this.randomPosition();
		}
	}

	public void randomPositionUntilDistanceBiggerThan(int distance, GameObject gameObject) {
		while (this.distanceBetween(gameObject) > distance) {
			this.randomPosition();
		}
	}

	public TwoPartVector vectorBetween(final int x, final int y) {
		final TwoPartVector vector = new TwoPartVector();
		vector.setX(x - position.x);
		vector.setY(y - position.y);
		return vector;
	}

	public int distanceBetween(final GameObject otherPoint) {
		final TwoPartVector vector = vectorBetween(otherPoint.position.x, otherPoint.position.y);
		return (int) Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
	}

	public boolean positionEquals(final GameObject otherPoint) {
		if (this.getPosition().equals(otherPoint.getPosition()))
			return true;
		else
			return false;
	}

	public Point getPosition() {
		return position;
	}

}