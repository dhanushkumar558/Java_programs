package JAVA_SYNTAX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGame extends JFrame implements ActionListener {
    private JLabel promptLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea resultArea;
    private int targetNumber;
    private int attempts;

    public GuessTheNumberGame() {
        setTitle("Guess the Number Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1; // Random number between 1 and 100
        attempts = 0;

        
        promptLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        
        guessButton.addActionListener(this);

        
        JPanel inputPanel = new JPanel();
        inputPanel.add(promptLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            if (guess < targetNumber) {
                resultArea.append("Attempt " + attempts + ": Too low!\n");
            } else if (guess > targetNumber) {
                resultArea.append("Attempt " + attempts + ": Too high!\n");
            } else {
                resultArea.append("Congratulations! You guessed the number in " + attempts + " attempts.\n");
                int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
            guessField.setText("");
        } catch (NumberFormatException ex) {
            resultArea.append("Please enter a valid number.\n");
        }
    }

    private void resetGame() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1;
        attempts = 0;
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessTheNumberGame game = new GuessTheNumberGame();
                game.setVisible(true);
            }
        });
    }
}
