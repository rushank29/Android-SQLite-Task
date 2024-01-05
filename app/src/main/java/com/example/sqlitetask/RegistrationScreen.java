package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrationScreen extends AppCompatActivity {

    TextInputEditText user_name, user_email;
    Button save_btn;
    SharedPreferences sharedPreferences;

    //    So create a shared preferences name and also create key name
    private static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        save_btn = findViewById(R.id.save_btn);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

//        When opening this

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  On button click, put data on Shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, user_name.getText().toString());
                editor.putString(KEY_NAME, user_email.getText().toString());
                editor.apply();

                Intent intent = new Intent(RegistrationScreen.this, EditProfile.class);
                startActivity(intent);

                Toast.makeText(RegistrationScreen.this, "User data saved successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}