package MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainUIScreen {
    private boolean closeWindow = false;

    public UserMainUIScreen(String username) {
        // Create and display the UserMainUIScreen
        JFrame userMainScreenFrame = new JFrame("User Main Screen");
        userMainScreenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userMainScreenFrame.setSize(700, 600);

        // Create an instance of the UserMainUIScreen
        JPanel mainPanel = new JPanel();
        JButton logDietButton = new JButton("Log Diet");
        JButton logExerciseButton = new JButton("Log Exercise");
        JButton DietAlignmentButton = new JButton("Diet Alignment CFG ");
        JButton WeightLossProjectionButton = new JButton("Weight Loss Calculator");
        JButton CalorieExerciseChartButton = new JButton("Calorie&Exercise Chart");
        JButton nutrientIntakeChartButton = new JButton("Nutrient Intake Chart");
        JButton editProfileButton = new JButton("Edit Profile");
        JButton deleteProfileButton = new JButton("Delete Profile");
        JButton settingsButton = new JButton("Settings");

        JLabel welcomeLabel = new JLabel("Welcome " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 19)); // Set the font explicitly for the Label component

        mainPanel.add(logDietButton);
        mainPanel.add(logExerciseButton);
        mainPanel.add(DietAlignmentButton);
        mainPanel.add(WeightLossProjectionButton);
        mainPanel.add(CalorieExerciseChartButton);
        mainPanel.add(nutrientIntakeChartButton);
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
                showDietLoggingPanel(username);
            }
        });

        logExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the ExerciseLoggingPanel
//                profile user = userManager.getUserProfile(testProfileId);
                showExerciseLoggingPanel(username);
            }
        });

        WeightLossProjectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWeightLossProjectionPanel(username);
            }
        });

        DietAlignmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDietAlignmentPanel(username);
            }
        });

        CalorieExerciseChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCalorieExerciseChart(username);
            }
        });

        nutrientIntakeChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNutrientIntakeChart(username);
            }
        });

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle edit profile functionality
                showEditProfilePanel(username);
            }
        });

        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle delete profile functionality
                showDeleteProfileConfirmation(username);
                // if they pressed yes, close the main ui window
                if (closeWindow) {
                    closeWindow = false;
                    userMainScreenFrame.dispose();
                }
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

    private void showDietLoggingPanel(String username) {
        // Create and display the DietLoggingPanel
        JFrame dietLoggingFrame = new JFrame("Diet Logging Management");
        dietLoggingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dietLoggingFrame.setSize(700, 600);

        // Create an instance of the DietLoggingPanel
        DietLoggingPanel dietLoggingPanel = new DietLoggingPanel(username);

        // Add the DietLoggingPanel to the DietLoggingFrame
        dietLoggingFrame.add(dietLoggingPanel);

        dietLoggingFrame.setVisible(true);
    }

    private void showExerciseLoggingPanel(String username) {
        // Create and display the ExerciseLoggingPanel
        JFrame exerciseLoggingFrame = new JFrame("Exercise Logging Management");
        exerciseLoggingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        exerciseLoggingFrame.setSize(700, 600);

        // Create an instance of the ExerciseLoggingPanel
        ExerciseLoggingPanel exerciseLoggingPanel = new ExerciseLoggingPanel(username);

        // Add the ExerciseLoggingPanel to the ExerciseLoggingFrame
        exerciseLoggingFrame.add(exerciseLoggingPanel);

        exerciseLoggingFrame.setVisible(true);
    }

    private void showCalorieExerciseChart(String user) {
        JFrame calorieExerciseChartFrame = new JFrame("CalorieExerciseChart Management");
        calorieExerciseChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        calorieExerciseChartFrame.setSize(1000, 800);

        CalorieExerciseChart calorieExerciseChart = new CalorieExerciseChart("Calorie and Exercise Chart", user);

        calorieExerciseChartFrame.setContentPane(calorieExerciseChart);

        calorieExerciseChartFrame.setVisible(true);
    }

    private void showNutrientIntakeChart(String username) {
        // Create and display the NutrientIntakeChart
        JFrame nutrientIntakeChartFrame = new JFrame("Nutrient Intake Chart");
        nutrientIntakeChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nutrientIntakeChartFrame.setSize(1000, 800);

        // Create an instance of NutrientIntakeChart
        NutrientIntakeChart nutrientIntakeChart = new NutrientIntakeChart(username);

        nutrientIntakeChartFrame.add(nutrientIntakeChart);

        nutrientIntakeChartFrame.setVisible(true);
    }

    private void showWeightLossProjectionPanel(String username) {
        JFrame weightLossProjectionFrame = new JFrame("Weight Loss Projection");
        weightLossProjectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        weightLossProjectionFrame.setSize(400, 300);

        // Create an instance of the WeightLossProjectionPanel
        WeightLossProjectionPanel weightLossProjectionPanel = new WeightLossProjectionPanel(username);
        weightLossProjectionFrame.add(weightLossProjectionPanel);

        weightLossProjectionFrame.setVisible(true);
    }

    private void showDietAlignmentPanel(String username) {
        JFrame dietAlignmentFrame = new JFrame("Diet Alignment Panel");
        dietAlignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dietAlignmentFrame.setSize(1000, 800);
        DietAlignmentPanel dietAlignmentPanel = new DietAlignmentPanel(username);
        dietAlignmentFrame.add(dietAlignmentPanel);
        dietAlignmentFrame.setVisible(true);
    }

    private void showDeleteProfileConfirmation(String username) {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your profile?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            application.userManager.deleteUserProfile(username);
            JOptionPane.showMessageDialog(null, "Profile deleted successfully!");
            closeWindow = true;
        }
    }

    private void showEditProfilePanel(String username) {
        JFrame editProfileFrame = new JFrame("Edit Profile");
        editProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editProfileFrame.setSize(400, 300);

        // Create an instance of the EditProfilePanel
        EditProfilePanel editProfilePanel = new EditProfilePanel(username);
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
