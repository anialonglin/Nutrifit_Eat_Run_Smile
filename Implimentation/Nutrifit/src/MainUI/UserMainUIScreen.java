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

        JLabel welcomeLabel = new JLabel("Hi, " + user.getName() + ". Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 19)); // Set the font explicitly for the Label component

        mainPanel.add(logDietButton);
        mainPanel.add(logExerciseButton);
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
}
