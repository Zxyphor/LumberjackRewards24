package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//faux-DB


//structure for storing profile information from DB
class ProfileInfo{
    //faux-DB
    final String[][] PROFILEDB = {{"john", "doe", "0", "false"}, {"joe", "momma", "3", "false"}, {"julienne", "doe", "15", "false"}};
    int id;
    String firstname;
    String lastname;
    int profilepic;
    boolean pinned;

    //array of badges (completed and pinned)
    BadgeInfo[] badges;

    public ProfileInfo(int id){
        this.id = id;
        this.firstname = PROFILEDB[id][0];
        this.lastname = PROFILEDB[id][1];
        this.profilepic = Integer.parseInt(PROFILEDB[id][2]);
        this.pinned = Boolean.parseBoolean(PROFILEDB[id][3]);
    }

    public int getID(){
        return this.id;
    }
    public String getFirstname(){
        return this.firstname;
    }
    public String getLastname(){
        return this.lastname;
    }
    public int getProfilepic(){
        return this.profilepic;
    }
    public BadgeInfo[] getBadges(){
        return this.badges;
    }
}

class BadgeInfo{
    //faux-DB
    final String[][] BADGEDB = {{"badge1", "cool badge", "badge_ex1.png", "1", "0", "do a cool thing", "1"},
                                {"badge2", "description 2", "badge_ex1.png", "1", "0", "description?", "2"},
                                {"badge3", "a badge", "badge_ex1.png", "1", "0", "no", "1"}};
    int id;
    String description;
    String name;
    String icon;
    int completionStatus;
    int redemptionType;
    String requirements;
    //boolean pinned;
    int steps;

    public BadgeInfo(int id){
        this.id = id;
        this.name = BADGEDB[id][0];
        this.description = BADGEDB[id][1];
        this.icon = BADGEDB[id][2];
        this.completionStatus = Integer.parseInt(BADGEDB[id][3]);
        this.redemptionType = Integer.parseInt(BADGEDB[id][4]);
        this.requirements = BADGEDB[id][5];
        //this.pinned = Boolean.parseBoolean(BADGEDB[id][6]);
        this.steps = Integer.parseInt(BADGEDB[id][7]);
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
    public int getRedemptionType(){
        return this.redemptionType;
    }
    public String getRequirements(){
        return this.requirements;
    }
//    public boolean getPinned(){
//        return this.pinned;
//    }
    public int getSteps(){
        return this.steps;
    }
}



public class Framework extends AppCompatActivity {


}
