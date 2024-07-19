package JAVA_SYNTAX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean xTurn;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        buttons = new JButton[3][3];
        xTurn = true; 

        
        statusLabel = new JLabel("X's Turn", JLabel.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

     
        if (!button.getText().equals("") || checkWinner() != null) {
            return;
        }

        
        if (xTurn) {
            button.setText("X");
            statusLabel.setText("O's Turn");
        } else {
            button.setText("O");
            statusLabel.setText("X's Turn");
        }

        xTurn = !xTurn;

       
        String winner = checkWinner();
        if (winner != null) {
            if (winner.equals("Tie")) {
                statusLabel.setText("It's a Tie!");
            } else {
                statusLabel.setText(winner + " Wins!");
            }
            disableAllButtons();
        }
    }

    private String checkWinner() {
       
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) {
                return buttons[i][0].getText();
            }
        }

      
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().equals("")) {
                return buttons[0][i].getText();
            }
        }

        
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            return buttons[0][0].getText();
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            return buttons[0][2].getText();
        }

       
        boolean allFilled = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    allFilled = false;
                    break;
                }
            }
        }
        if (allFilled) {
            return "Tie";
        }

        return null;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToe game = new TicTacToe();
                game.setVisible(true);
            }
        });
    }
}
