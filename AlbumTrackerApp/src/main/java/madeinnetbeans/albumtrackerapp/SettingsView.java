package madeinnetbeans.albumtrackerapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 *      Author: GROUT GOBBLER
 *      Date Created: 7/15/2025
 *      Project: ALBUM TRACKER APP
 *      File Name: SettingsView.java
 */

public class SettingsView extends JPanel implements ActionListener {
    JButton delete_button;
    JLabel delete_label;
    JLabel testing_code_label;
    
    public SettingsView() {
        delete_label = new JLabel("Deletes all of your saved data.");
        testing_code_label = new JLabel("Nothing much is happening.");
        delete_button = new JButton("Delete.");
            delete_button.addActionListener(this);
        
        JPanel button_and_label_delete = new JPanel();
            button_and_label_delete.add(delete_button);
            button_and_label_delete.add(delete_label);
        
        setLayout(new BorderLayout());
        add(button_and_label_delete, BorderLayout.CENTER);
        add(testing_code_label, BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        testing_code_label.setText("Something happened.");
        JOptionPane.showMessageDialog(null, "Saved data has been deleted.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
    }
}