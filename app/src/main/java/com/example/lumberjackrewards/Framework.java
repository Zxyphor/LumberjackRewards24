package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.sql.Blob;
import java.net.*;
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
        String output = stream(id, "getUser.php?badgeID=");
        return output;
    }
    public static int getProfilepic(){
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
