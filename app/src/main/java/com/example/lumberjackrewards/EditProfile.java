package com.example.lumberjackrewards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EditProfile extends AppCompatActivity {
    private ImageButton back;
    TextView Name;
    private TextView profileIdTextView;
    ArrayList <User> persons;

    private RecyclerView rvBadge;
    private ArrayAdapter<ProfileItemModel> adapter;
    // Define an array to hold profile picture resource IDs
    //private int[] profilePics = {R.drawable.pfp_01, R.drawable.pfp_02, R.drawable.pfp_purple /* add more profile pics as needed */};
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);


        //this Utilizes the UI elements.
//        userName = (EditText) findViewById(R.id.etName);
//        userSurname = (EditText) findViewById(R.id.etSurname);
//        results = (TextView) findViewById(R.id.result);
       // int userId = getCurrentUserId();

        // Create a ProfileInfo object for the current user
        //ProfileInfo profileInfo = new ProfileInfo(userId);

        back = findViewById(R.id.backBtn);
        Name = findViewById(R.id.Name);
        imageView = findViewById(R.id.imageView);
        ProfileItemModel profileItem = new ProfileItemModel();
// Assuming getProfilepic() returns the resource ID of the profile picture
       // setProfilePicForImageView(0); // Set the default profile picture

// Now you can set the profile picture for the imageView
        //imageView.setImageResource(profileItem.getProfilepic());
        //ImageView profImage = findViewById(R.id.imageView);
        // profImage.setImageResource(R.drawable.blank_user);
        persons = new ArrayList<User>();
        //loadData(); // this load existing data when the activity starts.

        // Initialize and assign variable
        rvBadge = findViewById(R.id.rvPinnedBadges);

        //this is the onClickListener for the button in the profileView page,
        // to move back to the home page.
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(EditProfile.this, ProfileSearch.class);
                // startActivity(intent);
                EditProfile.this.finish();
            }
        });
        // Retrieve the profile name from the profile page
        Intent intent = getIntent();
        if (intent.hasExtra("profileIdTextView")) {
            String profId = intent.getStringExtra("profileIdTextView");


            ProfileInfo profInfo = new ProfileInfo(Integer.parseInt(profId));

            try {
                Name.setText(profInfo.getName(Integer.parseInt(profId)));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //imageView.setImageResource(profInfo.getProfilepic());
            //itemDescriptionTextView.setText(badgeInfo.getDescription());
            ArrayList<BadgeItemModel> arrBadges = new ArrayList<>();

            try {
                String[][] tempBadgeArray = UserBadgeInfo.getCompleteUserBadges(Integer.parseInt(profId));
                Log.i("TEMP BADGE LENGTH", String.valueOf(tempBadgeArray.length));

                for (int i = 0; i < tempBadgeArray.length; i++) {
                    Log.i("TEMP BADGE INFO", Arrays.toString(tempBadgeArray[i]));
                    BadgeItemModel bim = new BadgeItemModel(Integer.parseInt(tempBadgeArray[i][0]), tempBadgeArray[i][1], tempBadgeArray[i][2], Integer.parseInt(tempBadgeArray[i][4]), Integer.parseInt(tempBadgeArray[i][7]), tempBadgeArray[i][3], Integer.parseInt(tempBadgeArray[i][6]));
                    arrBadges.add(bim);
                }
            } catch (InterruptedException e) {
                Log.i("TEMP BADGE EXCEPTION", "oops");
                throw new RuntimeException(e);
            }

            displayAllBadges(arrBadges);
        }
    }

    //private int getCurrentUserId() {
        // Implementation to retrieve the user ID
        // This might involve accessing shared preferences, database, or any other method
        // Return the user ID
   // }

    // Method to set profile picture for imageView
//    private void setProfilePicForImageView(int profilePicIndex) {
//        if (profilePicIndex >= 0 && profilePicIndex < profilePics.length) {
//            int profilePicResourceId = profilePics[profilePicIndex];
//            imageView.setImageResource(profilePicResourceId);
//        } else {
//
//        }
//    }

    private void displayAllBadges(ArrayList<BadgeItemModel> arrBadges) {

        //layout manager for badge test
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

        //set layout manager
        rvBadge.setLayoutManager(layoutManager);

        //set adapter
        rvBadge.setAdapter(new BadgeViewAdapter(arrBadges));
    }


}

