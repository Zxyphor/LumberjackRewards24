package com.example.lumberjackrewards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.StringTokenizer;

public class ProfileView extends AppCompatActivity {
    private Button back;
    EditText userName, userSurname;
    TextView results;
    ArrayList <User> persons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        //this Utilizes the UI elements.
        userName = (EditText) findViewById(R.id.etName);
        userSurname = (EditText) findViewById(R.id.etSurname);
        results = (TextView) findViewById(R.id.result);
        back = findViewById(R.id.backBtn);
        TextView resultsTextView = findViewById(R.id.textView);
        persons = new ArrayList<User>();
        loadData(); // this load existing data when the activity starts.
        setTextToTextView();

        //this is the onClickListener for the button in the profileView page,
        // to move back to the home page.
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //This method calls the save method when it is clicked.
    public void btnSave(View v){
        //this would save the data to its internal storage.
        save();
    }

    //This method saves the data to internal storage.
    private void save(){
        try{
            FileOutputStream file = openFileOutput("data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            //This writes the users data to file
            for (int i = 0; i < persons.size(); i++){
                outputFile.write(persons.get(i).getName() + ", "
                        + persons.get(i).getSurname() + "\n");
            }
            outputFile.flush();
            outputFile.close();
            Toast.makeText(ProfileView.this, "Success", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            Toast.makeText(ProfileView.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //This method will load existing data from file
    public void loadData(){
        persons.clear();
        File file = getApplicationContext().getFileStreamPath("data.txt");
        String lineFromFile;

        //This checks if the file does exist.
        if (file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("data.txt")));
                // Reads data from file and populate ArrayList
                while ((lineFromFile = reader.readLine()) != null) {
                    StringTokenizer tokens = new StringTokenizer(lineFromFile, ", ");
                    User person = new User(tokens.nextToken(), tokens.nextToken());
                    persons.add(person);
                }
                reader.close();
            }
            catch(IOException e) {
                Toast.makeText(ProfileView.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(ProfileView.this, "File not found", Toast.LENGTH_SHORT).show();
        }
        //calls method to set text to textView
        setTextToTextView();
    }

    public void btnAdd(View v){
        String name = userName.getText().toString();
        String surname = userSurname.getText().toString();

        User person = new User(name, surname);
        persons.add(person);

        if(!name.isEmpty() && !surname.isEmpty()){ // Check if both name and surname fields are not empty
            // This is for saving one entry
            this.persons.clear(); // this would clear an previous data, if any
            User persons = new User(name, surname); // Creates a new User object
            this.persons.add(person); // Adds the new person
            save(); // Saves the data
        } else {
            Toast.makeText(this, "Please enter both name and surname", Toast.LENGTH_SHORT).show();
        }

        //calls method to set text to textView
        setTextToTextView();
    }

    private void setTextToTextView(){
         //Display data in the results TextView
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < persons.size(); i++){
            text.append(persons.get(i).getName()).append(", ").append(persons.get(i).getSurname()).append("\n");
        }
        TextView textView = findViewById(R.id.textView); // Get the TextView instance
        textView.setText(text.toString()); // Set the text
    }

}
