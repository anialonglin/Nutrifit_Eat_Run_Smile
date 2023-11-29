package MainUI;

import application.foodManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DietLoggingPanel extends JPanel {
    private final JTextField dateField;
    private final JComboBox<String> mealTypeComboBox;
    private final JTextField foodItemField;
    private final JTextField quantityField;
    private final JButton logDietButton;
    private final JComboBox<String> foodItemDropdown;

    public DietLoggingPanel(String username) {
        setLayout(new GridBagLayout()); // Use GridBagLayout for more control

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Adjust spacing

        // Add labels and input fields for diet logging
        addComponent(new JLabel("Date(dd-MM-yyyy):"), 0, 0, constraints);
        dateField = new JTextField(15); // Set the column width (adjust as needed)
        addComponent(dateField, 0, 1, constraints);

        addComponent(new JLabel("Meal Type:"), 1, 0, constraints);
        String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Snack"};
        mealTypeComboBox = new JComboBox<>(mealTypes);
        addComponent(mealTypeComboBox, 1, 1, constraints);

        addComponent(new JLabel("Food Item:"), 2, 0, constraints);
        foodItemField = new JTextField(15); // Set the column width
        addComponent(foodItemField, 2, 1, constraints);

        foodItemDropdown = new JComboBox<>(); // Set the column width
        addComponent(foodItemDropdown, 3, 1, constraints);

        addComponent(new JLabel("Quantity:"), 4, 0, constraints);
        quantityField = new JTextField(15); // Set the column width
        addComponent(quantityField, 4, 1, constraints);

        // Add a button to log the diet
        logDietButton = new JButton("Log Diet");
        addComponent(logDietButton, 6, 0, 2, 1, constraints);

        // Add an action listener to the log diet button
        logDietButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to log the diet entry here
                logDietEntry(username);
                foodItemDropdown.removeAllItems();
            }
        });

        //add lister to foodItemField to update dropdown
        foodItemField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to log the diet entry here
                foodManager.queryFoodItem(foodItemField.getText());
                //Add the list of food items to the dropdown
                foodItemDropdown.removeAllItems();
                for (String foodItem : foodManager.queryFoodItem(foodItemField.getText())) {
                    foodItemDropdown.addItem(foodItem);
                }
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

    private void logDietEntry(String username) {
        // Retrieve diet entry information from input fields
        String date = dateField.getText();
        String mealType = (String) mealTypeComboBox.getSelectedItem();
        String foodItem = foodItemDropdown.getSelectedItem().toString();
        int quantity = Integer.parseInt(quantityField.getText());

        // Add code to save the diet entry to your data storage (e.g., database)
        foodManager.addMeal(username, date, mealType, foodItem, quantity);
        // Show a confirmation message
        JOptionPane.showMessageDialog(this, "Diet entry logged successfully!");

        // Clear the input fields after logging the diet
        clearFields();
    }

    private void reloadDatabase() {
        // Add code to reload the database;
        foodManager.reloadDatabase();
        // This can include fetching updated data, refreshing UI, etc.
        JOptionPane.showMessageDialog(this, "database Reloaded!");
    }

    private void clearFields() {
        dateField.setText("");
        mealTypeComboBox.setSelectedIndex(0);
        foodItemField.setText("");
        quantityField.setText("");
    }
}
