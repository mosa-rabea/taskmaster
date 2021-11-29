package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


        AppDatabase db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "taskMaster").allowMainThreadQueries().build();
        TaskDao userDao = db.taskDao();


        List<Task> tasks = userDao.getAll();

        RecyclerView allTasksRecuclerView = findViewById(R.id.tasksRecucleView);
        allTasksRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecuclerView.setAdapter(new TaskAdapter(tasks));

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