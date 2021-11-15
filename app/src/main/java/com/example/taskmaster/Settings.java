package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    EditText usernameInput;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Button saveUsernameButton = findViewById(R.id.settingUsernameButton);
        saveUsernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_LONG).show();
                 sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
                 sharedPreferencesEditor = sharedPreferences.edit();

                 usernameInput = findViewById(R.id.username);
                 username = usernameInput.getText().toString();

                sharedPreferencesEditor.putString("username1", username);
                sharedPreferencesEditor.apply();
            }
        });
    }

}
