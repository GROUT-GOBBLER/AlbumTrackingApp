 package madeinnetbeans.albumtrackerapp;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
 
/*
 *      Author: GROUT GOBBLER
 *      Date Created: 7/14/2025
 *      Project: ALBUM TRACKER APP
 *      File Name: AlbumTrackerApp.java
 */
 
public class AlbumTrackerApp {
    private static FileWriter jsonFile;
    
    public static void main(String[] args) {
        // Using GUI ... https://www.geeksforgeeks.org/java/introduction-to-java-swing/
        // Parsing JSON ... https://www.youtube.com/watch?v=0nN2stWIHM0
        // Write JSON to file ... https://crunchify.com/how-to-write-json-object-to-file-in-java/
        // Reading JSON from file ... https://www.delftstack.com/howto/java/read-json-file-java/
            // ^ If writing before reading, need to flush and close FileWriter before reading.
        
        JSONObject albumObject = new JSONObject();
            albumObject.put("Name", "yea I think");
            albumObject.put("Artist", "cr1tter");
            albumObject.put("Genre", "Cloud Rap");
            albumObject.put("Release Year", "2024");
            
            JSONArray trackList = new JSONArray();
                trackList.add("Song: dolce & gabbana");
                trackList.add("Song: etch a sketch");
                trackList.add("Song: rob dyrdek");
                trackList.add("Song: beans");
                trackList.add("Song: now i rlly kno");
                trackList.add("Song: bella thorne");
                trackList.add("Song: fall out");
                trackList.add("Song: pls dont forget me");
            albumObject.put("Track List", trackList);
            
            albumObject.put("Rating", "?/5");
            albumObject.put("Review", "Haven't listened to it yet.");
            albumObject.put("Date Added", "7/14/2025");
        
        try {
            jsonFile = new FileWriter("jsonTest.json");
            jsonFile.write(albumObject.toJSONString());
        }
        catch(Exception e) {
            System.out.println("An exception occured while trying to create / write to the file.");
            return;
        }
        
        try {
            jsonFile.flush();
            jsonFile.close();
        } catch (IOException ex) {
            System.out.println("Failed at flush / close step.");
            return;
        }
        
        FileReader file_reader;
        JSONObject album_object_from_file;
        
        try {
            file_reader = new java.io.FileReader("jsonTest.json");
            JSONParser parser = new JSONParser();
            album_object_from_file = (JSONObject) parser.parse(file_reader);
        } catch (Exception ex) {
            System.out.println("Exception at file reader. " + ex);
            return;
        }
        
        String name_test = (String) album_object_from_file.get("Name");
        
        System.out.println("Name ... " + name_test);
        
        System.out.println("Program end.");
    }
}
