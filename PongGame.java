package JAVA_SYNTAX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends JFrame implements ActionListener, KeyListener {
    private Timer timer;
    private int ballX, ballY, ballDiameter, ballXSpeed, ballYSpeed;
    private int paddleX, paddleY, paddleWidth, paddleHeight;
    private int paddleSpeed, score;

    public PongGame() {
        setTitle("Pong Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Initialize game variables
        ballDiameter = 20;
        ballX = getWidth() / 2 - ballDiameter / 2;
        ballY = getHeight() / 2 - ballDiameter / 2;
        ballXSpeed = 5;
        ballYSpeed = 3;

        paddleWidth = 100;
        paddleHeight = 10;
        paddleX = getWidth() / 2 - paddleWidth / 2;
        paddleY = getHeight() - paddleHeight - 30;
        paddleSpeed = 20;

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

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        // Ball collision with walls
        if (ballX <= 0 || ballX >= getWidth() - ballDiameter) {
            ballXSpeed = -ballXSpeed;
        }
        if (ballY <= 0) {
            ballYSpeed = -ballYSpeed;
        }

        // Ball collision with paddle
        if (ballY >= paddleY - ballDiameter && ballX + ballDiameter >= paddleX && ballX <= paddleX + paddleWidth) {
            ballYSpeed = -ballYSpeed;
            score++;
        }

        // Ball falls below the paddle
        if (ballY > getHeight()) {
            JOptionPane.showMessageDialog(this, "Game Over! Your score: " + score);
            resetGame();
        }

        repaint();
    }

    private void resetGame() {
        ballX = getWidth() / 2 - ballDiameter / 2;
        ballY = getHeight() / 2 - ballDiameter / 2;
        ballXSpeed = 5;
        ballYSpeed = 3;
        paddleX = getWidth() / 2 - paddleWidth / 2;
        score = 0;
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
        // Not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PongGame().setVisible(true));
    }
}
