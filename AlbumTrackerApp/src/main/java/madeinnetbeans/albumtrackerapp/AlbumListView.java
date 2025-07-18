package madeinnetbeans.albumtrackerapp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 *      Author: GROUT GOBBLER
 *      Date Created: 7/15/2025
 *      Project: ALBUM TRACKER APP
 *      File Name: AlbumListView.java
 */

public class AlbumListView extends JPanel {
    private ArrayList<String> list_of_album_titles;
    private ArrayList<JSONObject> list_of_albums_json;
    private String file_name = "User-Saved-Data.json";
    
    public AlbumListView() {        
        list_of_album_titles = new ArrayList<>();    
        list_of_albums_json = new ArrayList<>();
            
        populateArrayList();
        buildView();
    }
    
    private void populateArrayList() {
        try {
            Object read_in_from_file = new JSONParser().parse(new FileReader(file_name));
            JSONArray json_array = (JSONArray) read_in_from_file;
            
            for (int x = 0; x < json_array.size(); x++ ) {
                JSONObject album_as_json = (JSONObject) json_array.get(x);
                String album_name = (String) album_as_json.get("Album Title");
                
                list_of_album_titles.add(album_name);
                list_of_albums_json.add(album_as_json);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An exception occurred.\n" + e, "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buildView() {
        JList blah = new JList<>(list_of_album_titles.toArray(new String[list_of_album_titles.size()]));
        
        blah.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(null, "Double click.", "Swg!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
       blah.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    JOptionPane.showMessageDialog(null, "Enter.", "Swg!", JOptionPane.INFORMATION_MESSAGE);                
                }
            }
        });
         
        add(new JScrollPane(blah));
    }
}
