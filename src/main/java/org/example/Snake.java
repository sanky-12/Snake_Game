package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class Snake {
    private ArrayList<Point> body;
    private int unitSize;

    public Snake(int startX, int startY, int unitSize){
        this.unitSize = unitSize;
        body = new ArrayList<>();
        body.add(new Point(startX, startY));
    }

    public void move(int direction){
        Point head = new Point(body.get(0));

        switch (direction){
            case KeyEvent.VK_UP:
                head.y -= unitSize;
                break;
            case KeyEvent.VK_DOWN:
                head.y += unitSize;
                break;
            case KeyEvent.VK_LEFT:
                head.x -= unitSize;
                break;
            case KeyEvent.VK_RIGHT:
                head.x += unitSize;
        }
        body.add(0,head);
        body.remove(body.size()-1);
    }

    public void grow(){
        body.add(new Point(body.get(body.size()-1)));
    }

    public Point getHead(){
        return body.get(0);
    }

    public boolean hasCollision(int width, int height){
        Point head = getHead();
        //Check ofr hte borders
        if(head.x < 0 || head.x >= width || head.y < 0 || head.y>= height){
            return true;
        }

        //Check for itself collision
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }

        return false;
    }

    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        for (Point point : body){
            g.fillRect(point.x, point.y, unitSize, unitSize);
        }
    }
}
