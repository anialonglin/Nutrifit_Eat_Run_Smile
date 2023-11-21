package MainUI;

import dataAccess.userData.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ProfileManagementPanel extends JPanel {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField sexField;
    private JTextField heightField;
    private JTextField weightField;
    private JButton saveProfileButton;

    public ProfileManagementPanel() {
        setLayout(new GridBagLayout()); // Use GridBagLayout for more control

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Adjust spacing

        // Add labels and input fields for profile information
        addComponent(new JLabel("User Name:"), 0, 0, constraints);
        nameField = new JTextField(15); // Set the column width (adjust as needed)
        addComponent(nameField, 0, 1, constraints);

        addComponent(new JLabel("Age:"), 1, 0, constraints);
        ageField = new JTextField(15); // Set the column width
        addComponent(ageField, 1, 1, constraints);

        addComponent(new JLabel("Sex:"), 2, 0, constraints);
        sexField = new JTextField(15); // Set the column width
        addComponent(sexField, 2, 1, constraints);

        addComponent(new JLabel("Height (cm):"), 3, 0, constraints);
        heightField = new JTextField(15); // Set the column width
        addComponent(heightField, 3, 1, constraints);

        addComponent(new JLabel("Weight (kg):"), 4, 0, constraints);
        weightField = new JTextField(15); // Set the column width
        addComponent(weightField, 4, 1, constraints);

        // Add a button to save the profile
        saveProfileButton = new JButton("Save Profile");
        addComponent(saveProfileButton, 5, 0, 2, 1, constraints);


        // Add an action listener to the save button
        saveProfileButton.addActionListener(e -> {
            // Retrieve profile information from input fields
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            boolean sex = Boolean.parseBoolean(sexField.getText());
            int height = Integer.parseInt(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());

            // Create a profile object and save it (Profile class)
            profile newProfile = new profile(name, age, sex, height, weight);
            // Save or update the profile using database

            // Show a confirmation message
            JOptionPane.showMessageDialog(this, "Profile saved successfully!");

            // Clear the input fields after save the profile
            clearFields();
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

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        sexField.setText("");
        heightField.setText("");
        weightField.setText("");
    }
}
