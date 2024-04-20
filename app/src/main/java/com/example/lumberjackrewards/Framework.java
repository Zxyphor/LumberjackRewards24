package com.example.lumberjackrewards;

import static com.example.lumberjackrewards.APICaller.stream;
import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.Blob;
import java.lang.Object;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.google.android.material.bottomnavigation.BottomNavigationView;

class APICaller extends AppCompatActivity{

    //Function to use API calls
    //single arg
    static String stream(int id, String arg) {
        try {
            String urlString = "https://cs.sfasu.edu/csci4267-00104/BackFrontEndStrikesBack/access/api/"+arg+id;
            Log.i("API Call", "Start of Call for: " + urlString);
            URL url = new URL(urlString);
            Log.i("API Call", "String Build");
            InputStream input = url.openStream();
            Log.i("API Call", "URL Build");
            InputStreamReader isr = new InputStreamReader(input);
            Log.i("API Call", "openStream Build");
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            Log.i("API Call", json.toString());
            return json.toString();
        } catch (Exception e) {
            Log.e("API Call", "Failed try/catch stream fn in profileInfo.\n" + e.toString());
        }
        return "Failed after try/catch stream fn in profileInfo.";
    }

    //double arg
    static String stream(int id, String arg, int id2, String arg2) {
        try {
            Log.i("API Call", "Start of Call");
            String urlString = "https://cs.sfasu.edu/csci4267-00104/BackFrontEndStrikesBack/access/api/"+arg+id+arg2+id2;
            Log.i("API Call", "String Build");
            URL url = new URL(urlString);
            Log.i("API Call", "URL Build");
            InputStream input = url.openStream();
            Log.i("API Call", "openStream Build");
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            Log.i("API Call", json.toString());
            return json.toString();
        } catch (Exception e) {
            Log.e("API Call", "Failed try/catch stream fn in profileInfo.");
        }
        return "Failed after try/catch stream fn in profileInfo.";
    }

}

//Set of commands for pulling a single user's information
class ProfileInfo extends APICaller{
    public static int getID(int id){
        return id;
    }
    public static String getName(int id) throws InterruptedException {
        final String[] out = {""};
        Thread threadProfileID = new Thread() {
            @Override
            public void run() {
                    String in = stream(id, "getUser.php?userID=");
                    String[] outAry = in.split("\"");
                    Log.i("ProfileInfo.getName in thread", Arrays.toString(outAry));
                    out[0] = outAry[5];
                    Log.i("ProfileInfo.getName in thread", out[0]);
            }
        };
        threadProfileID.start();
        sleep(100);
        Log.i("ProfileInfo.getName returns", out[0]);
        return out[0];
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

class BadgeInfo{
    //faux-DB
    //same as above, though completionStatus and steps are user and badge specific
    //if completionStatus = steps, the badge is complete
    //steps is immutable except by backend team, badge specific
    //completionStatus is stored per individual user
    static final String[][] BADGEDB = {{"badge1", "cool badge", "bronze_badge.png", "1", "0", "do a cool thing"},
                                {"badge2", "description 2", "silver_badge.png", "1", "0", "description?"},
                                {"badge3", "a badge", "gold_badge.png", "1", "0", "no"}};
    int id;
    String name;
    String description;
    String icon;
    int completionStatus;   //user-specific current completion
    int steps;              //badge requirements of completion
    String requirements;

    public static int getID(int id){
        return id;
    }
    public static String getName(int id) throws InterruptedException {
        final String[] out = {"idea"};
        Thread threadBadgeName = new Thread() {
            @Override
            public void run(){
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("Badgeinfo.getName in thread", Arrays.toString(outAry));
                out[0] = outAry[9];
                Log.i("Badgeinfo.getName in thread", out[0]);
            }
        };
        threadBadgeName.start();
        sleep(1000);
        Log.i("Badgeinfo.getName returns", out[0]);
        return out[0];
    }
//
//    public int getID(){
//        return this.id;
//    }
    public static String getDescription(int id){
        return "desc";
    }
    public static String getIcon(int id){
        return "icon";
    }
    public static int getCompletionStatus(int id){
        return 4;
    }
    public static int getSteps(int id){
        return 2;
    }
    public static String getRequirements(int id){
        return "req";
    }
    public static String getCriteria(int id){
        return "testcriteria";
    }


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



public class Framework extends AppCompatActivity {


}
