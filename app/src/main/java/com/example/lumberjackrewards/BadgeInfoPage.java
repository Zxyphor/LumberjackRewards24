package com.example.lumberjackrewards;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BadgeInfoPage extends AppCompatActivity{
    private ImageButton returnButton;
    private TextView badgeIdTextView;
    private TextView badgeNameTextView;
    private TextView itemDescriptionTextView;
    private ImageView imgBadgeIcon;
    private ProgressBar progress_bar;
    private TextView itemStepsTextView;
    private Button redeemButton;
    private int badgeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_badge_info);
        badgeNameTextView = findViewById(R.id.badgeNameTextView);
        itemDescriptionTextView = findViewById(R.id.itemDescriptionTextView);
        imgBadgeIcon = findViewById(R.id.imgBadgeIcon);
        progress_bar = findViewById(R.id.progress_bar);
        itemStepsTextView = findViewById(R.id.itemStepsTextView);
        returnButton = findViewById(R.id.backButton);
        redeemButton = findViewById(R.id.redeemBtn);
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
            badgeId = Integer.parseInt(intent.getStringExtra("badgeIdTextView"));

            try {
                badgeNameTextView.setText(BadgeInfo.getName(badgeId));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                itemDescriptionTextView.setText(BadgeInfo.getDescription(badgeId));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //imgBadgeIcon.setImageIcon(badgeInfo.getIcon());
            //progress_bar.setProgress(badgeInfo.getCompletionStatus());
            try {
                itemStepsTextView.setText(BadgeInfo.getCriteria(badgeId));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),BadgesActivity.class));
                //finish();
                try {
                    UpdateUserBadge.updateBadge(2,badgeId);
                    redeemButton.setText("Redeemed");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}