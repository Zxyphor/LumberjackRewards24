package com.example.lumberjackrewards;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class BadgesActivity extends AppCompatActivity {
    // Badges backend
    private EditText itemEdt;
    private ArrayList<BadgeItemModel> lngList;
    private ArrayAdapter<BadgeItemModel> adapter;
    private FirebaseFirestore db;
    private RecyclerView rvBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_badges);

        // on below line we are accessing Cloud Firestore instance
        db = FirebaseFirestore.getInstance();

        // Initialize and assign variable
        rvBadge = findViewById(R.id.rvBadges);
        ArrayList<BadgeItemModel> arrBadges = new ArrayList<>();

        EditText editText = (EditText)findViewById(R.id.searchEditText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<BadgeItemModel> searchList = new ArrayList<BadgeItemModel>();
                for (BadgeItemModel badge :arrBadges){
                    if (badge.getName().contains(s)){
                        searchList.add(badge);
                    }
                }
                displayAllBadges(searchList);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //not needed to work, but stub needed to run
            }

            @Override
            public void afterTextChanged(Editable s) {
                // not needed to work, but stub needed to run
            }
        });

//        // manually create 10 badges
//        for (int i=0; i<10; i++) {
//            BadgeItemModel bim = new BadgeItemModel(i, "name", "desc", "badge_ex1.png", 0, 0, "req", 3);
//            arrBadges.add(bim);
//        }

        // TODO:
        // 3 from how many badges in static array, will be some length function
        // make method in BadgeInfo to get number of badges to create
        // make getter methods in BadgeInfo static so they can be accessed without object
        for (int i =1; i < 11; i++){
            BadgeItemModel bim = null;
            try {
                bim = new BadgeItemModel(i, BadgeInfo.getName(i), BadgeInfo.getDescription(i), BadgeInfo.getIcon(i), 1 /*TODO fix once API finished to have completino status*/, BadgeInfo.getCriteria(i), 1 /*TODO fix once API finished to have steps (number to complete)*/);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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

        /*ListView languageLV = findViewById(R.id.idLVLanguages);*/
        //Button addBtn = findViewById(R.id.btnBadgeAdd);
        Button btnProfile = (Button) findViewById(R.id.profilesBtn);
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
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(BadgesActivity.this, ProfileSearch.class);
            startActivity(intent);
            //finish();
        });

        /*
        btnManage.setOnClickListener(v -> {
            Intent intent = new Intent(BadgesActivity.this, ActivityManage.class);
            startActivity(intent);
            finish();
        });
        */


        /*
        removeBtn.setOnClickListener(v -> {
            // on below line we are getting text from edit text
            /*String badgeName = itemEdt.getText().toString();

            // on below line we are checking if item is not empty
            if (!badgeName.isEmpty()) {
                deleteBadge(badgeName);
                displayAllBadges(arrBadges);
            }

        });
*/

        //-----------------uncomment(if languageLV will still be used)---------------
        // the onItemClickListener below makes the remove button obsolete
       /* languageLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BadgeItemModel item = adapter.getItem(position);
                item.deleteBadgeItem(db);
                lngList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });*/
    }
    private void displayAllBadges(ArrayList<BadgeItemModel> arrBadges) {
        //arrBadges.clear();
//        db.collection("badges")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            BadgeItemModel badge = document.toObject(BadgeItemModel.class);
//                            arrBadges.add(badge);
//                        }
//                        Log.d("PRINT_ARRAY", arrBadges.get(0).toString());
//

                        //layout manager for badge test
                        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

                        //set layout manager
                        rvBadge.setLayoutManager(layoutManager);

                        //set adapter
                        rvBadge.setAdapter(new BadgeViewAdapter(arrBadges));
//                    }

//                });
    }

    public void search(){

    }

}