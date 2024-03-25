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
    //faux-DB should have matching params to array entries
    final String[][] PROFILEDB = {{"john doe", "0"}, {"joe momma", "3"}, {"julienne doe", "15"}};
    int id;
    String name;
    int profilepic;

    //array of badges (completed and pinned)
    BadgeInfo[] badges;

    public ProfileInfo(int id){
        /*
        try {
            String getInfoURL = "";
            URL url = new URL(getInfoURL);
            HttpURLConnection dbConn = (HttpURLConnection)url.openConnection();
            InputStream inS = dbConn.getInputStream();
            InputStreamReader inSR = new InputStreamReader(inS, "ISO-8859-1"); //charsetname may change
            BufferedReader bufRead = new BufferedReader(inSR);
            //add API calls and stuff here */

            this.id = id;
            this.name = PROFILEDB[id][0];
            this.profilepic = Integer.parseInt(PROFILEDB[id][1]);
            /*
        } catch (IOException e) {
            Log.d("ERROR", "Failed to load URL");
            throw new RuntimeException(e);
        }*/
    }

    public int getID(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int getProfilepic(){
        return this.profilepic;
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
    //faux-DB
    //same as above, though completionStatus and steps are user and badge specific
    //if completionStatus = steps, the badge is complete
    //steps is immutable except by backend team, badge specific
    //completionStatus is stored per individual user

    //Remove when API is implemented
    final String[][] BADGEDB = {{"badge1", "cool badge", "bronze_badge.png", "1", "0", "do a cool thing"},
                                {"badge2", "description 2", "silver_badge.png", "1", "0", "description?"},
                                {"badge3", "a badge", "gold_badge.png", "1", "0", "no"}};

    //Remove when API is implemented
    int id;
    String name;
    String description;
    String icon;
    int completionStatus;   //user-specific current completion
    int steps;              //badge requirements of completion
    String requirements;

    //Remove when API is implemented
    public BadgeInfo(int id){

        /*
        try {
            String getInfoURL = "";
            URL url = new URL(getInfoURL);
            HttpURLConnection dbConn = (HttpURLConnection)url.openConnection();
            InputStream inS = dbConn.getInputStream();
            InputStreamReader inSR = new InputStreamReader(inS, "ISO-8859-1"); //charsetname may change
            BufferedReader bufRead = new BufferedReader(inSR);
            //add API calls and stuff here*/


            this.id = id;
            this.name = BADGEDB[id][0];
            this.description = BADGEDB[id][1];
            this.icon = BADGEDB[id][2];
            this.steps = Integer.parseInt(BADGEDB[id][3]);
            this.completionStatus = Integer.parseInt(BADGEDB[id][4]);
            this.requirements = BADGEDB[id][5];

            /*
        } catch (IOException e) {
            Log.d("ERROR", "Failed to load URL");
            throw new RuntimeException(e);
        }*/
    }

    public int getID(){
        return this.id;
    }
    public String getDescription(){
        return this.description;
    }
    public String getName(){
        return this.name;
    }
    public String getIcon(){
        return this.icon;
    }
    public int getCompletionStatus(){
        return this.completionStatus;
    }
    public int getSteps(){
        return this.steps;
    }
    public String getRequirements(){
        return this.requirements;
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
