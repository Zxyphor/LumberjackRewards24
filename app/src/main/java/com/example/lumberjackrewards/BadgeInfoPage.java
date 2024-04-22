package com.example.lumberjackrewards;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BadgeInfoPage extends AppCompatActivity{
    private ImageButton returnButton;
    private TextView badgeNameTextView;
    private TextView itemDescriptionTextView;
    private ImageView imgBadgeIcon;
    private ProgressBar progress_bar;
    private TextView itemStepsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_badge_info);
        badgeNameTextView = findViewById(R.id.badgeNameTextView);
        returnButton = findViewById(R.id.backButton);
        itemDescriptionTextView = findViewById(R.id.itemDescriptionTextView);
        imgBadgeIcon = findViewById(R.id.imgBadgeIcon);
        progress_bar = findViewById(R.id.progress_bar);
        itemStepsTextView = findViewById(R.id.itemStepsTextView);
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
        if (intent.hasExtra("badgeIdTextView")) {
            String badgeId = intent.getStringExtra("badgeIdTextView");

            try {
                badgeNameTextView.setText(BadgeInfo.getName(Integer.parseInt(badgeId)));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                itemDescriptionTextView.setText(BadgeInfo.getDescription(Integer.parseInt(badgeId)));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //imgBadgeIcon.setImageIcon(badgeInfo.getIcon());
            //progress_bar.setProgress(badgeInfo.getCompletionStatus());
            try {
                itemStepsTextView.setText(BadgeInfo.getCriteria(Integer.parseInt(badgeId)));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}