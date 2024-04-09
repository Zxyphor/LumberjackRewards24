package com.example.lumberjackrewards;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.animation.Animator;
import android.animation.AnimatorInflater;

public class ProfileViewAdapter extends RecyclerView.Adapter<ProfileViewAdapter.ViewHolder> {
    private ArrayList<ProfileItemModel> arrItemProfiles;

    public ProfileViewAdapter(ArrayList<ProfileItemModel>arrItemProfiles) {
        this.arrItemProfiles = arrItemProfiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_badge, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set text
        holder.nameTextView.setText(arrItemProfiles.get(position).getName());
        //holder.descriptionTextView.setText(arrItemBadges.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return arrItemProfiles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;


        //badge icon flip animation variable
        //private boolean isFrontIcon = true;
        ProgressBar progressBar;
        TextView progressTxt;

        public boolean isPinned = false;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.badgeNameTextView);
            //descriptionTextView = itemView.findViewById(R.id.itemDescriptionTextView);

            //onclick to move to the badges information page when a badge is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //https://codingwitht.com/how-to-pass-data-from-one-activity-to-another-in-android-studio/
                    //Stores badge name
                    String badgeName = nameTextView.getText().toString();
                    //Intent is used to navigate from one page to another, we are sending itemView info to badge info pg
                    Intent intent = new Intent(itemView.getContext(), EditProfile.class);
                    //adding extra info to intent, in this case badgeName with a key 'nameTextView'
                    intent.putExtra("nameTextView", badgeName);
                    itemView.getContext().startActivity(intent);
                }
            });

            progressBar = itemView.findViewById(R.id.progress_bar);
            progressTxt = itemView.findViewById(R.id.progress_txt);


            //modifies the camera scale for flip animation
            float scale = itemView.getContext().getResources().getDisplayMetrics().density;

            ImageView frontIconDisplay = itemView.findViewById(R.id.imgBadgeIcon);

            frontIconDisplay.setCameraDistance( 8000 * scale);

            // set the front/back animation
            Animator front_animation = AnimatorInflater.loadAnimator(itemView.getContext(), R.animator.front_animator);

            // onclick event listener for the container that holds the
            // the badge image and progress circle
            View containerView = itemView.findViewById(R.id.badge_IconContainer);

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    progressBar.setProgress(progressBar.getProgress()+(int)(Math.random()*10));
                    front_animation.setTarget(frontIconDisplay);
                    front_animation.start();

                }
            });
// Pinned badges are long term goal
//            //pinned badge view button
//            View pinBadgeBtn = itemView.findViewById(R.id.pinBadgeButton);
//            pinBadgeBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   isPinned = (!isPinned) ? true : false;
//                    Log.d("Pinned Value" , "Pinned is: " + isPinned);
//                    arrItemBadges.get(getAdapterPosition()).setIsPinned(isPinned);
//                }
//            });




        }
//
//        public boolean getPinned(){
//            return isPinned;
//        };
//
//        public void setPinned(boolean isPinned){
//            this.isPinned = isPinned;
//        }
    }
}
