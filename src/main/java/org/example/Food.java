package org.example;

import java.awt.*;
import java.util.Random;

public class Food {
    private Point position;
    private int unitSize;

    public Food(int width, int height, int unitSize){
        this.unitSize = unitSize;
        spawn(width, height);
    }

    private void spawn(int width, int height) {
        Random rand = new Random();
        int x = rand.nextInt(width/unitSize)*unitSize;
        int y = rand.nextInt(height/unitSize)*unitSize;

        position = new Point(x,y);
    }

    public Point getPosition(){
        return position;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(position.x, position.y, unitSize, unitSize);
    }
}
