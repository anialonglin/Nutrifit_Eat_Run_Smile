package MainUI;

import application.userManager;
import dataAccess.userData.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private int testProfileId;

    public LoginScreen(int testProfileId) {
        this.testProfileId = testProfileId;
        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // Set layout manager
        setLayout(new GridLayout(3, 2));

        // Add components to the panel
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(loginButton);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate credentials
                if (isValidCredentials(usernameField.getText(), new String(passwordField.getPassword()))) {
                    // If valid, show a message
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login Successful!");

                    // TODO: Perform actions after successful login, such as showing buttons or navigating to another panel
                } else {
                    // If invalid, display an error message
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // In a real-world scenario, this would involve checking against a database or other secure means
        // For simplicity, we'll use a hardcoded test profile ID in this example
        if (username.equals("Alex") && password.equals("admin")) {
            // If the provided credentials match the test profile, get the user profile
            profile user = userManager.getUserProfile(testProfileId);

            // Fire the loginSuccess event and pass the user profile as a property
            firePropertyChange("loginSuccess", null, user);

            return true;
        } else {
            // If the provided credentials do not match, return false
            return false;
        }
    }



}
