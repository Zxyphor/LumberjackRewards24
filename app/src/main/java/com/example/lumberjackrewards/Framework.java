package com.example.lumberjackrewards;

import static java.lang.Thread.onSpinWait;
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
        threadProfileID.join();
        Log.i("ProfileInfo.getName returns", out[0]);
        return out[0];
    }
    public static String getLastLogin(int id) throws InterruptedException {
        final String[] out = {""};
        Thread threadLastLogin = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getUser.php?userID=");
                String[] outAry = in.split("\"");
                Log.i("ProfileInfo.LastLogin in thread", Arrays.toString(outAry));
                out[0] = outAry[9];
                Log.i("ProfileInfo.LastLogin in thread", out[0]);
            }
        };
        threadLastLogin.start();
        threadLastLogin.join();
        Log.i("ProfileInfo.LastLogin returns", out[0]);
        return out[0];
    }
    public static int getProfilepic(int id) throws InterruptedException {
        final String[] out = {""};
        Thread threadProfilePic = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getUser.php?userID=");
                String[] outAry = in.split("\"");
                Log.i("ProfileInfo.ProfilePic in thread", Arrays.toString(outAry));
                out[0] = outAry[13];
                Log.i("ProfileInfo.ProfilePic in thread", out[0]);
            }
        };
        threadProfilePic.start();
        threadProfilePic.join();
        Log.i("ProfileInfo.ProfilePic returns", out[0]);
        return Integer.parseInt(out[0]);
    }
}

//Set of commands for pulling a single badge's info
class BadgeInfo extends APICaller{

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
                Log.i("BadgeInfo.getName in thread", Arrays.toString(outAry));
                out[0] = outAry[9];
                Log.i("BadgeInfo.getName in thread", out[0]);
            }
        };
        threadBadgeName.start();
        sleep(100);
        Log.i("BadgeInfo.getName returns", out[0]);
        return out[0];
    }
    public static String getDescription(int id) throws InterruptedException {
        final String[] out = {""};
        Thread threadDescription = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("BadgeInfo.Description in thread", Arrays.toString(outAry));
                out[0] = outAry[13];
                Log.i("BadgeInfo.Description in thread", out[0]);
            }
        };
        threadDescription.start();
        threadDescription.join();
        Log.i("BadgeInfo.Description returns", out[0]);
        return out[0];
    }

    public static String getCriteria(int id) throws InterruptedException {
        final String[] out = {""};
        Thread threadCriteria = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("BadgeInfo.Criteria in thread", Arrays.toString(outAry));
                out[0] = outAry[17];
                Log.i("BadgeInfo.Criteria in thread", out[0]);
            }
        };
        threadCriteria.start();
        threadCriteria.join();
        Log.i("BadgeInfo.Criteria returns", out[0]);
        return out[0];
    }
    public static int getIcon(int id) throws InterruptedException {
        final String[] out = {""};
        Thread threadIcon = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("BadgeInfo.Icon in thread", Arrays.toString(outAry));
                out[0] = outAry[21];
                Log.i("BadgeInfo.Icon in thread", out[0]);
            }
        };
        threadIcon.start();
        threadIcon.join();
        Log.i("BadgeInfo.Icon returns", out[0]);
        return Integer.parseInt(out[0]);
    }
    public static String getCreationDate(int id) throws InterruptedException {
        final String[] out = {""};
        Thread threadCreationDate = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("BadgeInfo.CreationDate in thread", Arrays.toString(outAry));
                out[0] = outAry[25];
                Log.i("BadgeInfo.CreationDate in thread", out[0]);
            }
        };
        threadCreationDate.start();
        threadCreationDate.join();
        Log.i("BadgeInfo.CreationDate returns", out[0]);
        return out[0];
    }
}

//Set of commands for pulling a set of a user's associated badges
class UserBadgeInfo extends APICaller {

    //HANDLE THESE FUNCTIONS
    //They will not work if there is no user badge data!
    public static String[][] getCompleteUserBadges(int id) throws InterruptedException{
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
