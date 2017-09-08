package com.example.york.teamcraft.personalsmanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by York on 2017/9/8.
 */

// A helper class to manage database creation and version management.
public class NotesDbHelper extends SQLiteOpenHelper{
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Personals.db";

    private static NotesDbHelper helper;

    public static NotesDbHelper getInstance(Context context){
        // Singleton Pattern
        if(helper == null){
            helper = new NotesDbHelper(context);
        }
        return helper;
    }

    public NotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NotesContract.Notes.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
