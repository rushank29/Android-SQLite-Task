package com.example.sqlitetask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_to_form_btn;
    MyDatabaseHelper myDB;
    ArrayList<String> user_id, user_name, user_email, user_phone, user_gender, user_profession;
    ArrayList<String> user_address, user_city, user_state, user_country, user_pinCode;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView = findViewById(R.id.recyclerView);
        add_to_form_btn = findViewById(R.id.add_to_form_btn);
        add_to_form_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(HomeScreen.this);
        user_id = new ArrayList<>();
        user_name = new ArrayList<>();
        user_email = new ArrayList<>();
        user_phone = new ArrayList<>();
        user_gender = new ArrayList<>();
        user_profession = new ArrayList<>();
        user_address = new ArrayList<>();
        user_city = new ArrayList<>();
        user_state = new ArrayList<>();
        user_country = new ArrayList<>();
        user_pinCode = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(HomeScreen.this, this, user_id, user_name, user_email,
                user_phone, user_gender, user_profession, user_address, user_city, user_state,
                user_country, user_pinCode);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeScreen.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                user_id.add(cursor.getString(0));
                user_name.add(cursor.getString(1));
                user_email.add(cursor.getString(2));
                user_phone.add(cursor.getString(3));
                user_gender.add(cursor.getString(4));
                user_profession.add(cursor.getString(5));
                user_address.add(cursor.getString(6));
                user_city.add(cursor.getString(7));
                user_state.add(cursor.getString(8));
                user_country.add(cursor.getString(9));
                user_pinCode.add(cursor.getString(10));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(HomeScreen.this);
                myDB.deleteAllData();

                //            Refresh Activity
                Intent intent = new Intent(HomeScreen.this, HomeScreen.class);
                startActivity(intent);
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