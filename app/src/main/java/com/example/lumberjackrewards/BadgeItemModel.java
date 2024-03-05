package com.example.lumberjackrewards;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class BadgeItemModel {
    private int badgeID;
    private String description;
    private String name;
    private String icon;
    private int completionStatus;
    // keeps track of steps completed so far per user
    // referenced for progress bar
    private int redemptionType;
    // honor system, automatic, QR code scan
    private String requirements;
    private int steps;
    // number of steps required for badge to be completed
    // referenced for progress bar
    //private boolean isPinned;

    // on below line are the class constructors
    public BadgeItemModel(){}

    public BadgeItemModel(int badgeID, String name, String description, String icon, int completionStatus, String requirements, int steps) {
        this.badgeID = badgeID;
        this.description = description;
        this.name = name;
        this.icon = icon;
        this.completionStatus = completionStatus;
        this.requirements = requirements;
        this.steps = steps;
    }

    // getters and setters
    public int getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(int badgeID) {
        this.badgeID = badgeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {return icon;}
    public void setIcon(String icon) {this.icon = icon;}

    public int getCompletionStatus(){
        return completionStatus;
    }
    public void setCompletionStatus(int completionStatus){
        this.completionStatus = completionStatus;
    }
    public String getRequirements(){
        return requirements;
    }
    public void setRequirements(String requirements){
        this.requirements = requirements;
    }
    public int getSteps(){
        return steps;
    }
    public void setSteps(int steps){
        this.steps = steps;
    }
    @Override
    public String toString() {
        return "BadgeItemModel{" +
                "badgeID=" + getBadgeID() +
                ", description='" + getDescription() + '\'' +
                ", name='" + getName() + '\'' +
                ", icon='" + getIcon() + '\'' +
                '}';
    }
//
//    public void addNewBadgeItem(FirebaseFirestore db){
//        String documentID = this.getName().replaceAll("\\s", "");
//
//        // on below line are are adding a new badge to the db
//        db.collection("badges").document(documentID).set(this)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("NEW_BADGE", "Successfully added new badge to db: " + this.toString());
//                    }
//                });
//    }
//
//    public void deleteBadgeItem(FirebaseFirestore db){
//        String documentID = this.getName().replaceAll("\\s", "");
//
//        // on below line are are adding a new badge to the db
//        db.collection("badges").document(documentID).delete()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("DELETE_BADGE", "Successfully deleted badge from db: " + this.toString());
//                    }
//                });
//    }


}
