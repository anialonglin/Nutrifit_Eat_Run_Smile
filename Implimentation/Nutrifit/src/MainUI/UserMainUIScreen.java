package MainUI;

import application.userManager;
import dataAccess.userData.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class UserMainUIScreen {
    private profile user;
    private int testProfileId;

    public UserMainUIScreen(profile user) {
        this.user = user;
        this.testProfileId = testProfileId;

        // Create and display the UserMainUIScreen
        JFrame userMainScreenFrame = new JFrame("User Main Screen");
        userMainScreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userMainScreenFrame.setSize(700, 600);

        // Create an instance of the UserMainUIScreen
        JPanel mainPanel = new JPanel();
        JButton logDietButton = new JButton("Log Diet");
        JButton logExerciseButton = new JButton("Log Exercise");
//        JButton mealSummaryButton = new JButton("View Meal Summary");
        JButton CalorieExerciseChartButton = new JButton("Calorie&Exercise Chart");
        JButton editProfileButton = new JButton("Edit Profile");
        JButton deleteProfileButton = new JButton("Delete Profile");
        JButton settingsButton = new JButton("Settings");

        JLabel welcomeLabel = new JLabel("Hi, " + user.getName() + ". Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 19)); // Set the font explicitly for the Label component

        mainPanel.add(logDietButton);
        mainPanel.add(logExerciseButton);
//        mainPanel.add(mealSummaryButton);
        mainPanel.add(CalorieExerciseChartButton);
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
                profile user = userManager.getUserProfile(testProfileId);
                showExerciseLoggingPanel();
            }
        });

//        mealSummaryButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                showMealSummaryPanel();
//            }
//        });

        CalorieExerciseChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCalorieExerciseChart(user);
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

//    private void showMealSummaryPanel() {
//        JFrame mealSummaryFrame = new JFrame("MealSummary Management");
//        mealSummaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        mealSummaryFrame.setSize(700, 600);
//
//        MealSummaryPanel mealSummaryPanel = new MealSummaryPanel();
//
//        mealSummaryFrame.add(mealSummaryPanel);
//
//        mealSummaryFrame.setVisible(true);
//    }

    private void showCalorieExerciseChart(profile user) {
        JFrame calorieExerciseChartFrame = new JFrame("CalorieExerciseChart Management");
        calorieExerciseChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calorieExerciseChartFrame.setSize(1000, 900);

        CalorieExerciseChart calorieExerciseChart = new CalorieExerciseChart("Calorie and Exercise Chart", user);

        calorieExerciseChartFrame.setContentPane(calorieExerciseChart);

        calorieExerciseChartFrame.setVisible(true);
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
