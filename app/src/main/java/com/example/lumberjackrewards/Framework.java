package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.Blob;
import java.lang.Object;
import java.net.http.*;
import java.io.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//pseudo-DB
//structure for storing profile information from DB
class ProfileInfo{

    static String stream(int id, String arg) {
        try {
            String urlString = "cs.sfasu.edu/csci4267-00101/BackFrontEndStrikesBack/access/api/"+arg+id;
            URL url = new URL(urlString);
            InputStream input = url.openStream();
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (Exception e) {
            System.out.println("Failed try/catch stream fn in profileInfo.");
        }
        return "Failed after try/catch stream fn in profileInfo.";

    }

    public static int getID(){
        return 0;
    }
    public static String getName(int id){
        String output = stream(id, "getUser.php?userID=");
        return "";
    }
    public static int getProfilepic(){
        return 0;
    }

    /*
    public int getID(int id){
        return BadgeInfo(id).id;
    }
    public String getName(int id){
        return BadgeInfo(id).name;
    }
    public int getProfilepic(int id){
        return BadgeInfo(id).profilepic;
    }
    */
}

class BadgeInfo{
    static String stream(int id, String arg) {
         try {
                URL url = new URL("https://cs.sfasu.edu/csci4267-00104/BackFrontEndStrikesBack/access/api/getUser.php?userID=1");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                while (in.available() > 0){
                    System.out.print((char)in.read());
                }
         } catch (Exception e) {
                System.out.println("stupid crap failed" + e.toString());
         }
        return "Failed after try/catch stream fn in profileInfo.";

    }

    public static int getID(int id){
        return id;
    }
    public static String getName(int id){

        return stream(id, "getUser.php?badgeID=");
    }
    public static int getProfilepic(int id){
        return 0;
    }

    /* When API is complete replace above with below
    public int getID(int id){
        return BadgeInfo(id).id
    }
    public String getDescription(int id){
        return BadgeInfo(id).description;
    }
    public String getName(int id){
        return BadgeInfo(id).name;
    }
    public String getIcon(int id){
        return BadgeInfo(id).icon;
    }
    public int getCompletionStatus(int id){
        return BadgeInfo(id).completionStatus;
    }
    public int getSteps(int id){
        return BadgeInfo(id).steps;
    }
    public String getRequirements(int id){
        return BadgeInfo(id).requirements;
    }
    */

}



public class Framework extends AppCompatActivity {


}
