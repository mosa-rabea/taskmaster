package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addTask = (Button) findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTask = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToAddTask);
            }
        });

        Button allTasks = (Button) findViewById(R.id.allTasks);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasks = new Intent(MainActivity.this, AllTasks.class);
                startActivity(goToAllTasks);
            }
        });


        Button presentation = findViewById(R.id.presentationButton);
        presentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String presentationTitle = presentation.getText().toString();
                Intent goToPresentationDetails = new Intent(MainActivity.this, TaskDetail.class);
                goToPresentationDetails.putExtra("title",presentationTitle);
                startActivity(goToPresentationDetails);
            }
        });


        Button codeChallenge = findViewById(R.id.codeChallengeButton);
        codeChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codeChallengeTitle = codeChallenge.getText().toString();
                Intent goToCodeChallengeDetails = new Intent(MainActivity.this, TaskDetail.class);
                goToCodeChallengeDetails.putExtra("title",codeChallengeTitle);
                startActivity(goToCodeChallengeDetails);
            }
        });



        Button solveLab = findViewById(R.id.solveButton);
        solveLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labName = solveLab.getText().toString();
                Intent goToLabPage = new Intent(MainActivity.this, TaskDetail.class);
                goToLabPage.putExtra("title",labName);
                startActivity(goToLabPage);

            }
        });



        Button setting = findViewById(R.id.settingsButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettingsPage = new Intent(MainActivity.this, Settings.class);
                startActivity(goToSettingsPage);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = sharedPreferences.getString("username", "userName");

        TextView usernameField = findViewById(R.id.userNameView);
        usernameField.setText(username );
    }
}