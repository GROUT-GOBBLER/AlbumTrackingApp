package madeinnetbeans.albumtrackerapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import org.json.simple.JSONArray;

/*
 *      Author: GROUT GOBBLER
 *      Date Created: 7/15/2025
 *      Project: ALBUM TRACKER APP
 *      File Name: SettingsView.java
 */

public class SettingsView extends JPanel implements ActionListener {
    JButton delete_button;
    JLabel delete_label;
    private String file_name = "User-Saved-Data.json";
    
    public SettingsView() {
        delete_label = new JLabel("Deletes all of your saved data.");
        delete_button = new JButton("Delete.");
            delete_button.addActionListener(this);
        
        JPanel button_and_label_delete = new JPanel();
            button_and_label_delete.add(delete_button);
            button_and_label_delete.add(delete_label);
        
        setLayout(new BorderLayout());
        add(button_and_label_delete, BorderLayout.CENTER);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        File file_to_be_deleted = new File(file_name);
        if (!file_to_be_deleted.exists()) {
            JOptionPane.showMessageDialog(null, "No saved data exists.", "Failure", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            file_to_be_deleted.delete();
            JOptionPane.showMessageDialog(null, "Saved data has been deleted.", "Sucess", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
}