package com.example.sqlitetask;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateActivity extends AppCompatActivity {
    private static final String TAG = "UpdateActivity";

    TextInputEditText et_form_name, et_form_email, et_form_phone, et_form_gender,
            et_form_profession, et_form_address, et_form_city, et_form_state,
            et_form_country, et_form_pinCode;
    Button update_data_button, delete_data_button;
    String id, name, email, phone, gender, profession, address, city, state, country, pinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_form_name = findViewById(R.id.update_form_name);
        et_form_email = findViewById(R.id.update_form_email);
        et_form_phone = findViewById(R.id.update_form_phone);
        et_form_gender = findViewById(R.id.update_form_gender);
        et_form_profession = findViewById(R.id.update_form_profession);
        et_form_address = findViewById(R.id.update_form_address);
        et_form_city = findViewById(R.id.update_form_city);
        et_form_state = findViewById(R.id.update_form_state);
        et_form_country = findViewById(R.id.update_form_country);
        et_form_pinCode = findViewById(R.id.update_form_pinCode);
        update_data_button = findViewById(R.id.update_data_button);
        delete_data_button = findViewById(R.id.delete_data_button);

        //  First this is called
        getAndSetIntentData();

        //  Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  And only then, this is called
                Log.d(TAG, "onClick: id=====>" + id);
                Log.d(TAG, "onClick: name=====>" + et_form_name.getText().toString().trim());
                Log.d(TAG, "onClick: email=====>" + et_form_email.getText().toString().trim());
                Log.d(TAG, "onClick: phone=====>" + et_form_phone.getText().toString().trim());
                Log.d(TAG, "onClick: gender=====>" + et_form_gender.getText().toString().trim());
                Log.d(TAG, "onClick: profession=====>" + et_form_profession.getText().toString().trim());
                Log.d(TAG, "onClick: address=====>" + et_form_address.getText().toString().trim());
                Log.d(TAG, "onClick: city=====>" + et_form_city.getText().toString().trim());
                Log.d(TAG, "onClick: state=====>" + et_form_state.getText().toString().trim());
                Log.d(TAG, "onClick: country=====>" + et_form_country.getText().toString().trim());
                Log.d(TAG, "onClick: pinCode=====>" + et_form_pinCode.getText().toString().trim());

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateUser(id, et_form_name.getText().toString().trim(),
                        et_form_email.getText().toString().trim(),
                        et_form_phone.getText().toString().trim(),
                        et_form_gender.getText().toString().trim(),
                        et_form_profession.getText().toString().trim(),
                        et_form_address.getText().toString().trim(),
                        et_form_city.getText().toString().trim(),
                        et_form_state.getText().toString().trim(),
                        et_form_country.getText().toString().trim(),
                        et_form_pinCode.getText().toString().trim());
            }
        });

        delete_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("email") && getIntent().hasExtra("gender") &&
                getIntent().hasExtra("phone") && getIntent().hasExtra("profession") &&
                getIntent().hasExtra("address") && getIntent().hasExtra("city") &&
                getIntent().hasExtra("state") && getIntent().hasExtra("country") &&
                getIntent().hasExtra("pinCode")) {
            //  Getting data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            email = getIntent().getStringExtra("email");
            gender = getIntent().getStringExtra("gender");
            phone = getIntent().getStringExtra("phone");
            profession = getIntent().getStringExtra("profession");
            address = getIntent().getStringExtra("address");
            city = getIntent().getStringExtra("city");
            state = getIntent().getStringExtra("state");
            country = getIntent().getStringExtra("country");
            pinCode = getIntent().getStringExtra("pinCode");

            //  Setting Intent Data
            et_form_name.setText(name);
            et_form_email.setText(email);
            et_form_phone.setText(phone);
            et_form_gender.setText(gender);
            et_form_profession.setText(profession);
            et_form_address.setText(address);
            et_form_city.setText(city);
            et_form_state.setText(state);
            et_form_country.setText(country);
            et_form_pinCode.setText(pinCode);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }
}