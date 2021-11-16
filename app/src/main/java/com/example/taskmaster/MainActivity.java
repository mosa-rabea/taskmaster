package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

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



        Button setting = findViewById(R.id.settingsButton);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(MainActivity.this, Settings.class);
                startActivity(goToSettings);
            }
        });


        List<Task> allTasksData = new ArrayList<>();
        allTasksData.add(new Task("Task_01","this is the Task_01 body","new"));
        allTasksData.add(new Task("Task_02","this is the Task_02 body","new"));
        allTasksData.add(new Task("Task_03","this is the Task_03 body","new"));
        allTasksData.add(new Task("Task_04","this is the Task_04 body","new"));
        allTasksData.add(new Task("Task_05","this is the Task_04 body","new"));
        allTasksData.add(new Task("Task_06","this is the Task_05 body","new"));
        allTasksData.add(new Task("Task_07","this is the Task_07 body","new"));
        allTasksData.add(new Task("Task_08","this is the Task_08 body","new"));

        RecyclerView allTasksRecuclerView = findViewById(R.id.tasksRecucleView);
        allTasksRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecuclerView.setAdapter(new TaskAdapter(allTasksData));

    }



    @Override
    protected void onStart() {
        super.onStart();

        String tasks = "'s Tasks";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = sharedPreferences.getString("username", "user");

        TextView usernameField = findViewById(R.id.textView3);
        usernameField.setText(username + tasks);
    }
}