package MainUI;

import application.WeightLossCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightLossProjectionPanel extends JPanel {
    private JLabel calorieIntakeField;
    private JLabel exerciseBurnField;
    private JTextField futureDateField;
    private JButton calculateButton;
    private JLabel resultLabel;

    private WeightLossCalculator weightLossCalculator;

    public WeightLossProjectionPanel(String username) {
        weightLossCalculator = new WeightLossCalculator();

        // Set layout manager
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create components
        calorieIntakeField = new JLabel(application.foodManager.avgCalories(username));
        exerciseBurnField = new JLabel();
        futureDateField = new JTextField(10);
        calculateButton = new JButton("calculate");
        resultLabel = new JLabel();

        // Add components to the panel
        add(new JLabel("Average daily Calories Intake:"));
        add(calorieIntakeField);

        add(new JLabel("Average Calories Burned through Exercise:"));
        add(exerciseBurnField);

        add(new JLabel("Select a Date in the Future (mm-dd-yyyy):"));
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
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
