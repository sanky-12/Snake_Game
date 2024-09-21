package org.example;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Food {
    private Point position;
    private int unitSize;

    public Food(int width, int height, int unitSize){
        this.unitSize = unitSize;
        spawn(width, height, null);
    }

    public void spawn(int width, int height, LinkedList<Point> snakeBody) {
        Random rand = new Random();
        boolean isOnSnake;

        do {
            int x = rand.nextInt(width / unitSize) * unitSize;
            int y = rand.nextInt(height / unitSize) * unitSize;
            position = new Point(x, y);

            // Check if the food is spawning on the snake's body
            isOnSnake = false;
            if (snakeBody != null) {
                for (Point part : snakeBody) {
                    if (position.equals(part)) {
                        isOnSnake = true;
                        break;
                    }
                }
            }
        } while (isOnSnake);
    }


    public Point getPosition(){
        return position;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(position.x, position.y, unitSize, unitSize);
    }
}
