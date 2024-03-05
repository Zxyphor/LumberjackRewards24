package com.example.lumberjackrewards;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BadgeInfoPage extends AppCompatActivity{
    private ImageButton returnButton;
    private TextView badgeNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_badge_info);
        badgeNameTextView = findViewById(R.id.badgeNameTextView);
        returnButton = findViewById(R.id.backButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),BadgesActivity.class));
                //finish();
                BadgeInfoPage.this.finish();
            }
        });
        // Retrieve the badge name from the badge page
        Intent intent = getIntent();
        if (intent.hasExtra("nameTextView")) {
            String badgeName = intent.getStringExtra("nameTextView");
            badgeNameTextView.setText(badgeName);
        }
    }
}
