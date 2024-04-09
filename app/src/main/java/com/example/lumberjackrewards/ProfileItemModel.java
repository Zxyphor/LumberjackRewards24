package com.example.lumberjackrewards;

public class ProfileItemModel {
    private int ProfileID;
    private String name;
    private int Profilepic;

    // on below line are the class constructors
    public ProfileItemModel(){}

    public ProfileItemModel(int ProfileID, String name, int Profilepic) {
        this.ProfileID = ProfileID;
        this.name = name;
        this.Profilepic = Profilepic;
    }

    // getters and setters
    public int getProfileID(){
        return this.ProfileID;
    }

    public void setProfileID(int ProfileID) {
        this.ProfileID = ProfileID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getProfilepic(){
        return this.Profilepic;
    }

    public void setProfilepic(int Profiler) {
        this.Profilepic = Profiler;
    }

    public String toString() {
        return "ProfileItemModel{" +
                "ProfileID=" + getProfileID() +
                ", name='" + getName() + '\'' +
                ", Profilepic='" + getProfilepic() + '\'' +
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


