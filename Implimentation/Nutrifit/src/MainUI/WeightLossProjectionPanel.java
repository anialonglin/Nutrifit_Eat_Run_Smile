package MainUI;

import application.WeightLossCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightLossProjectionPanel extends JPanel {
    private JTextField calorieIntakeField;
    private JTextField exerciseBurnField;
    private JTextField futureDateField;
    private JButton calculateButton;
    private JLabel resultLabel;

    private WeightLossCalculator weightLossCalculator;

    public WeightLossProjectionPanel() {
        weightLossCalculator = new WeightLossCalculator();

        // Set layout manager
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create components
        calorieIntakeField = new JTextField(10);
        exerciseBurnField = new JTextField(10);
        futureDateField = new JTextField(10);
        calculateButton = new JButton("Calculate Weight Loss");
        resultLabel = new JLabel();

        // Add components to the panel
        add(new JLabel("Calories Intake:"));
        add(calorieIntakeField);

        add(new JLabel("Calories Burned through Exercise:"));
        add(exerciseBurnField);

        add(new JLabel("Select a Date in the Future (yyyy-MM-dd):"));
        add(futureDateField);

        add(calculateButton);
        add(resultLabel);

        // Add action listener to the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateWeightLoss();
            }
        });
    }

    private void calculateWeightLoss() {
        try {
            // Get input values
            double calorieIntake = Double.parseDouble(calorieIntakeField.getText());
            double exerciseBurn = Double.parseDouble(exerciseBurnField.getText());
            String futureDateString = futureDateField.getText();

            // Parse future date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date futureDate = dateFormat.parse(futureDateString);

            // Calculate weight loss
            double weightLoss = calculateWeightLoss(calorieIntake, exerciseBurn, futureDate);

            // Display result
            resultLabel.setText("Estimated Weight Loss by " + futureDateString + ": " + weightLoss + " kg");
        } catch (NumberFormatException | ParseException ex) {
            resultLabel.setText("Invalid input. Please enter valid numbers and date.");
        }
    }

    private double calculateWeightLoss(double calorieIntake, double exerciseBurn, Date futureDate) {
        // Calculate the net calorie deficit
        double netCalorieDeficit = calorieIntake - exerciseBurn;

        // Calculate the Weight loss in kg
        double weightLoss = netCalorieDeficit / 7700;

        // Calculate the number of days until the future date
        long daysUntilFutureDate = daysBetween(new Date(), futureDate);

        // Adjust Weight loss based on the number of days
        return weightLoss * daysUntilFutureDate;
    }

    private long daysBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("weight Loss Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            WeightLossProjectionPanel calculatorPanel = new WeightLossProjectionPanel();
            frame.getContentPane().add(calculatorPanel);

            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}
