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
    //edit this to speed or slow threading
    static final int TIMER = 100;

    //Function to use API calls
    //single arg
    //returns a JSON string
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
    static String stream(int userID, String arg1, int badgeID, String arg2) {
        try {
            Log.i("API Call", "Start of Call");
            String urlString = "https://cs.sfasu.edu/csci4267-00104/BackFrontEndStrikesBack/access/api/"+arg1+userID+arg2+badgeID;
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
class ProfileInfo extends APICaller {
    private int userId;

    public ProfileInfo(int userId) {
        super();
        this.userId = userId;
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
        sleep(TIMER);
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
        sleep(TIMER);
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
        sleep(TIMER);

        Log.i("ProfileInfo.ProfilePic returns", out[0]);
        if (out[0].isEmpty()) // Check if the string is empty
            return 1; // Return a default profile pic ID or handle this case appropriately
        else {
            // Parse the datetime string or handle it appropriately
            // For example, if out[0] is "2024-04-24 16:27:56", you can return a default ID
            return 1;
        }
    }
}

    //Set of commands for pulling a single badge's info
class BadgeInfo extends APICaller{

    public static int getID(int id){
        return id;
    }
    public static String getName(int id) throws InterruptedException {
        String[] out = {"Error"};
        Thread threadBadgeName = new Thread() {
            @Override
            public void run(){
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("1BadgeInfo.getName in thread", Arrays.toString(outAry));
                out[0] = outAry[9];
                Log.i("2BadgeInfo.getName in thread", out[0]);
            }
        };
        threadBadgeName.start();
        sleep(TIMER);
        Log.i("3BadgeInfo.getName returns", out[0]);
        return out[0];
    }
    public static String getDescription(int id) throws InterruptedException {
        String[] out = {""};
        Thread threadDescription = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("1BadgeInfo.Description in thread", Arrays.toString(outAry));
                out[0] = outAry[13];
                Log.i("2BadgeInfo.Description in thread", out[0]);
            }
        };
        threadDescription.start();
        sleep(TIMER);
        Log.i("3BadgeInfo.Description returns", out[0]);
        return out[0];
    }

    public static String getCriteria(int id) throws InterruptedException {
        String[] out = {""};
        Thread threadCriteria = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getBadge.php?badgeID=");
                String[] outAry = in.split("\"");
                Log.i("1BadgeInfo.Criteria in thread", Arrays.toString(outAry));
                out[0] = outAry[17];
                Log.i("2BadgeInfo.Criteria in thread", out[0]);
            }
        };
        threadCriteria.start();
        sleep(TIMER);
        Log.i("3BadgeInfo.Criteria returns", out[0]);
        return out[0];
    }
    public static int getIcon(int id) throws InterruptedException {
        String[] out = {""};
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
        sleep(TIMER);
        Log.i("BadgeInfo.Icon returns", out[0]);
        if (out[0] == "") //last minute bug fixing
            out[0] = "1";
        return Integer.parseInt(out[0]);
    }
    public static String getCreationDate(int id) throws InterruptedException {
        String[] out = {""};
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
        sleep(TIMER);
        Log.i("BadgeInfo.CreationDate returns", out[0]);
        return out[0];
    }
}

//Set of commands for pulling a set of a user's associated badges
class UserBadgeInfo extends APICaller {

    //HANDLE THESE FUNCTIONS
    //They will not work if there is no user badge data!
    public static String[][] getCompleteUserBadges(int id) throws InterruptedException{
        String[] in = {""};
        Thread threadCompleteBadges = new Thread() {
            @Override
            public void run() {
                in[0] = stream(id, "getUserBadges.php?userID=");

            }
        };

        threadCompleteBadges.start();
        sleep(TIMER);

        //splits given JSON dictionary into parsable string array
        String[] workingAry = in[0].substring(in[0].indexOf("completed_badges"), in[0].indexOf("in_progress")).split("\"");
        String[][] outAry = new String[workingAry.length/32][8];

        //split array has 4 pieces "JSON punctuation", "nameofcolumn", ":", "the data" per required entry
        //So we're taking every 4th entry, and starting at entry 4 since the 0 entry is "completed badges"
        int badgeIterator = 0;
        int badgeInfoIterator = 0;
        for (int i = 4; i < workingAry.length; i = i+4){
            Log.i("getComplete in process", workingAry[i]);
            if (badgeInfoIterator == 8){
                badgeIterator++;
                badgeInfoIterator = 0;
                if (badgeIterator >= outAry.length){
                    break;
                }
            }
            Log.i("getComplete being assigned", outAry.length + " " + Arrays.toString(outAry[badgeIterator]));
            outAry[badgeIterator][badgeInfoIterator] = workingAry[i];
            badgeInfoIterator++;
        }
        Log.i("getComplete returns", Arrays.toString(outAry));
        return outAry;
    }

    //HANDLE THESE FUNCTIONS
    //They will not work if there is no user badge data!
    public static String[][] getInprogUserBadges(int id){
        String in = "";
        Thread threadCreationDate = new Thread() {
            @Override
            public void run() {
                String in = stream(id, "getUserBadges.php?userID=");
            }
        };

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
        Log.i("getInprog returns", Arrays.toString(outAry));
        return outAry;
    }
}

//Set of commands for updating badges for a user
class UpdateUserBadge extends APICaller{
    //requires userID and badgeID in that order, returns a boolean if the badge was updated
    //does not check for badges that don't exist
    public static boolean updateBadge(int userID, int badgeID) throws InterruptedException {
        String in = "";
        Thread threadUpdateBadge = new Thread() {
            @Override
            public void run() {
                String in = stream(userID, "postStepComplete.php?userID=", badgeID, "&badgeID=");
            }
        };
        threadUpdateBadge.start();
        sleep(TIMER);

        //throw error if the badge is already redeemed
        if (in.contains("error"))
            return false;
        return true;
    }
}

//Set of commands for changing user info
class ChangeUserInfo extends APICaller{

}

public class Framework extends AppCompatActivity {


}

