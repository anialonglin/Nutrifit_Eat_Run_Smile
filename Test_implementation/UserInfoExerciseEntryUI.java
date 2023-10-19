import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoExerciseEntryUI {
    private static JTextField exerciseNameField;
    private static JTextField durationField;
    private static JComboBox<String> exerciseTypeDropdown;
    private static JComboBox<String> intensityDropdown;

    private static JTextField idField;
    private static JTextField nameField;
    private static JTextField ageField;
    private static JTextField sexField;
    private static JTextField heightField;
    private static JTextField weightField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("User Information & Exercise Entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JPanel userPanel = createUserInformationPanel();
        frame.add(userPanel);

        JPanel exercisePanel = createExerciseInformationPanel();
        frame.add(exercisePanel);

        JPanel buttonPanel = createButtonPanel();
        frame.add(buttonPanel);

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createUserInformationPanel() {
        JPanel userPanel = new JPanel(new GridLayout(6, 2));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        sexField = new JTextField();
        heightField = new JTextField();
        weightField = new JTextField();

        userPanel.add(new JLabel("ID:"));
        userPanel.add(idField);

        userPanel.add(new JLabel("Name:"));
        userPanel.add(nameField);

        userPanel.add(new JLabel("Age:"));
        userPanel.add(ageField);

        userPanel.add(new JLabel("Sex:"));
        userPanel.add(sexField);

        userPanel.add(new JLabel("Height (cm):"));
        userPanel.add(heightField);

        userPanel.add(new JLabel("Weight (kg):"));
        userPanel.add(weightField);

        userPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        return userPanel;
    }

    private static JPanel createExerciseInformationPanel() {
        JPanel exercisePanel = new JPanel(new GridLayout(4, 2));

        exerciseNameField = new JTextField();
        durationField = new JTextField();
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

        exercisePanel.setBorder(BorderFactory.createTitledBorder("Exercise Information"));

        return exercisePanel;
    }

    private static JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton submitExerciseButton = new JButton("Submit Exercise Info");
        JButton submitUserButton = new JButton("Submit User Info");

        buttonPanel.add(submitUserButton);
        buttonPanel.add(submitExerciseButton);

        submitExerciseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle exercise information submission
                // Calculate calories and display result (as shown in the previous response)
                calculateCalories();
            }
        });

        submitUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle user information submission
                // Calculate BMR and display result
                calculateBMR();
            }
        });

        return buttonPanel;
    }

    private static void calculateCalories() {
        // Calculate and display calories (as shown in the previous response)
        // ...
    }

    private static void calculateBMR() {
        // Calculate BMR based on user information
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String sex = sexField.getText(); // Assuming "M" or "F"

            double bmr;
            if (sex.equalsIgnoreCase("M")) {
                bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
            } else if (sex.equalsIgnoreCase("F")) {
                bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
            } else {
                bmr = 0; // Invalid sex
            }

            JOptionPane.showMessageDialog(null, "Basal Metabolic Rate (BMR): " + bmr, "BMR Calculation Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numerical values for age, weight, and height.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


