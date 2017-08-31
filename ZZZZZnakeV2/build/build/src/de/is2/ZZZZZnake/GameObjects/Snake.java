package de.is2.ZZZZZnake.GameObjects;

import java.awt.*;

public class Snake extends GameObject {
    private Point[] snakePositions = new Point[5];
    private int snakeIdx = 0;
    private int snakeIdxlast = 0;

    public Snake() {
        randomPosition();
        snakePositions[0] = new Point(getPosition());
    }

    @Override
    public void randomPositionUntilDistanceSmallerThan( int distance, GameObject gameObject ){
        while ( this.distanceBetween(gameObject) < distance ){
            this.randomPosition();
            snakePositions[0] = new Point(getPosition());
        }
    }
    @Override
    public void randomPositionUntilDistanceBiggerThan( int distance, GameObject gameObject ){
        while ( this.distanceBetween(gameObject) > distance ){
            this.randomPosition();
            snakePositions[0] = new Point(getPosition());
        }
    }

    public Point[] getSnakePositions(){
        return snakePositions;
    }
    public void snakeMovementTowards(Player player ) {
        if (snakePositions[4] == null) {
            snakePositions[snakeIdx + 1] = new Point(0, 0);
        }
        snakeIdxlast = snakeIdx;
        if (snakeIdx < 4) {
            snakeIdx++;
        }
        else {
            snakeIdx -= 4;
        }
        if (player.getPosition().y < snakePositions[snakeIdxlast].y) {
            snakePositions[snakeIdx].y = snakePositions[snakeIdxlast].y - 1;
        }
        else if (player.getPosition().y > snakePositions[snakeIdxlast].y) {
            snakePositions[snakeIdx].y = snakePositions[snakeIdxlast].y + 1;
        }
        else {
            snakePositions[snakeIdx].y = snakePositions[snakeIdxlast].y;
        }
        if (player.getPosition().x < snakePositions[snakeIdxlast].x) {
            snakePositions[snakeIdx].x = snakePositions[snakeIdxlast].x - 1;
        }
        else if (player.getPosition().x > snakePositions[snakeIdxlast].x) {
            snakePositions[snakeIdx].x = snakePositions[snakeIdxlast].x + 1;
        }
        else {
            snakePositions[snakeIdx].x = snakePositions[snakeIdxlast].x;
        }
    }
}
