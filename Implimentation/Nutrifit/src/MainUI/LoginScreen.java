package MainUI;

import application.userManager;
import dataAccess.HC_Old_user_data.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {
    private JTextField usernameField;
    private int testProfileId;

    public LoginScreen(int testProfileId) {
        this.testProfileId = testProfileId;

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        JButton loginButton = new JButton("Login");

        // Set layout manager
        setLayout(new GridBagLayout());

        // Create GridBagConstraints for layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Add components to the panel with preferred sizes
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        usernameField.setPreferredSize(new Dimension(150, 25)); // Set preferred size
        add(usernameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(loginButton, gbc);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate credentials
                if (isValidUsername(usernameField.getText())) {
                    // If valid, show a message
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login Successful!");

                } else {
                    // If invalid, display an error message
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean isValidUsername(String username) {
        // In a real-world scenario, this would involve checking against a database or other secure means
        // For simplicity, we'll use a hardcoded test profile ID in this example
        if (username.equals("Alex")) {
            // If the provided username matches the test profile, get the user profile
            profile user = userManager.getUserProfile(testProfileId);

            // Fire the loginSuccess event and pass the user profile as a property
            firePropertyChange("loginSuccess", null, user);

            return true;
        } else {
            // If the provided username does not match, return false
            return false;
        }
    }
}
