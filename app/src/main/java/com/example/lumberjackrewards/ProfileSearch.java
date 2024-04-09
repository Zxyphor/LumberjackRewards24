package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProfileSearch extends AppCompatActivity {
    private EditText itemEdt;
    private ArrayList<ProfileItemModel> lngList;
    private ArrayAdapter<ProfileItemModel> adapter;
    private FirebaseFirestore db;
    private RecyclerView profBadge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_search);

        // on below line we are accessing Cloud Firestore instance
        db = FirebaseFirestore.getInstance();

        // Initialize and assign variable
        profBadge = findViewById(R.id.profBadge);
        ArrayList<ProfileItemModel> arrUsers = new ArrayList<>();

        for (int i =0; i < 3; i++){
            ProfileInfo Profile = new ProfileInfo(i);
            ProfileItemModel bim = new ProfileItemModel(Profile.getID(), Profile.getName(), Profile.getProfilepic());
            arrUsers.add(bim);
        }

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        displayAllProfiles(arrUsers);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_badges);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.navigation_home:
                    //Pinned badges are long term goal
//                    //Sends back to main activity what badges are pinned
//                    ArrayList<BadgeItemModel> pinnedBadges = new ArrayList<>();
//
//                    for(int i = 0; i < arrBadges.size(); i++){
//                        boolean isPinned = arrBadges.get(i).getIsPinned();
//                        if(isPinned) {
//                            pinnedBadges.add(arrBadges.get(i));
//                        }
//                    }
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    if(!pinnedBadges.isEmpty()) {
//                        intent.putExtra("pinnedBadges", pinnedBadges);
//                    }
//                    startActivity(intent);

                    //previous startActivity for testing for different branches to revert back
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));


                    //overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_badges:
                    break;
                case R.id.navigation_settings:
                    startActivity(new Intent(getApplicationContext(),Settings.class));
                    //overridePendingTransition(0,0);
                    break;
            }

            return true;
        });

        Button btnBadges = (Button) findViewById(R.id.badgesBtn);
        /*ListView languageLV = findViewById(R.id.idLVLanguages);*/
        //Button addBtn = findViewById(R.id.btnBadgeAdd);
        // Button removeBtn = findViewById(R.id.btnBadgeRemove);
        //Button btnManage = findViewById(R.id.btnManageUsersAndGroups);
        lngList = new ArrayList<>();

        // on below line we are adding items to our list
        //lngList.add("C++");
        //lngList.add("Python");

        // on the below line we are initializing the adapter for our list view.
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lngList);

        // on below line we are setting adapter for our list view.
        /* languageLV.setAdapter(adapter);*/

        // on below line we are adding click listener for our button.
        btnBadges.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileSearch.this, BadgesActivity.class);
            startActivity(intent);
            //finish();
       });

    }

    private void displayAllProfiles(ArrayList<ProfileItemModel> arrprof) {
        //layout manager for badge test
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

        //set layout manager
        profBadge.setLayoutManager(layoutManager);

        //set adapter
        profBadge.setAdapter(new ProfileViewAdapter(arrprof));
    }
}

