package MainUI;

import dataAccess.HC_Old_user_data.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfilePanel extends JPanel {
    private profile user;

    public EditProfilePanel(profile user) {
        this.user = user;

        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(user.getName());
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(String.valueOf(user.getAge()));
        JLabel sexLabel = new JLabel("Sex:");
        JTextField sexField = new JTextField(String.valueOf(user.isSex()));
        JLabel heightLabel = new JLabel("Height (cm):");
        JTextField heightField = new JTextField(String.valueOf(user.getHeight()));
        JLabel weightLabel = new JLabel("Weight (kg):");
        JTextField weightField = new JTextField(String.valueOf(user.getWeight()));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve edited information
                String newName = nameField.getText();
                int newAge = Integer.parseInt(ageField.getText());
                // Retrieve other fields as needed

                // Update the user's profile
                user.setName(newName);
                user.setAge(newAge);
                // Update other fields as needed

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

