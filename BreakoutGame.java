package JAVA_SYNTAX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BreakoutGame extends JFrame implements ActionListener, KeyListener {
    private Timer timer;
    private int ballX, ballY, ballDiameter, ballXSpeed, ballYSpeed;
    private int paddleX, paddleY, paddleWidth, paddleHeight;
    private int paddleSpeed;
    private boolean[][] bricks;
    private final int brickWidth = 60, brickHeight = 20;
    private final int rows = 5, cols = 10;
    private int score;

    public BreakoutGame() {
        setTitle("Breakout Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        
        ballDiameter = 20;
        ballX = getWidth() / 2 - ballDiameter / 2;
        ballY = getHeight() / 2 - ballDiameter / 2;
        ballXSpeed = 4;
        ballYSpeed = 4;

        paddleWidth = 100;
        paddleHeight = 10;
        paddleX = getWidth() / 2 - paddleWidth / 2;
        paddleY = getHeight() - paddleHeight - 30;
        paddleSpeed = 20;

        bricks = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bricks[i][j] = true;
            }
        }

        score = 0;

        timer = new Timer(20, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.RED);
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter);

        g.setColor(Color.BLUE);
        g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);

        g.setColor(Color.GREEN);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (bricks[i][j]) {
                    g.fillRect(j * brickWidth, i * brickHeight, brickWidth, brickHeight);
                }
            }
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        
        if (ballX <= 0 || ballX >= getWidth() - ballDiameter) {
            ballXSpeed = -ballXSpeed;
        }
        if (ballY <= 0) {
            ballYSpeed = -ballYSpeed;
        }

        
        if (ballY >= paddleY - ballDiameter && ballX + ballDiameter >= paddleX && ballX <= paddleX + paddleWidth) {
            ballYSpeed = -ballYSpeed;
        }

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (bricks[i][j] &&
                    ballX + ballDiameter >= j * brickWidth &&
                    ballX <= (j + 1) * brickWidth &&
                    ballY + ballDiameter >= i * brickHeight &&
                    ballY <= (i + 1) * brickHeight) {
                    bricks[i][j] = false;
                    ballYSpeed = -ballYSpeed;
                    score += 10;
                }
            }
        }

        
        if (ballY > getHeight()) {
            JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
            resetGame();
        }

        repaint();
    }

    private void resetGame() {
        ballX = getWidth() / 2 - ballDiameter / 2;
        ballY = getHeight() / 2 - ballDiameter / 2;
        ballXSpeed = 4;
        ballYSpeed = 4;
        paddleX = getWidth() / 2 - paddleWidth / 2;
        paddleY = getHeight() - paddleHeight - 30;
        score = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bricks[i][j] = true;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT && paddleX > 0) {
            paddleX -= paddleSpeed;
        }
        if (keyCode == KeyEvent.VK_RIGHT && paddleX < getWidth() - paddleWidth) {
            paddleX += paddleSpeed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BreakoutGame().setVisible(true));
    }
}
