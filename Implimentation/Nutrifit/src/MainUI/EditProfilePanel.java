package MainUI;

import application.userManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditProfilePanel extends JPanel {

    public EditProfilePanel(String username) {
        setLayout(new GridLayout(6, 2, 10, 10));
        ArrayList<String> user = userManager.getUserProfile(username);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(user.get(0));
        nameField.setEnabled(false);
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(user.get(1));
        JLabel sexLabel = new JLabel("Sex:");
        JTextField sexField = new JTextField(user.get(2));
        JLabel heightLabel = new JLabel("Height (cm):");
        JTextField heightField = new JTextField(user.get(3));
        JLabel weightLabel = new JLabel("Weight (kg):");
        JTextField weightField = new JTextField(user.get(4));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve edited information
                // Update the user's profile
                userManager.setAge(username, Integer.parseInt(ageField.getText()));
                userManager.setSex(username, sexField.getText());
                userManager.setHeight(username, Double.parseDouble(heightField.getText()));
                userManager.setWeight(username, Double.parseDouble(weightField.getText()));

                // Print the updated profile to the console
                System.out.println("Updated Profile successfully!");

                // Close the EditProfilePanel
                SwingUtilities.getWindowAncestor(EditProfilePanel.this).dispose();
            }
        });

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(sexLabel);
        add(sexField);
        add(heightLabel);
        add(heightField);
        add(weightLabel);
        add(weightField);
        add(new JLabel()); // Empty label for spacing
        add(saveButton);
    }

    private void clearFields() {
        // Clear text fields for the next edit
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }
}

