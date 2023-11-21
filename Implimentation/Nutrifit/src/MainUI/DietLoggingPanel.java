package MainUI;

import application.nutritionDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietLoggingPanel extends JPanel {
    private JTextField dateField;
    private JComboBox<String> mealTypeComboBox;
    private JTextField foodItemField;
    private JTextField quantityField;
    private JButton logDietButton;
    private JList<String> foodItemSuggestions;

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

        // Suggestions for matching food items
        foodItemSuggestions = new JList<>();
        JScrollPane suggestionsScrollPane = new JScrollPane(foodItemSuggestions);
        suggestionsScrollPane.setPreferredSize(new Dimension(150, 70));
        addComponent(suggestionsScrollPane, 3, 1, constraints);

        addComponent(new JLabel("Quantity:"), 4, 0, constraints);
        quantityField = new JTextField(15); // Set the column width
        addComponent(quantityField, 4, 1, constraints);

        // Add a button to log the diet
        logDietButton = new JButton("Log Diet");
        addComponent(logDietButton, 5, 0, 2, 1, constraints);

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
        addComponent(reloadDatabaseButton, 6, 1, 1, 1, constraints);

        // Add a key listener to the food item field for suggestions
        foodItemField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                suggestFoodItems();
            }
        });
    }

    private void addComponent(Component component, int row, int column, GridBagConstraints constraints) {
        constraints.gridx = column;
        constraints.gridy = row;
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

    private void suggestFoodItems() {
        String input = foodItemField.getText().toLowerCase();
        List<String> suggestions = getMatchingFoodItems(input);

        DefaultListModel<String> model = new DefaultListModel<>();
        for (String suggestion : suggestions) {
            model.addElement(suggestion);
        }

        foodItemSuggestions.setModel(model);
    }

    private List<String> getMatchingFoodItems(String query) {
        List<String> matchingFoodItems = new ArrayList<>();

        // Retrieve the raw HashMap from nutritionDataManager
        HashMap rawFoodItems = nutritionDataManager.listFoods();

        // Iterate over the rawFoodItems and add matching items to the list
        for (Object key : rawFoodItems.keySet()) {
            String foodItem = (String) key;
            if (foodItem.toLowerCase().contains(query.toLowerCase())) {
                matchingFoodItems.add(foodItem);
            }
        }

        return matchingFoodItems;
    }

}
