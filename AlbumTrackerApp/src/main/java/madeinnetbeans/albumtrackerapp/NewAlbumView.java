package madeinnetbeans.albumtrackerapp;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import org.json.simple.*;
import org.json.simple.parser.*;

/*
 *      Author: GROUT GOBBLER
 *      Date Created: 7/15/2025
 *      Project: ALBUM TRACKER APP
 *      File Name: NewAlbumView.java
 */

public class NewAlbumView extends JPanel implements ActionListener {
    String file_name = "User-Saved-Data.json";
    
    // Swing components.
        private JButton submit_button;
        private JPanel user_entry, album_title_panel, artist_name_panel, genre_panel, release_year_panel, tracklist_panel, rating_panel, review_panel;    
        private JTextField album_title_textfield, artist_name_textfield, genre_textfield, release_year_textfield, tracklist_textfield, rating_textfield, review_textfield;
    
    public NewAlbumView() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(new Insets(20,20,20,20)));
        
        BuildInputPanels();
        BuildSubmitButton();
        
        add(user_entry);
        add(submit_button);
    }
    
    // Used in NewAlbumView constructor.
        private void BuildInputPanels() { 
            // Create main user_entry panel.
                user_entry = new JPanel();
                user_entry.setLayout(new BoxLayout(user_entry,BoxLayout.Y_AXIS));

            // Create other panels to go in user_entry.
                album_title_panel = new JPanel();
                album_title_textfield = new JTextField(16);
                album_title_panel.add(new JLabel("Title: "));
                album_title_panel.add(album_title_textfield);

                artist_name_panel = new JPanel();
                artist_name_textfield = new JTextField(16);
                artist_name_panel.add(new JLabel("Artist: "));
                artist_name_panel.add(artist_name_textfield);

                genre_panel = new JPanel();
                genre_textfield = new JTextField(16);
                genre_panel.add(new JLabel("Genre(s): "));
                genre_panel.add(genre_textfield);

                release_year_panel = new JPanel();
                release_year_textfield = new JTextField(16);
                release_year_panel.add(new JLabel("Year of Release: "));
                release_year_panel.add(release_year_textfield);

                tracklist_panel = new JPanel();
                tracklist_textfield = new JTextField(16);
                tracklist_panel.add(new JLabel("Tracklist (separated by commas): "));
                tracklist_panel.add(tracklist_textfield);

                rating_panel = new JPanel();
                rating_textfield = new JTextField(16);
                rating_panel.add(new JLabel("Rating (out of 10): "));
                rating_panel.add(rating_textfield);

                review_panel = new JPanel();
                review_textfield = new JTextField(50);
                review_panel.add(new JLabel("Review: "));
                review_panel.add(review_textfield);

            // Add all panels to user_entry.
                user_entry.add(album_title_panel);
                user_entry.add(artist_name_panel);
                user_entry.add(genre_panel);
                user_entry.add(release_year_panel);
                user_entry.add(tracklist_panel);
                user_entry.add(rating_panel);
                user_entry.add(review_panel);
        }

        private void BuildSubmitButton() {
            submit_button = new JButton("Submit");  
            submit_button.addActionListener(this);
            submit_button.setBounds(50,100,95,30);
        }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try { 
            if (saveDataCheck()) { // Save data found. 
                JSONParser json_parser = new JSONParser();
                    FileReader file_reader = new FileReader(file_name);
                    Object obj = json_parser.parse(file_reader);
                    file_reader.close();
  
                JSONArray json_array = (JSONArray) obj;
                    json_array.add(json_array.size(), buildJSONObject());
                   
                File delete_file = new File (file_name);
                    delete_file.delete();
                    
                writeToFile(json_array);
            }
            else { // Save data not found.
                writeToFile(buildNewJSONArray());
            }    
        }
        catch (Exception except) { 
            JOptionPane.showMessageDialog(null, "An exception occurred.\n" + except, "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        resetTextFields(); 
    }
    
    // Used in actionPerformed().
        public boolean saveDataCheck() { // (T/F) = (exists/doesn't).
            File save_data_check = new File(file_name);
            return (save_data_check.exists() && !save_data_check.isDirectory());
        }
        
        public void writeToFile(JSONArray temp_json_array) {
            try {
                FileWriter test_file_writer = new FileWriter(file_name);
                    test_file_writer.write(temp_json_array.toJSONString());

                test_file_writer.flush();
                test_file_writer.close();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An exception occurred.\n" + e, "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }

        public void resetTextFields() {
            album_title_textfield.setText("");
            artist_name_textfield.setText("");
            genre_textfield.setText("");
            release_year_textfield.setText("");
            tracklist_textfield.setText("");
            rating_textfield.setText("");
            review_textfield.setText("");
        }

        public JSONArray buildNewJSONArray() {            
            JSONArray json_array = new JSONArray();
                json_array.add(0, buildJSONObject());

            return json_array;
        }

        public JSONObject buildJSONObject() {
            JSONObject temp_json_object = new JSONObject();
                temp_json_object.put("Album Title", album_title_textfield.getText());
                temp_json_object.put("Artist Name", artist_name_textfield.getText());
                temp_json_object.put("Genre", genre_textfield.getText());
                temp_json_object.put("Release Year", release_year_textfield.getText());
                temp_json_object.put("Tracklist", tracklist_textfield.getText());
                temp_json_object.put("Rating", rating_textfield.getText());
                temp_json_object.put("Review", review_textfield.getText());
                temp_json_object.put("Date Added", new Date().toString());

            return temp_json_object;
        }
}
