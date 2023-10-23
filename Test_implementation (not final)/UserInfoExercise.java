import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoExercise {
    private static JTextField exerciseNameField;
    private static JTextField durationField;
    private static JComboBox<String> exerciseTypeDropdown;
    private static JComboBox<String> intensityDropdown;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Exercise Information Entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1));

        JPanel exercisePanel = createExerciseInformationPanel();
        frame.add(exercisePanel);

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createExerciseInformationPanel() {
        JPanel exercisePanel = new JPanel(new GridLayout(0, 2, 10, 5));

        exerciseNameField = new JTextField(10);  // Specify the width here
        durationField = new JTextField(10);  // Specify the width here
        exerciseTypeDropdown = new JComboBox<>(new String[]{"Walking", "Running", "Biking", "Swimming", "Other"});
        intensityDropdown = new JComboBox<>(new String[]{"Low", "Medium", "High", "Very High"});

        exercisePanel.add(new JLabel("Exercise Name:"));
        exercisePanel.add(exerciseNameField);

        exercisePanel.add(new JLabel("Duration (minutes):"));
        exercisePanel.add(durationField);

        exercisePanel.add(new JLabel("Type:"));
        exercisePanel.add(exerciseTypeDropdown);

        exercisePanel.add(new JLabel("Intensity:"));
        exercisePanel.add(intensityDropdown);

        JButton submitExerciseButton = new JButton("Submit Exercise Info");
        exercisePanel.add(submitExerciseButton);

        submitExerciseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Calculate calories and display result
                calculateCalories();
            }
        });

        exercisePanel.setBorder(BorderFactory.createTitledBorder("Exercise Information"));

        return exercisePanel;
    }

    private static void calculateCalories() {
        // Calculate calories based on exercise information
        try {
            int duration = Integer.parseInt(durationField.getText());
            String exerciseType = exerciseTypeDropdown.getSelectedItem().toString();
            String intensity = intensityDropdown.getSelectedItem().toString();

            double calorieMultiplier = 1.0;

            // Apply exercise type multipliers
            if (exerciseType.equals("Running")) {
                calorieMultiplier *= 6.0;
            } else if (exerciseType.equals("Biking")) {
                calorieMultiplier *= 5.0;
            } else if (exerciseType.equals("Swimming")) {
                calorieMultiplier *= 7.0;
            } else {
                calorieMultiplier *= 3.0;
            }

            // Apply intensity multipliers
            if (intensity.equals("High")) {
                calorieMultiplier *= 1.4;
            } else if (intensity.equals("Very High")) {
                calorieMultiplier *= 1.6;
            } else if (intensity.equals("Medium")) {
                calorieMultiplier *= 1.2;
            }

            double caloriesBurned = duration * 4 * calorieMultiplier;

            JOptionPane.showMessageDialog(null, "Calories Burned: " + caloriesBurned, "Calories Calculation Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numerical values for duration.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
