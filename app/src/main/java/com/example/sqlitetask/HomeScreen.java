package com.example.sqlitetask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton add_to_form_btn;
    MyDatabaseHelper myDB;
    ArrayList<String> user_id, user_name, user_email, user_phone, user_gender, user_profession;
    ArrayList<String> user_address, user_city, user_state, user_country, user_pinCode;
    CustomAdapter customAdapter;
    TextView headerName, headerEmail;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //For Header Hooks
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        headerName = header.findViewById(R.id.header_name);
        headerEmail = header.findViewById(R.id.header_email);


        recyclerView = findViewById(R.id.recyclerView);
        add_to_form_btn = findViewById(R.id.add_to_form_btn);

        //Set Header values from preferences
        SharedPreferences preferences = getSharedPreferences("mypref", MODE_PRIVATE);
        String name = preferences.getString(KEY_NAME, null);
        String email = preferences.getString(KEY_EMAIL, null);
        headerName.setText(name);
        headerEmail.setText(email);

        //  Toolbar
        setSupportActionBar(toolbar);

        //  Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

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

    // On pressing the back button, when navigation drawer is open
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    // Menu opening for Delete All button
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_home) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (menuItem.getItemId() == R.id.nav_edit_profile) {
            Intent intent = new Intent(HomeScreen.this, EditProfile.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (menuItem.getItemId() == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

}