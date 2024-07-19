package JAVA_SYNTAX;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSignUpDemo extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public LoginSignUpDemo() {
       
        setTitle("Login and Sign-Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

      
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

      
        JPanel loginForm = createLoginForm();
        JPanel signUpForm = createSignUpForm();

        mainPanel.add(loginForm, "login");
        mainPanel.add(signUpForm, "signup");

        add(mainPanel);

     
        cardLayout.show(mainPanel, "login");
    }

    private JPanel createLoginForm() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                JOptionPane.showMessageDialog(panel, "Logged in successfully!");
            }
        });

        JButton switchToSignUpButton = new JButton("Sign Up");
        switchToSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "signup");
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(switchToSignUpButton);

        return panel;
    }

    private JPanel createSignUpForm() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sign-up logic here
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Add your registration logic here
                JOptionPane.showMessageDialog(panel, "Registered successfully!");
            }
        });

        JButton switchToLoginButton = new JButton("Login");
        switchToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signUpButton);
        panel.add(switchToLoginButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginSignUpDemo().setVisible(true);
            }
        });
    }
}
