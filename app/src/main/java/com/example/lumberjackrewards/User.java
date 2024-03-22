package com.example.lumberjackrewards;

import java.util.ArrayList;

public class User {
    private String name;
    //private String surname;

    private int image;
    private int Id;

    private boolean pinned;

   //g   private Badges object[] //(pinned and completed)

    public User(String name, String surname){
        this.name = name;
        //this.surname = surname;
        this.image= image;
        this.Id = Id;
        this.pinned =pinned;

    }

    public void setName(String name){
        this.name = name;
    }

    //public void setSurname(String surname){
    //    this.surname = surname;
    //}

    public void setImage(int image){
        this.image = image;
    }

//    public void setId(int Id){
//        this.Id = Id;
//    }

    public String getName() {
        return name;
    }

    //public String getSurname() {
    //    return surname;
   // }

    public int getImage(){
        return image;
    }

//    public long getId() {
//        return Id;
//    }

}
