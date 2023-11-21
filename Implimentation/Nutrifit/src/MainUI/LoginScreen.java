package MainUI;

import application.userManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginScreen extends JPanel {
    public JTextField usernameField;

    public LoginScreen() {

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
                    //return main screen logged in as user
                    new UserMainUIScreen(usernameField.getText());

                    // Close the LoginScreen
                    SwingUtilities.getWindowAncestor(LoginScreen.this).dispose();

                } else {
                    // If invalid, display an error message
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean isValidUsername(String username) {
        // Check if the username is valid
        ArrayList<String> users = userManager.listProfiles();
        return users.contains(username);
    }
}

