package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistrationScreen extends AppCompatActivity {

    RadioGroup user_gender;
    RadioButton user_gender_male, user_gender_female;
    TextInputEditText user_name, user_email, user_phone, user_profession;
    TextInputEditText user_address, user_city, user_state, user_country, user_pinCode;
    Button save_user_btn;
    SharedPreferences sharedPreferences;

    //    So create a shared preferences name and also create key name
    private static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PROFESSION = "profession";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CITY = "city";
    public static final String KEY_STATE = "state";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_PINCODE = "pinCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_phone = findViewById(R.id.user_phone);
        user_profession = findViewById(R.id.user_profession);
        user_address = findViewById(R.id.user_address);
        user_city = findViewById(R.id.user_city);
        user_state = findViewById(R.id.user_state);
        user_country = findViewById(R.id.user_country);
        user_pinCode = findViewById(R.id.user_pinCode);
        save_user_btn = findViewById(R.id.save_user_btn);

        //  Hooks for radio buttons
        user_gender = (RadioGroup) findViewById(R.id.user_gender);
        user_gender_male = (RadioButton) findViewById(R.id.user_gender_male);
        user_gender_female = (RadioButton) findViewById(R.id.user_gender_female);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

//        When opening this activity, first check whether the shared preferences data is available or not
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String phone = sharedPreferences.getString(KEY_PHONE, null);
        String gender = sharedPreferences.getString(KEY_GENDER, null);
        String profession = sharedPreferences.getString(KEY_PROFESSION, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);
        String city = sharedPreferences.getString(KEY_CITY, null);
        String state = sharedPreferences.getString(KEY_STATE, null);
        String country = sharedPreferences.getString(KEY_COUNTRY, null);
        String pinCode = sharedPreferences.getString(KEY_PINCODE, null);

        //  Set the data to TextInputEditText
        user_name.setText("" + name);
        user_email.setText("" + email);

        if ((name != null) && (email != null)) {
            //  if data is available, then directly call Edit Profile Screen
            Intent intent = new Intent(RegistrationScreen.this, HomeScreen.class);
            startActivity(intent);
        }

        save_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=3;
                //  On button click, put data on Shared preferences
                if (user_gender_male.isChecked()) {
                    value= 0;
                } else if (user_gender_female.isChecked()) {
                    value = 1;
                }
                //  Setting validation for empty inputs
                if (TextUtils.isEmpty(user_name.getText().toString()) ||
                        TextUtils.isEmpty(user_email.getText().toString()) ||
                        TextUtils.isEmpty(user_phone.getText().toString()) ||
                        TextUtils.isEmpty(user_profession.getText().toString()) ||
                        TextUtils.isEmpty(user_address.getText().toString()) ||
                        TextUtils.isEmpty(user_city.getText().toString()) ||
                        TextUtils.isEmpty(user_state.getText().toString()) ||
                        TextUtils.isEmpty(user_country.getText().toString()) ||
                        TextUtils.isEmpty(user_pinCode.getText().toString())) {
                    Toast.makeText(RegistrationScreen.this, "Enter pending details", Toast.LENGTH_SHORT).show();
                } else {
                    //  On button click, put data on Shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, user_name.getText().toString());
                    editor.putString(KEY_EMAIL, user_email.getText().toString());
                    editor.putString(KEY_PHONE, user_phone.getText().toString());
                    editor.putInt(KEY_GENDER, value);
                    editor.putString(KEY_PROFESSION, user_profession.getText().toString());
                    editor.putString(KEY_ADDRESS, user_address.getText().toString());
                    editor.putString(KEY_CITY, user_city.getText().toString());
                    editor.putString(KEY_STATE, user_state.getText().toString());
                    editor.putString(KEY_COUNTRY, user_country.getText().toString());
                    editor.putString(KEY_PINCODE, user_pinCode.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(RegistrationScreen.this, HomeScreen.class);
                    startActivity(intent);

                    Toast.makeText(RegistrationScreen.this, "User data saved successfully",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}