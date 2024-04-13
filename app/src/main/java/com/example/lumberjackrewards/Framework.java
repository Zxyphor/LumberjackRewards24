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
import java.io.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

class APICaller{
    //Function to use API calls
    //single arg
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

    //double arg
    static String stream(int id, String arg, int id2, String arg2) {
        try {
            String urlString = "cs.sfasu.edu/csci4267-00101/BackFrontEndStrikesBack/access/api/"+arg+id+arg2+id2;
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


}

//Set of commands for pulling a single user's information
class ProfileInfo extends APICaller{
    public static int getID(int id){
        return id;
    }
    public static String getName(int id){
        String in =  stream(id, "getUser.php?userID=");
        String[] outAry = in.split("\"");
        return outAry[5];
    }
    public static String getLastLogin(int id){
        String in =  stream(id, "getUser.php?userID=");
        String[] outAry = in.split("\"");
        return outAry[9];
    }
    public static int getProfilepic(int id){
        String in =  stream(id, "getUser.php?userID=");
        String[] outAry = in.split("\"");
        return Integer.parseInt(outAry[13]);
    }
}

//Set of commands for pulling a single badge's info
class BadgeInfo extends APICaller{

    public static int getID(int id){
        return id;
    }
    public static String getName(int id){
        String in =  stream(id, "getBadge.php?badgeID=");
        String[] outAry = in.split("\"");
        return outAry[9];
    }
    public static String getDescription(int id){
        String in =  stream(id, "getBadge.php?badgeID=");
        String[] outAry = in.split("\"");
        return outAry[13];
    }
    public static String getCriteria(int id){
        String in =  stream(id, "getBadge.php?badgeID=");
        String[] outAry = in.split("\"");
        return outAry[17];
    }
    public static int getIcon(int id){
        String in =  stream(id, "getBadge.php?badgeID=");
        String[] outAry = in.split("\"");
        return Integer.parseInt(outAry[21]);
    }
    public static String getCreationDate(int id){
        String in =  stream(id, "getBadge.php?badgeID=");
        String[] outAry = in.split("\"");
        return outAry[25];
    }
}

//Set of commands for pulling a set of a user's associated badges
class UserBadgeInfo extends APICaller {

    //HANDLE THESE FUNCTIONS
    //They will not work if there is no user badge data!
    public static String[][] getCompleteUserBadges(int id){
        String in =  stream(id, "getUserBadges.php?userID=");

        //splits given JSON dictionary into parsable string array
        String[] workingAry = in.substring(in.indexOf("completed_badges"), in.indexOf("in_progress")).split("\"");
        String[][] outAry = new String[workingAry.length/32][8];

        //split array has 4 pieces "JSON punctuation", "nameofcolumn", ":", "the data" per required entry
        //So we're taking every 4th entry, and starting at entry 4 since the 0 entry is "completed badges"
        int badgeIterator = 0;
        int badgeInfoIterator = 0;
        for (int i = 4; i < workingAry.length; i = i+4){
            if (badgeInfoIterator == 7){
                badgeIterator++;
                badgeInfoIterator = 0;
            }
            outAry[badgeIterator][badgeInfoIterator] = workingAry[i];
            badgeInfoIterator++;
        }
        return outAry;
    }
    
    //HANDLE THESE FUNCTIONS
    //They will not work if there is no user badge data!
    public static String[][] getInprogUserBadges(int id){
        String in =  stream(id, "getUserBadges.php?userID=");

        //splits given JSON dictionary into parsable string array
        String[] workingAry = in.substring(in.indexOf("in_progress")).split("\"");
        String[][] outAry = new String[workingAry.length/32][8];

        //split array has 4 pieces "JSON punctuation", "nameofcolumn", ":", "the data" per required entry
        //So we're taking every 4th entry, and starting at entry 4 since the 0 entry is "completed badges"
        int badgeIterator = 0;
        int badgeInfoIterator = 0;
        for (int i = 4; i < workingAry.length; i = i+4){
            if (badgeInfoIterator == 7){
                badgeIterator++;
                badgeInfoIterator = 0;
            }
            outAry[badgeIterator][badgeInfoIterator] = workingAry[i];
            badgeInfoIterator++;
        }
        return outAry;
    }
}

//Set of commands for changing user info
class ChangeUserInfo extends APICaller{

}

//Set of commands for updating
class UpdateUserBadge extends APICaller{

}

public class Framework extends AppCompatActivity {


}
