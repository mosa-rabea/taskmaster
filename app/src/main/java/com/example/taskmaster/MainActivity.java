package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        Todo todo = Todo.builder()
                .title("My first todo")
                .body("todo description")
                .state("todo description")
                .build();

        Amplify.API.mutate(
                ModelMutation.create(todo),
                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );

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

//
//        AppDatabase db =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "taskMaster").allowMainThreadQueries().build();
//        TaskDao userDao = db.taskDao();


//        List<Task> tasks = userDao.getAll();

//        RecyclerView allTasksRecuclerView = findViewById(R.id.tasksRecucleView);
//        allTasksRecuclerView.setLayoutManager(new LinearLayoutManager(this));
//        allTasksRecuclerView.setAdapter(new TaskAdapter(tasks));



        RecyclerView allTasksRecuclerView = findViewById(R.id.tasksRecucleView);
        List<Todo> tasks = new ArrayList<>();


        Handler handler =new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTasksRecuclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });



        allTasksRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        allTasksRecuclerView.setAdapter(new TaskAdapter(tasks));



        Amplify.API.query(
                ModelQuery.list(Todo.class),
                response -> {
                    ///looping through data to render it
                    for (Todo taskMaster : response.getData()) {
                        Log.i("MyAmplifyApp", taskMaster.getTitle());
                        Log.i("MyAmplifyApp", taskMaster.getBody());
                        Log.i("MyAmplifyApp", taskMaster.getState());
                        ///add new data to array
                        tasks.add(taskMaster);

                    }

                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "outsoid the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

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