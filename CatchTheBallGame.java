package JAVA_SYNTAX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class CatchTheBallGame extends JFrame implements KeyListener, ActionListener {
    private Timer timer;
    private int ballX, ballY, ballDiameter;
    private int paddleX, paddleY, paddleWidth, paddleHeight;
    private int ballSpeed;
    private int score;

    public CatchTheBallGame() {
        setTitle("Catch the Ball Game");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        initGame();
    }

    private void initGame() {
        ballDiameter = 20;
        paddleWidth = 100;
        paddleHeight = 10;
        paddleY = getHeight() - paddleHeight - 30;
        ballSpeed = 5;
        score = 0;

        resetBall();
        paddleX = getWidth() / 2 - paddleWidth / 2;

        timer = new Timer(30, this);
        timer.start();
    }

    private void resetBall() {
        Random rand = new Random();
        ballX = rand.nextInt(getWidth() - ballDiameter);
        ballY = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter);

        g.setColor(Color.RED);
        g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballY += ballSpeed;

        if (ballY + ballDiameter >= paddleY && ballX + ballDiameter >= paddleX && ballX <= paddleX + paddleWidth) {
            score++;
            resetBall();
        }

        if (ballY > getHeight()) {
            resetBall();
            score = 0;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT && paddleX > 0) {
            paddleX -= 20;
        }
        if (keyCode == KeyEvent.VK_RIGHT && paddleX < getWidth() - paddleWidth) {
            paddleX += 20;
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CatchTheBallGame game = new CatchTheBallGame();
                game.setVisible(true);
            }
        });
    }
}

