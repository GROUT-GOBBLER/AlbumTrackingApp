 package madeinnetbeans.albumtrackerapp;

import javax.swing.*;
 
/*
 *      Author: GROUT GOBBLER
 *      Date Created: 7/14/2025
 *      Project: ALBUM TRACKER APP
 *      File Name: AlbumTrackerApp.java
 */
 
 /*
    NOTES.
        Using GUI ... https://www.geeksforgeeks.org/java/introduction-to-java-swing/
        Parsing JSON ... https://www.youtube.com/watch?v=0nN2stWIHM0
        Write JSON to file ... https://crunchify.com/how-to-write-json-object-to-file-in-java/
        Reading JSON from file ... https://www.delftstack.com/howto/java/read-json-file-java/
            ^ If writing before reading, need to flush and close FileWriter before reading.
        Delete file ... https://www.w3schools.com/java/java_files_delete.asp
        Make a button click do something ... https://www.tutorialsfield.com/jbutton-click-event/
        JFrame ... https://www.geeksforgeeks.org/java/java-jframe/
        JOptionPane ... https://www.geeksforgeeks.org/java/java-joptionpane/
        JScrollBar ... https://www.geeksforgeeks.org/java/java-jscrollbar/
        GridLayout ... https://www.geeksforgeeks.org/java/java-awt-gridlayout-class/
        JList ... https://www.geeksforgeeks.org/java/java-swing-jlist-with-examples/
        Check if file exists ... https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
 */

 
public class AlbumTrackerApp {
    // Global Variables.
        private static String file_name = "toBeDeleted.json";
        private static JLabel code_tester_label;
        
        private static JFrame main_window;
        
        private static AlbumListView first_tab = new AlbumListView();
        private static NewAlbumView second_tab = new NewAlbumView();
        private static SettingsView third_tab = new SettingsView();
        
    public static void main(String[] args) {
        SetupFrame();
    }
    
    public static void SetupFrame() {
        main_window = new JFrame("Album Tracker App");
                main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                main_window.setSize(800, 500);

        JTabbedPane panel_of_tabs = new JTabbedPane(JTabbedPane.LEFT);

        panel_of_tabs.addTab("View All.", first_tab);
        panel_of_tabs.addTab("New Album Entry.", second_tab);
        panel_of_tabs.addTab("Settings.", third_tab);

        main_window.add(panel_of_tabs);
        main_window.setVisible(true);
    }
}
