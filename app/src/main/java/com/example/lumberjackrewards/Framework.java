package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.sql.Blob;

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
        this.id = id;
        this.name = PROFILEDB[id][0];
        this.profilepic = Integer.parseInt(PROFILEDB[id][1]);
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

    public BadgeInfo(int id){
        this.id = id;
        this.name = BADGEDB[id][0];
        this.description = BADGEDB[id][1];
        this.icon = BADGEDB[id][2];
        this.steps = Integer.parseInt(BADGEDB[id][3]);
        this.completionStatus = Integer.parseInt(BADGEDB[id][4]);
        this.requirements = BADGEDB[id][5];
    }
//
//    public int getID(){
//        return this.id;
//    }
    public static String getDescription(int id){
        return BADGEDB[id][1];
    }
    public static String getName(int id){
        return BADGEDB[id][0];
    }
    public static String getIcon(int id){
        return BADGEDB[id][2];
    }
    public static int getCompletionStatus(int id){
        return Integer.parseInt(BADGEDB[id][4]);
    }
    public static int getSteps(int id){
        return Integer.parseInt(BADGEDB[id][3]);
    }
    public static String getRequirements(int id){
        return BADGEDB[id][5];
    }


}



public class Framework extends AppCompatActivity {


}
