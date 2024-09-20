package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 20;
    private final int DELAY = 200;

    private Snake snake;
    private Food food;

    private boolean running = false;
    private int direction = KeyEvent.VK_RIGHT;
    private Timer timer;
    private GameState gameState = GameState.START_SCREEN;

    public Game(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initializeGame();
    }

    private void initializeGame() {
        snake = new Snake(WIDTH/2, HEIGHT/2, UNIT_SIZE);
        food = new Food(WIDTH, HEIGHT, UNIT_SIZE);
        direction = KeyEvent.VK_RIGHT;
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        if(running){
//            snake.draw(g);
//            food.draw(g);
//        }
//        else {
//            showGameOver(g);
//        }
        switch (gameState){
            case START_SCREEN:
                showStartScreen(g);
                break;
            case PLAYING:
                if(running){
                    snake.draw(g);
                    food.draw(g);
                }
                break;
            case GAME_OVER:
                showGameOver(g);
                break;
        }

    }

    public void showStartScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Press Enter to Start", (WIDTH- metrics.stringWidth("Press Enter to Start"))/2, HEIGHT/2);
    }

    public void showGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(g.getFont());

        String gameOverText = "GAME OVER";
        int xGameOver = (WIDTH - metrics.stringWidth(gameOverText)) / 2;
        g.drawString(gameOverText, xGameOver, HEIGHT / 2 - 50);

        String restartText = "Press Enter to Restart";
        int xRestart = (WIDTH - metrics.stringWidth(restartText)) / 2;
        g.drawString(restartText, xRestart, HEIGHT / 2 + 50);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(gameState == GameState.PLAYING && running){
            snake.move(direction);
            checkCollisions();
            checkFood();
            repaint();
        }
    }

    public void checkCollisions() {
        if(snake.hasCollision(WIDTH, HEIGHT)){
            running = false;
            timer.stop();
            gameState = GameState.GAME_OVER;
            repaint();
        }
    }

    public void checkFood() {
        if(snake.getHead().equals(food.getPosition())){
            snake.grow();
            food.spawn(WIDTH, HEIGHT);
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        switch (gameState){
            case START_SCREEN:
                if(key == KeyEvent.VK_ENTER){
                    gameState = GameState.PLAYING;
                    initializeGame();
                }
                break;
            case PLAYING:
                int newDirection = e.getKeyCode();
                if((newDirection == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) ||
                        (newDirection == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) ||
                        (newDirection == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) ||
                        (newDirection == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT)){
                    direction = newDirection;
                }
                break;
            case GAME_OVER:
                if(key == KeyEvent.VK_ENTER){
                    gameState = GameState.START_SCREEN;
                    repaint();
                }
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args){
        JFrame frame = new JFrame();
        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
