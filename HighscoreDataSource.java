package com.example.user.mathquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class HighscoreDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID , MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_SCORE};


    public HighscoreDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void createHighscore(String name, int score){

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_SCORE, score);

        database.insert(MySQLiteHelper.TABLE_HIGHSCORE,null,values);
    }

    public boolean checkHighscore(int score){
        Cursor cursor = database.query(MySQLiteHelper.TABLE_HIGHSCORE, allColumns,null,null,null,null, MySQLiteHelper.COLUMN_SCORE + " DESC");

        if(cursor.getCount()<10)
            return true;
        else
            cursor.moveToPosition(9);

        Highscore rank10 = cursorToHighscore(cursor);
        int rank_10_score = rank10.getScore();

        if(rank_10_score<score)
            return true;
        else
            return false;
    }

    public List<Highscore> getAllHighscore(){
        List<Highscore> highscoreList = new ArrayList<Highscore>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_HIGHSCORE, allColumns,null,null,null,null, MySQLiteHelper.COLUMN_SCORE + " DESC");

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Highscore highscore = cursorToHighscore(cursor);
            highscoreList.add(highscore);
            cursor.moveToNext();
        }

        cursor.close();
        return highscoreList;
    }

    private Highscore cursorToHighscore(Cursor cursor){

        Highscore highscore = new Highscore();
        highscore.setId(cursor.getLong(0));
        highscore.setName(cursor.getString(1));
        highscore.setScore(cursor.getInt(2));

        return highscore;
    }

    public Cursor findCursor(){

        Cursor cursor = database.query(MySQLiteHelper.TABLE_HIGHSCORE,allColumns,null, null, null, null, null);
        return cursor;
    }
}
