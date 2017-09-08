package com.example.york.teamcraft.personalsmanage;

import android.provider.BaseColumns;

/**
 * Created by York on 2017/9/8.
 */

public final class NotesContract {
    public NotesContract() {}

    public static abstract class Notes implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_IMPORTANCE = "importance";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Notes.TABLE_NAME + " (" +
                        Notes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +   // _ID: BaseColumns內建的常數, Constant Value: "_id"
                        Notes.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        Notes.COLUMN_NAME_CONTENT + TEXT_TYPE + ")";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Notes.TABLE_NAME;
    }
}
