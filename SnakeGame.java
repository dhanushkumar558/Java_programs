package JAVA_SYNTAX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;
public class SnakeGame extends JFrame implements ActionListener, KeyListener {
    private Timer timer;
    private LinkedList<Point> snake;
    private Point food;
    private int direction; 
    private final int SIZE = 20;
    private final int GRID_SIZE = 20;
    private int score;
    public SnakeGame() {
        setTitle("Snake Game");
        setSize(GRID_SIZE * SIZE, GRID_SIZE * SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        snake = new LinkedList<>();
        snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
        direction = 1; 
        score = 0;

        placeFood();

        timer = new Timer(100, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

      
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * SIZE, p.y * SIZE, SIZE, SIZE);
        }

      
        g.setColor(Color.RED);
        g.fillRect(food.x * SIZE, food.y * SIZE, SIZE, SIZE);

     
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        Point head = snake.getFirst();
        Point newHead = new Point(head);
        switch (direction) {
            case 0 -> newHead.y--;
            case 1 -> newHead.x++;
            case 2 -> newHead.y++;
            case 3 -> newHead.x--;
        }

        
        if (newHead.x < 0 || newHead.x >= GRID_SIZE || newHead.y < 0 || newHead.y >= GRID_SIZE || snake.contains(newHead)) {
            JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
            resetGame();
            return;
        }

        snake.addFirst(newHead);

        
        if (newHead.equals(food)) {
            score += 10;
            placeFood();
        } else {
            snake.removeLast();
        }

        repaint();
    }

    private void placeFood() {
        Random rand = new Random();
        do {
            food = new Point(rand.nextInt(GRID_SIZE), rand.nextInt(GRID_SIZE));
        } while (snake.contains(food));
    }

    private void resetGame() {
        snake.clear();
        snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
        direction = 1; 
        score = 0;
        placeFood();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP && direction != 2) {
            direction = 0;
        }
        if (keyCode == KeyEvent.VK_RIGHT && direction != 3) {
            direction = 1;
        }
        if (keyCode == KeyEvent.VK_DOWN && direction != 0) {
            direction = 2;
        }
        if (keyCode == KeyEvent.VK_LEFT && direction != 1) {
            direction = 3;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeGame().setVisible(true));
    }
}
