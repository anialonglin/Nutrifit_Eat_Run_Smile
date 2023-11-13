package MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {

    public SettingsPanel() {
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton changePasswordButton = new JButton("Change Password");
        JButton notificationSettingsButton = new JButton("Notification Settings");
        JButton closeButton = new JButton("Close");

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic for changing password
                JOptionPane.showMessageDialog(null, "Change Password functionality goes here.");
            }
        });

        notificationSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic for notification settings
                JOptionPane.showMessageDialog(null, "Notification Settings functionality goes here.");
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the settings panel
                SwingUtilities.getWindowAncestor(SettingsPanel.this).dispose();
            }
        });

        add(changePasswordButton);
        add(notificationSettingsButton);
        add(closeButton);
    }
}

