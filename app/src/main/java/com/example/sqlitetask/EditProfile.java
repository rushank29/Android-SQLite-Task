package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfile extends AppCompatActivity {

    TextInputEditText edit_user_name, edit_user_email;
    Button update_user_btn;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edit_user_name = findViewById(R.id.edit_user_name);
        edit_user_email = findViewById(R.id.edit_user_email);
        update_user_btn = findViewById(R.id.update_user_btn);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        if (name != null || email != null) {
//            So set the data on TextInputEditText
            edit_user_name.setText("" + name);
            edit_user_email.setText("" + email);
        }

//        update_user_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  On button click, put data on Shared preferences
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(KEY_NAME, edit_user_name.getText().toString());
//                editor.putString(KEY_NAME, edit_user_email.getText().toString());
//                editor.apply();
//
//                Intent intent = new Intent(EditProfile.this, EditProfile.class);
//                startActivity(intent);
//
//                Toast.makeText(EditProfile.this, "User data updated successfully",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}