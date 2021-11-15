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

    Button addTask , allTasks,Lorem1,Lorem2 ,Lorem3 ,setting;
    SharedPreferences sharedPreferences;
    String username;
    TextView usernameField;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         addTask = (Button) findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTask = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToAddTask);
            }
        });

         allTasks = (Button) findViewById(R.id.allTasks);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasks = new Intent(MainActivity.this, AllTasks.class);
                startActivity(goToAllTasks);
            }
        });




        // Lorem-------------------//
         Lorem1 = findViewById(R.id.loremButton1);
        Lorem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loremText1=Lorem1.getText().toString();
                Intent goToLorem1=new Intent(MainActivity.this,TaskDetail.class);
                goToLorem1.putExtra("title",loremText1);
                startActivity(goToLorem1);
            }
        });


         Lorem2 = findViewById(R.id.loremButton2);
        Lorem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loremText2 = Lorem2.getText().toString();
                Intent goToLorem2 = new Intent(MainActivity.this, TaskDetail.class);
                goToLorem2.putExtra("title",loremText2);
                startActivity(goToLorem2);

            }

        });




         Lorem3 = findViewById(R.id.loremButton3);
        Lorem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loremText3 = Lorem3.getText().toString();
                Intent goToLorem3 = new Intent(MainActivity.this, TaskDetail.class);
                goToLorem3.putExtra("title",loremText3);
                startActivity(goToLorem3);


            }
        });

        // Lorem-------------------//


         setting = findViewById(R.id.settingsButton);
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


         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
         username = sharedPreferences.getString("username1", "userName");

         usernameField = findViewById(R.id.userNameView);
        usernameField.setText(username );
    }
}