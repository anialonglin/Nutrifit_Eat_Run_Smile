import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInformationUI {
    private static JTextField idField;
    private static JTextField nameField;
    private static JTextField ageField;
    private static JTextField sexField;
    private static JTextField heightField;
    private static JTextField weightField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("User Information Entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 1));

        JPanel userPanel = createUserInformationPanel();
        frame.add(userPanel);

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createUserInformationPanel() {
        JPanel userPanel = new JPanel(new GridLayout(0, 2, 10, 5));

        idField = new JTextField(10);  // Specify the width here
        nameField = new JTextField(10);  // Specify the width here
        ageField = new JTextField(10);  // Specify the width here
        sexField = new JTextField(10);  // Specify the width here
        heightField = new JTextField(10);  // Specify the width here
        weightField = new JTextField(10);  // Specify the width here

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

        JButton submitUserButton = new JButton("Submit User Info");
        userPanel.add(submitUserButton);

        submitUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Calculate BMR and display result
                calculateBMR();
            }
        });

        userPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        return userPanel;
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
