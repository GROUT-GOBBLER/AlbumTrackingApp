package madeinnetbeans.albumtrackerapp;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import org.json.simple.*;
import java.util.ArrayList;
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
    
    private JButton refresh_button;
    private JComboBox<String> sort_selector;
    
    private int current_sort_type;
        private static final int ALBUM_TITLE = 0;
        private static final int ARTIST_NAME = 1;
        private static final int DATE_ADDED = 2;
    
    public AlbumListView() {
        list_of_album_titles = new ArrayList<>();    
        list_of_albums_json = new ArrayList<>();
        
        current_sort_type = DATE_ADDED;
            
        populateArrayList(current_sort_type);        
        buildView();
    }
    
    private void populateArrayList(int sort_type) {
        list_of_album_titles = new ArrayList<>();    
        list_of_albums_json = new ArrayList<>();
        
        if (saveDataCheck()) {
            try {
                FileReader read_from_file = new FileReader(file_name);
                    Object read_in_from_file = new JSONParser().parse(read_from_file);
                    read_from_file.close();

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
    }
    
    // For populateArrayList().
        public boolean saveDataCheck() { // (T/F) = (exists/doesn't).
        File save_data_check = new File(file_name);
        return (save_data_check.exists() && !save_data_check.isDirectory());
    }
    
    private void buildView() {        
        buildRefreshButton();
        buildSortSelector();
        
        JPanel top_panel = new JPanel();
            top_panel.add(refresh_button);
            top_panel.add(sort_selector);
            top_panel.setMaximumSize(top_panel.getPreferredSize());
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(top_panel);
            add(new JScrollPane(buildAlbumListObject()));
            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    
    // For buildView().
        private void buildRefreshButton() {
            refresh_button = new JButton("Refresh");
            refresh_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshView(current_sort_type);
                }
            });
        }
        
        public void refreshView(int sort_type) {
            removeAll();
            
            populateArrayList(current_sort_type);
            buildView();

            revalidate();
            repaint();
        }
        
        private void buildSortSelector() {
            String sort_methods_to_choose[] = { "Album Title", "Artist Name", "Date Added" };
            
            sort_selector = new JComboBox<>(sort_methods_to_choose);
                sort_selector.setBounds(0,0,140,20);
                sort_selector.setSelectedIndex(current_sort_type);
                
            sort_selector.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    current_sort_type = sort_selector.getSelectedIndex();
                    refreshView(current_sort_type);
                    JOptionPane.showMessageDialog(null, current_sort_type, "Test!", JOptionPane.ERROR_MESSAGE);

                }
            });
            
        }

        private JList buildAlbumListObject() {
            JList temp_jlist = new JList<>(list_of_album_titles.toArray(new String[list_of_album_titles.size()]));

            temp_jlist.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (me.getClickCount() == 2) {
                        showAlbumInfo(temp_jlist.getSelectedIndex());
                    }
                }
            });

            temp_jlist.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        showAlbumInfo(temp_jlist.getSelectedIndex());
                    }
                }
            });

            return temp_jlist;
        }

        public void showAlbumInfo(int selected_item_index) {
            JSONObject selected_json_object = list_of_albums_json.get(selected_item_index);

            String album_title = (String) selected_json_object.get("Album Title");
                String artist_name = (String) selected_json_object.get("Artist Name");
                String release_year = (String) selected_json_object.get("Release Year");
                String genre = (String) selected_json_object.get("Genre");
                String rating = (String) selected_json_object.get("Rating");
                String review = (String) selected_json_object.get("Review");

            JLabel album_title_name_release_year_label = new JLabel("'" + album_title + "' (" + release_year + ") by " + artist_name);
                JLabel album_genre_label = new JLabel("Genre: " + genre);
                JLabel album_rating_label = new JLabel("Rating: " + rating);
                JLabel album_review_label = new JLabel("Review: " + review);

            JPanel popup_window_panel = new JPanel();
                popup_window_panel.setLayout(new BoxLayout(popup_window_panel, BoxLayout.Y_AXIS));

                popup_window_panel.add(album_title_name_release_year_label);
                popup_window_panel.add(album_genre_label);
                popup_window_panel.add(album_rating_label);
                popup_window_panel.add(album_review_label);

            JFrame popup_window = new JFrame();
                popup_window.setTitle("Album Overview.");
                popup_window.add(popup_window_panel);
                popup_window.setBounds(100,50,500,500);
                popup_window.setVisible(true);
        }
}
