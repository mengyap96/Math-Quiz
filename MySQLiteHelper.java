package com.example.user.mathquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_HIGHSCORE = "highscore_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_SCORE = "SCORE";

    private static final String DATABASE_NAME = "highscore.db";
    private static int DATAVASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_HIGHSCORE
            + "( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT, "
            + COLUMN_SCORE + " INTEGER);";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATAVASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
        onCreate(database);
    }

}
