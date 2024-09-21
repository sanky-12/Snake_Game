package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private int unitSize;
    private int currentScore;
    private int highScore;
    private Image headUp, headDown, headLeft, headRight;
    private Image tailUp, tailDown, tailLeft, tailRight;
    private Image bodyHorizontal, bodyVertical;
    private Image cornerUpLeft, cornerUpRight, cornerDownLeft, cornerDownRight;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public Snake(int startX, int startY, int unitSize){
        this.unitSize = unitSize;
        this.currentScore = 0;
        body = new LinkedList<>();
        body.add(new Point(startX, startY));

        headUp = new ImageIcon(getClass().getResource("/images/snake_head_up.png")).getImage();
        headDown = new ImageIcon(getClass().getResource("/images/snake_head_down.png")).getImage();
        headLeft = new ImageIcon(getClass().getResource("/images/snake_head_left.png")).getImage();
        headRight = new ImageIcon(getClass().getResource("/images/snake_head_right.png")).getImage();

        tailUp = new ImageIcon(getClass().getResource("/images/snake_tail_down.png")).getImage();
        tailDown = new ImageIcon(getClass().getResource("/images/snake_tail_up.png")).getImage();
        tailLeft = new ImageIcon(getClass().getResource("/images/snake_tail_right.png")).getImage();
        tailRight = new ImageIcon(getClass().getResource("/images/snake_tail_left.png")).getImage();

        bodyHorizontal = new ImageIcon(getClass().getResource("/images/snake_body_left.png")).getImage();
        bodyVertical = new ImageIcon(getClass().getResource("/images/snake_body_up.png")).getImage();

        cornerUpLeft = new ImageIcon(getClass().getResource("/images/top_left.png")).getImage();
        cornerUpRight = new ImageIcon(getClass().getResource("/images/top_right.png")).getImage();
        cornerDownLeft = new ImageIcon(getClass().getResource("/images/bottom_left.png")).getImage();
        cornerDownRight = new ImageIcon(getClass().getResource("/images/bottom_right.png")).getImage();
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

        if (head.x < 0) {
            head.x = WIDTH - unitSize; // Wrap from left to right
        } else if (head.x >= WIDTH) {
            head.x = 0; // Wrap from right to left
        }

        if (head.y < 0) {
            head.y = HEIGHT - unitSize; // Wrap from top to bottom
        } else if (head.y >= HEIGHT) {
            head.y = 0; // Wrap from bottom to top
        }
        body.addFirst(head);
        body.removeLast();
    }

    public void grow(int currentDirection) {
        Point tail = body.get(body.size() - 1);
        Point newSegment = new Point(tail);


        switch (currentDirection) {
            case KeyEvent.VK_UP:
                newSegment.y += unitSize;
                break;
            case KeyEvent.VK_DOWN:
                newSegment.y -= unitSize;
                break;
            case KeyEvent.VK_LEFT:
                newSegment.x += unitSize;
                break;
            case KeyEvent.VK_RIGHT:
                newSegment.x -= unitSize;
                break;
        }


        body.add(newSegment);
    }

    public void incrementScore() {
        currentScore += 10;
        if (currentScore > highScore) {
            highScore = currentScore;
        }
    }

    public int getCurrentScore(){
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public Point getHead(){
        return body.get(0);
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public boolean hasCollision(int width, int height){
        Point head = getHead();


        //Check for itself collision
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }

        return false;
    }

    public void reset(int startX, int startY) {
        // Reset the snake body to its initial position with just the head
        body.clear();
        body.add(new Point(startX, startY));

        // Reset the current score but keep the high score intact
        currentScore = 0;
    }


    public void draw(Graphics g, int direction) {

        if (body.isEmpty()) return;

        // Draw head
        Point head = body.get(0);
        switch (direction) {
            case KeyEvent.VK_UP:
                g.drawImage(headUp, head.x, head.y, unitSize, unitSize, null);
                break;
            case KeyEvent.VK_DOWN:
                g.drawImage(headDown, head.x, head.y, unitSize, unitSize, null);
                break;
            case KeyEvent.VK_LEFT:
                g.drawImage(headLeft, head.x, head.y, unitSize, unitSize, null);
                break;
            case KeyEvent.VK_RIGHT:
                g.drawImage(headRight, head.x, head.y, unitSize, unitSize, null);
                break;
        }

        // Draw body parts (this assumes the body is always horizontal or vertical)
        for (int i = 1; i < body.size() - 1; i++) {
            Point p = body.get(i);
            Point next = body.get(i + 1);
            Point previous = body.get(i - 1);

            // Check for corners
            if (previous.x != next.x && previous.y != next.y) { // It's a corner

                if (previous.x > p.x && next.y < p.y || next.x > p.x && previous.y < p.y) {
                    g.drawImage(cornerDownLeft, p.x, p.y, unitSize, unitSize, null);
                } else if (previous.x < p.x && next.y < p.y || next.x < p.x && previous.y < p.y) {
                    g.drawImage(cornerDownRight, p.x, p.y, unitSize, unitSize, null);
                } else if (previous.x > p.x && next.y > p.y || next.x > p.x && previous.y > p.y) {
                    g.drawImage(cornerUpLeft, p.x, p.y, unitSize, unitSize, null);
                } else if (previous.x < p.x && next.y > p.y || next.x < p.x && previous.y > p.y) {
                    g.drawImage(cornerUpRight, p.x, p.y, unitSize, unitSize, null);
                }
            } else {
                // Draw straight body parts
                if (previous.x != p.x) {
                    g.drawImage(bodyHorizontal, p.x, p.y, unitSize, unitSize, null);
                } else {
                    g.drawImage(bodyVertical, p.x, p.y, unitSize, unitSize, null);
                }
            }
        }

        // Draw tail
        if (body.size() > 1) {
            Point tail = body.get(body.size() - 1);
            Point beforeTail = body.get(body.size() - 2);
            if (tail.x < beforeTail.x) {
                g.drawImage(tailLeft, tail.x, tail.y, unitSize, unitSize, null);
            } else if (tail.x > beforeTail.x) {
                g.drawImage(tailRight, tail.x, tail.y, unitSize, unitSize, null);
            } else if (tail.y < beforeTail.y) {
                g.drawImage(tailUp, tail.x, tail.y, unitSize, unitSize, null);
            } else if (tail.y > beforeTail.y) {
                g.drawImage(tailDown, tail.x, tail.y, unitSize, unitSize, null);
            }
        }
    }


}
