package MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NutrifitGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 12)); // Set a default font for labels
            UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 12)); // Set a default font for buttons

            // Create and display your GUI components
            JFrame frame = new JFrame("Nutrifit: Eat, Run, Smile!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);  // Set your preferred dimensions.
            frame.setLayout(new BorderLayout());

            // Create and add GUI components (buttons and labels) to the frame.
            JPanel mainPanel = new JPanel();
            JButton createProfileButton = new JButton("Create Profile");
            JButton loginScreenButton = new JButton("Login Profile");

            JLabel welcomeLabel = new JLabel("Welcome to Nutrifit!");
            welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 19)); // Set the font explicitly for the Label component

            mainPanel.add(createProfileButton);
            mainPanel.add(loginScreenButton);
            frame.add(mainPanel, BorderLayout.CENTER);
            frame.add(welcomeLabel, BorderLayout.NORTH);

            frame.setVisible(true);

            createProfileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Display the Profile Management panel
                    showProfileManagementPanel();
                }
            });

            loginScreenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Display the Login Screen
                    showLoginScreen();

                }
            });
        });
    }

    // Function to display the Profile Management panel
    public static void showProfileManagementPanel() {
        // Create and display the Profile Management panel
        JFrame profileFrame = new JFrame("Profile Management");
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setSize(700, 600);

        // Create an instance of the ProfileManagementPanel
        ProfileManagementPanel profileManagementPanel = new ProfileManagementPanel();

        // Add the ProfileManagementPanel to the profileFrame
        profileFrame.add(profileManagementPanel);

        profileFrame.setVisible(true);
    }


    private static void showLoginScreen() {
        // Create and display the Login Screen
        JFrame loginFrame = new JFrame("Login Screen");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(700, 600);

        // Create an instance of the Login Screen with the test profile ID
        LoginScreen loginScreen = new LoginScreen();

        // Add the LoginScreen to the LoginScreenFrame
        loginFrame.add(loginScreen);

        loginFrame.setVisible(true);

    }
}