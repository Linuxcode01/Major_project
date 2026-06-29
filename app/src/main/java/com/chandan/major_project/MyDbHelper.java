package com.chandan.major_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "registerDb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "register_table";

    private static final String KEY_ID = "id";
    private static final String FULL_NAME = "full_name";
    private static final String USER_NAME = "user_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static final String CONTACT = "contact";

    private static final String ADDRESS = "address";


    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FULL_NAME + " TEXT, "
                + EMAIL + " TEXT, "
                + USER_NAME + " TEXT, "
                + CONTACT + " TEXT, "
                + ADDRESS + " TEXT, "
                + PASSWORD + " TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public void add_user(String full_name, String email, String user_name,String contact, String address , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FULL_NAME, full_name);
        values.put(EMAIL, email);
        values.put(USER_NAME,user_name);
        values.put(CONTACT, contact);
        values.put(ADDRESS, address);
        values.put(PASSWORD,password);

        db.insert(TABLE_NAME, null,values);

    }

    public Cursor findUser(String userId, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME +
                        " WHERE " + " (" + USER_NAME + " = ? OR " + EMAIL + " = ? ) " + " AND " + PASSWORD + " = ?",
                new String[]{userId,userId, password}
        );

        return cursor;
    }

    public Cursor fetchUser(String userid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME
                        + " WHERE " + USER_NAME
                        + " = ? OR " + EMAIL + " = ? ", new String[]{userid, userid}
        );

        return  cursor;
    }

}
