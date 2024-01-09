package com.example.sqlitetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddActivity extends AppCompatActivity {

    TextInputEditText form_name, form_email, form_phone, form_gender, form_profession;
    TextInputEditText form_address, form_city, form_state, form_country, form_pinCode;
    Button add_data_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        form_name = findViewById(R.id.form_name);
        form_email = findViewById(R.id.form_email);
        form_phone = findViewById(R.id.form_phone);
        form_gender = findViewById(R.id.form_gender);
        form_profession = findViewById(R.id.form_profession);
        form_address = findViewById(R.id.form_address);
        form_city = findViewById(R.id.form_city);
        form_state = findViewById(R.id.form_state);
        form_country = findViewById(R.id.form_country);
        form_pinCode = findViewById(R.id.form_pinCode);
        add_data_button = findViewById(R.id.add_data_button);
        add_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                if (TextUtils.isEmpty(form_name.getText().toString()) ||
                        TextUtils.isEmpty(form_email.getText().toString()) ||
                        TextUtils.isEmpty(form_phone.getText().toString()) ||
                        TextUtils.isEmpty(form_gender.getText().toString()) ||
                        TextUtils.isEmpty(form_profession.getText().toString()) ||
                        TextUtils.isEmpty(form_address.getText().toString()) ||
                        TextUtils.isEmpty(form_city.getText().toString()) ||
                        TextUtils.isEmpty(form_state.getText().toString()) ||
                        TextUtils.isEmpty(form_country.getText().toString()) ||
                        TextUtils.isEmpty(form_pinCode.getText().toString())) {
                    Toast.makeText(AddActivity.this, "Enter pending details", Toast.LENGTH_SHORT).show();
                } else {
                    myDB.addUser(form_name.getText().toString().trim(),
                            form_email.getText().toString().trim(),
                            form_phone.getText().toString().trim(),
                            form_gender.getText().toString().trim(),
                            form_profession.getText().toString().trim(),
                            form_address.getText().toString().trim(),
                            form_city.getText().toString().trim(),
                            form_state.getText().toString().trim(),
                            form_country.getText().toString().trim(),
                            form_pinCode.getText().toString().trim());
                    Intent intent = new Intent(AddActivity.this, HomeScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}