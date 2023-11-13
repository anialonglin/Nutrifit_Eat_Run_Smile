package MainUI;

import application.userManager;
import dataAccess.userData.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainUIScreen {
    private profile user;

    public UserMainUIScreen(profile user) {
        this.user = user;

        // Create and display the UserMainUIScreen
        JFrame userMainScreenFrame = new JFrame("User Main Screen");
        userMainScreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userMainScreenFrame.setSize(700, 600);

        // Create an instance of the UserMainUIScreen
        JPanel mainPanel = new JPanel();
        JButton logDietButton = new JButton("Log Diet");
        JButton logExerciseButton = new JButton("Log Exercise");
        JButton editProfileButton = new JButton("Edit Profile");
        JButton deleteProfileButton = new JButton("Delete Profile");
        JButton settingsButton = new JButton("Settings");

        JLabel welcomeLabel = new JLabel("Hi, " + user.getName() + ". Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 19)); // Set the font explicitly for the Label component

        mainPanel.add(logDietButton);
        mainPanel.add(logExerciseButton);
        mainPanel.add(editProfileButton);
        mainPanel.add(deleteProfileButton);
        mainPanel.add(settingsButton);

        userMainScreenFrame.add(mainPanel, BorderLayout.CENTER);
        userMainScreenFrame.add(welcomeLabel, BorderLayout.NORTH);

        userMainScreenFrame.setVisible(true);

        logDietButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the DietLoggingPanel
                showDietLoggingPanel();
            }
        });

        logExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the ExerciseLoggingPanel
                showExerciseLoggingPanel();
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle edit profile functionality
                showEditProfilePanel();
            }
        });

        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle delete profile functionality
                showDeleteProfileConfirmation();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle settings functionality
                showSettingsPanel();
            }
        });
    }

    private void showDietLoggingPanel() {
        // Create and display the DietLoggingPanel
        JFrame dietLoggingFrame = new JFrame("Diet Logging Management");
        dietLoggingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dietLoggingFrame.setSize(700, 600);

        // Create an instance of the DietLoggingPanel
        DietLoggingPanel dietLoggingPanel = new DietLoggingPanel();

        // Add the DietLoggingPanel to the DietLoggingFrame
        dietLoggingFrame.add(dietLoggingPanel);

        dietLoggingFrame.setVisible(true);
    }

    private void showExerciseLoggingPanel() {
        // Create and display the ExerciseLoggingPanel
        JFrame exerciseLoggingFrame = new JFrame("Exercise Logging Management");
        exerciseLoggingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exerciseLoggingFrame.setSize(700, 600);

        // Create an instance of the ExerciseLoggingPanel
        ExerciseLoggingPanel exerciseLoggingPanel = new ExerciseLoggingPanel();

        // Add the ExerciseLoggingPanel to the ExerciseLoggingFrame
        exerciseLoggingFrame.add(exerciseLoggingPanel);

        exerciseLoggingFrame.setVisible(true);
    }

    private void showDeleteProfileConfirmation() {
        int option = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete your profile?", "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Implement the logic to delete the profile
            // ...
        }
    }

    private void showEditProfilePanel() {
        JFrame editProfileFrame = new JFrame("Edit Profile");
        editProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editProfileFrame.setSize(400, 300);

        // Create an instance of the EditProfilePanel
        EditProfilePanel editProfilePanel = new EditProfilePanel(user);
        editProfileFrame.add(editProfilePanel);

        editProfileFrame.setVisible(true);
    }

    private void showSettingsPanel() {
        JFrame settingsFrame = new JFrame("Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(300, 200);

        SettingsPanel settingsPanel = new SettingsPanel();

        settingsFrame.add(settingsPanel);
        settingsFrame.setVisible(true);
    }
}
