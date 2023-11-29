package MainUI;

import application.WeightLossCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class WeightLossProjectionPanel extends JPanel {
    private JLabel calorieIntakeField;
    private JLabel exerciseBurnField;
    private JLabel BMRField;
    private JTextField futureDateField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public WeightLossProjectionPanel(String username) {

        // Set layout manager
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create components
        calorieIntakeField = new JLabel(String.valueOf(application.foodManager.avgCalories(username)));
        exerciseBurnField = new JLabel(String.valueOf(application.exerciseManager.avgExercise(username)));
        BMRField = new JLabel(String.valueOf(application.WeightLossCalculator.BMRcalc(username)));
        futureDateField = new JTextField(10);
        calculateButton = new JButton("calculate");
        resultLabel = new JLabel();

        // Add components to the panel
        add(new JLabel("Average daily Calories Intake:"));
        add(calorieIntakeField);
        add(new JLabel("Average Calories Burned through Exercise:"));
        add(exerciseBurnField);
        add(new JLabel("BMR:"));
        add(BMRField);
        add(new JLabel("Select a Date in the Future (dd-MM-yyyy):"));
        add(futureDateField);
        add(calculateButton);
        add(resultLabel);

        // Add action listener to the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateWeightLoss(username);
            }
        });
    }

    private void calculateWeightLoss(String username) {
        try {
            // Get date
            String futureDateString = futureDateField.getText();

            // Parse future date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date futureDate = dateFormat.parse(futureDateString);

            // Calculate weight loss
            double weightLoss = WeightLossCalculator.calculateWeightLoss(username, futureDate);

            // Display result
            resultLabel.setText("Estimated Weight Loss by " + futureDateString + ": " + weightLoss + " kg");
        } catch (NumberFormatException | ParseException ex) {
            resultLabel.setText("Invalid input. Please enter valid numbers and date.");
        }
    }
}
