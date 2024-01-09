package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfile extends AppCompatActivity {

    TextInputEditText edit_user_name, edit_user_email, edit_user_phone, edit_user_profession;
    TextInputEditText edit_user_address, edit_user_city, edit_user_state, edit_user_country, edit_user_pinCode;
    RadioButton edit_user_gender_male, edit_user_gender_female;
    RadioGroup edit_user_gender;
    Button update_user_btn;
    SharedPreferences sharedPreferences;
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
        setContentView(R.layout.activity_edit_profile);

        edit_user_name = findViewById(R.id.edit_user_name);
        edit_user_email = findViewById(R.id.edit_user_email);
        edit_user_phone = findViewById(R.id.edit_user_phone);
        edit_user_profession = findViewById(R.id.edit_user_profession);
        edit_user_address = findViewById(R.id.edit_user_address);
        edit_user_city = findViewById(R.id.edit_user_city);
        edit_user_state = findViewById(R.id.edit_user_state);
        edit_user_country = findViewById(R.id.edit_user_country);
        edit_user_pinCode = findViewById(R.id.edit_user_pinCode);

        //  Hooks for radio buttons
        edit_user_gender = (RadioGroup) findViewById(R.id.edit_user_gender);
        edit_user_gender_male = (RadioButton) findViewById(R.id.edit_user_gender_male);
        edit_user_gender_female = (RadioButton) findViewById(R.id.edit_user_gender_female);

        update_user_btn = findViewById(R.id.update_user_btn);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String phone = sharedPreferences.getString(KEY_PHONE, null);
        int gender = sharedPreferences.getInt(KEY_GENDER, 0);
        String profession = sharedPreferences.getString(KEY_PROFESSION, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);
        String city = sharedPreferences.getString(KEY_CITY, null);
        String state = sharedPreferences.getString(KEY_STATE, null);
        String country = sharedPreferences.getString(KEY_COUNTRY, null);
        String pinCode = sharedPreferences.getString(KEY_PINCODE, null);
        if (name != null || email != null) {
            //  So set the data on TextInputEditText
            edit_user_name.setText("" + name);
            edit_user_email.setText("" + email);
            edit_user_phone.setText("" + phone);
            if(gender == 0){
                edit_user_gender_male.setChecked(true);
            }else {
                edit_user_gender_female.setChecked(true);
            }
            edit_user_profession.setText("" + profession);
            edit_user_address.setText("" + address);
            edit_user_city.setText("" + city);
            edit_user_state.setText("" + state);
            edit_user_country.setText("" + country);
            edit_user_pinCode.setText("" + pinCode);
        }

        update_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=0;
                //  On button click, put data on Shared preferences
                if (edit_user_gender_male.isChecked()) {
                    value= 0;
                } else if (edit_user_gender_female.isChecked()) {
                     value = 1;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, edit_user_name.getText().toString().trim());
                editor.putString(KEY_EMAIL, edit_user_email.getText().toString().trim());
                editor.putString(KEY_PHONE, edit_user_phone.getText().toString().trim());
                editor.putInt(KEY_GENDER, value);
                editor.putBoolean(KEY_GENDER, edit_user_gender_female.isChecked());
                editor.putString(KEY_PROFESSION, edit_user_profession.getText().toString().trim());
                editor.putString(KEY_ADDRESS, edit_user_address.getText().toString().trim());
                editor.putString(KEY_CITY, edit_user_city.getText().toString().trim());
                editor.putString(KEY_STATE, edit_user_state.getText().toString().trim());
                editor.putString(KEY_COUNTRY, edit_user_country.getText().toString().trim());
                editor.putString(KEY_PINCODE, edit_user_pinCode.getText().toString().trim());
                editor.apply();

                Intent intent = new Intent(EditProfile.this, HomeScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                Toast.makeText(EditProfile.this, "User data updated successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}