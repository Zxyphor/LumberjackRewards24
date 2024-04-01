package com.example.lumberjackrewards;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileSearch extends AppCompatActivity {
    private EditText itemEdt;
    private ArrayList<BadgeItemModel> lngList;
    private ArrayAdapter<BadgeItemModel> adapter;
    private FirebaseFirestore db;
    private RecyclerView profBadge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_search);

        // on below line we are accessing Cloud Firestore instance
        db = FirebaseFirestore.getInstance();

        // Initialize and assign variable
        profBadge = findViewById(R.id.profBadges);
        ArrayList<BadgeItemModel> arrBadges = new ArrayList<>();

        for (int i =0; i < 3; i++){
            BadgeInfo badge = new BadgeInfo(i);
            BadgeItemModel bim = new BadgeItemModel(i, badge.getName(), badge.getDescription(), badge.getIcon(), badge.getCompletionStatus(), badge.getRequirements(), badge.getSteps());
            arrBadges.add(bim);
        }

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        displayAllBadges(arrBadges);

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

    private void displayAllBadges(ArrayList<BadgeItemModel> arrBadges) {
        //layout manager for badge test
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

        //set layout manager
        profBadge.setLayoutManager(layoutManager);

        //set adapter
        profBadge.setAdapter(new BadgeViewAdapter(arrBadges));
    }
}

