package com.example.sqlitetask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Matrimony_Form.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "MyDatabaseHelper";

    private static final String TABLE_NAME = "matrimony_form";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "user_form_name";
    private static final String COLUMN_EMAIL = "user_form_email";
    private static final String COLUMN_PHONE = "user_form_phone";
    private static final String COLUMN_GENDER = "user_form_gender";
    private static final String COLUMN_PROFESSION = "user_form_profession";
    private static final String COLUMN_ADDRESS = "user_form_address";
    private static final String COLUMN_CITY = "user_form_city";
    private static final String COLUMN_STATE = "user_form_state";
    private static final String COLUMN_COUNTRY = "user_form_country";
    private static final String COLUMN_PIN_CODE = "user_form_pinCode";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE matrimony_form (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " user_form_name TEXT, user_form_email TEXT, user_form_phone TEXT, " +
                "user_form_gender TEXT, user_form_profession TEXT, user_form_address TEXT, " +
                "user_form_city TEXT, user_form_state TEXT, user_form_country TEXT, " +
                "user_form_pinCode TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addUser(String name, String email, String phone, String gender, String profession,
                 String address, String city, String state, String country, String pinCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_PROFESSION, profession);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_CITY, city);
        cv.put(COLUMN_STATE, state);
        cv.put(COLUMN_COUNTRY, country);
        cv.put(COLUMN_PIN_CODE, pinCode);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateUser(String row_id, String name, String email, String phone, String gender, String profession,
                    String address, String city, String state, String country, String pinCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_PROFESSION, profession);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_CITY, city);
        cv.put(COLUMN_STATE, state);
        cv.put(COLUMN_COUNTRY, country);
        cv.put(COLUMN_PIN_CODE, pinCode);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
