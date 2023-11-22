package MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ExerciseLoggingPanel extends JPanel {
    private JTextField dateField;
    private JComboBox<String> exerciseTypeComboBox;
    private JTextField durationField;
    private JComboBox<String> intensityComboBox;
    private JButton logExerciseButton;

    public ExerciseLoggingPanel(String username) {
        setLayout(new GridBagLayout()); // Use GridBagLayout for more control

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Adjust spacing

        // Add labels and input fields for exercise logging
        addComponent(new JLabel("Date:"), 0, 0, constraints);
        dateField = new JTextField(15); // Set the column width (adjust as needed)
        addComponent(dateField, 0, 1, constraints);

        addComponent(new JLabel("Exercise Type:"), 1, 0, constraints);
        String[] exerciseTypes = {"Walking", "Running", "Biking", "Swimming", "Others"};
        exerciseTypeComboBox = new JComboBox<>(exerciseTypes);
        addComponent(exerciseTypeComboBox, 1, 1, constraints);

        addComponent(new JLabel("Duration (minutes):"), 2, 0, constraints);
        durationField = new JTextField(15); // Set the column width
        addComponent(durationField, 2, 1, constraints);

        addComponent(new JLabel("Intensity:"), 3, 0, constraints);
        String[] intensityLevels = {"Low", "Medium", "High", "Very High"};
        intensityComboBox = new JComboBox<>(intensityLevels);
        addComponent(intensityComboBox, 3, 1, constraints);

        // Add a button to log the exercise
        logExerciseButton = new JButton("Log Exercise");
        addComponent(logExerciseButton, 4, 0, 2, 1, constraints);

        // Add an action listener to the log exercise button
        logExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to log the exercise entry here
                logExerciseEntry(username);
            }
        });
    }

    private void addComponent(Component component, int row, int column, GridBagConstraints constraints) {
        constraints.gridx = column;
        constraints.gridy = row;
        add(component, constraints);
    }

    private void addComponent(Component component, int row, int column, int width, int height, GridBagConstraints constraints) {
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        add(component, constraints);
    }

    private void logExerciseEntry(String username) {
        // Retrieve exercise entry information from input fields
        String date = dateField.getText();
        String exerciseType = (String) exerciseTypeComboBox.getSelectedItem();
        int duration = Integer.parseInt(durationField.getText());
        String intensity = Objects.requireNonNull(intensityComboBox.getSelectedItem()).toString();

        // Add code to save the exercise entry to your data storage (e.g., database)
        application.exerciseManager.addExercise(username, date, exerciseType, duration, intensity);

        // Show a confirmation message
        JOptionPane.showMessageDialog(this, "Exercise entry logged successfully!");

        // Clear the input fields after logging the exercise
        clearFields();
    }

    private void clearFields() {
        dateField.setText("");
        exerciseTypeComboBox.setSelectedIndex(0);
        durationField.setText("");
        intensityComboBox.setSelectedIndex(0);
    }
}

