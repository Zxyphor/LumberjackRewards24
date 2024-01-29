package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FAQ extends AppCompatActivity {
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        ImageButton backBtn = (ImageButton)findViewById(R.id.backButton);

        // Set settings selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_settings);

        // Perform item selected listener for settings page
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_badges:
                    startActivity(new Intent(getApplicationContext(), BadgesActivity.class));
                    finish();
                    //overridePendingTransition(0,0);
                    break;
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    break;
                case R.id.navigation_settings:
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    finish();
                    break;
            }
            return true;
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FAQ.this, Settings.class));
                finish();
            }
        });
    }
}
