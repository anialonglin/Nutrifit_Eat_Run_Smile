package MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietLoggingPanel extends JPanel {
    private JTextField dateField;
    private JComboBox<String> mealTypeComboBox;
    private JTextField foodItemField;
    private JTextField quantityField;
    private JButton logDietButton;

    public DietLoggingPanel() {
        setLayout(new GridBagLayout()); // Use GridBagLayout for more control

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Adjust spacing

        // Add labels and input fields for diet logging
        addComponent(new JLabel("Date:"), 0, 0, constraints);
        dateField = new JTextField(15); // Set the column width (adjust as needed)
        addComponent(dateField, 0, 1, constraints);

        addComponent(new JLabel("Meal Type:"), 1, 0, constraints);
        String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Snack"};
        mealTypeComboBox = new JComboBox<>(mealTypes);
        addComponent(mealTypeComboBox, 1, 1, constraints);

        addComponent(new JLabel("Food Item:"), 2, 0, constraints);
        foodItemField = new JTextField(15); // Set the column width
        addComponent(foodItemField, 2, 1, constraints);

        addComponent(new JLabel("Quantity:"), 3, 0, constraints);
        quantityField = new JTextField(15); // Set the column width
        addComponent(quantityField, 3, 1, constraints);

        // Add a button to log the diet
        logDietButton = new JButton("Log Diet");
        addComponent(logDietButton, 4, 0, 2, 1, constraints);

        // Add an action listener to the log diet button
        logDietButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to log the diet entry here
                logDietEntry();
            }
        });

        // Add a button to reload the database
        JButton reloadDatabaseButton = new JButton("Reload Database");
        reloadDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to reload the database here
                reloadDatabase();
            }
        });
        addComponent(reloadDatabaseButton, 5, 1, 1, 1, constraints);
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

    private void logDietEntry() {
        // Retrieve diet entry information from input fields
        String date = dateField.getText();
        String mealType = (String) mealTypeComboBox.getSelectedItem();
        String foodItem = foodItemField.getText();
        String quantity = quantityField.getText();

        // Add code to save the diet entry to your data storage (e.g., database)

        // Show a confirmation message
        JOptionPane.showMessageDialog(this, "Diet entry logged successfully!");

        // Clear the input fields after logging the diet
        clearFields();
    }

    private void reloadDatabase() {
        // Add code to reload the database
        // This can include fetching updated data, refreshing UI, etc.
        JOptionPane.showMessageDialog(this, "Database Reloaded!");
    }

    private void clearFields() {
        dateField.setText("");
        mealTypeComboBox.setSelectedIndex(0);
        foodItemField.setText("");
        quantityField.setText("");
    }
}
